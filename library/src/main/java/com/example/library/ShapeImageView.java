package com.example.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class ShapeImageView extends AppCompatImageView {
    private Paint mPaint;
    private Path mPath;
    private RectF mRectF;

    private float mRadius;
    private float mBorder;
    private int mColor;

    private static final float DEFAULT_RADIUS = 20;
    private static final float DEFAULT_BORDER = 1;
    private static final int DEFAULT_COLOR = Color.TRANSPARENT;



    public ShapeImageView(Context context) {
        this(context, null);
    }

    public ShapeImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        TypedArray typedArray = getContext().obtainStyledAttributes(R.styleable.ShapeImageView);
        mRadius = typedArray.getDimension(R.styleable.ShapeImageView_radius, DEFAULT_RADIUS);
        mBorder = typedArray.getDimension(R.styleable.ShapeImageView_border, DEFAULT_BORDER);
        mColor = typedArray.getColor(R.styleable.ShapeImageView_color, DEFAULT_COLOR);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mBorder);
        mPaint.setColor(mColor);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CW);
        canvas.drawRoundRect(mRectF, mRadius, mRadius, mPaint);
        canvas.clipPath(mPath);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();
        mRectF = new RectF(0, 0, width, height);
    }
}
