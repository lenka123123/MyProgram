package com.wokun.tysl.smartretail.ui.fragment;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.request.base.Request;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.smartretail.bean.PeopleNumberBean;

/**
 * 智慧零售-会员管理-今日人数
 */

public class TodayFragment extends SimpleRefreshAndLoadMoreFragment<PeopleNumberBean> {

    @Override
    public Request initRequest() {
        return null;
    }

    @Override
    public BaseQuickAdapter<PeopleNumberBean, BaseViewHolder> initAdapter() {
        return null;
    }

    @Override
    public void loadData(boolean isRefresh) {

    }
}
