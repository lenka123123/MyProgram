package com.wokun.tysl.shopcart;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shantoo.common.utils.ResourceUtil;
import com.shantoo.common.utils.UIUtil;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseFragment1;

import butterknife.BindView;


public class ShopCartFragment1 extends BaseFragment1 {

    @BindView(R.id.status_bar)View statusBar;

    @Override
    public int createView() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    public void initViews() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);
        //statusBar.setBackgroundColor(UIUtil.getColor(R.color.colorPrimary));
    }

    @Override
    public void loadData() {

    }
}