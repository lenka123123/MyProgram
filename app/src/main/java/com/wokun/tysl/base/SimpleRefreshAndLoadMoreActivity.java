package com.wokun.tysl.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;

import butterknife.BindView;

public abstract class SimpleRefreshAndLoadMoreActivity<T> extends BaseRefreshAndLoadMoreActivity<T> {

    @BindView(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public int createView() {
        return R.layout.activity_normal_refresh_layout;
    }

    @Override
    public RecyclerView initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL));
        return mRecyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }
}