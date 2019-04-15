package com.wokun.tysl.earnings.ui;

import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTransparencyBindingActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.earnings.adapter.AccountListAdapter;
import com.wokun.tysl.earnings.controller.EarningsMgr;
import com.wokun.tysl.earnings.bean.AccountList;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//账户列表
public class AccountListActivity extends BaseTransparencyBindingActivity {

    @BindString(R.string.tysl_account_list)
    String title;
    @BindView(R.id.toolbar)
    WidgetBar widgetBar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public int createView() {
        return R.layout.activity_account_settings;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        super.init();
        AccountListAdapter adapter = new AccountListAdapter(R.layout.item_account_list, null);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                AccountList.AccountListBean bean = (AccountList.AccountListBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.ACCOUNT_ID, bean.getAccount_id());
                intent.putExtra(Constants.ACCOUNT_TYPE_NAME, bean.getAccount_type_name());
                intent.putExtra(Constants.ACCOUNT_CODE, bean.getAccount_code());
                intent.putExtra(Constants.ACCOUNT_TYPE, bean.getAccount_type());
                setResult(88,intent);
                //intent.setClass(AccountListActivity.this, WithdrawDepositActivity.class);
                //AppManager.getAppManager().finishActivity(AccountListActivity.class);
                finish();
            }
        });

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        EarningsMgr.getInstance().getAccountList(adapter);
    }

    @OnClick({R.id.action_deposit_card, R.id.action_alipay})
    public void action(View view) {
        if(R.id.action_deposit_card == view.getId()){//添加储蓄卡账号页面
            startActivity(AddDepositCardActivity.class);
        }else if(R.id.action_alipay == view.getId()){//添加支付宝账号页面
            startActivity(AddAlipayAccountActivity.class);
        }
    }
}
