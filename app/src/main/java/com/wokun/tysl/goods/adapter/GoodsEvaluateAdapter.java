package com.wokun.tysl.goods.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hedgehog.ratingbar.RatingBar;
import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.wokun.tysl.R;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.duotupianshangchuan.MyMultiplePhotoSelector;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.utils.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GoodsEvaluateAdapter extends BaseQuickAdapter<GoodsBean, BaseViewHolder> {
private  String myeditcontext;
    private MyMultiplePhotoSelector mMultiplePhotoSelector;

    public GoodsEvaluateAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsBean> data) {
        super(layoutResId, data);
    }
    @Override
    protected void convert(BaseViewHolder helper, final GoodsBean  item) {
        ImageLoader.loadImage(item.getGoodsImage(), (ImageView) helper.getView(R.id.iv_goods_image));
        helper.setText(R.id.tv_title,item.getGoodsName());
        RatingBar mRatingBar = helper.getView(R.id.rating_bar);
        mMultiplePhotoSelector = (MyMultiplePhotoSelector) helper.getView(R.id.multiple_photo_selector);
        mMultiplePhotoSelector.setOnMultiplePhotoUpLoadListener(new OnMultiplePhotoUpLoadListener() {
            @Override
            public void onMultiplePhotoUpLoad(List<String> photosPath) {
                multiplePhotoSelectorListener.onMultiplePhotoSelectorListener(photosPath);
            }
        });
        EditText etComment =(EditText) helper.getView(R.id.et_comment);
              myeditcontext = etComment.getText().toString();
        mRatingBar.setStar(5);
        mRatingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {
                mOnRatingChangeListener.onRatingChange(RatingCount);
            }
        });
        etComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(item);
            }
        });







    }


    private OnRatingChangeListener mOnRatingChangeListener2;
    private OnRatingChangeListener mOnRatingChangeListener3;
    private OnRatingChangeListener mOnRatingChangeListener;
    private OnRatingChangeListener mOnRatingChangeListener1;
    private   EditContextListener  mEditContextListener;

    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(GoodsBean item);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }


    public interface OnRatingChangeListener{
        void onRatingChange(float RatingCount);
    }

    public void setOnRatingChangeListener(OnRatingChangeListener l){
        this.mOnRatingChangeListener = l;

    }



    private   MultiplePhotoSelectorListener  multiplePhotoSelectorListener;
    public interface MultiplePhotoSelectorListener{
        void  onMultiplePhotoSelectorListener(List<String> photosPath);
    }

    public void setMultiplePhotoSelectorListener(MultiplePhotoSelectorListener multiplePhotoSelectorListener) {
        this.multiplePhotoSelectorListener = multiplePhotoSelectorListener;
    }

    public interface TextComitListener{
        void  onTextcomit();
    }



    public interface EditContextListener{
        void  onEditContext(String editcontext);
    }

    public void setmEditContextListener(EditContextListener mEditContextListener) {
        this.mEditContextListener = mEditContextListener;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mMultiplePhotoSelector.onActivityResult(requestCode, resultCode, data);
    }
}
