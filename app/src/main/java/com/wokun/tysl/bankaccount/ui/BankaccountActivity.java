package com.wokun.tysl.bankaccount.ui;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class BankaccountActivity extends SimpleRefreshAndLoadMoreActivity {
    @Override
    public Request initRequest() {
        return null;
    }

    @Override
    public BaseQuickAdapter initAdapter() {
        return null;
    }

    @Override
    public void loadData(boolean isRefresh) {

    }

    @Override
    public WidgetBar createToolBar() {
        return null;
    }
}
