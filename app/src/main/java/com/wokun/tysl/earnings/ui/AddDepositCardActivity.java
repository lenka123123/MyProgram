package com.wokun.tysl.earnings.ui;

import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTransparencyBindingActivity;
import com.wokun.tysl.earnings.adapter.SelectBankAdapter;
import com.wokun.tysl.earnings.controller.EarningsMgr;
import com.wokun.tysl.earnings.bean.Bank;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//添加储蓄卡账户
public class AddDepositCardActivity extends BaseTransparencyBindingActivity {

    @BindString(R.string.tysl_add_deposit_card)
    String title;
    @BindView(R.id.toolbar)
    WidgetBar widgetBar;
    @BindView(R.id.account_name)
    EditText accountName;
    @BindView(R.id.account_code)
    EditText accountCode;
    @BindView(R.id.deposit_bank)
    TextView depositBank;

    private BottomSheetDialog selectBank;
    private SelectBankAdapter mAdapter;

    private int bank_id;
    private String bank_name;

    @Override
    public int createView() {
        return R.layout.activity_add_deposit_card;
    }

    @Override
    public WidgetBar createToolBar() {
        return widgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        super.init();

        View selectBankView = UIUtil.createView(R.layout.layout_select_bank);
        RecyclerView mRecyclerView = (RecyclerView) selectBankView.findViewById(R.id.recycler_view);
        selectBank = new BottomSheetDialog(this);
        selectBank.setContentView(selectBankView);

        mAdapter = new SelectBankAdapter(R.layout.item_select_bank, null);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bank bank = (Bank) adapter.getData().get(position);
                bank_id = bank.getBank_id();
                bank_name = bank.getBank_name();
                depositBank.setText(bank_name);
                selectBank.dismiss();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        EarningsMgr.getInstance().getBank(mAdapter);
    }

    @OnClick({R.id.action_deposit_bank, R.id.action_immediately_add})
    public void action(View v) {
        if (R.id.action_deposit_bank == v.getId()) {//选择开户银行
            mAdapter.notifyDataSetChanged();
            selectBank.show();
        } else if (R.id.action_immediately_add == v.getId()) {//立即添加
            String account_name = accountName.getText().toString().trim();
            String account_code = accountCode.getText().toString().trim();
            EarningsMgr.getInstance().addBankAccount(this, account_name, account_code, bank_id, bank_name);
        }
    }
}
