package com.wokun.tysl.goods.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.model.bean.SubBean;
import com.wokun.tysl.goods.bean.SubCategoryBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class SubCategoryAdapter extends BaseSectionQuickAdapter<SubCategoryBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SubCategoryAdapter(int layoutResId, int sectionHeadResId, List<SubCategoryBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SubCategoryBean item) {
        helper.setText(R.id.header, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SubCategoryBean item) {
        SubBean subBean = item.t;
        ImageView imageView = helper.getView(R.id.section_image);
        ImageLoader.loadImage(subBean.getGc_icon(), imageView);
        helper.setText(R.id.section_text, subBean.getGc_name());
    }
}
