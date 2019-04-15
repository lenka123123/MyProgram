package com.wokun.tysl.servicein.ui;

import android.view.View;
import android.widget.LinearLayout;

import com.lzy.okgo.OkGo;
import com.shantoo.common.utils.StatusBarUtil;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;

import butterknife.BindString;
import butterknife.BindView;

//服务入驻结果页面
public class ServiceAuthenticationResultActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_service_authentication_result)String title;
    @BindView(R.id.service_authentication_success)LinearLayout serviceAuthenticationSuccess;
    @BindView(R.id.service_authentication_checking)LinearLayout serviceAuthenticationChecking;
    @BindView(R.id.service_authentication_not_pass)LinearLayout serviceAuthenticationNotPass;

    private int current_state = RESULT_SUCCESS;
    private final static int RESULT_SUCCESS = 0xfffff1;
    private final static int RESULT_CHECKING = 0xfffff2;
    private final static int RESULT_NOT_PASS = 0xfffff3;

    @Override
    public int createView() {
        return R.layout.activity_service_authentication_result;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        StatusBarUtil.setColor(this, UIUtil.getColor(R.color.colorWhite));
        switch (current_state){
            case RESULT_SUCCESS:
                serviceAuthenticationSuccess.setVisibility(View.VISIBLE);
                serviceAuthenticationChecking.setVisibility(View.GONE);
                serviceAuthenticationNotPass.setVisibility(View.GONE);
                break;
            case RESULT_CHECKING:
                serviceAuthenticationSuccess.setVisibility(View.GONE);
                serviceAuthenticationChecking.setVisibility(View.VISIBLE);
                serviceAuthenticationNotPass.setVisibility(View.GONE);
                break;
            case RESULT_NOT_PASS:
                serviceAuthenticationSuccess.setVisibility(View.GONE);
                serviceAuthenticationChecking.setVisibility(View.GONE);
                serviceAuthenticationNotPass.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}
