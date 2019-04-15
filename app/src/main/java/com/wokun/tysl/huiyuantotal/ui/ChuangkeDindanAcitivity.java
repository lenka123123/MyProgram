package com.wokun.tysl.huiyuantotal.ui;

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
import com.wokun.tysl.huiyuantotal.adapter.ChuangkeAdapter;
import com.wokun.tysl.huiyuantotal.bean.ChuankeBean;
import com.wokun.tysl.huiyuantotal.bean.ChuankeDetaiBean;
import com.wokun.tysl.huiyuantotal.bean.ChuankeResponse;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;

/**
 * Created by Administrator on 2018\10\24 0024
 * 创客订单
 */

public class ChuangkeDindanAcitivity  extends SimpleRefreshAndLoadMoreActivity<ChuankeBean> {

    @BindView(R.id.toolbar) WidgetBar widgetBar;
    private ChuangkeAdapter mAdapter;
    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<ChuankeResponse>>post(Constants.BASE_URL + Constants.MAKER_ORDER_API)
                .tag(this)
                .params(Constants.STORE_CODE, getIntent().getStringExtra(Constants.STORE_CODE));
    }

    @Override
    public BaseQuickAdapter<ChuankeBean, BaseViewHolder> initAdapter() {
        mAdapter = new ChuangkeAdapter(R.layout.item_zhihui_chuangke_body, R.layout.item_zhihui_chuangke_header, R.layout.item_zhihui_chuangke_footer, null);

        return mAdapter;
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<ChuankeResponse>>(Constants.WITH_TOKEN,Constants.MAKER_ORDER_API) {
            @Override
            public void onSuccess(Response<BaseResponse<ChuankeResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    ChuankeResponse data = (ChuankeResponse) body.getData();
                    if(data==null){return;}
                    List<ChuankeBean> list = data.getOrder();
                    List<ChuankeBean> newData = new ArrayList<>();
                    if (list != null) {
                        for (ChuankeBean goodsOrder : list) {
                            newData.add(new ChuankeBean(mAdapter.HEADER,goodsOrder.getOrder_sn(),goodsOrder.getPay_time()));

                            ArrayList<ChuankeDetaiBean> subBeanList = goodsOrder.getSubOrder();
                            if (subBeanList != null) {
                                for (ChuankeDetaiBean goods : subBeanList) {
                                newData.add(new ChuankeBean(mAdapter.BODY, goods,goodsOrder.getOrder_id()));
                                  }
                            }
                             newData.add(new ChuankeBean(mAdapter.FOOTER, goodsOrder.getUsername(), goodsOrder.getMobile(), goodsOrder.getOrder_id(),goodsOrder.getNums(),goodsOrder.getReward()));
                        }
                    }
                    setData(isRefresh, newData);
                }
            }
        });
    }


    @Override
    public WidgetBar createToolBar() {

        return widgetBar.setWidgetBarTitle("创客订单");
    }




}
