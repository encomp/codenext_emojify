# Emojify Me

### Purpose
The goal of the is to develop a mobile app that takes a picture of people and using Firebase 
[Mobile Vision](https://developers.google.com/vision) replace a facial expression with an emoji.

### Goals
* Understand the basics of how artificial intelligence works in particular vision
* Make use of the Firbase MLKit Vision Api to build a face detector.
* Determine how many faces there are on a photo.
* Identify facial characteristics for each face:
  * Right eye open or close
  * Left eye open or close
  * Smiling or frown
* Replace each face with the proper emoji on top of the original photo.
  * Smile: 
   <p align="center">
     <img src="/app/src/main/res/drawable/smile.png" alt="" data-canonical-src="/images/smile.png" width="144" height="144" />
     <img src="/app/src/main/res/drawable/right_wink_smile.png" alt="" data-canonical-src="/images/rightwink.png" width="144" height="144" />
     <img src="/app/src/main/res/drawable/left_wink_smile.png" alt="" data-canonical-src="/images/leftwink.png" width="144" height="144" />
     <img src="/app/src/main/res/drawable/closed_eye_smile.png" alt="" data-canonical-src="/images/closed_smile.png" width="144" height="144" />
   </p>
   
   * Frown: 
   <p align="center">
     <img src="/app/src/main/res/drawable/frown.png" alt="" data-canonical-src="/images/frown.png" width="144" height="144" />
     <img src="/app/src/main/res/drawable/right_wink_frown.png" alt="" data-canonical-src="/images/rightwinkfrown.png" width="144" height="144" />
     <img src="/app/src/main/res/drawable/left_wink_frown.png" alt="" data-canonical-src="/images/leftwinkfrown.png" width="144" height="144" />
     <img src="/app/src/main/res/drawable/closed_eye_frown.png" alt="" data-canonical-src="/images/closed_frown.png" width="144" height="144" />
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
     <img src="/resources/icon.png" alt="" data-canonical-src="/images/icon.png" width="176" height="353" />
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

##### Photo Fragment
The photo fragment defines a bottom app bar with a FAB button that saves a picture and two more 
buttons one to share a photo the second one to delete it.

  <p align="center">
      <img src="/resources/photo.png" alt="" data-canonical-src="/images/photo.png" width="176" height="353" />
  </p>
  
 #### Navigation between fragments
 The application make use of [navigation component](https://www.youtube.com/watch?time_continue=1&v=Y0Cs2MQxyIs&feature=emb_logo)
 to navigate between the screens. Navigation refers to the interactions that allow users to navigate 
 across, into, and back out from the different screens of an application.
 
  <p align="center">
      <img src="/resources/navigation.png" alt="" data-canonical-src="/images/navigation.png" width="400" height="350" />
  </p>

 