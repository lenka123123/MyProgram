package com.wokun.tysl.goods.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.goods.bean.SubCategoryBean;
import com.wokun.tysl.model.bean.SubBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class SubCategoryNewAdapter extends BaseQuickAdapter<SubCategoryBean, BaseViewHolder> {


    public SubCategoryNewAdapter(@LayoutRes int layoutResId, @Nullable List<SubCategoryBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoryBean item) {
        //SubBean subBean = item.t;
        ImageView imageView = helper.getView(R.id.section_image);

        ImageLoader.loadImage(item.getGcIcon(), imageView);
        helper.setText(R.id.section_text, item.getGcName());
    }
}
