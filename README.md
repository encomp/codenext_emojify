# Emojify Me

### Exercise Four: Exercise Match the Face to an Emoji
The goal is to develop a method called `getClassifications()` that
calculates the following probabilities of a given `Face` such as:

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
