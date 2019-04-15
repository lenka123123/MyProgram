package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.OnSearchListener;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.CategoryAdapter;
import com.wokun.tysl.goods.adapter.SubCategoryAdapter;
import com.wokun.tysl.goods.bean.SubCategoryBean;
import com.wokun.tysl.goods.bean.TopCategoryBean;
import com.wokun.tysl.goods.response.SubCategoryResponse;
import com.wokun.tysl.goods.response.TopCategoryResponse;
import com.wokun.tysl.goods.ui.fragment.CategoryFragment;
import com.wokun.tysl.model.bean.SubBean;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

import static com.wokun.tysl.TyslApp.getContext;

//商品分类页面
public class GoodsCategoryActivity extends BaseBindingActivity {

  @BindView(R.id.tab_layout)
    VerticalTabLayout tabLayout;

    @Override
    public int createView() {
        return R.layout.activity_goods_category;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
            .showSearchView()
            .setOnSearchListener(new OnSearchListener() {
                @Override
                public void onSearch() {
                    String keywords = mWidgetBar.getSearchView().getText().toString().trim();
                    Intent intent = new Intent();
                    intent.setClass(GoodsCategoryActivity.this, GoodsListActivity.class);
                    intent.putExtra(Constants.KEYWORDS, keywords);
                    startActivity(intent);
                }
        });
    }

    @Override
    public void init() {
        getTopCategory();
    }

    private void initTab(List<TopCategoryBean> list) {
        List<Fragment> fragments = getFragments(list);
        tabLayout.setupWithFragment(getSupportFragmentManager(), R.id.fragment_container, fragments, new CategoryAdapter(list));
        tabLayout.setTabSelected(0);
    }

    private List<Fragment> getFragments(List<TopCategoryBean> list) {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            CategoryFragment fragment = new CategoryFragment();
            Bundle bundle = new Bundle();
            bundle.putString("gc_id", list.get(i).getGcId());
            bundle.putString("gc_name", list.get(i).getGcName());
            fragment.setArguments(bundle);
            fragments.add(fragment);
        }
        return fragments;
    }

    //获取商品一级分类   //向服务器发送请求首先获取一级分类列表信息
    public void getTopCategory() {
        mMultipleStatusView.showLoading();
        OkGo.<BaseResponse<TopCategoryResponse>>post(Constants.BASE_URL + Constants.TOP_CATEGORY_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<TopCategoryResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<TopCategoryResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            mMultipleStatusView.showContent();
                            TopCategoryResponse data = (TopCategoryResponse) body.getData();
                            List<TopCategoryBean> list = data.getTopCategory();
                            if (list != null && list.size() > 0) {
                                initTab(list);
                            }
                        }
                    }
            });
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
      OkGo.getInstance().cancelTag(this);
    }
}