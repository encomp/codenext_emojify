package com.google.codenext.emojify.viewmodel;

import android.graphics.Bitmap;

import androidx.lifecycle.ViewModel;

import timber.log.Timber;

/**
 * Define the {@link com.google.codenext.emojify.MainActivity} view model that will live during the
 * entire app live. The goal of this view model is to hold data that can be shared across the
 * following fragments:
 *
 * <ul>
 *   <li>{@link com.google.codenext.emojify.ui.HomeFragment}.
 *   <li>{@link com.google.codenext.emojify.ui.PhotoFragment}.
 * </ul>
 *
 * <p>See <a
 * href="https://android.jlelse.eu/the-complete-android-splash-screen-guide-c7db82bce565">Splash
 * screen</a> to learn more about Android Jetpack ViewModel.
 */
public class MainActivityViewModel extends ViewModel {

  private static final String TAG = "MainActivityViewModel";
  private String photoPath;
  private Bitmap photo;

  /** Provides the file path of the where a photo is stored on the device. */
  public String getPhotoPath() {
    return photoPath;
  }

  /** Stores the file path of where a given photo is stored on the device. */
  public void setPhotoPath(String photoPath) {
    Timber.tag(TAG).d(photoPath);
    this.photoPath = photoPath;
  }

  /** Provides a {@link Bitmap} JPG photo. */
  public Bitmap getPhoto() {
    return photo;
  }

  /** Stores a JPG photo as a {@link Bitmap} */
  public void setPhoto(Bitmap photo) {
    this.photo = photo;
  }
}
