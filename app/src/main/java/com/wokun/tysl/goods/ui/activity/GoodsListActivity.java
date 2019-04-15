package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.GoodsListAdapter;
import com.wokun.tysl.goods.bean.GoodsListBean;
import com.wokun.tysl.goods.response.GoodsListResponse;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//商品列表页
public class GoodsListActivity extends BaseBindingActivity implements
        SwipeRefreshLayout.OnRefreshListener ,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener{

    @BindString(R.string.tysl_goods_list) String title;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.drawable_btn_sort_down_synthesize)SelectorImageView drawableBtnSortDownSynthesize;//默认向下箭头
    @BindView(R.id.drawable_btn_sort_down_sales)SelectorImageView drawableBtnSortDownSales;//销量向下箭头
    @BindView(R.id.drawable_btn_sort_down_newest)SelectorImageView drawableBtnSortDownNewest;//最新向下箭头
    @BindView(R.id.drawable_btn_sort_up_price)SelectorImageView drawableBtnSortUpPrice;//价格向上箭头
    @BindView(R.id.drawable_btn_sort_down_price)SelectorImageView drawableBtnSortDownPrice;//价格向下箭头

    private Request mRequest;
    private int mNextRequestPage = 1;
    private int PAGE_SIZE = Constants.PG_SIZE;
    private List<GoodsListBean> mDataList;
    private boolean current_price_sort = false;//默认价格降序排序
    private BaseQuickAdapter<GoodsListBean, BaseViewHolder> mAdapter;

    @Override
    public int createView() {
        return R.layout.activity_goods_list;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();
        drawableBtnSortDownSynthesize.toggle(true);
        drawableBtnSortDownSales.toggle(false);
        drawableBtnSortDownNewest.toggle(false);
        drawableBtnSortUpPrice.toggle(false);
        drawableBtnSortDownPrice.toggle(false);

        initRecyclerView();
        onRefresh();
    }

    private void initRecyclerView(){
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(this);
         mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRequest = initRequest();
        mAdapter = initAdapter();
        mRecyclerView.setAdapter(mAdapter);
    }

    public Request initRequest() {
        String goodClassId,sgcId,keywords,storeId,dietitianId;
        goodClassId = getIntent().getStringExtra("gc_id");
       //  goodClassId = getIntent().getStringExtra(Constants.GOOD_CLASS_ID);

        sgcId = getIntent().getStringExtra(Constants.SGC_ID);
        keywords = getIntent().getStringExtra(Constants.KEYWORDS);
        storeId = getIntent().getStringExtra(Constants.STORE_ID);
        dietitianId = getIntent().getStringExtra(Constants.DIETITIAN_ID);

        mRequest = OkGo.<BaseResponse<GoodsListResponse>>post(Constants.BASE_URL + Constants.GOODS_LIST_URL).tag(this);

        if(!TextUtils.isEmpty(goodClassId)){
            mRequest.params(Constants.GOOD_CLASS_ID, goodClassId);
        }else if(!TextUtils.isEmpty(sgcId)){
            mRequest.params(Constants.SGC_ID, sgcId);
        }else if(!TextUtils.isEmpty(keywords)){
            mRequest.params(Constants.KEYWORDS, keywords);
        }else if(!TextUtils.isEmpty(storeId)){
            mRequest.params(Constants.STORE_ID, storeId);
        }else if(!TextUtils.isEmpty(dietitianId)){
            mRequest.params(Constants.DIETITIAN_ID, dietitianId);
        }
        return mRequest ;
    }

    public BaseQuickAdapter<GoodsListBean, BaseViewHolder> initAdapter() {
        mAdapter = new GoodsListAdapter(R.layout.item_goods_list,null);
        mAdapter.setEmptyView(R.layout.layout_no_data_view, (ViewGroup) mRecyclerView.getParent());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(this);
        return mAdapter;
    }

    @OnClick({R.id.action_sort_synthesize,
              R.id.action_sort_sales,
              R.id.action_sort_newest,
              R.id.action_sort_price})
    public void action(View v) {
        if(R.id.action_sort_synthesize == v.getId()){//综合排序
            drawableBtnSortDownSynthesize.toggle(true);
            drawableBtnSortDownSales.toggle(false);
            drawableBtnSortDownNewest.toggle(false);
            drawableBtnSortUpPrice.toggle(false);
            drawableBtnSortDownPrice.toggle(false);
            mRequest.params(Constants.ORDER, Constants.Z);
            onRefresh();
        }else if(R.id.action_sort_sales == v.getId()){//销量排序
            drawableBtnSortDownSynthesize.toggle(false);
            drawableBtnSortDownSales.toggle(true);
            drawableBtnSortDownNewest.toggle(false);
            drawableBtnSortUpPrice.toggle(false);
            drawableBtnSortDownPrice.toggle(false);
            mRequest.params(Constants.ORDER, Constants.N);
            onRefresh();
        }else if(R.id.action_sort_newest == v.getId()){//最新排序
            drawableBtnSortDownSynthesize.toggle(false);
            drawableBtnSortDownSales.toggle(false);
            drawableBtnSortDownNewest.toggle(true);
            drawableBtnSortUpPrice.toggle(false);
            drawableBtnSortDownPrice.toggle(false);
            mRequest.params(Constants.ORDER, Constants.N);
            onRefresh();
        }else if(R.id.action_sort_price == v.getId()){//价格排序
            drawableBtnSortDownSynthesize.toggle(false);
            drawableBtnSortDownSales.toggle(false);
            drawableBtnSortDownNewest.toggle(false);
            if (!current_price_sort) {
                mRequest.params(Constants.ORDER, Constants.PU);//从低到高
                drawableBtnSortUpPrice.toggle(true);
                drawableBtnSortDownPrice.toggle(false);
                current_price_sort = true;
            } else {
                mRequest.params(Constants.ORDER, Constants.PD);//从高到低
                drawableBtnSortUpPrice.toggle(false);
                drawableBtnSortDownPrice.toggle(true);
                current_price_sort = false;
            }
            onRefresh();
        }
    }

    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<GoodsListResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<GoodsListResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    mMultipleStatusView.showContent();
                    GoodsListResponse goodsListResponse = (GoodsListResponse) body.getData();
                    if(goodsListResponse==null){return;}
                    setData(isRefresh, goodsListResponse.getGoods_list());
                }
            }
        });
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

    public void doOnRefreshData(){
        loadData(true);
    }

    public void doOnLoadMoreData(){
        loadData(false);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsListBean data = mDataList.get(position);
        //Logger.e(TAG, JSONUtil.toJSON(data));
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.GOODS_ID, data.getGoodsId());
        intent.setClass(this, GoodsDetailActivity.class);
        startActivity(intent);
    }

    public void setData(boolean isRefresh, List<GoodsListBean> data) {
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
            /*if(!isRefresh){
                Toast.makeText(this, "沒有更多数据了", Toast.LENGTH_SHORT).show();
            }*/
        } else {
            mAdapter.loadMoreComplete();
        }
        mDataList = mAdapter.getData();
    }
}