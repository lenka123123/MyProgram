package com.wokun.tysl.store.ui;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.FavoritesMgr;
import com.wokun.tysl.R;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.StoreFavoritesAdapter;
import com.wokun.tysl.model.bean.Store;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.FavoritesResponse;

import java.util.ArrayList;

import butterknife.BindString;

//店铺收藏页面
public class StoreFavoritesActivity extends SimpleRefreshAndLoadMoreActivity<Store> {

  //  @BindString(R.string.tysl_shop_favorites)String title;

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);

        return mWidgetBar;

    }

    @Override
    public BaseQuickAdapter<Store, BaseViewHolder> initAdapter() {
        StoreFavoritesAdapter adapter = new StoreFavoritesAdapter(R.layout.item_store_favorites, new ArrayList<Store>());

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Store bean = (Store) adapter.getData().get(position);

                if(R.id.isCollect == view.getId()){
                    FavoritesMgr.getInstance().deleteFavorites(StoreFavoritesActivity.this,Constants.FAVORITES_TYPE_STORE, bean.getStore_id());
                }
            }
        });
        return adapter;
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<FavoritesResponse<Store>>>post(Constants.BASE_URL + Constants.FAVORITES_LIKE_LIST_URL)
                .tag(this).params(Constants.TYPE, Constants.FAVORITES_TYPE_STORE);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mMultipleStatusView.showLoading();
        mRequest.execute(new JsonCallback<BaseResponse<FavoritesResponse<Store>>>(Constants.WITH_TOKEN,Constants.FAVORITES_LIKE_LIST_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<FavoritesResponse<Store>>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    mMultipleStatusView.showContent();
                    FavoritesResponse<Store> data = (FavoritesResponse<Store>) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getLikeList());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Store data = mDataList.get(position);
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.STORE_ID, data.getStore_id());
        intent.setClass(this, StoreIndexActivity.class);
        startActivity(intent);
    }
}