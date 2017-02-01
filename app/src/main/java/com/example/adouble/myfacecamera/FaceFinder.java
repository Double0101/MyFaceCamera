package com.example.adouble.myfacecamera;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by Double on 2017/1/30.
 */

public class FaceFinder implements Camera.FaceDetectionListener {

    private Context context;

    private Handler handler;

    public FaceFinder(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {
        if (faces != null) {
            Message message = handler.obtainMessage();
            message.what = MainActivity.UPDATE_FACES;
            message.obj = faces;
            message.sendToTarget();
        }
    }
}
