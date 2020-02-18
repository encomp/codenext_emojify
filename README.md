# Emojify Me

### Exercise Three: Detect Faces
The goal is to develop a method called `getClassifications()` that
calculates the following probabilities of a given `Face` such as:
* The probability of the **left** eye being open.
* The probability of the **right** eye being open.
* The probability of the person being **smiling**.

Using the method `getClassifications()` iterate over the
`SparseArray<Face>` and calculate the probability for each `Face`.


#### Define a new method called `getClassifications`
Create a private static method called getClassifications() that receives
a `Face` object as an argument.

#### The method should log the probability of the left eye being open.
Refer to the vision [javadoc](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face)
and look for the method(s) that allows you to calculate the probability.

#### The method should log the probability of the right eye being open.
Refer to the vision [javadoc](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face)
and look for the method(s) that allows you to calculate the probability.

#### The method should log the probability of the person being smiling.
Refer to the vision [javadoc](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face)
and look for the method(s) that allows you to calculate the probability.

#### Iterate through the faces and call getClassifications() for each face.
Refer to the Android [javadoc](https://developer.android.com/reference/android/util/SparseArray)
and understand how to iterate through the `SparseArray` class. Finally,
for each `Face` call the method `getClassifications()` to calculate its
probabilities.

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
