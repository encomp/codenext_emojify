# Emojify Me

### Exercise Two: Detect Faces
The goal is to `log` the number of faces found on given photo.

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

### Lastly do not forget to test on your phone.
To test the photo should be taken with the phone on `landscape` not
`portrait`. Otherwise the number of faces will be zero.

<p align="center">
    <img src="/resources/photo_demo.png" data-canonical-src="/images/photo_demo.png" width="353" height="176" />
</p>

#### Verify your log
Once the app is deployed and if your code is correct your log message
should appear on `Logcat`.

<p align="center">
    <img src="/resources/logcat.png" data-canonical-src="/images/logcat.png" width="819" height="232" />
</p>
