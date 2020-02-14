package com.google.codenext.emojify.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

  /**
   * Method for detecting faces in a bitmap.
   *
   * @param context The application context.
   * @param picture The picture in which to detect the faces.
   * @return a new bitmap image with the proper emoji on top of a face.
   */
  public static Bitmap detectFacesandOverlayEmoji(Context context, Bitmap picture) {
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

    // Initialize it to the original picture bitmap.
    Bitmap resultBitmap = picture;

    // If there are no faces on a bitmap show a Toast message.
    if (faces.size() == 0) {
      Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
    } else {
      // Iterate through all the faces to calculate the probabilities for each one.
      for (int i = 0; i < faces.size(); ++i) {
        Face face = faces.valueAt(i);

        Bitmap emojiBitmap;

        // Specifies which emoji will be used for the face.
        Emoji emoji = whichEmoji(face);

        // From the Emoji enum create a bitmap of the appropriate PNG emoji image
        switch (emoji) {
          case FROWN:
            emojiBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.frown);
            break;

          case CLOSED_EYE_FROWN:
            emojiBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.closed_eye_frown);
            break;

          case LEFT_WINK_FROWN:
            emojiBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.left_wink_frown);
            break;

          case RIGHT_WINK_FROWN:
            emojiBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.right_wink_frown);
            break;

          case SMILE:
            emojiBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.smile);
            break;

          case CLOSED_EYE_SMILE:
            emojiBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.closed_eye_smile);
            break;

          case LEFT_WINK_SMILE:
            emojiBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.left_wink_smile);
            break;

          case RIGHT_WINK_SMILE:
            emojiBitmap =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.right_wink_smile);
            break;

          default:
            emojiBitmap = null;
            // Toast.makeText(context, R.string.no_emoji, Toast.LENGTH_SHORT).show();
            break;
        }

        // Add the emojiBitmap to the proper position in the original image
        resultBitmap = addBitmapToFace(resultBitmap, emojiBitmap, face);
      }
    }

    // Release the detector to free up native resources that are no longer needed.
    detector.release();
    return resultBitmap;
  }

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
   * @return the proper {@link Emoji} of the face provided
   */
  private static Emoji whichEmoji(Face face) {
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
    return emoji;
  }

  /**
   * Takes the background bitmap, the Emoji bitmap, and a Face object as arguments and returns the
   * combined bitmap with the Emoji over the face.
   *
   * @param backgroundBitmap specifies the photo
   * @param emojiBitmap specifies the emoji that will be render on top of a face
   * @param face specifies the face location in where the emoji will be render
   */
  private static Bitmap addBitmapToFace(Bitmap backgroundBitmap, Bitmap emojiBitmap, Face face) {
    // Create a copy of the original image.
    Bitmap resultBitmap =
        Bitmap.createBitmap(
            backgroundBitmap.getWidth(),
            backgroundBitmap.getHeight(),
            backgroundBitmap.getConfig());

    // Determine the size of the emoji to match the width and height of the face.
    int emojiWidth = (int) (face.getWidth() * EMOJI_SCALE_FACTOR);
    int emojiHeight =
        (int) (emojiBitmap.getHeight() * emojiWidth / emojiBitmap.getWidth() * EMOJI_SCALE_FACTOR);

    // Scale the emoji.
    emojiBitmap = Bitmap.createScaledBitmap(emojiBitmap, emojiWidth, emojiHeight, false);

    // Determine the emoji position so it best lines up with the face.
    float emojiPositionX =
        (face.getPosition().x + face.getWidth() / 2) - emojiBitmap.getWidth() / 2;
    float emojiPositionY =
        (face.getPosition().y + face.getHeight() / 2) - emojiBitmap.getHeight() / 3;

    // Create the canvas and draw the bitmaps to it.
    Canvas canvas = new Canvas(resultBitmap);
    canvas.drawBitmap(backgroundBitmap, 0, 0, null);
    canvas.drawBitmap(emojiBitmap, emojiPositionX, emojiPositionY, null);

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
