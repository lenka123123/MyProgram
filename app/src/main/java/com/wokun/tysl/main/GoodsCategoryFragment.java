package com.wokun.tysl.main;

import android.content.Intent;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itheima.loopviewpager.LoopViewPager;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.Logger;
import com.shantoo.common.utils.ResourceUtil;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseFragment1;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;

import com.wokun.tysl.goods.adapter.SubCategoryNewAdapter;
import com.wokun.tysl.goods.bean.SubCategoryBean;
import com.wokun.tysl.goods.bean.TopCategoryBean;
import com.wokun.tysl.goods.response.SubCategoryResponse;

import com.wokun.tysl.goods.ui.activity.GoodsListActivity;

import com.wokun.tysl.model.bean.SubBean;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;


public class GoodsCategoryFragment extends BaseFragment1 {

/*   @BindView(R.id.status_bar) View statusBar;
    @BindView(R.id.tab_layout) VerticalTabLayout tabLayout;

    private Object fragmentMgr;
    private Method noteStateNotSavedMethod;
    private String[] activityClassName = {"Activity", "FragmentActivity"};

    @Override
    public int createView() {
        return R.layout.activity_goods_category;
    }

    @Override
    public void initViews() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initToolBar() {
    }

    @Override
    public void onResume() {
        super.onResume();
        getTopCategory();
    }

    private void initTab(List<TopCategoryBean> list) {
        List<Fragment> fragments = getFragments(list);
        tabLayout.setupWithFragment(getChildFragmentManager(), R.id.fragment_container, fragments, new CategoryAdapter(list));
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

    //获取商品一级分类
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
                            //Logger.e(TAG,JSONUtil.toJSON(body));
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        invokeFragmentManagerNoteStateNotSaved();
    }

    private void invokeFragmentManagerNoteStateNotSaved() {
        //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }
        try {
            if (noteStateNotSavedMethod != null && fragmentMgr != null) {
                noteStateNotSavedMethod.invoke(fragmentMgr);
                return;
            }
            Class cls = getClass();
            do {
                cls = cls.getSuperclass();
            } while (!(activityClassName[0].equals(cls.getSimpleName())
                    || activityClassName[1].equals(cls.getSimpleName())));

            Field fragmentMgrField = prepareField(cls, "mFragments");
            if (fragmentMgrField != null) {
                fragmentMgr = fragmentMgrField.get(this);
                noteStateNotSavedMethod = getDeclaredMethod(fragmentMgr, "noteStateNotSaved");
                if (noteStateNotSavedMethod != null) {
                    noteStateNotSavedMethod.invoke(fragmentMgr);
                }
            }

        } catch (Exception ex) {
        }
    }

    private Field prepareField(Class<?> c, String fieldName) throws NoSuchFieldException {
        while (c != null) {
            try {
                Field f = c.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } finally {
                c = c.getSuperclass();
            }
        }
        throw new NoSuchFieldException();
    }

    private Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
            }
        }
        return null;
    }*/
    @BindView(R.id.status_bar) View statusBar;
    @BindView(R.id.recycler_view5)
     RecyclerView mRecyclerView;
    @BindView(R.id.goods_topImage)
    ImageView goodsTopImage;
    @BindString(R.string.tysl_goods_fenlei) String title;
    private SubCategoryNewAdapter adapter;
    private  List<SubCategoryBean> list;
    private  String  bannerImage;
    @Override
    public int createView() {
        return R.layout.activity_goods_categorynew;
    }

    @Override
    public void initToolBar() {
        super.initToolBar();
    }

    @Override
    public void initViews() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);
        initRecyclerView();


    }


    private void initRecyclerView() {
  // adapter = new SubCategoryAdapter(R.layout.item_category_section_content, R.layout.layout_category_section_head, null);
        //adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter = new SubCategoryNewAdapter(R.layout.item_category_section_content, null);

        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SubCategoryBean bean = (SubCategoryBean) adapter.getData().get(position);
                SubBean subBean = bean.t;
                Intent intent = new Intent();
                intent.putExtra("gc_id", list.get(position).getGcId());
                intent.putExtra("gc_name", list.get(position).getGcName());
               intent.setClass(getContext(), GoodsListActivity.class);
                startActivity(intent);
            }
        });
    }




    @Override
    public void loadData() {
        mMultipleStatusView.showLoading();
        OkGo.<BaseResponse<SubCategoryResponse>>post(Constants.BASE_URL + Constants.GOODS_CLASS)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<SubCategoryResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<SubCategoryResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                           Logger.e(TAG, JSONUtil.toJSON(body));
                           Log.e("商品列表",JSONUtil.toJSON(body));
                            mMultipleStatusView.showContent();
                            SubCategoryResponse data = (SubCategoryResponse) body.getData();
                              list = data.getGoodsClass();
                              bannerImage = data.getBanner();
                            Log.e("商品列表111",data.getBanner());
                            ImageLoader.loadImage(bannerImage, goodsTopImage);
                            if (list != null && list.size() > 0) {
                                 adapter.setNewData(list);
                            }
                        }
                    }
                });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

}