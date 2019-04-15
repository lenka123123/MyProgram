package com.wokun.tysl.article.ui;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.article.bean.GoodsServiceBean;
import com.wokun.tysl.article.bean.ServiceBean;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.EvaAdapter;
import com.wokun.tysl.dietician.bean.EvalDataBean;
import com.wokun.tysl.model.response.BaseResponse;

//评价商品列表
public class PinjiaGoodsListActivity extends SimpleRefreshAndLoadMoreActivity<EvalDataBean> {

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("全部商品评价");
    }

    @Override
    public BaseQuickAdapter<EvalDataBean, BaseViewHolder> initAdapter() {

        return new EvaAdapter(R.layout.item_eval, null);
    }

    @Override
    public Request initRequest() {
       int goodslistId = getIntent().getIntExtra(Constants.GOODS_ID,0);
        return OkGo.<BaseResponse<ServiceBean>>post(Constants.BASE_URL + Constants.GOODS_EVAL_LIST_URL)
                .tag(this).params(Constants.GOODS_ID, goodslistId);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<GoodsServiceBean>>() {
            @Override
            public void onSuccess(Response<BaseResponse<GoodsServiceBean>> response) {
                BaseResponse body = response.body();
                Log.e("11body",""+body);
                if(body == null)return;

                if(body.isState()){
                    GoodsServiceBean data = (GoodsServiceBean) body.getData();
                    if(data == null){return;}
                    Log.e("11data",""+data);
                    setData(isRefresh,data.getEvalList());
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
      //  ArticleMgr.getInstance().jumpToArticleDetailActivity(PinjiaServiceListActivity.this,mDataList.get(position));
    }
}
