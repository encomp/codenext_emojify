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

  // Define a log TAG
  private static final String TAG = Emojifier.class.getSimpleName();

  private static final float EMOJI_SCALE_FACTOR = .9f;
  // TODO (3b): Use these threshold constants to define 3 variables: smiling, left eye closed, right
  // eye closed.
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
        // Log the probabilities for each face using the method getClassifications.
        getClassifications(face);
      }
    }

    // Release the detector to free up native resources that are no longer needed.
    detector.release();
  }

  // TODO (2): Change the name of the getClassifications() method to whichEmoji().
  /**
   * Calculate the following probabilities on a given {@link Face} instance:
   *
   * <ul>
   *   <li>Calculate the probability of the left eye being open.
   *   <li>Calculate the probability of the right eye being open.
   *   <li>The method should log probability of the person being smiling.
   * </ul>
   *
   * @param face the object that will be used analyzed.
   */
  private static void getClassifications(Face face) {
    // The method should log the probability of the left eye being open.
    Timber.tag(TAG).d("LeftEyeOpen:" + face.getIsLeftEyeOpenProbability());

    // The method should log the probability of the right eye being open.
    Timber.tag(TAG).d("RightEyeOpen:" + face.getIsRightEyeOpenProbability());

    // The method should log probability of the person being smiling.
    Timber.tag(TAG).d("RightEyeOpen:" + face.getIsSmilingProbability());

    // TODO (3a): Create 3 boolean variables to track the state of the facial expression based on
    // the threshold constants: smiling, left eye closed, right eye closed.

    // TODO (4): Create an if/else system that selects the appropriate emoji enum type based on the
    // above booleans and log the result.

    // TODO (5): Log the emoji that was determined from the prior step.
  }

  // TODO (1): Create an enum class called Emoji that contains all the possible emoji you can make
  // (smile, frown, left wink smile, right wink smile, left wink frown, right wink frown,
  // closed eye smile, close eye frown).
}
