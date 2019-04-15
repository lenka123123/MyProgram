package com.wokun.tysl.dialogview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.wokun.tysl.R;

/**
 * Created by Administrator on 2018/9/8 0008.
 */

public class CircleProgressView extends View {
    /**
     * 背景颜色
     */
    private int backgroundColor;
    /**
     * 前景颜色
     */
    private int foregroundColor;
    /**
     * 进度条颜色
     */
    private int progressColor;
    /**
     * 字体颜色
     */
    private int fontColor;
    /**
     * 当前进度
     */
    private int currentProgress = 0;
    /**
     * 最大进度
     */
    private int maxProgress;
    /**
     * 画笔
     */
    private Paint mPaint = new Paint();
    /**
     * 半径长
     */
    private int mRadius;
    /**
     * 进度宽
     */
    private int progressWidth;
    /**
     * 字体大小
     */
    private int textSize;
    /**
     * 回调监听
     */
    private ProgressedListener listener;


    @SuppressLint("ResourceAsColor")
    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressView);
        backgroundColor = a.getColor(R.styleable.CircleProgressView_circleprogress_backgroundcolor, Color.TRANSPARENT );
        foregroundColor = a.getColor(R.styleable.CircleProgressView_circleprogress_foregroundcolor,Color.parseColor("#ff6347"));
        progressColor = a.getColor(R.styleable.CircleProgressView_circleprogress_progresscolor, Color.WHITE);
        fontColor = a.getColor(R.styleable.CircleProgressView_circleprogress_fontcolor, Color.WHITE);
        maxProgress = a.getInteger(R.styleable.CircleProgressView_circleprogress_maxprogress, 100);
        progressWidth = a.getInteger(R.styleable.CircleProgressView_circleprogress_width, 6);
        textSize = a.getInteger(R.styleable.CircleProgressView_circleprogress_textsize, 10);
        a.recycle();//释放资源
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(50, 50);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(heightMeasureSpec, heightMeasureSpec);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthMeasureSpec, widthMeasureSpec);
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > h) {
            mRadius = h / 2;
        } else {
            mRadius = w / 2;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadius, mPaint);//画最下边的圆
        mPaint.setColor(progressColor);
     canvas.drawArc(getWidth() / 2 - mRadius,
                getHeight() / 2 - mRadius,
                getWidth() / 2 + mRadius,
                getHeight() / 2 + mRadius,
                -90,
                360 * (((float) currentProgress) / maxProgress),
                true,
                mPaint);//画扇形
        mPaint.setColor(foregroundColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2,
                mRadius - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, progressWidth, getResources().getDisplayMetrics()),
                mPaint);//画中心圆
        mPaint.setColor(fontColor);
        mPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, textSize, getResources().getDisplayMetrics()));
        String text = currentProgress + "%";
        canvas.drawText(text,
                getWidth() / 2 - mPaint.measureText(text) / 2,
                getHeight() / 2 + mPaint.measureText("%") / 2,
                mPaint);//画文字
    }

    /**
     * 设置当前进度
     *
     * @param currentProgress
     */
    public void setCurrentProgress(int currentProgress) {
        if (currentProgress <= 0) {
            currentProgress = 0;
            if (listener != null) {
                listener.startLoad();
            }
        }
        if (currentProgress >= maxProgress) {
            currentProgress = maxProgress;
            if (listener != null) {
                listener.loadEnd();
            }
        }
        this.currentProgress = currentProgress;
        if (listener != null) {
            listener.progressLoading(currentProgress);
        }
        invalidate();//重绘
    }

    public void setListener(ProgressedListener listener) {
        this.listener = listener;
    }

    public interface ProgressedListener {
        void loadEnd();//加载结束

        void progressLoading(int progressed);//加载中

        void startLoad();//开始加载
    }
}
