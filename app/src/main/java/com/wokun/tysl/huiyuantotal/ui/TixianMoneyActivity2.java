package com.wokun.tysl.huiyuantotal.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.bankaccount.ui.BankTestActivity2;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.adapter.CardListadapter;
import com.wokun.tysl.huiyuantotal.bean.CardListResponse;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.adapter.MerchantAccountManagementAdapter3;
import com.wokun.tysl.smartretail.bean.OrderInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/26/026.
 */

public class TixianMoneyActivity2 extends BaseBindingActivity {
    @BindString(R.string.tysl_user_bank)String title;

    @BindView(R.id.recyclerView)
    RecyclerView recycler_view;

    @BindView(R.id.cd_addcard)
    TextView cd_addcard;

    private  CardListadapter mCardListadapter;
    @Override
    public int createView() {
        return R.layout.activity_merchant_account_huiyuan2;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        initList();
        loadDataListCard();
        cd_addcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(BankTestActivity2.class);
            }
        });

    }
    private void initList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(linearLayoutManager);
        mCardListadapter = new CardListadapter();
        recycler_view.setAdapter(mCardListadapter);


    }

    private void loadDataListCard() {

        OkGo.<BaseResponse<CardListResponse>>post(Constants.BASE_URL + Constants.RETAIL_BANK_LIST)
                .tag(this)
                .execute(new DialogCallback<BaseResponse<CardListResponse>>(this,Constants.WITH_TOKEN,Constants.RETAIL_BANK_LIST) {
                    @Override
                    public void onSuccess(Response<BaseResponse<CardListResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            RxToast.showShort(body.getMsg());
                            CardListResponse cardlist= (CardListResponse) body.getData();
                            mCardListadapter.setData(cardlist.getBank_data());
                            Log.e("进来来dsa22","进来来dsa22");
                        }
                    }
                });
    }
  /*  @OnClick(R.id.zhihui_card_bangdin)
    public void CardBangdi() {
        startActivity(BankTestActivity2.class);
    }*/
}
