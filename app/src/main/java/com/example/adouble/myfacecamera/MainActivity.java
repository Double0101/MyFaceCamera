package com.example.adouble.myfacecamera;

import android.hardware.Camera;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    public static final int HAS_PREVIEW = 987;

    public static final int UPDATE_FACES = 654;

    public static final String TAG = "MMDXXJ";

    private CameraView cameraView;

    private FaceView faceView;

    private MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        cameraView = (CameraView) findViewById(R.id.camera_view);
        faceView = (FaceView) findViewById(R.id.face_view);
        myHandler = new MyHandler();
        myHandler.sendEmptyMessageDelayed(HAS_PREVIEW, 500);
    }

    private void startFaceDetect() {
        Camera.Parameters parameters = cameraView.parameters;
        if (parameters.getMaxNumDetectedFaces() > 0) {
            if (faceView != null) {
                faceView.clearFaces();
                faceView.setVisibility(View.VISIBLE);
            }
            cameraView.camera.setFaceDetectionListener(new FaceFinder(getApplicationContext(), myHandler));
            cameraView.camera.startFaceDetection();
        }
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HAS_PREVIEW :
                    startFaceDetect();
                    break;
                case UPDATE_FACES :
                    Camera.Face[] faces = (Camera.Face[]) msg.obj;
                    faceView.setFaces(faces);
                    break;
            }
            super.handleMessage(msg);
        }
    }
}
