package com.example.android.emojify;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;


public class Emojifier {

    public static final String LOG_TAG = "SIGMA";

    static void detectFace(Context context, Bitmap picture) {

        FaceDetector detector = new com.google.android.gms.vision.face.FaceDetector.Builder(context)
                .setTrackingEnabled(false)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        Frame frame = new Frame.Builder().setBitmap(picture).build();

        SparseArray<Face> faces = detector.detect(frame);

        Log.d(LOG_TAG, "DetectFaces:" + faces.size());

        if (faces.size() == 0) {
            Toast.makeText(context, R.string.no_face_message, Toast.LENGTH_SHORT).show();
        } else {
            for (int i = 0; i < faces.size(); i++) {
                Face face = faces.valueAt(i);
                getClassification(face);

            }
        }

        detector.release();
    }

    private static void getClassification(Face face) {
        Log.d(LOG_TAG, "getClassification: smilingProbability=" + face.getIsSmilingProbability());
        Log.d(LOG_TAG, "getClassification: leftEyeOpenProbability=" + face.getIsLeftEyeOpenProbability());
        Log.d(LOG_TAG, "getClassification: rightEyeOpenProbability=" + face.getIsRightEyeOpenProbability());
    }
}
