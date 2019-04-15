package com.wokun.tysl.order.ui;

import android.support.v4.app.Fragment;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTabActivity;
import com.wokun.tysl.config.Constants;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;

/**
 * Created by xl on 2018/7/24.
 * 订单管理
 */

public class MyOrderActivity extends BaseTabActivity {


	@BindString(R.string.tysl_lead_order)
	String title;
	@BindString(R.string.tysl_line_order)
	String lineOrder;
	@BindString(R.string.tysl_follow_order)
	String followOrder;
	@BindString(R.string.tysl_unline_order)
	String unlineOrder;

	@Override
	protected int initState() {
		return getIntent().getIntExtra(Constants.STATE, 0);
	}

	@Override
	protected String initTitle() {
		return title;
	}

	@Override
	protected List<String> initTabTitles() {
		mTitles = new ArrayList<>();
		mTitles.add(lineOrder);
		mTitles.add(unlineOrder);
		mTitles.add(followOrder);

		return mTitles;
	}
	@Override
	public WidgetBar createToolBar() {
		return mWidgetBar.setWidgetBarTitle(title);
	}


	@Override
	protected List<Fragment> initFragments() {
		mFragments = new ArrayList<>();
		mFragments.add(new LineOrder());
		mFragments.add(new UnLineOrder());
		mFragments.add(new FollowOrder());
		return mFragments;
	}
}
