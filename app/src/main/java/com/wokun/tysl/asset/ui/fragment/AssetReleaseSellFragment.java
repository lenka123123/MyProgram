package com.wokun.tysl.asset.ui.fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.wokun.tysl.asset.adapter.AssetReleaseBuyOutAdapter;
import com.wokun.tysl.asset.bean.AssetReleaseBean;
import com.wokun.tysl.asset.bean.ReleaseResponse;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.DecimalUtil;

import cn.carbs.android.library.MDDialog;

/**
 * 资产的出售和买入
 */

public class AssetReleaseSellFragment extends SimpleRefreshAndLoadMoreFragment<AssetReleaseBean> {

    private TextView et2;
    private int number;
    private String unitPrice;
    private String url = Constants.BASE_URL + Constants.ASSETS_RELEASE_LIST_URL;

    @Override
    public void onResume() {
        super.onResume();
        doOnRefreshData();
    }

    /**
     * 传入需要的参数，设置给arguments
     */
    /*public static AssetReleaseSellFragment newInstance(int argument) {
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.STATE, argument);
        AssetReleaseSellFragment contentFragment = new AssetReleaseSellFragment();
        contentFragment.setArguments(bundle);
        return contentFragment;
    }*/

    @Override
    public Request initRequest() {
        //int state = getArguments().getInt(Constants.STATE);
        return OkGo.<BaseResponse<AssetReleaseBean>>post(url)
                   .params(Constants.TYPE,1)
                   .tag(this);
    }

    @Override
    public BaseQuickAdapter<AssetReleaseBean, BaseViewHolder> initAdapter() {
       //final int state = getArguments().getInt(Constants.STATE);
        AssetReleaseBuyOutAdapter adapter = new AssetReleaseBuyOutAdapter(R.layout.item_asset_release_newlist,null,1);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                AssetReleaseBean data = (AssetReleaseBean) adapter.getData().get(position);
                unitPrice = data.getUnitPrice();
                if(R.id.action_asset_release_sell == view.getId()){
                    if(!TyslApp.getInstance().isLogin()){
                        startActivity(LoginActivity.class);
                    }
                    showDialog(data.getId());
                }
            }
        });
        return adapter;
    }

    @Override
    public void loadData(boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<ReleaseResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<ReleaseResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    ReleaseResponse data = (ReleaseResponse) body.getData();
                    if(data==null){return;}
                    setData(true,data.getRelease());
                }
            }
        });
    }

    private Handler handler = new Handler();

    private Runnable delayRunnable = new Runnable() {
        @Override
        public void run() {
            if(!TextUtils.isEmpty(unitPrice))
            et2.setText(String.valueOf(DecimalUtil.mul(Double.valueOf(unitPrice),number)));
        }
    };

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //每次editText有变化的时候，则移除上次发出的延迟线程
            if(delayRunnable!=null){
                handler.removeCallbacks(delayRunnable);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            number = Integer.valueOf(s.toString());//数量
            //延迟800ms，如果不再输入字符，则执行该线程的run方法
            handler.postDelayed(delayRunnable, 800);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
    };

    private void showDialog(final String id){
        new MDDialog.Builder(getContext())
                .setContentView(R.layout.layout_content_dialog)
                .setContentViewOperator(new MDDialog.ContentViewOperator() {
                    @Override
                    public void operate(View contentView) {
                        ((TextView) contentView.findViewById(R.id.title1)).setText("卖出数量:");
                        ((TextView) contentView.findViewById(R.id.title2)).setText("实际花费:");
                        EditText et1 = (EditText) contentView.findViewById(R.id.edit1);
                        et2 = (TextView) contentView.findViewById(R.id.edit2);
                        et1.setHint("请输入卖出数量");
                        et1.addTextChangedListener(watcher);
                    }
                })
                .setPositiveButtonMultiListener(new MDDialog.OnMultiClickListener() {
                    @Override
                    public void onClick(View clickedView, View contentView) {
                        String numbers = ((EditText) contentView.findViewById(R.id.edit1)).getText().toString().trim();
                        if(TextUtils.isEmpty(numbers)){
                            RxToast.showShort("请输入卖出数量");
                            return;
                        }
                        if(Integer.valueOf(numbers)<=0){
                            RxToast.showShort("卖出数量必须大于0");
                            return;
                        }
                        doMySell(AssetReleaseSellFragment.this,id,numbers,unitPrice);
                        /*if(state == 2){
                            doMyBuy(AssetReleaseSellFragment.this,id,numbers,unitPrice);
                        } else if(state == 1){
                            doMySell(AssetReleaseSellFragment.this,id,numbers,unitPrice);
                        }*/
                    }
                })
                .setWidthMaxDp(600)
                .setShowTitle(false)
                .setShowButtons(true)
                .setPrimaryTextColor(UIUtil.getColor(R.color.colorPrimary))
                .create()
                .show();
    }

    /** 我要出售操作*/
    public void doMySell(final SimpleRefreshAndLoadMoreFragment tag, String r_id, String numbers, String unit_price){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.ASSETS_DO_MY_SELL_URL)
                .params(Constants.R_ID,r_id)
                .params(Constants.NUMBERS,numbers)
                .params(Constants.UNIT_PRICE,unit_price)
                .tag(tag)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.ASSETS_DO_MY_SELL_URL) {
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