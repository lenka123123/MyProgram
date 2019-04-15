package com.wokun.tysl.home.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.wokun.tysl.TyslApp;

import static android.R.attr.x;
import static android.R.attr.y;
import static java.security.AccessController.getContext;

/**
 * Created by Administrator on 2018/10/22.
 */

public class FloatScanView extends android.support.v7.widget.AppCompatImageView {

    private int statusHeight;
    int sW;
    int sH;
    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;
    private boolean isMove = false;
    private Context context;

    private WindowManager wm = (WindowManager) getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

    //此wmParams变量为获取的全局变量，用以保存悬浮窗口的属性
    private WindowManager.LayoutParams wmParams = ((TyslApp)getContext().getApplicationContext()).getMywmParams();

    private float mLastX;
    private float mLastY;
    private float mStartX;
    private float mStartY;
    private long mLastTime;
    private long mCurrentTime;
    private int widthPixels=100;
    private int heightPixels;
    private OnClickListener mClickListener;



    public FloatScanView(Context context) {
        super(context);
        this.context = context;
    }

    public FloatScanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(displayMetrics);
//        widthPixels = displayMetrics.widthPixels;
//        heightPixels = displayMetrics.heightPixels;
//        Log.e("屏幕宽", widthPixels + "");
//        Log.e("屏幕高", heightPixels + "");
        statusHeight = getStatusHeight(context);
    }

    public FloatScanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        // DisplayMetrics displayMetrics = new DisplayMetrics();
//        wm.getDefaultDisplay().getMetrics(displayMetrics);
//         widthPixels = displayMetrics.widthPixels;
//         heightPixels = displayMetrics.heightPixels;
//        Log.e("屏幕宽", widthPixels + "");
//        Log.e("屏幕高", heightPixels + "");
        statusHeight = getStatusHeight(context);
    }

    /**
     *      * 获得状态栏的高度
     *      *
     *      * @param context
     *      * @return
     *      
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }
    @Override
    public void setOnClickListener(OnClickListener l) {
        this.mClickListener = l;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY() - statusHeight;  //statusHeight是系统状态栏的高度
        Log.i("currP", "currX" + x + "====currY" + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: //捕获手指触摸按下动作
                DisplayMetrics displayMetrics = new DisplayMetrics();

                wm.getDefaultDisplay().getMetrics(displayMetrics);
                widthPixels = displayMetrics.widthPixels;
                heightPixels = displayMetrics.heightPixels;
//获取相对View的坐标，即以此View左上角为原点
                mTouchStartX = event.getX();
                mTouchStartY = event.getY();
                mStartX = event.getRawX();
                mStartY = event.getRawY();
                mLastTime = System.currentTimeMillis();
                Log.i("startP", "startX" + mTouchStartX + "====startY" + mTouchStartY);
                isMove = false;

                break;
            case MotionEvent.ACTION_MOVE: //捕获手指触摸移动动作
//                Log.e("屏幕RawX",x+"");
//                Log.e("屏幕RawY",y+"");
//                Log.e("屏幕mTouchStartX",mTouchStartX+"");
//                Log.e("屏幕mTouchStartY",mTouchStartY+"");
                if (Math.abs(mStartY - event.getRawY())>100||Math.abs(mStartX - event.getRawX())>50) {
                    updateViewPosition();
                    isMove = true;
                }

                break;


            case MotionEvent.ACTION_UP://捕获手指触摸离开动作
                mLastX = event.getRawX();
                mLastY = event.getRawY();


// 抬起手指时让floatView紧贴屏幕左右边缘
                wmParams.x = wmParams.x <= (widthPixels / 2) ?  0: widthPixels;
                if (isMove) {
//                    wmParams.y = (int) -(heightPixels / 2 - y - mTouchStartY);
                    wmParams.y = (int) -(heightPixels / 2 - y);
                    wm.updateViewLayout(this, wmParams);
                    isMove = false;
                }

                mCurrentTime = System.currentTimeMillis();
                if (mCurrentTime - mLastTime < 200) {
                    if (Math.abs(mStartX - mLastX) < 10.0 && Math.abs(mStartY - mLastY) < 10.0) {
//处理点击的事件
                        if (mClickListener != null) {
                            mClickListener.onClick(this);
                        }


                    }
                }


                break;
        }
        return true;
    }


    private void updateViewPosition() {
        //更新浮动窗口位置参数
        wmParams.x = (int) (widthPixels-x- mTouchStartX);
        wmParams.y = (int) (-(heightPixels/2-y ));
//        Log.e("xxx",wmParams.x+"");
//        Log.e("xxxwidthPixels",widthPixels+"");
//        Log.e("yyy",wmParams.y+"");
        wm.updateViewLayout(this, wmParams); //刷新显示

    }


}
