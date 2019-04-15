package com.wokun.tysl.smartretail.ui.activity;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.base.Request;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.adapter.IdCartInfoAdapter;
import com.wokun.tysl.smartretail.bean.IdCartInfoBean;
import com.wokun.tysl.smartretail.bean.SmartRetailListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 银行卡信息管理
 */

public class IdCartInfoMgrActivity extends SimpleRefreshAndLoadMoreActivity<IdCartInfoBean> {

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<SmartRetailListBean>>post(Constants.BASE_URL + Constants.INTEGRAL_GET_EXCHANGE_RECORD_URL).tag(this);
    }

    @Override
    public BaseQuickAdapter<IdCartInfoBean, BaseViewHolder> initAdapter() {
        return new IdCartInfoAdapter(R.layout.item_id_cart_info_mgr,null);
    }

    @Override
    public void loadData(boolean isRefresh) {
        mMultipleStatusView.showLoading();
        List<IdCartInfoBean> list = new ArrayList<>();
        IdCartInfoBean bean1 = new IdCartInfoBean();
        bean1.setIdCartName("中国工商银行");
        bean1.setIdCartNumber("3456456456456456");
        bean1.setUserName("杨兵兵");
        IdCartInfoBean bean2 = new IdCartInfoBean();
        bean2.setIdCartName("中国农业银行");
        bean2.setIdCartNumber("234464575757573457");
        bean2.setUserName("李冰冰");
        list.add(bean1);
        list.add(bean2);
        mMultipleStatusView.showContent();
        setData(true,list);
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("银行卡信息管理")
                .setMenu("+新增银行卡",null)
                .setMenuTextColor(UIUtil.getColor(R.color.colorPrimary))
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(AccountNumberActivity.class);
                    }
                }, null);
    }
}