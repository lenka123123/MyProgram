package com.wokun.tysl.order.ui;
        import android.content.Intent;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Toast;

        import com.chad.library.adapter.base.BaseQuickAdapter;
        import com.chad.library.adapter.base.BaseViewHolder;
        import com.example.zhouwei.library.CustomPopWindow;
        import com.lzy.okgo.OkGo;
        import com.lzy.okgo.model.Response;
        import com.lzy.okgo.request.base.Request;
        import com.shantoo.widget.toast.RxToast;
        import com.wokun.tysl.R;
        import com.wokun.tysl.TyslApp;
        import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
        import com.wokun.tysl.callback.JsonCallback;
        import com.wokun.tysl.config.Constants;
        import com.wokun.tysl.dietician.adapter.ServiceOrderAdapter;
        import com.wokun.tysl.dietician.bean.ServiceOrderBean;
        import com.wokun.tysl.dietician.controler.DieticianServiceOrderMgr;
        import com.wokun.tysl.goods.controller.GoodsOrderMgr;
        import com.wokun.tysl.goods.ui.activity.GoodsOrderDetailActivity;
        import com.wokun.tysl.model.response.BaseResponse;
        import com.wokun.tysl.model.response.OrderListResponse;
        import com.wokun.tysl.order.adapter.MyOrderServiceAdapter;
        import com.wokun.tysl.order.adapter.ZhihuiGenAdapter;
        import com.wokun.tysl.order.adapter.ZhihuiOrderAdapter;
        import com.wokun.tysl.order.bean.MyOrderBean;
        import com.wokun.tysl.order.bean.ZhihuiGenBean;
        import com.wokun.tysl.order.bean.ZhihuiGenDetailBean;
        import com.wokun.tysl.order.bean.ZhihuiGenResponse;
        import com.wokun.tysl.order.bean.ZhihuiOrderBean;
        import com.wokun.tysl.order.bean.ZhihuiOrderDetaiBean;
        import com.wokun.tysl.order.bean.ZhihuiOrderResponse;
        import com.wokun.tysl.utils.EditInputView;


        import java.util.ArrayList;
        import java.util.List;

//跟进订单
public class FollowOrder extends SimpleRefreshAndLoadMoreFragment<ZhihuiGenBean> {
    private ZhihuiGenAdapter mAdapter;
    @Override
    public Request initRequest() {
        return  OkGo.<BaseResponse<ZhihuiGenResponse>>post(Constants.BASE_URL + Constants.RETAIL_STORE_ORDER)
                .tag(this)
                .params(Constants.TYPE, 2);
    }

    @Override
    public BaseQuickAdapter<ZhihuiGenBean, BaseViewHolder> initAdapter() {
        mAdapter = new ZhihuiGenAdapter(R.layout.item_zhihui_chuangke_body, R.layout.item_zhihui_chuangke_header, R.layout.item_zhihui_chuangke_footer, null);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                ZhihuiGenBean bean = (ZhihuiGenBean) adapter.getData().get(position);
                if(R.id.zhihui_dindan_genjin == view.getId()){//立即跟进
                    Log.e("23123e14","222222");
                    GoodsOrderMgr.getInstance().onGenjinState(FollowOrder.this,bean);
                }else if(R.id.zhihui_dindan_genjin2 == view.getId()) {//已跟进
                //    GoodsOrderMgr.getInstance().onGenjinState(FollowOrder.this,bean);
                    Log.e("23123gc14","222222");

                }
            }
        });


        return mAdapter;


    }

    @Override
    public void loadData(final boolean isRefresh) {

        mRequest.execute(new JsonCallback<BaseResponse<ZhihuiGenResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_ORDER) {
            @Override
            public void onSuccess(Response<BaseResponse<ZhihuiGenResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    ZhihuiGenResponse data = (ZhihuiGenResponse) body.getData();
                    if(data==null){return;}
                    List<ZhihuiGenBean> list = data.getOrder();
                    List<ZhihuiGenBean> newData = new ArrayList<>();
                    if (list != null) {
                        for (ZhihuiGenBean goodsOrder : list) {
                            newData.add(new ZhihuiGenBean(mAdapter.HEADER,goodsOrder.getStore_follow(),goodsOrder.getCreate_time()));

                          //  ArrayList<ZhihuiGenDetailBean> subBeanList = goodsOrder.getSubOrder();
                            ZhihuiGenDetailBean goods_info = goodsOrder.getGoods_info();


                            if (goods_info != null) {

                                    newData.add(new ZhihuiGenBean(mAdapter.BODY, goods_info));

                            }
                            newData.add(new ZhihuiGenBean(mAdapter.FOOTER, goodsOrder.getUsername(), goodsOrder.getMobile(), goodsOrder.getOrder_id()));
                        }
                    }
                    setData(isRefresh, newData);
                }
            }
        });

    }

}