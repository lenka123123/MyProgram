package com.wokun.tysl.article.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.article.bean.ArticleBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

//学堂文章列表数据适配器
public class XueTangArticleListAdapter extends BaseQuickAdapter<ArticleBean,BaseViewHolder> {

    public XueTangArticleListAdapter(@LayoutRes int layoutResId, @Nullable List<ArticleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ArticleBean item) {
        ImageLoader.loadImage(item.getArticleImage(), (ImageView) helper.getView(R.id.article_image));
        helper.setText(R.id.tv_title, item.getTitle())
        .setText(R.id.tv_click_num, item.getClickNum())
        .setText(R.id.tv_good_num, item.getGoodNum())
      //  .setText(R.id.tv_comments_nums, item.getCommentsNums())
        .setText(R.id.add_time, item.getAddTime());
    }
}
