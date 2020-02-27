package com.google.codenext.emojify.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.codenext.emojify.R;

import timber.log.Timber;

/** Utility class that detect the number of faces on a {@link Bitmap} image. */
public final class Emojifier {

  private Emojifier() {}

  private static final String TAG = Emojifier.class.getSimpleName();
  private static final float EMOJI_SCALE_FACTOR = .9f;
  private static final double SMILING_PROB_THRESHOLD = .15;
  private static final double EYE_OPEN_PROB_THRESHOLD = .5;

  // TODO (3): Refactor the method detectFaces()
  // TODO (3.1): Change the name of the detectFaces() method to detectFacesAndOverlayEmoji() and the
  // return type from void to Bitmap
  /**
   * Method for detecting faces in a bitmap.
   *
   * @param context The application context.
   * @param picture The picture in which to detect the faces.
   */
  public static void detectFaces(Context context, Bitmap picture) {
    // Add the code that detects the number of faces in a given bitmap.
    // Create a FaceDetector that identifies the faces of people in a Bitmap graphic object.
    FaceDetector detector =
        new FaceDetector.Builder(context)
            .setTrackingEnabled(false)
            .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
            .build();

    // Create a Frame instance from the bitmap to supply to the detector.
    Frame frame = new Frame.Builder().setBitmap(picture).build();

    // Detect the all the faces on a frame.
    SparseArray<Face> faces = detector.detect(frame);

    // Log the number of faces found on the bitmap.
    Timber.tag(TAG).d("The number of faces found are: " + faces.size());

    // TODO (3.2): Create a variable called resultBitmap and initialize it to the original picture
    //  bitmap passed into the detectFacesAndOverlayEmoji() method.

    // If there are no faces on a bitmap show a Toast message.
    if (faces.size() == 0) {
      Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
    } else {
      // Iterate through all the faces to calculate the probabilities for each one.
      for (int i = 0; i < faces.size(); ++i) {
        Face face = faces.valueAt(i);
        // Log the probabilities for each face using the method whichEmoji.

        // TODO (3.3) Create a variable of type Bitmap called emojiBitmap to hold the appropriate
        //  Emoji as a bitmap.

        // TODO (3.4): Create a variable called emoji to hold the result from the call of the method
        //  whichEmoji().
        whichEmoji(face);

        // TODO (3.5): Create a switch statement on the result of the whichEmoji() call, and assign
        //  the proper emoji bitmap to the variable emojiBitmap.

        // TODO (3.6): Call addBitmapToFace(), passing in the resultBitmap, the emojiBitmap and the
        //  Face  object, and assigning the result to resultBitmap.
      }
    }

    // Release the detector to free up native resources that are no longer needed.
    detector.release();
    // TODO (3.7): Return the resultBitmap
  }

  // TODO (1): Refactor the method whichEmoji()
  // TODO (1.1): Change the return type of the whichEmoji() method from void to Emoji.
  /**
   * Determines the proper emoji for a given {@link Face} instance:
   *
   * <p>This method calculates the probability of the eyes being open and the mouth being smiling.
   *
   * <ul>
   *   <li>Calculate the probability of the left eye being open.
   *   <li>Calculate the probability of the right eye being open.
   *   <li>The method should log probability of the person being smiling.
   * </ul>
   *
   * Using the probabilities above an emoji will be assigned to the given face.
   *
   * @param face the object that will be used analyzed.
   */
  private static void whichEmoji(Face face) {
    // The method should log the probability of the left eye being open.
    Timber.tag(TAG).d("LeftEyeOpen:" + face.getIsLeftEyeOpenProbability());

    // The method should log the probability of the right eye being open.
    Timber.tag(TAG).d("RightEyeOpen:" + face.getIsRightEyeOpenProbability());

    // The method should log probability of the person being smiling.
    Timber.tag(TAG).d("Smile:" + face.getIsSmilingProbability());

    // Create 3 boolean variables to track the state of the facial expression based on the threshold
    // constants: smiling, left eye closed, right eye closed.
    boolean smiling = face.getIsSmilingProbability() > SMILING_PROB_THRESHOLD;
    boolean leftEyeClosed = face.getIsLeftEyeOpenProbability() < EYE_OPEN_PROB_THRESHOLD;
    boolean rightEyeClosed = face.getIsRightEyeOpenProbability() < EYE_OPEN_PROB_THRESHOLD;

    // Create an if/else system that selects the appropriate emoji enum type based on the above
    // booleans and log the result.
    Emoji emoji;
    if (smiling) {
      if (leftEyeClosed && !rightEyeClosed) {
        emoji = Emoji.LEFT_WINK_SMILE;
      } else if (rightEyeClosed && !leftEyeClosed) {
        emoji = Emoji.RIGHT_WINK_SMILE;
      } else if (leftEyeClosed) {
        emoji = Emoji.CLOSED_EYE_SMILE;
      } else {
        emoji = Emoji.SMILE;
      }
    } else {
      if (leftEyeClosed && !rightEyeClosed) {
        emoji = Emoji.LEFT_WINK_FROWN;
      } else if (rightEyeClosed && !leftEyeClosed) {
        emoji = Emoji.RIGHT_WINK_FROWN;
      } else if (leftEyeClosed) {
        emoji = Emoji.CLOSED_EYE_FROWN;
      } else {
        emoji = Emoji.FROWN;
      }
    }

    // Log the chosen Emoji
    Timber.tag(TAG).d("whichEmoji: " + emoji.name());
    // TODO (1.2): Have the method return the selected Emoji type.
  }

  // TODO (2): Add the missing functionality to the method addBitmapToFace() which takes the
  //  background bitmap, the Emoji bitmap, and a Face object as arguments and returns the combined
  //  bitmap with the Emoji over the face.
  private static Bitmap addBitmapToFace(Bitmap backgroundBitmap, Bitmap emojiBitmap, Face face) {
    // Create a copy of the original image.
    Bitmap resultBitmap =
        Bitmap.createBitmap(
            backgroundBitmap.getWidth(),
            backgroundBitmap.getHeight(),
            backgroundBitmap.getConfig());

    // TODO (2.1): Determine the size of the emoji to match the width and height of the face.

    // TODO (2.2): Scale the emoji.

    // TODO (2.3): Determine the emoji position so it best lines up with the face.

    // TODO (2.4): Create the canvas and draw the bitmaps to it.
    Canvas canvas = new Canvas(resultBitmap);
    canvas.drawBitmap(backgroundBitmap, 0, 0, null);

    return resultBitmap;
  }

  /**
   * Enum class that contains all the possible emojis:
   *
   * <ul>
   *   <li>smiling
   *   <li>frowning
   *   <li>left wink
   *   <li>right wink
   *   <li>left wink frowning
   *   <li>right wink frowning
   *   <li>closed eye smiling
   *   <li>close eye frowning
   * </ul>
   */
  private enum Emoji {
    SMILE,
    FROWN,
    LEFT_WINK_SMILE,
    RIGHT_WINK_SMILE,
    LEFT_WINK_FROWN,
    RIGHT_WINK_FROWN,
    CLOSED_EYE_SMILE,
    CLOSED_EYE_FROWN
  }
}
