package com.wokun.tysl.smartretail.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.bankaccount.ui.BankTestActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.ui.ChuangkeToyalActivity;
import com.wokun.tysl.huiyuantotal.ui.ErWeiMaTotalActivity;
import com.wokun.tysl.huiyuantotal.ui.HistoryKehuActivity;
import com.wokun.tysl.huiyuantotal.ui.HuiyuanTotalActivity;
import com.wokun.tysl.huiyuantotal.ui.TixianMoneyActivity;
import com.wokun.tysl.huiyuantotal.ui.TixianMoneyActivity2;
import com.wokun.tysl.huiyuantotal.ui.TixianTotalActivity;
import com.wokun.tysl.kucuntotal.ui.KucunTotalActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.order.ui.MyOrderActivity;
import com.wokun.tysl.smartretail.bean.StoreManageBean;
import com.wokun.tysl.stock.ui.InventoryActivity;
import com.wokun.tysl.utils.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/24/024.
 */

public class MechantAccountMangermentNewActivity extends BaseBindingActivity {
    private    StoreManageBean data;

    @BindView(R.id.zhihui_shopname)
    TextView zhihui_shopname;
    @BindView(R.id.user_head_img)
    RoundedImageView user_head_img;

    @BindView(R.id.store_number)
    TextView store_number;
    @BindView(R.id.last_money)
    TextView last_money;
    @BindView(R.id.last_daijiesuanmoney)
    TextView last_daijiesuanmoney;

    @BindView(R.id.today_turnover)
    TextView today_turnover;
    @BindView(R.id.today_Profit)
    TextView today_Profit;

    @BindView(R.id.zhihui_show_chuangke)
    RelativeLayout zhihui_show_chuangke;

    @BindView(R.id.zhihui_show_erweima)
    RelativeLayout zhihui_show_erweima;
   private  String storeCode;


    @Override
    public int createView() {
        return R.layout.activity_merchant_account_newmanager;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("终端管理");
    }

    @Override
    public void init() {
       loadData();




    }

    private void loadData() {

       storeCode = getIntent().getStringExtra(Constants.STORE_CODE);

        if(TextUtils.isEmpty(storeCode)){
            RxToast.showShort("store_code 未找到");
            return;
        }

        OkGo.<BaseResponse<StoreManageBean>>post(Constants.BASE_URL + Constants.RETAIL_STORE_MANAGE_URL)
                .tag(this)
                .params(Constants.STORE_CODE, storeCode)
                .execute(new DialogCallback<BaseResponse<StoreManageBean>>(this,Constants.WITH_TOKEN,Constants.RETAIL_STORE_MANAGE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<StoreManageBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                          //  RxToast.showShort(body.getMsg());
                           data = (StoreManageBean) body.getData();
                            zhihui_shopname.setText(data.getStoreName());
                            ImageLoader.loadImage(data.getStorePicture(),user_head_img);
                            store_number.setText("No."+data.getStoreNo());
                            last_money.setText("余额：￥"+data.getAmountAvailable());
                            last_daijiesuanmoney.setText("待结算：￥"+data.getCumulativeIncome());

                            today_turnover.setText("￥"+data.getTodayTurnover());
                            today_Profit.setText("￥"+data.getTodayProfit());

                            if(data.getRole_type()==1){
                                zhihui_show_chuangke.setVisibility(View.GONE);
                                zhihui_show_erweima.setVisibility(View.GONE);
                            }
                        }
                    }
                });
    }

    /** 会员管理 */
    @OnClick(R.id.zhihui_show_huiyuan)
    public void ShowHuiyuan() {
        Intent intent2 = new Intent(MechantAccountMangermentNewActivity.this, HuiyuanTotalActivity.class);
        intent2.putExtra(Constants.STORE_CODE,storeCode);
        startActivity(intent2);
    }

    /** 库存管理 */
    @OnClick(R.id.zhihui_show_kucun)
    public void ShowKuncun() {
        startActivity(InventoryActivity.class);
    }

    /** 订单管理 */
    @OnClick(R.id.zhihui_show_dindan)
    public void ShowDindan() {
        startActivity(MyOrderActivity.class);
    }
    /** 去提现 */
    @OnClick(R.id.zhihui_show_qutixian)
    public void ShowQuTixian() {
        startActivity(TixianMoneyActivity.class);
    }

    /** 提现明细 */
    @OnClick(R.id.zhihui_show_tixian)
    public void ShowTiXian() {
        startActivity(BankTestActivity.class);
    }
    /** 创客二维码 */
    @OnClick(R.id.zhihui_show_erweima)
    public void ShowErWeiMa() {
        startActivity(ErWeiMaTotalActivity.class);
    }
    /** 创客管理 */
    @OnClick(R.id.zhihui_show_chuangke)
    public void ShowChuangke() {
        Intent intent = new Intent(MechantAccountMangermentNewActivity.this,ChuangkeToyalActivity.class);
        intent.putExtra(Constants.STORE_CODE,storeCode);

        startActivity(intent);
    }
/*    *//** 意向客户 *//*
    @OnClick(R.id.zhihui_show_yixiangkehu)
    public void ShowYixiangkehu() {
        startActivity(HistoryKehuActivity.class);
    }*/
    /** 提现账号管理 */
    @OnClick(R.id.zhihui_show_addcard)
    public void ShowAddcard() {
        startActivity(TixianMoneyActivity2.class);
    }
    /** 店铺首页 */
    @OnClick(R.id.zhihui_show_shop)
    public void Showshop() {
//        Intent intent = new Intent(MechantAccountMangermentNewActivity.this, SmartRetailStorageRackActivity1.class);
//        intent.putExtra(Constants.STORE_CODE,storeCode);
//        startActivity(intent);
    }

}
