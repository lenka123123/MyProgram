package com.wokun.tysl.dietician.ui.activity;

import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.DieticianFavoritesAdapter;
import com.wokun.tysl.dietician.bean.DieticianFavoritesBean;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.FavoritesResponse;

import java.util.ArrayList;

import butterknife.BindString;

//营养师收藏页面
public class DieticianFavoritesActivity extends SimpleRefreshAndLoadMoreActivity<DieticianFavoritesBean> {

   // @BindString(R.string.tysl_dietician_favorites)String title;

    @Override
    public WidgetBar createToolBar() {

        mWidgetBar.setVisibility(View.GONE);

        return mWidgetBar;
    }

    @Override
    public BaseQuickAdapter<DieticianFavoritesBean, BaseViewHolder> initAdapter() {
        return new DieticianFavoritesAdapter(R.layout.item_dietician_favorites, new ArrayList<DieticianFavoritesBean>());
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<FavoritesResponse<DieticianFavoritesBean>>>post(Constants.BASE_URL + Constants.FAVORITES_LIKE_LIST_URL)
                .tag(this).params(Constants.TYPE, Constants.FAVORITES_TYPE_DIETICIAN);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mMultipleStatusView.showLoading();
        mRequest.execute(new JsonCallback<BaseResponse<FavoritesResponse<DieticianFavoritesBean>>>(Constants.WITH_TOKEN,Constants.FAVORITES_LIKE_LIST_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<FavoritesResponse<DieticianFavoritesBean>>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    mMultipleStatusView.showContent();
                    FavoritesResponse<DieticianFavoritesBean> data =
                            (FavoritesResponse<DieticianFavoritesBean>) body.getData();
                    if(data==null){return;}
                    setData(isRefresh,data.getLikeList());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DieticianFavoritesBean data = mDataList.get(position);
        if(data==null){return;}
        Intent intent = new Intent();
        intent.putExtra(Constants.DIETITIAN_ID, data.getDietitianId());
        intent.setClass(this, DieticianDetailActivity.class);
        startActivity(intent);
    }
}