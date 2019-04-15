package com.wokun.tysl.smartretail.ui.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter1;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter2;
import com.wokun.tysl.smartretail.bean.BusinessData;
import com.wokun.tysl.smartretail.bean.OrderInfoBean;
import com.wokun.tysl.smartretail.bean.StoreManageBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 商家管理主界面
 */

public class MerchantAccountManagementActivity extends BaseBindingActivity {

    @BindView(R.id.recycler_view1)RecyclerView mRecyclerView1;
    @BindView(R.id.recycler_view2)RecyclerView mRecyclerView2;

    private String storeNo; //店铺编号
    private String amountAvailable; //可提金额
    private String cumulativeIncome; //未结算收益
    private String todayTurnover; //今日营业额
    private String todayProfit; //今日收益

    @Override
    public int createView() {
        return R.layout.activity_merchant_account_management;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("商家管理主界面");
    }

    @Override
    public void init() {
        List<BusinessData> list = new ArrayList<>();
        BusinessData bean1 = new BusinessData();
        bean1.setCount("¥ 0.00");
        bean1.setTitle("今日营业额");
        BusinessData bean2 = new BusinessData();
        bean2.setCount("0");
        bean2.setTitle("累计会员数");
        BusinessData bean3 = new BusinessData();
        bean3.setCount("0");
        bean3.setTitle("今日订单数");
        BusinessData bean4 = new BusinessData();
        bean4.setCount("¥ 0.00");
        bean4.setTitle("今日收益");
        BusinessData bean5 = new BusinessData();
        bean5.setCount("¥ 0.00");
        bean5.setTitle("累计收益");
        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        mRecyclerView1.setNestedScrollingEnabled(false);
        mRecyclerView1.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView1.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.VERTICAL, R.color.colorLine));
        mRecyclerView1.addItemDecoration(new MItemDecoration(this, DividerItemDecoration.HORIZONTAL, R.color.colorLine));
        mRecyclerView1.setAdapter(new MerchantAccountManagementAdapter1(R.layout.item_merchant_account_managerment1,list));

        List<OrderInfoBean> list2 = new ArrayList<>();
        OrderInfoBean bean6 = new OrderInfoBean();
        bean6.setUserName("不曾逗留 435345345");
        bean6.setTotalPrice("147.00");
        bean6.setProductName("酵素");
        bean6.setProductPrice("1553x1");
        bean6.setProductEarnings("14.54");
        OrderInfoBean bean7 = new OrderInfoBean();
        bean7.setUserName("不曾逗留2 435345345");
        bean7.setTotalPrice("147.00");
        bean7.setProductName("酵素3");
        bean7.setProductPrice("1553x12");
        bean7.setProductEarnings("122.12");
        OrderInfoBean bean8 = new OrderInfoBean();
        bean8.setUserName("不曾逗留3 435345345");
        bean8.setTotalPrice("147.00");
        bean8.setProductName("酵素2");
        bean8.setProductPrice("1553x1");
        bean8.setProductEarnings("23.54");
        list2.add(bean6);
        list2.add(bean7);
        list2.add(bean8);
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView2.addItemDecoration(new MItemDecoration(this));
        mRecyclerView2.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView2.setAdapter(new MerchantAccountManagementAdapter2(R.layout.item_merchant_account_managerment2,list2));


    }

    private void loadData(){
        String storeCode = getIntent().getStringExtra(Constants.STORE_CODE);

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
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }
}