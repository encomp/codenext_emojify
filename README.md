# Emojify Me

### Exercise Five: Draw Emojis over Faces
The goal is to finish the app. To finish the app we need to draw an
emoji on each face on the photo. These will require a bit of work.

#### Refactor of the method `whichEmoji()`
1. Change the return type from [`void`](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L101)
   to [Emoji](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L183).
2. Add the [`return`](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L144)
   statement at the end of the method.

#### Add the missing functionality to the method `addBitmapToFace()`
The aim of `addBitmapToFace()` is to take the background
[Bitmap](https://developer.android.com/reference/android/graphics/Bitmap),
an [emojiBitmap](https://developer.android.com/reference/android/graphics/Bitmap)
and a [Face](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face)
as arguments and returns the combined bitmap with the Emoji Bitmap over
the given [Face](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face).

1. Determine the size of the emoji to match the width and height of the
   face.

   1. The width of the emoji (`emojiWidth`) can be calculated as follows:

     int `emojiWidth` = [Width of the `Face`](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face#getWidth())
      X [EMOJI_SCALE_FACTOR](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L21)

   2. The height of the emoji (`emojiHeight`) can be calculated as follows:

     int `emojiHeight` = [Height of `emojiBitmap`](https://developer.android.com/reference/android/graphics/Bitmap#getHeight())
      X `emojiWidth` / [Width of `emojiBitmap`](https://developer.android.com/reference/android/graphics/Bitmap#getWidth())
      X [EMOJI_SCALE_FACTOR](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L21)

2. To Scale the `emojiBitmap` we can use the method
   [createScaledBitmap()](https://developer.android.com/reference/android/graphics/Bitmap#createScaledBitmap(android.graphics.Bitmap,%20int,%20int,%20boolean))

3. Determine the emoji position so it best lines up with the face.

   1. To determine the coordinate X (`emojiPositionX`) can be calculated
   as follows:

      ([`face`<a href="https://developer.android.com/reference/android/graphics/PointF#x"><b> coordinate X </b></a>](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face#public-pointf-getposition-)
      `+` ([Width of the `face`](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face#public-float-getwidth-)
      / 2)) - [Width of `emojiBitmap`](https://developer.android.com/reference/android/graphics/Bitmap#getWidth())
      / 2

   2.  To determine the coordinate Y (`emojiPositionY`) can be calculated
   as follows:

       ([`face`<a href="https://developer.android.com/reference/android/graphics/PointF#y"><b> coordinate Y </b></a>](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face#public-pointf-getposition-)
      `+` ([Height of the `face`](https://developers.google.com/android/reference/com/google/android/gms/vision/face/Face#public-float-getheight-)
      / 2)) - [Height of `emojiBitmap`](https://developer.android.com/reference/android/graphics/Bitmap#getHeight())
      / 3

4. Create the canvas and draw the bitmaps to it. Use the method
[drawBitmap](https://developer.android.com/reference/android/graphics/Canvas#drawBitmap(android.graphics.Bitmap,%20android.graphics.Rect,%20android.graphics.RectF,%20android.graphics.Paint))
to draw the `emojiBitmap` on top of the face.

#### Rename the method `detectFaces()`
1. Change the name of the mehtod [detectFaces()](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L34)
to the following name `detectFacesAndOverlayEmoji()`.

   You could do it using the Refactor :
     1. Select the method `detectFaces()` perform a Right click.
     2. From the drop down menu select `Refactor`.
     3. From the drop down menu select `Rename`.
     4. Finally type the name `detectFacesAndOverlayEmoji()` and hit Enter.
   <p align="center">
       <img src="/resources/refactor_method.png" data-canonical-src="/images/refactor_method.png" width="483" height="479" />
   </p>
2. Create a variable of type [Bitmap](https://developer.android.com/reference/android/graphics/Bitmap).
   Define a new variable called `resultBitmap` and initialize it to the
   original `picture` bitmap passed into the
   [detectFacesAndOverlayEmoji()](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L34)
   method.
3. Create a variable of type [Bitmap](https://developer.android.com/reference/android/graphics/Bitmap)
   called `emojiBitmap` to hold the appropriate Emoji as a bitmap.
4. Create a variable of type [Emoji](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L183)
   called `emoji` to hold the result from the call of the method
   [whichEmoji()](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L101).
5. Create a `switch` statement on the result of the
   [whichEmoji()](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L101)
   call, and assign the proper emoji bitmap to the variable `emojiBitmap`.

   **Hints**

   1. You need to build a [switch](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/ui/PhotoFragment.java#L81)
      statement as follows for each type of the enum class
      [Emoji](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L183):
      ```java
      switch (emoji) {
          case FROWN:
              ....
            break;
          
          case SMILE:
              ....
            break;
          
          ...    
      }        
      ```
   3. All the emojis images are under the res folder called
      [drawable](https://github.com/encomp/codenext_emojify/tree/06-branch/app/src/main/res/drawable).
   4. You need to convert each Emoji image to a
      [Bitmap](https://developer.android.com/reference/android/graphics/Bitmap).
      This can be accomplish using the class
      [BitmapFactory](https://developer.android.com/reference/android/graphics/BitmapFactory#decodeResource(android.content.res.Resources,%20int,%20android.graphics.BitmapFactory.Options)).
      ```java
      BitmapFactory.decodeResource(context.getResources(), R.drawable.frown);
      ```
6. Finally add the emojiBitmap to a face.

