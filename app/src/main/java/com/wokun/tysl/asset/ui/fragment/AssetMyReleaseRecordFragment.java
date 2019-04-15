package com.wokun.tysl.asset.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.asset.adapter.AssetMyReleaseRecordAdapter;
import com.wokun.tysl.asset.bean.MyReleaseRecordBean;
import com.wokun.tysl.asset.bean.MyReleaseRecordResponse;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;

import cn.carbs.android.library.MDDialog;

/**
 * 我的发布记录
 */

public class AssetMyReleaseRecordFragment extends SimpleRefreshAndLoadMoreFragment<MyReleaseRecordBean> {

    @Override
    public void onResume() {
        super.onResume();
        doOnRefreshData();
    }

    /**
     * 传入需要的参数，设置给arguments
     */
    public static AssetMyReleaseRecordFragment newInstance(int argument) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STATE, argument);
        AssetMyReleaseRecordFragment contentFragment = new AssetMyReleaseRecordFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public Request initRequest() {
        int state = getArguments().getInt(Constants.STATE);
        return OkGo.<BaseResponse<MyReleaseRecordBean>>post(Constants.BASE_URL + Constants.ASSETS_MY_RELEASE_RECORD_URL)
                .params(Constants.USER_ID, TyslApp.getInstance().getUser().getUserId())
                .params(Constants.TYPE,state)
                .tag(this);
    }

    @Override
    public BaseQuickAdapter<MyReleaseRecordBean, BaseViewHolder> initAdapter() {
        final int state = getArguments().getInt(Constants.STATE);
        AssetMyReleaseRecordAdapter adapter = new AssetMyReleaseRecordAdapter(R.layout.item_asset_my_release_record,null,state);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MyReleaseRecordBean data = (MyReleaseRecordBean) adapter.getData().get(position);
                if(R.id.action_asset_release_delete == view.getId()){
                    delRelease(AssetMyReleaseRecordFragment.this,data.getrId());
                }else if(R.id.action_asset_release_edit == view.getId()){
                    showDialog(state,data.getrId());
                }
            }
        });
        return adapter;
    }

    private void showDialog(final int state, final String id){
        final String type = state==1?"买入":"卖出";

        new MDDialog.Builder(getContext())
                .setContentView(R.layout.layout_content_dialog_release_record)
                .setContentViewOperator(new MDDialog.ContentViewOperator() {
                    @Override
                    public void operate(View contentView) {
                        ((TextView) contentView.findViewById(R.id.title1)).setText(state==1?"买入单价:":"卖出单价:");
                        ((TextView) contentView.findViewById(R.id.title2)).setText("发布数量:");

                        ((EditText) contentView.findViewById(R.id.edit1)).setHint(state==1?"请输入买入单价":"请输入卖出单价");
                        ((EditText) contentView.findViewById(R.id.edit2)).setHint("请输入发布数量");
                    }
                })
                .setPositiveButtonMultiListener(new MDDialog.OnMultiClickListener() {
                    @Override
                    public void onClick(View clickedView, View contentView) {
                        String unitPrice = ((EditText) contentView.findViewById(R.id.edit1)).getText().toString().trim();
                        String releaseNum = ((EditText) contentView.findViewById(R.id.edit2)).getText().toString().trim();
                        if(TextUtils.isEmpty(unitPrice)){
                            RxToast.showShort(state==1?"请输入买入单价":"请输入卖出单价");
                            return;
                        }
                        if(Integer.valueOf(unitPrice)<=0){
                            RxToast.showShort(type+"单价必须大于0");
                            return;
                        }
                        if(TextUtils.isEmpty(releaseNum)){
                            RxToast.showShort("请输入发布数量");
                            return;
                        }
                        if(Integer.valueOf(releaseNum)<=0){
                            RxToast.showShort("发布数量必须大于0");
                            return;
                        }
                        editRelease(AssetMyReleaseRecordFragment.this,id,releaseNum,unitPrice);
                    }
                })
                .setWidthMaxDp(600)
                .setShowTitle(false)
                .setShowButtons(true)
                .setPrimaryTextColor(UIUtil.getColor(R.color.colorPrimary))
                .create()
                .show();
    }

    @Override
    public void loadData(boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<MyReleaseRecordResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<MyReleaseRecordResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    MyReleaseRecordResponse data = (MyReleaseRecordResponse) body.getData();
                    if(data==null){return;}
                    setData(true,data.getRelease());
                }
            }
        });
    }

    /** 编辑发布记录*/
    public void editRelease(final SimpleRefreshAndLoadMoreFragment tag, String r_id, String numbers, String unit_price){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ASSETS_EDIT_URL)
                .tag(tag)
                .params(Constants.R_ID,r_id)
                .params(Constants.NUMBERS,numbers)
                .params(Constants.UNIT_PRICE,unit_price)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ASSETS_EDIT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            RxToast.showShort(body.getMsg());
                            tag.doOnRefreshData();
                        }
                    }
                });
    }

    /** 删除发布记录*/
    public void delRelease(final SimpleRefreshAndLoadMoreFragment tag, String r_id){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ASSETS_DEL_RELEASE_URL)
                .tag(tag)
                .params(Constants.R_ID,r_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ASSETS_DEL_RELEASE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            RxToast.showShort(body.getMsg());
                            tag.doOnRefreshData();
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