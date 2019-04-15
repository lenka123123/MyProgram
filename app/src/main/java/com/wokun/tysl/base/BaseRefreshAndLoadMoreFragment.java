package com.wokun.tysl.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.config.Constants;

import java.util.List;

/**
 * 下拉刷新，上拉加载几类Fragment
 * */
public abstract class BaseRefreshAndLoadMoreFragment<T> extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener ,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener{

    private int mNextRequestPage = 1;
    private int PAGE_SIZE = Constants.PG_SIZE;

    protected Request mRequest;
    protected List<T> mDataList;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    protected String TAG = this.getClass().getSimpleName();

    public abstract RecyclerView initRecyclerView();

    public abstract SwipeRefreshLayout initSwipeRefreshLayout();

    public abstract Request initRequest();

    public abstract BaseQuickAdapter<T, BaseViewHolder> initAdapter();

    public abstract void loadData(boolean isRefresh);

    @Override
    public void initViews() {}

    @Override
    public void loadData() {}

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout = initSwipeRefreshLayout();
        mRecyclerView = initRecyclerView();

        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mAdapter = initAdapter();
        mRequest = initRequest();

        mAdapter.setEmptyView(R.layout.layout_no_data_view, (ViewGroup) mRecyclerView.getParent());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);

        onRefresh();
    }

    @Override
    public void onRefresh() {
        mNextRequestPage = 1;
        mRequest.params(Constants.PAGE, mNextRequestPage)
                .params(Constants.PAGE_SIZE, PAGE_SIZE);
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        doOnRefreshData();
    }

    @Override
    public void onLoadMoreRequested() {
        mRequest.params(Constants.PAGE, mNextRequestPage)
                .params(Constants.PAGE_SIZE, PAGE_SIZE);
        doOnLoadMoreData();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {}

    public void setData(boolean isRefresh, List<T> data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            mAdapter.setNewData(data);
            mAdapter.disableLoadMoreIfNotFullPage();
            mSwipeRefreshLayout.setRefreshing(false);
        } else {
            if (size > 0) {
                mAdapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mAdapter.loadMoreEnd(isRefresh);
           /* if(!isRefresh){
                RxToast.showShort("沒有更多数据了");
            }*/
        } else {
            mAdapter.loadMoreComplete();
        }
        mDataList = mAdapter.getData();
    }

    public void doOnRefreshData(){
        loadData(true);
    }

    public void doOnLoadMoreData(){
        loadData(false);
    }
}