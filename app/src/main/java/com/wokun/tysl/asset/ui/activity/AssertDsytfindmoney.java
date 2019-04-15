package com.wokun.tysl.asset.ui.activity;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.AppManager;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.bean.DsytFindMoneyBean;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.ui.activity.DieticianIndexActivity;
import com.wokun.tysl.dietician.ui.activity.DietitianServiceOrderActivity;
import com.wokun.tysl.dietician.ui.fragment.DietitianServiceOrderAll;
import com.wokun.tysl.goods.ui.activity.GoodsOrderActivity;
import com.wokun.tysl.main.MainActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.ucenter.ui.MyApplyForActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\11\23 0023.
 * 发现余额
 */

public class AssertDsytfindmoney  extends BaseBindingActivity{
    @BindView(R.id.find_txt_yaoqin)
    TextView findTxt_yaoqin;
    @BindView(R.id.find_txt_buyservice)
    TextView findTxt_buyservice;
    @BindView(R.id.find_txt_pinjia)
    TextView findTxt_pinjia;
    @BindView(R.id.find_txt_service_pinjia)
    TextView findTxt_service_pinjia;
    @BindView(R.id.find_txt_share)
    TextView findTxt_share;
    @BindView(R.id.find_txt_shop)
    TextView findTxt_shop;

    @Override
    public int createView() {
        return R.layout.activity_dsyt_findmoney;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("");
    }

    @Override
    public void init() {
          loadData();
    }

    private void loadData() {
        OkGo.<BaseResponse<DsytFindMoneyBean>>post(Constants.BASE_URL + Constants.INTEGRAL_FIND_MONEY)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<DsytFindMoneyBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<DsytFindMoneyBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        DsytFindMoneyBean data = (DsytFindMoneyBean) body.getData();
                        if(data==null)return;
                        Log.e("数据",data.toString());
                        findTxt_yaoqin.setText("+"+data.getInvite());
                        findTxt_buyservice.setText("+"+data.getBuy_service());
                        findTxt_pinjia.setText("+"+data.getGoods_eval());
                        findTxt_service_pinjia.setText("+"+data.getService_eval());
                        findTxt_share.setText("+"+data.getShare());
                        findTxt_shop.setText(data.getBuy_goods());
                    }
                });
    }
    /**
     * 邀请注册
     */
    @OnClick({R.id.find_yaoqin})
    public  void  actionFindYaoqin(){
        startActivity(DsytYaoqingActivity.class);

    }
    /**
     * 商城购物
     */
    @OnClick({R.id.find_shop})
    public  void  actionFindShop(){
     //   Intent intent = new Intent(AssertDsytfindmoney.this, MainActivity.class);
    //     startActivity(MainActivity.class);
        AppManager.getAppManager().finishAllActivity();
        TyslApp.getInstance().setRefreshShopCart(true);
        TyslApp.getInstance().setTabPosition(Constants.TAB_POSITION_ZB);

    }
    /**
     * 商品评价
     */
    @OnClick({R.id.find_pinjia})
    public  void  actionFindPinjia(){
        Intent intent = new Intent(AssertDsytfindmoney.this, GoodsOrderActivity.class);
        intent.putExtra(Constants.STATE, 0);//查看全部商品订单
        startActivity(intent);
    }
    /**
     * 购买服务
     */
    @OnClick({R.id.find_bugservice})
    public  void  actionFindBuyservice(){
       startActivity(DieticianIndexActivity.class);
    }

    /**
     * 服务评价
     */
    @OnClick({R.id.find_service_pinjia})
    public  void  actionFindServicePinjia(){
     /*   Intent intent = new Intent(AssertDsytfindmoney.this, DietitianServiceOrderActivity.class);
        intent.putExtra(Constants.STATE, 0);//查看全部服务订单
        startActivity(intent);*/

        startActivity(MyApplyForActivity.class);
    }
    /**
     * 分享
     */
    @OnClick({R.id.find_share})
    public  void  actionFindShare(){

    }


}
