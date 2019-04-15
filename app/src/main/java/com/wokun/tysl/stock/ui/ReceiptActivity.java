package com.wokun.tysl.stock.ui;

import android.support.v4.app.Fragment;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTabActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.stock.fragnent.CurrentReceipt;
import com.wokun.tysl.stock.fragnent.HistoryReceipt;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;

public class ReceiptActivity extends BaseTabActivity {
	//activity_receipt

	@BindString(R.string.tysl_lead_order)
	String title;
	@BindString(R.string.tysl_order_current_receipt)
	String currentReceipt;
	@BindString(R.string.tysl_order_history_receipt)
	String historyReceipt;

	@Override
	protected int initState() {
		return getIntent().getIntExtra(Constants.STATE, 0);
	}

	@Override
	protected String initTitle() {
		return title;
	}
	@Override
	public WidgetBar createToolBar() {
		return mWidgetBar.setWidgetBarTitle(title);
	}

	@Override
	protected List<String> initTabTitles() {
		mTitles = new ArrayList<>();
		mTitles.add(currentReceipt);
		mTitles.add(historyReceipt);
		return mTitles;
	}

	@Override
	protected List<Fragment> initFragments() {
		mFragments = new ArrayList<>();
		mFragments.add(new CurrentReceipt());
		mFragments.add(new HistoryReceipt());
		return mFragments;
	}
}
