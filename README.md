# Emojify Me

### Exercise Two Detect Faces
The goal is to look for the Mobile Vision dependency that needs to be
added to our `build.gradle` file.

#### Define a log TAG
Define a constant on the class that will be used to log messages using
[Timber](https://medium.com/mindorks/better-logging-in-android-using-timber-72e40cc2293d).

#### Add the code that detects the number of faces in a given bitmap.
Go to the vision [tutorial](https://developers.google.com/vision/android/detect-faces-tutorial)
and understand how to define and build a Face Detector.

#### Log the number of faces found on the bitmap.
* Visit the vision tutorial to understand how to detect all on a `Frame`
  [faces](https://developers.google.com/vision/android/detect-faces-tutorial#detecting_faces_and_facial_landmarks).
* Lastly, log the number of faces using Timber.

#### If there are no faces on a bitmap show a Toast message.
If the `SparseArray` size is equal to zero display a [Toast](https://developer.android.com/guide/topics/ui/notifiers/toasts)
message.

#### Call detect face to log the emoji information.
Finally on the `HomeFragment.java` use the `Emojifier.java` and invoke
the method `detectFaces()`.

### Lastly do not forget to test on you phone.
To test the photo should be taken with the phone on `landscape` not
`portrait`. Otherwise the number of faces will be zero every time.