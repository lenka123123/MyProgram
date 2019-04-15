package com.wokun.tysl.asset;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

public class AssetMgr {

    private AssetMgr(){

    }

    private static class AssetMgrHolder{
        private static AssetMgr instance = new AssetMgr();
    }

    public static AssetMgr getInstance(){
        return AssetMgrHolder.instance;
    }

    /** 我要买入操作*/
    public void doMyBuy(final SimpleRefreshAndLoadMoreFragment tag, String r_id, String numbers, String unit_price){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ASSETS_DO_MY_BUY_URL)
                .params("r_id",r_id)
                .params("numbers",numbers)
                .params("unit_price",unit_price)
                .tag(tag)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ASSETS_DO_MY_BUY_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            RxToast.showShort(body.getMsg());
                           // tag.doOnRefreshData();
                        }
                    }
                });
    }

    /** 我要出售操作*/
    public void doMySell(final SimpleRefreshAndLoadMoreFragment tag, String r_id, String numbers, String unit_price){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ASSETS_DO_MY_SELL_URL)
                .params("r_id",r_id)
                .params("numbers",numbers)
                .params("unit_price",unit_price)
                .tag(tag)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ASSETS_DO_MY_SELL_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            RxToast.showShort(body.getMsg());
                            //tag.doOnRefreshData();
                        }
                    }
                });
    }



}
