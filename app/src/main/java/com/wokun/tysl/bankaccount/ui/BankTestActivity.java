package com.wokun.tysl.bankaccount.ui;

import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.article.bean.ServiceBean;
import com.wokun.tysl.bankaccount.adapter.EvaAdapter3;
import com.wokun.tysl.bankaccount.bean.TixianBean;
import com.wokun.tysl.bankaccount.bean.TixianResponse;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.base.SimpleRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.EvaAdapter2;
import com.wokun.tysl.huiyuantotal.ui.HistoryKehuActivity;
import com.wokun.tysl.huiyuantotal.ui.TixianMoneyActivity;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class BankTestActivity   extends SimpleRefreshAndLoadMoreActivity<TixianBean> {
    @BindString(R.string.tysl_user_tixian)String title;

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<TixianBean>>post(Constants.BASE_URL + Constants.RETAIL_WITHDRAW_RECORD)
                .tag(this);
    }

    @Override
    public BaseQuickAdapter<TixianBean, BaseViewHolder> initAdapter() {
        return new EvaAdapter3(R.layout.item_zhihui_tixian, null);
    }

    @Override
    public void loadData(final boolean isRefresh) {

        mRequest.execute(new JsonCallback<BaseResponse<TixianResponse>>(Constants.WITH_TOKEN,Constants.RETAIL_WITHDRAW_RECORD) {
            @Override
            public void onSuccess(Response<BaseResponse<TixianResponse>> response) {
                BaseResponse body = response.body();
                Log.e("11body",""+body);
                if(body == null)return;

                if(body.isState()){
                    TixianResponse data = (TixianResponse) body.getData();
                    if(data == null){return;}
                    Log.e("11data",""+data);
                    setData(isRefresh,data.getRecord());
                }
            }
        });


    }

    @Override
    public WidgetBar createToolBar() {

        return mWidgetBar.setWidgetBarTitle(title);
    }
  /*  @Override
    public int createView() {
        return R.layout.activity_zhihui_bank;
    }

    @Override
    public WidgetBar createToolBar()  {
       return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {

    }
   @OnClick(R.id.zhihui_addcard)
    public void Addcard() {
        startActivity(BankTestActivity2.class);
    }*/
}
