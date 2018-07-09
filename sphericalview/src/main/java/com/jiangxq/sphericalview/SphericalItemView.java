package com.jiangxq.sphericalview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SphericalItemView extends View implements ValueAnimator.AnimatorUpdateListener {
    private Paint paint1;// 绘制中心圆的画笔
    private Paint paint2 ; // 绘制外面渐变圆的画笔
    private int mStartColor; // 中心圆的颜色 固定值 也是下面渐变圆的色彩初始值
    private int circleX = 0; // 圆心坐标
    private int circleY = 0;
    private int centerCircleRadius = 1; // 中心圆的半径
    private float radiusRatio = 0.5f; // 让中心圆是整个view的几分之几
    private int defaultSize = 20; // 当view设置为wrap_content 时 默认的大小是20dp
    private ValueAnimator mAnimator;
    private static final long HEART_BEAT_RATE = 2000;//每隔多久闪烁一次
    private Handler mHandler;
    private int alaph = 255; // 默认初始时渐变圆的透明度为不透明
    private double mFraction;


    public SphericalItemView(Context context) {
        this(context,null);
    }

    public SphericalItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SphericalItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if(attrs!=null){
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SphericalItemView);
            mStartColor = typedArray.getColor(R.styleable.SphericalItemView_mainColor,Color.BLUE);
            radiusRatio = typedArray.getFloat(R.styleable.SphericalItemView_radiusRatio,0.8f);
            typedArray.recycle();
        }else{
            mStartColor = Color.BLUE;
        }
        initPaint();
    }
    private void initPaint(){
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setAntiAlias(true);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setAntiAlias(true);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circleX = w/2;
        circleY = h/2;
        paint1.setColor(mStartColor);
        centerCircleRadius = (int)(getMeasuredWidth()/2*radiusRatio);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        mFraction = (float) animation.getAnimatedValue();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
            paint2.setColor(mStartColor);
            paint2.setAlpha((int)(alaph*(1-mFraction)));
            Log.d("onDraw","update alaph:"+(int)(alaph*(1-mFraction)));
            canvas.drawCircle(circleX,circleY,(int)(centerCircleRadius*(mFraction)/radiusRatio),paint2);
            canvas.drawCircle(circleX, circleY, centerCircleRadius,paint1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getSize(defaultSize,widthMeasureSpec);
        int height = getSize(defaultSize,heightMeasureSpec);
        if(width<height) {
            height = width;
        }else{
            width = height;
        }
        setMeasuredDimension(width,height);
    }

    private int getSize(int defaultSize,int measureSpec){
        int mSize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode){
            case MeasureSpec.UNSPECIFIED:{
                mSize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST:{
                mSize = defaultSize;
                break;
            }
            case MeasureSpec.EXACTLY: {
                mSize = size;
                break;
            }
        }
        return mSize;
    }

    private void update(double fraction){
        Log.d("fraction",fraction+"");

        Log.d("update fraction",+mFraction+"");
        invalidate();
    }
    private double downX;
    private double downY;
    private double xDistance;
    private double yDistance;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (this.isEnabled()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    downX = event.getX();
                    downY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                     xDistance = Math.abs(event.getX() - downX);
                     yDistance = Math.abs(event.getY() - downY);
                    mFraction = ((Math.sin((xDistance+yDistance)/180)+1)/2);
                    if(mFraction>0.99){
                        downX = event.getX();
                        downY = event.getY();
                    }
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    xDistance = 0;
                    yDistance= 0;
                    break;
                case MotionEvent.ACTION_CANCEL:
                    xDistance = 0;
                    yDistance = 0;
                    break;
            }
            return true;
        }
        return false;
    }
}
