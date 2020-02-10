package com.google.codenext.emojify.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.LayoutRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.codenext.emojify.R;
import com.google.codenext.emojify.bitmap.BitmapUtils;
import com.google.codenext.emojify.viewmodel.MainActivityViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Photo fragment that allows to emojify a selfie as well as allow to save it, share it or discard
 * it.
 */
public class PhotoFragment extends Fragment {

  private MainActivityViewModel viewModel;

  @BindView(R.id.toolbar)
  MaterialToolbar topToolbar;

  @BindView(R.id.bottomAppBar)
  BottomAppBar bottomAppBar;

  @BindView(R.id.image_view)
  ImageView imageView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    /**
     * Define the ViewModel that allows to share information across fragments. For more information:
     *
     * <p>See <a href="https://www.youtube.com/watch?v=5qlIPTDE274">ViewModel</a>.
     */
    viewModel = ViewModelProviders.of(getActivity()).get(MainActivityViewModel.class);
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(getLayout(), container, false);
    // Bind the views
    ButterKnife.bind(this, view);
    // Specifies the close fragment button in the tool bar
    topToolbar.setNavigationIcon(R.drawable.ic_close_black_24dp);
    // Specifies the close navigation listener to go back to the home fragment
    topToolbar.setNavigationOnClickListener(
        v ->
            Navigation.findNavController(getView())
                .navigate(R.id.action_photoFragment_to_mainFragment));
    // Specifies the menu options for the bottom bar such as share and delete
    bottomAppBar.replaceMenu(R.menu.photo_menu_fragment);
    bottomAppBar.setOnMenuItemClickListener(item -> onMenuItemSelected(item));
    // Renders the JPG Photo in an image view
    imageView.setImageBitmap(viewModel.getPhoto());
    return view;
  }

  @LayoutRes
  protected int getLayout() {
    return R.layout.photo_fragment;
  }

  private boolean onMenuItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_share:
        onShareMenuSelected();
        return true;

      case R.id.menu_delete:
        onDeleteMenuSelected();
        return true;

      default:
        break;
    }
    return false;
  }

  private void onDeleteMenuSelected() {
    // Delete the temporary image file
    BitmapUtils.deleteImageFile(getContext(), viewModel.getPhotoPath());
  }

  /** OnClick method for the save button. */
  @OnClick(R.id.fab_save)
  public void onClickSaveFAB() {
    // Save the image
    BitmapUtils.saveImage(getContext(), viewModel.getPhoto());
  }

  private void onShareMenuSelected() {
    onClickSaveFAB();
    // Share the image
    BitmapUtils.shareImage(getContext(), viewModel.getPhotoPath());
  }
}
