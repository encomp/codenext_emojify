package com.google.codenext.emojify;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.google.codenext.emojify.viewmodel.MainActivityViewModel;

import timber.log.Timber;

/**
 * Defines the only activity of the Emojify Me app. See the following references:
 *
 * <ul>
 *   <li>See <a href="https://www.youtube.com/watch?v=2k8x8V77CrU">Why, When, and How</a>.
 *   <li>See <a href="https://www.youtube.com/watch?v=9O1D_Ytk0xg">10 Best Practices for Moving to a
 *       Single Activity</a>.
 * </ul>
 */
public class MainActivity extends AppCompatActivity {

  private MainActivityViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Set up Timber
    Timber.plant(new Timber.DebugTree());

    /**
     * This line allows the transition of splash screen to the app theme:
     *
     * <p>See <a
     * href="https://android.jlelse.eu/the-complete-android-splash-screen-guide-c7db82bce565">Splash
     * screen</a>.
     */
    setTheme(R.style.MyTheme_DayNight);

    /**
     * Define the ViewModel that allows to share information across fragments. For more information:
     *
     * <p>See <a href="https://www.youtube.com/watch?v=5qlIPTDE274">ViewModel</a>.
     */
    viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

    setContentView(R.layout.main_activity);
  }
}
