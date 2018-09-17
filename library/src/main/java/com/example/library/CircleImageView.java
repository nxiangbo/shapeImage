package com.example.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircleImageView extends AppCompatImageView{
    private Paint mPaint;
    private Path mPath;

    private float mRadius;
    private float mX;
    private float mY;

    private Paint mBorderPaint;


    public CircleImageView(Context context) {
        super(context);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.RED);

        mPath = new Path();

        mBorderPaint = new Paint();
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setColor(Color.BLUE);



    }

    @Override
    protected void onDraw(Canvas canvas) {

        mPath.addCircle(mX, mY, mRadius, Path.Direction.CW);
        canvas.drawCircle(mX, mY, mRadius, mPaint);
        canvas.clipPath(mPath);
        mBorderPaint.setStrokeWidth(20);

        super.onDraw(canvas);
        canvas.drawCircle(mX, mY, mRadius-10, mBorderPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingBottom() - getPaddingTop();
        mRadius = Math.min(width, height)/2.f;
        mX = getWidth()/2.f + getPaddingLeft();
        mY = getHeight()/2.f + getPaddingTop();
    }
}
