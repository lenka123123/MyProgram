package com.wokun.tysl.serviceinto.ui;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.store.MerchantMgr;
import com.wokun.tysl.ucenter.ui.SettingsActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/10/010.
 * 商家入驻
 */

public class ServiceShangjiaActivity  extends BaseBindingActivity{
    @BindString(R.string.tysl_edit_shangjia)String title;
    @BindView(R.id.in_name)
    EditText in_name;//真实姓名
    @BindView(R.id.in_number)
    EditText in_number;//真实姓名

    @BindView(R.id.in_youxiang)
    EditText in_youxiang;//真实姓名


    @Override
    public int createView() {
        return R.layout.activity_service_shangjia;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.action_submit)
    public void actionSubmit(View v) {
        Log.e("输入的资料","进来了");
        String mInname = in_name.getText().toString().trim();
        String mInnumber = in_number.getText().toString().trim();
        String mInyouxiang = in_youxiang.getText().toString().trim();

         //   loadMessage();
        MerchantMgr.getInstance().doJoinBusiness2(mInname,mInnumber,mInyouxiang);
    }

    private void loadMessage() {
        String mInname = in_name.getText().toString().trim();
        String mInnumber = in_number.getText().toString().trim();
        String mInyouxiang = in_youxiang.getText().toString().trim();
        Log.e("输入的资料",mInname+mInnumber+mInyouxiang);
        if(TextUtils.isEmpty(mInnumber)){
            RxToast.showShort(Constants.NULL_MOBILE_MESSAGE);
            return;
        }
        if(TextUtils.isEmpty(mInname)){
            RxToast.showShort("姓名不能为空");
            return;
        }
        if(TextUtils.isEmpty(mInyouxiang)){
            RxToast.showShort("邮箱不能为空");
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.JOIN_BUSINESS_URL)
                .tag(this)
                .params("name",mInname)
                .params("mobile",mInnumber)
                .params("email",mInyouxiang)
                .execute(new JsonCallback<BaseResponse<Object>>()
                {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            RxToast.showShort(body.getMsg());
                        } else {
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });




    }


}
