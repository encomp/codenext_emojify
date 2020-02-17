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
The application has only one activity. The activity is the entry point of an Android application and 
this activity is launched once the user click on your icon of your application.

 <p align="center">
     <img src="/resources/icon.png" alt="" data-canonical-src="/images/icon.png" width="176" height="353" />
 </p>
 