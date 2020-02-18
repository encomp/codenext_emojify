# Emojify Me

### Project Synopsis
Develop a mobile app that takes a picture of people using Firebase 
[Mobile Vision](https://developers.google.com/vision) identify all the faces in the photo. Lastly, 
replace all the faces with the proper emoji.

### Goals
* Understand the basics of how artificial intelligence works in particular the field of vision.
* Make use of the Firbase MLKit Vision Api to build a face detector.
* Determine how many faces there are on a photo.
* Identify facial characteristics for each face:
  * Right eye open or close
  * Left eye open or close
  * Smiling or frown
* Replace each face with the proper emoji on top of the original photo.
  * Smile: 
   <p align="center">
     <img src="/app/src/main/res/drawable/smile.png" alt="" data-canonical-src="/images/smile.png" width="72" height="72" />
     <img src="/app/src/main/res/drawable/right_wink_smile.png" alt="" data-canonical-src="/images/rightwink.png" width="72" height="72" />
     <img src="/app/src/main/res/drawable/left_wink_smile.png" alt="" data-canonical-src="/images/leftwink.png" width="72" height="72" />
     <img src="/app/src/main/res/drawable/closed_eye_smile.png" alt="" data-canonical-src="/images/closed_smile.png" width="72" height="72" />
   </p>
   
   * Frown: 
   <p align="center">
     <img src="/app/src/main/res/drawable/frown.png" alt="" data-canonical-src="/images/frown.png" width="72" height="72" />
     <img src="/app/src/main/res/drawable/right_wink_frown.png" alt="" data-canonical-src="/images/rightwinkfrown.png" width="72" height="72" />
     <img src="/app/src/main/res/drawable/left_wink_frown.png" alt="" data-canonical-src="/images/leftwinkfrown.png" width="72" height="72" />
     <img src="/app/src/main/res/drawable/closed_eye_frown.png" alt="" data-canonical-src="/images/closed_frown.png" width="72" height="72" />
   </p>

### App overview
This section provides an overview of the code that is provided. The started code allows you to 
concentrate on the Vision api aspect. However, it is important to understand how this code is 
architecture and how it works.

#### Single Activity
The application has a [single activity](https://www.youtube.com/watch?v=9O1D_Ytk0xg). The activity 
is the entry point of an Android application. The activity is launched once the user click on 
the application icon.

 <p align="center">
     <img src="/resources/launch.gif" alt="" data-canonical-src="/images/launch.gif" width="176" height="353" />
 </p>

#### Screens 
The application defines two screens or fragments. A [fragment](https://www.youtube.com/watch?v=k3IT-IJ0J98) 
defines its own UI and encapsulates its own functionality.

##### Home Fragment 
The home fragment defines a bottom app bar with a single FAB button that triggers the camera to take
a picture.

  <p align="center">
      <img src="/resources/home.png" alt="" data-canonical-src="/images/home.png" width="176" height="353" />
  </p>
  
###### Layout
The home fragment is defined on the `home_fragment.xml` as follows:

  <p align="center">
      <img src="/resources/home_fragment.png" data-canonical-src="/images/home_fragment.png" width="535" height="790" />
  </p>

  * Defines a [Coordinator Layout](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout)
    as the main container to respond to scrolling techniques from Toolbars.
  * Defines an [AppBar](https://material.io/components/app-bars-top/#usage)
    to displays information or actions relating to the current screen.
  * Defines a [Material Tool Bar](https://developer.android.com/reference/com/google/android/material/appbar/MaterialToolbar)
    that provides the activity title and can declare other interactive
    items.
  * Defines a [Text View](https://developer.android.com/reference/android/widget/TextView)
    to display the title of the application.
  * Defines an include layout with the value of `home_content.xml`
  * Defines a [Bottom App Bar](https://material.io/develop/android/components/bottom-app-bar/)
    to define one main FAB located in the center of the bar.
  * [Floating Action Button](https://material.io/develop/android/components/floating-action-button/)
    (FAB) to denote the primary action of the screen in this case to
    take a photo.

The `home_content.xml` is defined as follows:

  <p align="center">
      <img src="/resources/home_content.png" data-canonical-src="/images/home_content.png" width="535" height="790" />
  </p>

   * Defines a [Constraint Layout](https://www.youtube.com/watch?time_continue=3&v=XamMbnzI5vE&feature=emb_logo)
     that allows [placing](https://developer.android.com/training/constraint-layout)
     components according to relationships between sibling views and the
     parent layout.
   * Defines two horizontal [guidelines](https://developer.android.com/reference/androidx/constraintlayout/widget/Guideline?hl=en)
     the first one at 35% and the second one at 70% of total height of
     the screen. These guidelines allow to properly place the
     [Material Card View](https://material.io/develop/android/components/material-card-view/)
     and the [Text View](https://developer.android.com/reference/android/widget/TextView).
   * Defines a [Material Card View](https://material.io/develop/android/components/material-card-view/)
     that renders a gif image.
   * Defines a [Text View](https://developer.android.com/reference/android/widget/TextView)
     that renders the text "Take a Selfie".

##### HomeFragment.java

This section describes the main aspects of the behavior defined for the
`home_fragment.xml`.

###### Emojify FAB

Here is the code snippet that is called when the user taps on the
emojify FAB:

```java
  @OnClick(R.id.fab_emojify)
  public void onClickEmojifyFAB() {
    // Check for the external storage permission
    // Verify if the app has permission to store a photo on the phone
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
```

The snippet above performs the following actions:

* Verify that the user has [permission](https://www.youtube.com/watch?v=55iL41U07KA&list=PLAwxTw4SYaPnMwH5-FNkErnnq_aSy706S&index=72)
  to store files on the phone.
* In case the user does not have [permissions](https://developer.android.com/guide/topics/permissions/overview)
  a dialog will be shown asking to grant a permission to store files.
* However, if the user does have permissions then `launchCameraIntent()`
  will be invoked.

The goal of method `launchCameraIntent()` is to launch an [intent](https://developer.android.com/guide/components/intents-filters)  
that allows the user to take a photo.

###### launchCameraIntent

```java
  /** Creates a temporary file in which a picture will be store. */
  private void launchCameraIntent() {
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
```
The snippet above performs the following actions:

* Create an intent that allows the user to take a photo called
  `takePictureIntent`.
* Validates that there is an app for taking photos installed on the
  phone.
* Creates a temporary file in where the photo will be stored `photoURI`.
* On the intent `takePictureIntent` specify as an extra where the
  temporary file on where the photo should be store `photoURI`.
* Launch the [intent](https://www.youtube.com/watch?v=95AARxHoupA&list=PLAwxTw4SYaPnMwH5-FNkErnnq_aSy706S&index=106)
  `startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)`.

The call of `startActivityForResult()` resolves the
intent to an app that can handle the intent and starts its corresponding
Activity. However, If there is more than one app that can handle the
intent, Android presents the user with a dialog to pick which app to
use.

###### onActivityResult

Lastly, once the picture has been taken and temporarily saved. The camera
intent will be completed and Android will call the life cycle method
`onActivityResult`:

```java
  @Override
  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    // If the image capture activity was called and was successful
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
      // Resample the saved image to fit the ImageView
      Bitmap photo = BitmapUtils.resamplePic(getContext(), viewModel.getPhotoPath());
      // Save the photo on the view model
      viewModel.setPhoto(photo);
      // Navigate to the Photo fragment to see the picture taken
      Navigation.findNavController(getView()).navigate(R.id.action_mainFragment_to_photoFragment);
    } else {
      // Otherwise, delete the temporary image file
      BitmapUtils.deleteImageFile(getContext(), viewModel.getPhotoPath());
    }
  }
```

The snippet above performs the following actions:

* If intent completed successfully the `requestCode` will be `RESULT_OK`.
  And the temporary file will be stored on the `ViewModel`. Lastly, the
  navigation component will render the `photo_fragment.xml`.
* However, if the intent was not successful the temporary file will be
  deleted.

##### Photo Fragment
The photo fragment defines a bottom app bar with a FAB button that saves a picture and two more 
buttons one to share a photo the second one to delete it.

  <p align="center">
      <img src="/resources/photo.png" alt="" data-canonical-src="/images/photo.png" width="176" height="353" />
  </p>

###### Layout
The photo fragment is defined on the `photo_fragment.xml` as follows:

  <p align="center">
      <img src="/resources/photo_fragment.png" data-canonical-src="/images/photo_fragment.png" width="535" height="790" />
  </p>

  * Defines a [Coordinator Layout](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout)
    as the main container to respond to scrolling techniques from Toolbars.
  * Defines an [AppBar](https://material.io/components/app-bars-top/#usage)
    to displays information or actions relating to the current screen.
  * Defines a [Material Tool Bar](https://developer.android.com/reference/com/google/android/material/appbar/MaterialToolbar)
    that provides the activity title and declares an interactive item
    to close the current fragment and go back to the `home_fragment.xml`.
  * Defines a [Text View](https://developer.android.com/reference/android/widget/TextView)
    to display the title of the application in this case "Photo".
  * Defines an include layout with the value of `photo_content.xml`
  * Defines a [Bottom App Bar](https://material.io/develop/android/components/bottom-app-bar/)
    to define one main FAB located in the end of the bar.
  * [Floating Action Button](https://material.io/develop/android/components/floating-action-button/)
    (FAB) to denote the primary action of the screen in this case to
    save the current photo on the phone.

The `photo_content.xml` is defined as follows:

  <p align="center">
      <img src="/resources/photo_content.png" data-canonical-src="/images/photo_content.png" width="535" height="790" />
  </p>

   * Defines a [Constraint Layout](https://www.youtube.com/watch?time_continue=3&v=XamMbnzI5vE&feature=emb_logo)
     that allows [placing](https://developer.android.com/training/constraint-layout)
     components according to relationships between sibling views and the
     parent layout.
   * Defines a horizontal [guidelines](https://developer.android.com/reference/androidx/constraintlayout/widget/Guideline?hl=en)
     at 50% of total height of the screen. This guidelines allow to properly place the
     [Material Card View](https://material.io/develop/android/components/material-card-view/).
   * Defines a [Material Card View](https://material.io/develop/android/components/material-card-view/)
     that renders the photo that was take with the camera.

##### PhotoFragment.java

This section describes the main aspects of the behavior defined for the
`photo_fragment.xml`.

###### Save FAB

Here is the code snippet that is called when the user taps on the
save FAB:

```java
  /** OnClick method for the save button. */
  @OnClick(R.id.fab_save)
  public void onClickSaveFAB() {
    // Save the image
    String photoPath = BitmapUtils.saveImage(getContext(), viewModel.getPhoto());
    viewModel.setPhotoPath(photoPath);
  }
```

The snippet above performs the following actions:

* Retrieves the photo from the `ViewModel` and saved it on the phones
  gallery.
* Upon successfully storing the photo the path is stored on the
  `ViewModel`.

###### Share Action Menu

Here is the code snippet that is called when the user taps on the
share action menu:

```java
  /** OnClick method share action menu. */
  private void onShareMenuSelected() {
    onClickSaveFAB();
    // Share the image
    BitmapUtils.shareImage(getContext(), viewModel.getPhotoPath());
  }
```

The snippet above performs the following actions:

* Calls the `onClickSaveFAB` to save the photo.
* Lastly, a share intent is trigger to allow the user share a photo
  through a list of different apps such as Google Photos, Google Drive
  to name a few.

###### Delete Action Menu

Here is the code snippet that is called when the user taps on the
delete action menu:

```java
  /** OnClick method delete action menu. */
  private void onDeleteMenuSelected() {
    // Delete the temporary image file
    BitmapUtils.deleteImageFile(getContext(), viewModel.getPhotoPath());
  }
```

The snippet above performs the following actions:

* Deletes the temporary file where the photo is stored.

###### MaterialToolbar Navigation OnClickListener

Here is the code snippet that is called when the user taps on the
Material Tool Bar Navigation:

```java
   // Specifies the close navigation listener to go back to the home fragment
   topToolbar.setNavigationOnClickListener(
      v ->
           Navigation.findNavController(getView())
                .navigate(R.id.action_photoFragment_to_mainFragment));
```

The snippet above performs the following actions:

* Closes the `photo_fragment.xml` and navigates back to the
  `home_fragment.xml`.

 #### Navigation between fragments
 The application make use of [navigation component](https://www.youtube.com/watch?time_continue=1&v=Y0Cs2MQxyIs&feature=emb_logo)
 to navigate between the screens. Navigation refers to the interactions that allow users to navigate 
 across, into, and back out from the different screens of an application.
 
  <p align="center">
      <img src="/resources/navigation.png" alt="" data-canonical-src="/images/navigation.png" width="400" height="350" />
  </p>

 