package com.wokun.tysl.goods.ui.activity;

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
import com.wokun.tysl.goods.adapter.GoodsFavoritesAdapter;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.FavoritesResponse;

import java.util.ArrayList;

import butterknife.BindString;

//商品收藏页面
public class GoodsFavoritesActivity extends SimpleRefreshAndLoadMoreActivity<GoodsBean> {

  //  @BindString(R.string.tysl_goods_favorites)String title;

    @Override
    public WidgetBar createToolBar() {

         mWidgetBar.setVisibility(View.GONE);

         return mWidgetBar;
        //  return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public BaseQuickAdapter<GoodsBean, BaseViewHolder> initAdapter() {
        GoodsFavoritesAdapter mAdapter = new GoodsFavoritesAdapter(R.layout.item_goods_favorites, new ArrayList<GoodsBean>());

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsBean bean = (GoodsBean) adapter.getData().get(position);

                if(R.id.favorites == view.getId()){
                    FavoritesMgr.getInstance().deleteFavorites(GoodsFavoritesActivity.this,Constants.FAVORITES_TYPE_GOODS, bean.getGoodsId()+"");
                }
            }
        });
        return mAdapter;
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<FavoritesResponse<GoodsBean>>>post(Constants.BASE_URL + Constants.FAVORITES_LIKE_LIST_URL)
                .tag(this)
                .params(Constants.TYPE, Constants.FAVORITES_TYPE_GOODS);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mMultipleStatusView.showLoading();
        mRequest.execute(new JsonCallback<BaseResponse<FavoritesResponse<GoodsBean>>>(Constants.WITH_TOKEN,Constants.FAVORITES_LIKE_LIST_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<FavoritesResponse<GoodsBean>>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    mMultipleStatusView.showContent();
                    FavoritesResponse<GoodsBean> data = (FavoritesResponse<GoodsBean>) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getLikeList());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        GoodsBean data = mDataList.get(position);
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.GOODS_ID, data.getGoodsId());
        intent.setClass(this, GoodsDetailActivity.class);
        startActivity(intent);
    }
}