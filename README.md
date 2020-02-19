# Emojify Me

### Exercise Four: Exercise Match the Face to an Emoji
The goal is to repurpose the method called `getClassifications()`. And
adapt its current functionality to determine the proper `Emoji` that
should be used for a given face. The method determines the proper
`Emoji` using the following three variables:

* Smiling
* Left eye closed
* Right eye closed

After the completion of this exercise the `Emoji` type selected will be
log and can be verified using `Logcat`.


#### Create an enum class Emoji
In the class [Emojifier](https://github.com/encomp/codenext_emojify/blob/05-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L98)
define a new [enum](https://www.w3schools.com/java/java_enums.asp) class
called `Emoji` that contains all the following:
* SMILE
* FROWN
* LEFT_WINK_SMILE
* RIGHT_WINK_SMILE
* LEFT_WINK_FROWN
* RIGHT_WINK_FROWN
* CLOSED_EYE_SMILE
* CLOSED_EYE_FROWN

#### Rename the method `getClassifications()`
Change the name of the mehtod [getClassifications()](https://github.com/encomp/codenext_emojify/blob/05-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L81)
to the following name `whichEmoji()`.

You could do it using the Refactor :

1. Select the method `getClassifications()` perform a Right click.
2. From the drop down menu select `Refactor`.
3. From the drop down menu select `Rename`.
4. Finally type the name `whichEmoji()` and hit Enter.

<p align="center">
    <img src="/resources/refactor_rename.png" data-canonical-src="/images/refactor_rename.png" width="483" height="479" />
</p>

### Create 3 boolean variables
Create three boolean variables: **smiling**, **left eye closed**,
**right eye closed** to track the state of the facial expression.

#### Create the `smiling` variable
Define a `boolean` variable called `smiling` to determine if the
provided `Face` is smiling. Make use of the threshold probability constant
[SMILING_PROB_THRESHOLD](https://github.com/encomp/codenext_emojify/blob/05-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L26).

If the following condition is met the value of the variable should be
`TRUE`:

* [Face](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face)
  probability of being smiling > SMILING_PROB_THRESHOLD

#### Create the `leftEyeClosed` variable
Define a `boolean` variable called `leftEyeClosed` to determine if the
provided `Face` left eye is closed. Make use of the threshold probability constant
[EYE_OPEN_PROB_THRESHOLD](https://github.com/encomp/codenext_emojify/blob/05-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L27).

If the following condition is met the value of the variable should be
`TRUE`:

* [Face](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face)
  probability of left eye being open < EYE_OPEN_PROB_THRESHOLD

#### Create the `rightEyeClosed` variable
Define a `boolean` variable called `rightEyeClosed` to determine if the
provided `Face` right eye is closed. Make use of the threshold probability constant
[EYE_OPEN_PROB_THRESHOLD](https://github.com/encomp/codenext_emojify/blob/05-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L27).

If the following condition is met the value of the variable should be
`TRUE`:

* [Face](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face)
  probability of right eye being open < EYE_OPEN_PROB_THRESHOLD

### Create an if/else system
Create an if/else system that selects the appropriate `Emoji` enum type
using the three variables:

* smiling
* leftEyeClosed
* rightEyeClosed

Using these three variables determine the proper combination for each
type of the `Emoji` enum:

* SMILE
* FROWN
* LEFT_WINK_SMILE
* RIGHT_WINK_SMILE
* LEFT_WINK_FROWN
* RIGHT_WINK_FROWN
* CLOSED_EYE_SMILE
* CLOSED_EYE_FROWN

### Log which emoji was selected
Lastly after the if/else system `log` the outcome of the enum type
selected. Use [Timber](https://medium.com/mindorks/better-logging-in-android-using-timber-72e40cc2293d)
and the [TAG](https://github.com/encomp/codenext_emojify/blob/05-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L21)
to log the outcome.

### Lastly do not forget to test on your phone.
To test the photo should be taken with the phone on `landscape` not
`portrait`. Otherwise the number of faces will be zero.

<p align="center">
    <img src="/resources/photo_demo.png" data-canonical-src="/images/photo_demo.png" width="353" height="176" />
</p>

#### Verify your log
Once the app is deployed and if your code is correct your log message
should appear on `Logcat`. To find the your log message type the name of
the [TAG](#define-a-log-tag) that you defined.

<p align="center">
    <img src="/resources/logcat.png" data-canonical-src="/images/logcat.png" width="819" height="232" />
</p>

#### Did not found the log message
If you could not find your log message open `Logcat` and do the
following:

<p align="center">
    <img src="/resources/logcat_debug.png" data-canonical-src="/images/logcat_debug.png" width="820" height="210" />
</p>

1. Make sure your selected your device.
2. Make sure the following package is selected `com.google.codenext.emojify`.
3. Make sure `Debug` is selected.
4. Type the following `TAG` name `MainActivityViewModel` on the search box.
5. You should be able to see the log message on the screen as follow:

`/storage/emulated/0/Android/data/com.google.codenext.emojify/cache/JPEG_20200218_094545_8292899126122382220.jpg`

The above message is the temporary location on where the photo is saved  
on the phone.
