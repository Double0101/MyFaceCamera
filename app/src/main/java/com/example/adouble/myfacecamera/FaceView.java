package com.example.adouble.myfacecamera;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by Double on 2017/1/28.
 */

public class FaceView extends ImageView {
    private Paint paint;

    private Context context;

    private Matrix matrix = new Matrix();

    private RectF rectF = new RectF();

    private Camera.Face[] faces;

    public FaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint();
        this.context = context;
    }

    public void setFaces(Camera.Face[] faces) {
        this.faces = faces;
        invalidate();        // 申请重绘
    }

    public void clearFaces() {
        faces = null;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (faces != null) {
            matrix.setScale(1, 1);
            matrix.postRotate(90);
            matrix.postScale(this.getWidth() / 2000f, this.getHeight() / 2000f);
            matrix.postTranslate(this.getWidth() / 2f, this.getHeight() / 2f);
            canvas.save();
//            matrix.setRotate(0);
//            canvas.rotate(-0);
            for (Camera.Face face : faces) {
                rectF.set(face.rect);
                matrix.mapRect(rectF);
                canvas.drawRect(rectF, paint);
            }
        }
        canvas.restore();
        super.onDraw(canvas);
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5f);
        paint.setAlpha(200);
    }

}
