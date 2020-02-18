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