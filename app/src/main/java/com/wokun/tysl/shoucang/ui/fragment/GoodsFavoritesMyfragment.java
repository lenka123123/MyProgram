package com.wokun.tysl.shoucang.ui.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.FavoritesMgr;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.BaseFragment1;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.GoodsFavoritesAdapter;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.goods.ui.activity.GoodsFavoritesActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.FavoritesResponse;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/9/009.
 */
//商品收藏
public class GoodsFavoritesMyfragment  extends BaseFragment1 {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerViews;
   private    GoodsFavoritesAdapter mAdapter;
    @Override
    public int createView() {
        return R.layout.activity_goods_shoucang;
    }

    @Override
    public void initViews() {
        mAdapter = new GoodsFavoritesAdapter(R.layout.item_goods_favorites, new ArrayList<GoodsBean>());
        mRecyclerViews.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViews.setAdapter(mAdapter);
        mRecyclerViews.setNestedScrollingEnabled(false);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                mMultipleStatusView.showLoading();
                GoodsBean bean = (GoodsBean) adapter.getData().get(position);
                if(R.id.favorites == view.getId()){
          //     FavoritesMgr.getInstance().deleteFavorites(GoodsFavoritesActivity.this,Constants.FAVORITES_TYPE_GOODS, bean.getGoodsId()+"");




                }
            }
        });
    }

    @Override
    public void loadData() {
        //网络请求
        OkGo.<BaseResponse<FavoritesResponse<GoodsBean>>>post(Constants.BASE_URL + Constants.FAVORITES_LIKE_LIST_URL)
                .tag(this)
                .params(Constants.TYPE, Constants.FAVORITES_TYPE_GOODS);



    }
}
