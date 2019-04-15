package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.SubCategoryAdapter;
import com.wokun.tysl.goods.bean.SubCategoryBean;
import com.wokun.tysl.goods.response.SubCategoryResponse;
import com.wokun.tysl.model.bean.SubBean;
import com.wokun.tysl.model.response.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.wokun.tysl.TyslApp.getContext;

/**
 * Created by Administrator on 2018/7/13/013.
 */

public class GoodsCategoryNewActivity  extends BaseBindingActivity{
    private SubCategoryAdapter adapter;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Override
    public int createView() {
        return R.layout.activity_goods_categorynew;
    }

    @Override
    public WidgetBar createToolBar() {
        return null;
    }

    @Override
    public void init() {
        initRecyclerView();
        loadData();

}

    private void loadData() {

        OkGo.<BaseResponse<SubCategoryResponse>>post(Constants.BASE_URL + Constants.GOODS_CLASS)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<SubCategoryResponse>>(Constants.WITH_TOKEN,Constants.SUB_CATEGORY_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SubCategoryResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            SubCategoryResponse subCategoryResponse = (SubCategoryResponse) body.getData();
                            List<SubCategoryBean> subCategoryList = subCategoryResponse.getGoodsClass();
                            List<SubCategoryBean> data = new ArrayList<>();
                            if (subCategoryList != null) {
                                for (SubCategoryBean subcategory : subCategoryList) {
                                  data.add(new SubCategoryBean(false,subcategory.getGcName()));
                                  data.add(new SubCategoryBean(false,subcategory.getGcIcon()));
                                    /*List<SubBean> subBeanList = subcategory.getSub();
                                    if (subBeanList != null) {
                                        for (SubBean bean : subBeanList) {
                                            data.add(new SubCategoryBean(bean));
                                        }
                                    }*/
                                }
                                adapter.setNewData(data);
                            }
                        }
                    }
                });




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
                intent.setClass(getContext(), GoodsListActivity.class);
                startActivity(intent);
            }
        });
    }

}
