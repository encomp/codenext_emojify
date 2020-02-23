# Emojify Me

### Exercise Five: Draw Emojis over Faces
The goal is to finish the app. To finish the app we need to draw an
emoji on each face on the photo. These will require a bit of work.

#### Refactor of the method `whichEmoji()`
1. Change the return type from [`void`](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L95)
   to [Emoji](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L177).
2. Add the [`return`](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L138)
   statement at the end of the method.

#### Rename the method `detectFaces()`
Change the name of the mehtod [detectFaces()](https://github.com/encomp/codenext_emojify/blob/06-branch/app/src/main/java/com/google/codenext/emojify/bitmap/Emojifier.java#L33)
to the following name `detectFacesAndOverlayEmoji()`.

You could do it using the Refactor :

1. Select the method `detectFaces()` perform a Right click.
2. From the drop down menu select `Refactor`.
3. From the drop down menu select `Rename`.
4. Finally type the name `detectFacesAndOverlayEmoji()` and hit Enter.

<p align="center">
    <img src="/resources/refactor_method.png" data-canonical-src="/images/refactor_method.png" width="483" height="479" />
</p>

