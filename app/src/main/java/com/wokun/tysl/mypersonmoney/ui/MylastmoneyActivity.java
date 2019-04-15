package com.wokun.tysl.mypersonmoney.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.mypersonmoney.adapter.MylastmoneyAdapter;
import com.wokun.tysl.mypersonmoney.bean.MylastMoneyBean;
import com.wokun.tysl.mypersonmoney.bean.MylastmoneyResponse;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.wokun.tysl.TyslApp.getContext;

/**
 * Created by Administrator on 2018/7/11/011.
 * 社交链明细
 */

public class MylastmoneyActivity  extends BaseBindingActivity implements
        SwipeRefreshLayout.OnRefreshListener ,
        BaseQuickAdapter.RequestLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener{
    private PopupWindow mPopupWindow;
    private View mAddView;
    private Unbinder unbinder;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private Request mRequest;
    private int mNextRequestPage = 1;
    private int PAGE_SIZE = Constants.PG_SIZE;
    private List<MylastMoneyBean> mDataList;
    private BaseQuickAdapter<MylastMoneyBean, BaseViewHolder> mAdapter;

    @BindView(R.id.center_mymoneycount)
     LinearLayout  centerMymoneycount;
    @BindView(R.id.back)
    ImageView mBack;

    @Override
    public int createView() {
        return R.layout.activity_center_lastmoney;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;
    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();

        initData();//数据
      //  mMultipleStatusView.showLoading();

        initRecyclerView();
        onRefresh();
    }

    private void initRecyclerView() {
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,1));
        mRequest = initRequest();
        mAdapter = initAdapter();
        mRecyclerView.setAdapter(mAdapter);

    }

    private Request initRequest() {

        mRequest = OkGo.<BaseResponse<MylastmoneyResponse>>post(Constants.BASE_URL + Constants.INTEGRAL_RECORD_URL).tag(this);
        User user = TyslApp.getInstance().getUser();
        mRequest.params(Constants.USER_ID, user.getUserId());
        mRequest.params(Constants.TYPE, 0);

        return mRequest;
    }

    public BaseQuickAdapter<MylastMoneyBean, BaseViewHolder> initAdapter() {
        mAdapter = new MylastmoneyAdapter(R.layout.item_lastmoney,null);
        mAdapter.setEmptyView(R.layout.layout_no_data_view, (ViewGroup) mRecyclerView.getParent());
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemClickListener(this);
        return mAdapter;
    }





    private void initData() {

           mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        centerMymoneycount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopWin(centerMymoneycount);
            }
        });
    }
      private void showPopWin(View parent) {
          if (mPopupWindow==null){
            mAddView = LayoutInflater.from(getContext()).inflate(R.layout.popwindow_show ,null, false);
            mPopupWindow = new PopupWindow(mAddView, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);
            // 设置PopupWindow的背景
            mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            // 设置PopupWindow是否能响应外部点击事件
            mPopupWindow.setOutsideTouchable(true);
            // 设置PopupWindow是否能响应点击事件
              mPopupWindow.setTouchable(true);
                }
              mPopupWindow.showAsDropDown(parent);

          LinearLayout popAll = (LinearLayout )mAddView.findViewById(R.id.pop_all);
          LinearLayout popBuy = (LinearLayout )mAddView.findViewById(R.id.pop_buy);
          LinearLayout popGet = (LinearLayout )mAddView.findViewById(R.id.pop_get);
          final ImageView popselect1 =(ImageView)mAddView.findViewById(R.id.pop_select1);
          final ImageView popselect2 =(ImageView)mAddView.findViewById(R.id.pop_select2);
          final ImageView popselect3 =(ImageView)mAddView.findViewById(R.id.pop_select3);

          popAll.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  mRequest.params(Constants.TYPE, 0);
                  mPopupWindow.dismiss();
                  onRefresh();
              }
          });
          popBuy.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  popselect1.setVisibility(View.INVISIBLE);
                  popselect2.setVisibility(View.VISIBLE);
                  popselect3.setVisibility(View.INVISIBLE);

                  mPopupWindow.dismiss();
                  mRequest.params(Constants.TYPE, 2);
                  onRefresh();

              }
          });
          popGet.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

                  popselect1.setVisibility(View.INVISIBLE);
                  popselect2.setVisibility(View.INVISIBLE);
                  popselect3.setVisibility(View.VISIBLE);
                  mPopupWindow.dismiss();
                  mRequest.params(Constants.TYPE, 1);
                  onRefresh();
              }
          });


}


    public void onRefresh() {

        mNextRequestPage = 1;
        mRequest.params(Constants.PAGE, mNextRequestPage)
                .params(Constants.PAGE_SIZE, PAGE_SIZE);
        mAdapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        doOnRefreshData();
    }
    public void doOnRefreshData(){
        loadData(true);
    }

    public void doOnLoadMoreData(){
        loadData(false);
    }

    public void loadData(final boolean isRefresh) {




        mRequest.execute(new JsonCallback<BaseResponse<MylastmoneyResponse>>(Constants.WITH_TOKEN,Constants.INTEGRAL_RECORD_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<MylastmoneyResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    mMultipleStatusView.showContent();
                    MylastmoneyResponse mylastmoneyResponse = (MylastmoneyResponse) body.getData();
                    if(mylastmoneyResponse==null){return;}
                    setData(isRefresh, mylastmoneyResponse.getIntegralRList());
                }
            }
        });
    }


    public void setData(boolean isRefresh, List<MylastMoneyBean> data) {
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





    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onLoadMoreRequested() {

    }
}
