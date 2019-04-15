package com.wokun.tysl.goods.ui.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.SubCategoryAdapter;
import com.wokun.tysl.goods.bean.SubCategoryBean;
import com.wokun.tysl.goods.response.SubCategoryResponse;
import com.wokun.tysl.goods.ui.activity.GoodsListActivity;
import com.wokun.tysl.model.bean.SubBean;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private String goodClassId;
    private SubCategoryAdapter adapter;

    @Override
    public int createView() {
        return R.layout.fragment_category_page;
    }

    @Override
    public void initViews() {
        initRecyclerView();
    }

    @Override
    public void loadData() {
        getSubCategory(getArguments().getString("gc_id"));
    }

    private void initRecyclerView() {
        adapter = new SubCategoryAdapter(R.layout.item_category_section_content, R.layout.layout_category_section_head, null);
        //adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mRecyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                SubCategoryBean bean = (SubCategoryBean) adapter.getData().get(position);
                SubBean subBean = bean.t;
                Intent intent = new Intent();
                intent.putExtra(Constants.GOOD_CLASS_ID, subBean.getGc_id());
                intent.setClass(TyslApp.getContext(), GoodsListActivity.class);
                startActivity(intent);
            }
        });
    }

    //获取商品二三级分类
    public void getSubCategory(String gc_id) {
        OkGo.<BaseResponse<SubCategoryResponse>>post(Constants.BASE_URL + Constants.SUB_CATEGORY_URL)
                .tag(this)
                .params("gc_id", gc_id)
                .execute(new JsonCallback<BaseResponse<SubCategoryResponse>>(Constants.WITH_TOKEN,Constants.SUB_CATEGORY_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SubCategoryResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            SubCategoryResponse subCategoryResponse = (SubCategoryResponse) body.getData();
                            List<SubCategoryBean> subCategoryList = subCategoryResponse.getSubCategory();
                            List<SubCategoryBean> data = new ArrayList<>();
                            if (subCategoryList != null) {
                                for (SubCategoryBean subcategory : subCategoryList) {
                                    data.add(new SubCategoryBean(true, subcategory.getGcName()));
                                    List<SubBean> subBeanList = subcategory.getSub();
                                    if (subBeanList != null) {
                                        for (SubBean bean : subBeanList) {
                                            goodClassId = bean.getGc_id();
                                            data.add(new SubCategoryBean(bean));
                                        }
                                    }
                                }
                                adapter.setNewData(data);
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