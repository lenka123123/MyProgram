package com.wokun.tysl.ucenter.ui;

import android.view.View;

import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.ArticleFavoritesActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.dietician.ui.activity.DieticianFavoritesActivity;
import com.wokun.tysl.goods.ui.activity.GoodsFavoritesActivity;
import com.wokun.tysl.store.ui.StoreFavoritesActivity;

import butterknife.BindString;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/4/004.
 */

public class ShouCangActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_goods_shoucang)String title;

    @Override
    public int createView() {
        return R.layout.activity_ucenter_shoucang;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }

    /** 查看收藏 */
    @OnClick({R.id.action_check_goods_favorites,
            R.id.action_check_shop_favorites,
            R.id.action_check_article_favorites,
            R.id.action_check_dietician_favorites})
    public void actionCheckFavorites(View view){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_check_goods_favorites == view.getId()){
            startActivity(GoodsFavoritesActivity.class, !isNeedLogin);//查看商品收藏
        } else if(R.id.action_check_shop_favorites == view.getId()){
            startActivity(StoreFavoritesActivity.class, !isNeedLogin);//查看店铺收藏
        } else if(R.id.action_check_article_favorites == view.getId()){
            startActivity(ArticleFavoritesActivity.class, !isNeedLogin);//查看文章收藏
        } else if(R.id.action_check_dietician_favorites == view.getId()){
            startActivity(DieticianFavoritesActivity.class, !isNeedLogin);//查看营养师收藏
        }
    }
}
