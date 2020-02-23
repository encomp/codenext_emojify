package com.google.codenext.emojify.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.codenext.emojify.R;
import com.google.codenext.emojify.bitmap.BitmapUtils;
import com.google.codenext.emojify.bitmap.Emojifier;
import com.google.codenext.emojify.viewmodel.MainActivityViewModel;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/** Home fragment initiates the camera that captures a selfie in landscape. */
public class HomeFragment extends Fragment {

  private static final String FILE_PROVIDER_AUTHORITY = "com.google.codenext.emojify.fileprovider";
  private static final int REQUEST_IMAGE_CAPTURE = 1;
  private static final int REQUEST_STORAGE_PERMISSION = 1;

  @BindView(R.id.fab_emojify)
  FloatingActionButton fabEmojify;

  @BindView(R.id.gif_image_view)
  GifImageView gifImageView;

  private MainActivityViewModel viewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /**
     * Define the ViewModel that allows to share information across fragments. For more information:
     *
     * <p>See <a href="https://www.youtube.com/watch?v=5qlIPTDE274">ViewModel</a>.
     */
    viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
  }

  @Nullable
  @Override
  public View onCreateView(
      @NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(getLayout(), container, false);
    // Bind the views
    ButterKnife.bind(this, view);
    // Specifies the GIF image that will be rendered
    gifImageView.setGifImageResource(R.drawable.emojify_banner);
    return view;
  }

  @LayoutRes
  protected int getLayout() {
    return R.layout.home_fragment;
  }

  @OnClick(R.id.fab_emojify)
  public void onClickEmojifyFAB() {
    // Check for the external storage permission
    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {
      // If you do not have permission, request it
      ActivityCompat.requestPermissions(
          getActivity(),
          new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
          REQUEST_STORAGE_PERMISSION);
    } else {
      // Launch the camera if the permission exists
      launchCameraIntent();
    }
  }

  /** Creates a temporary file in which a picture will be store. */
  private void launchCameraIntent() {
    // setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    // Create the capture image intent
    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    // Ensure that there's a camera activity to handle the intent
    if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
      // Create the temporary File where the photo should go
      File photoFile = null;
      try {
        photoFile = BitmapUtils.createTempImageFile(getContext());
      } catch (IOException ex) {
        // Error occurred while creating the File
        ex.printStackTrace();
      }
      // Continue only if the File was successfully created
      if (photoFile != null) {
        // Get the path of the temporary file
        viewModel.setPhotoPath(photoFile.getAbsolutePath());
        // Get the content URI for the image file
        Uri photoURI = FileProvider.getUriForFile(getContext(), FILE_PROVIDER_AUTHORITY, photoFile);
        // Add the URI so the camera can store the image
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        // Launch the camera activity
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
      }
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // If the image capture activity was called and was successful
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
      // Resample the saved image to fit the ImageView
      Bitmap photo = BitmapUtils.resamplePic(getContext(), viewModel.getPhotoPath());
      // TODO (4): Save the result from detecFaces(...) on the photo variable.
      // Detect faces
      Emojifier.detectFaces(getContext(), photo);
      // Save the photo on the view model
      viewModel.setPhoto(photo);
      // Navigate to the Photo fragment to see the picture taken
      Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_photoFragment);
    } else {
      // Otherwise, delete the temporary image file
      BitmapUtils.deleteImageFile(getContext(), viewModel.getPhotoPath());
    }
  }
}
