package com.google.codenext.emojify.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
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
  private static final double SMILING_PROB_THRESHOLD = .15;
  private static final double EYE_OPEN_PROB_THRESHOLD = .5;

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

    // If there are no faces on a bitmap show a Toast message.
    if (faces.size() == 0) {
      Toast.makeText(context, R.string.no_faces_message, Toast.LENGTH_SHORT).show();
    } else {
      // Iterate through all the faces to calculate the probabilities for each one.
      for (int i = 0; i < faces.size(); ++i) {
        Face face = faces.valueAt(i);
        // Log the probabilities for each face using the method whichEmoji.
        whichEmoji(face);
      }
    }

    // Release the detector to free up native resources that are no longer needed.
    detector.release();
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
   */
  private static void whichEmoji(Face face) {
    // The method should log the probability of the left eye being open.
    Timber.tag(TAG).d("LeftEyeOpen:" + face.getIsLeftEyeOpenProbability());

    // The method should log the probability of the right eye being open.
    Timber.tag(TAG).d("RightEyeOpen:" + face.getIsRightEyeOpenProbability());

    // The method should log probability of the person being smiling.
    Timber.tag(TAG).d("RightEyeOpen:" + face.getIsSmilingProbability());

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
        emoji = Emoji.LEFT_WINK;
      } else if (rightEyeClosed && !leftEyeClosed) {
        emoji = Emoji.RIGHT_WINK;
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
    LEFT_WINK,
    RIGHT_WINK,
    LEFT_WINK_FROWN,
    RIGHT_WINK_FROWN,
    CLOSED_EYE_SMILE,
    CLOSED_EYE_FROWN
  }
}
