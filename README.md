# Emojify Me

### Exercise Five: Draw Emojis over Faces
The goal is to finish the app. To finish the app we need to draw an
emoji on each face on the photo. These will require a bit of work.

#### Refactor of the method `whichEmoji()`
1. Change the return type from [`void`](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L101)
   to [Emoji](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L183).
2. Add the [`return`](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L144)
   statement at the end of the method.

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
      statement as follows:
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
   2. All the emojis images are under the res folder called
      [drawable](https://github.com/encomp/codenext_emojify/tree/06-branch/app/src/main/res/drawable).
   3. You need to convert each Emoji image to a
      [Bitmap](https://developer.android.com/reference/android/graphics/Bitmap).
      This can be accomplish using the class
      [BitmapFactory](https://developer.android.com/reference/android/graphics/BitmapFactory#decodeResource(android.content.res.Resources,%20int,%20android.graphics.BitmapFactory.Options)).
      ```java
      BitmapFactory.decodeResource(context.getResources(), R.drawable.frown);
      ```
6. Finally add the emojiBitmap to a face.

