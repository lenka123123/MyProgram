package com.wokun.tysl.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MyGridView extends GridView implements AdapterView.OnItemClickListener {
    private OnRadioItemClickListener mListener;
    private int currentPosition = 0;
    private int mBgImageSelected;
    private int mBgImageUnselected;

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    //    该自定义控件只是重写了GridView的onMeasure方法，使其不会出现滚动条，ScrollView嵌套ListView也是同样的道理，不再赘述。
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        //AT_MOST(表示子控件的高度能扩展多高就扩展多高，但要小于给出的size)
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,  MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }
//


    //可以在xml文件直接设置item背景，是不是很方便
    private void initView(Context context, AttributeSet attrs) {
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadioGridView);
//        mBgImageSelected = typedArray.getResourceId(R.styleable.RadioGridView_item_selected,
//                R.drawable.mm_bank_bg);
//        mBgImageUnselected = typedArray.getResourceId(R.styleable.RadioGridView_item_unselected,
//                R.drawable.mm_bank_default);
//        typedArray.recycle();
        setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//一个很小的逻辑，刚开始用的for循环，感觉太low了，改为这种
        int lastPosition = currentPosition;
        currentPosition = position;
//        parent.getChildAt(lastPosition).setBackgroundResource(mBgImageUnselected);//未被选择时的背景
//        view.setBackgroundResource(mBgImageSelected);//被选择是的背景
        if (mListener != null) {
            mListener.onItemClick(getId(), position);
        }
    }

    /**
     * 设置item点击监听器
     *
     * @param listener item点击监听器
     */
    public void setOnRadioItemClickListener(OnRadioItemClickListener listener) {
        mListener = listener;
    }

    /**
     * item点击监听器
     */
    public interface OnRadioItemClickListener {
        /**
         * @param gridViewId gridView id
         * @param position   item 位置
         */
        void onItemClick(int gridViewId, int position);
    }


}