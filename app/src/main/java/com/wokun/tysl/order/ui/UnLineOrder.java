package com.wokun.tysl.order.ui;
       import android.app.AlertDialog;
       import android.content.Intent;
       import android.util.Log;
       import android.view.LayoutInflater;
       import android.view.View;
       import android.widget.Button;
       import android.widget.ImageView;
       import android.widget.Toast;

       import com.chad.library.adapter.base.BaseQuickAdapter;
        import com.chad.library.adapter.base.BaseViewHolder;
        import com.lzy.okgo.OkGo;
       import com.lzy.okgo.model.Response;
       import com.lzy.okgo.request.base.Request;
        import com.shantoo.widget.toast.RxToast;
        import com.wokun.tysl.R;
        import com.wokun.tysl.TyslApp;
        import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
       import com.wokun.tysl.callback.JsonCallback;
       import com.wokun.tysl.config.Constants;
        import com.wokun.tysl.dietician.bean.ServiceOrderBean;
        import com.wokun.tysl.goods.ui.activity.GoodsOrderDetailActivity;
        import com.wokun.tysl.model.response.BaseResponse;
        import com.wokun.tysl.model.response.OrderListResponse;
       import com.wokun.tysl.order.adapter.MyOrderServiceAdapter;
       import com.wokun.tysl.order.adapter.ZhihuiOrderAdapter;
       import com.wokun.tysl.order.bean.MyOrderBean;
       import com.wokun.tysl.order.bean.ZhihuiOrderBean;
       import com.wokun.tysl.order.bean.ZhihuiOrderDetaiBean;
       import com.wokun.tysl.order.bean.ZhihuiOrderResponse;


       import java.util.ArrayList;
       import java.util.List;

//自提订单
public class UnLineOrder extends SimpleRefreshAndLoadMoreFragment<ZhihuiOrderBean> {
    private ZhihuiOrderAdapter mAdapter;
    private  String  inputContent;
    private  String  pass;
    private        List<ZhihuiOrderBean> list;
    @Override
    public Request initRequest() {
        return  OkGo.<BaseResponse<ZhihuiOrderResponse>>post(Constants.BASE_URL + Constants.RETAIL_STORE_ORDER)
                .tag(this)
                .params(Constants.TYPE, 1);
    }

    @Override
    public BaseQuickAdapter<ZhihuiOrderBean, BaseViewHolder> initAdapter() {
        mAdapter = new ZhihuiOrderAdapter(R.layout.item_zhihui_chuangke_body, R.layout.item_zhihui_chuangke_header, R.layout.item_zhihui_chuangke_footer, null);
         mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
           @Override
           public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
               ZhihuiOrderBean bean = (ZhihuiOrderBean) adapter.getData().get(position);
               if(R.id.zhihui_dindan_time2 == view.getId()){//
                   Log.e("23214","222222");
                   Toast.makeText(getContext(), "已完成", Toast.LENGTH_SHORT).show();


                    }  if(R.id.zhihui_dindan_time1 == view.getId()){//确认自提
                   showpayDialog(bean);

               }




           }
       });
        return mAdapter;
    }

    private void showpayDialog(final ZhihuiOrderBean bean) {
        AlertDialog.Builder customizeDialog = new AlertDialog.Builder(getContext());
        View dialogView = LayoutInflater.from(getContext())
                .inflate(R.layout.pop_layout3, null);
        customizeDialog.setView(dialogView);
        final AlertDialog myDialog = customizeDialog.show();
        ImageView delete_pop = (ImageView) dialogView.findViewById(R.id.delete_pop);
        Button actionSubmit = (Button) dialogView.findViewById(R.id.btnCommit);
        //    EditInputView edit1 = (EditInputView)dialogView.findViewById(R.id.edit1);
        final VerificationCodeView icv = (VerificationCodeView) dialogView.findViewById(R.id.icv);
        delete_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });


        icv.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                inputContent = icv.getInputContent();
                if (inputContent.length() == 6) {
                    pass = inputContent;
                }
                Log.i("icv_input", icv.getInputContent());
            }

            @Override
            public void deleteContent() {
                Log.i("icv_delete", icv.getInputContent());
            }
        });
        actionSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();

                 comitData(bean);
            }
        });


    }

    private void comitData( ZhihuiOrderBean bean) {
        Log.e("self_lifting_code", pass);
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.RETAIL_SELF_LIFTING)
                .tag(this)
                .params(Constants.ORDER_ID, bean.getOrder_id())
                .params("self_lifting_code",pass)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.RETAIL_SELF_LIFTING) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            RxToast.showShort(body.getMsg());
                        }

                    }
                });






    }


    @Override
    public void loadData(final boolean isRefresh) {

        mRequest.execute(new JsonCallback<BaseResponse<ZhihuiOrderResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_STORE_ORDER) {
            @Override
            public void onSuccess(Response<BaseResponse<ZhihuiOrderResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    ZhihuiOrderResponse data = (ZhihuiOrderResponse) body.getData();
                    if(data==null){return;}
                      list = data.getOrder();

                    List<ZhihuiOrderBean> newData = new ArrayList<>();
                    if (list != null) {
                        for (ZhihuiOrderBean goodsOrder : list) {
                            newData.add(new ZhihuiOrderBean(mAdapter.HEADER,goodsOrder.getOrder_sn(),goodsOrder.getCreate_time(),goodsOrder.getOrder_state()));

                            ArrayList<ZhihuiOrderDetaiBean> subBeanList = goodsOrder.getSubOrder();
                            if (subBeanList != null) {
                                for (ZhihuiOrderDetaiBean goods : subBeanList) {
                                    newData.add(new ZhihuiOrderBean(mAdapter.BODY, goods));
                                }
                            }
                            newData.add(new ZhihuiOrderBean(mAdapter.FOOTER, goodsOrder.getUsername(), goodsOrder.getMobile(), goodsOrder.getOrder_id(),goodsOrder.getNums(),goodsOrder.getStore_order_reward()));
                        }
                    }
                    setData(isRefresh, newData);
                }
            }
        });

    }
}