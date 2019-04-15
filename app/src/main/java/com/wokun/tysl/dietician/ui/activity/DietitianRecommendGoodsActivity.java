package com.wokun.tysl.dietician.ui.activity;

import android.support.v4.app.Fragment;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseTabActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.ui.fragment.DietitianRecommendableGoods;
import com.wokun.tysl.dietician.ui.fragment.DietitianRecommendedGoods;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;

//推荐商品
public class DietitianRecommendGoodsActivity extends BaseTabActivity {

    @BindString(R.string.tysl_user_recommend_goods)
    String title;

    @BindString(R.string.tysl_state_recommendable_goods)
    String recommendableGoods;
    @BindString(R.string.tysl_state_recommended_goods)
    String recommendedGoods;

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected int initState() {
        return getIntent().getIntExtra(Constants.STATE,0);
    }

    @Override
    protected String initTitle() {
        return null;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }


    @Override
    protected List<String> initTabTitles() {
        mTitles = new ArrayList<>();
        mTitles.add(recommendableGoods);
        mTitles.add(recommendedGoods);
        return mTitles;
    }

    @Override
    protected List<Fragment> initFragments() {
        mFragments = new ArrayList<>();
        mFragments.add(new DietitianRecommendableGoods());
        mFragments.add(new DietitianRecommendedGoods());
        return mFragments;
    }
}