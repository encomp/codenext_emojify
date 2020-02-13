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
    }

    // Release the detector to free up native resources that are no longer needed.
    detector.release();
  }
}
