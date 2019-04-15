package com.wokun.tysl.bankaccount.ui;

import android.text.TextUtils;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.ui.HistoryKehuActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.Test5;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class BankTestActivity2 extends BaseBindingActivity {
    @BindString(R.string.tysl_add_card)String title;
    @BindView(R.id.card_poople)
    EditText card_poople;
    @BindView(R.id.card_kaihu_place)
    EditText card_kaihu_place;
    @BindView(R.id.card_poople_place)
    EditText card_poople_place;
    @BindView(R.id.card_people_number)
    EditText card_people_number;


    @Override
    public int createView() {
        return R.layout.activity_zhihui_addcard;
    }

    @Override
    public WidgetBar createToolBar()  {
       return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {


    }

    private void lodeData() {
        String cardPoople = card_poople.getText().toString().trim();//持卡人
        String cardKaihuPlace = card_kaihu_place.getText().toString().trim();     //     开户支行
        String cardPooplePlace = card_poople_place.getText().toString().trim(); //  开户行
        String cardPeopleNumber = card_people_number.getText().toString().trim(); // 卡号
        if(TextUtils.isEmpty(cardPoople)) {
            RxToast.showShort("持卡人姓名不能为空");
                 return;
             }
        if(TextUtils.isEmpty(cardKaihuPlace))
        {
            RxToast.showShort("开户支行不能为空");
            return;
        }

        if(TextUtils.isEmpty(cardPooplePlace)) {
            RxToast.showShort("开户行不能为空");
            return;
        }

        if(Test5.checkBankCard(cardPeopleNumber)){
            RxToast.showShort("卡号不完整");

            return;
        }


        OkGo.<BaseResponse<String>>post(Constants.BASE_URL + Constants.RETAIL_ADD_BANKCARD)
                .tag(this)
                .params("realname",cardPoople)
                .params("bankname",cardPooplePlace)
                .params("bankbranch",cardKaihuPlace)
                .params("accountcode",cardPeopleNumber)
                .execute(new DialogCallback<BaseResponse<String>>(this,Constants.WITH_TOKEN,Constants.RETAIL_ADD_BANKCARD) {
                    @Override
                    public void onSuccess(Response<BaseResponse<String>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            RxToast.showShort(body.getMsg());

                        }
                    }
                });

    }
   @OnClick(R.id.action_submit)
        public void Sumbitcard() {
      //  startActivity(HistoryKehuActivity.class);

           lodeData();



    }
}
