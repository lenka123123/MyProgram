package com.wokun.tysl.settled;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.ui.activity.DietitianJobTypeActivity;
import com.wokun.tysl.store.ui.MerchantEnterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by shantoo on 2018/5/2.
 * 入驻
 */

public class SettledActivity extends BaseBindingActivity{

    @BindView(R.id.recycler_view)RecyclerView mRecyclerView;

    @Override
    public int createView() {
        return R.layout.activity_settled;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle("入驻");
    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();
        List<String> list = new ArrayList<>();
        list.add("商家入驻");
        list.add("服务入驻");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this));
        DataAdapter adapter = new DataAdapter(R.layout.item_title,list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent();
                if(position == 0){
                    intent.setClass(SettledActivity.this,MerchantEnterActivity.class);
                    startActivity(intent,!TyslApp.getInstance().isLogin());
                }else if(position == 1){
                    intent.putExtra(Constants.TYPE,1);
                    intent.setClass(SettledActivity.this,DietitianJobTypeActivity.class);
                    startActivity(intent,!TyslApp.getInstance().isLogin());
                }
            }
        });
        mRecyclerView.setAdapter(adapter);
        mMultipleStatusView.showContent();
    }

    private class DataAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

        public DataAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.title,item);
        }
    }
}