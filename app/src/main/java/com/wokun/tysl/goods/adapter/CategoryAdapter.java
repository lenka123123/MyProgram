package com.wokun.tysl.goods.adapter;

import com.shantoo.widget.utils.UITool;
import com.wokun.tysl.R;
import com.wokun.tysl.goods.bean.TopCategoryBean;

import java.util.List;

import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.QTabView;

public class CategoryAdapter implements TabAdapter {

    private List<TopCategoryBean> list;

    public CategoryAdapter(List<TopCategoryBean> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public QTabView.TabBadge getBadge(int position) {
        return null;
    }

    @Override
    public QTabView.TabIcon getIcon(int position) {
        return null;
    }

    @Override
    public QTabView.TabTitle getTitle(int position) {
        return new QTabView.TabTitle.Builder()
                .setContent(list.get(position).getGcName())
                .setTextColor(UITool.getColor(R.color.colorPrimary), UITool.getColor(R.color.color22))
                .build();
    }

    @Override
    public int getBackground(int position) {
        return -1;
    }
}
