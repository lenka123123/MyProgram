package com.wokun.tysl.huiyuantotal.ui;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.huiyuantotal.bean.ErweimaBean;
import com.wokun.tysl.huiyuantotal.bean.ZXingUtils;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/25/025.
 */

public class ErWeiMaTotalActivity extends BaseBindingActivity {
    @BindString(R.string.tysl_user_erweima)String title;
    @BindView(R.id.store_place)
    TextView storePlace;
    @BindView(R.id.erweima)
    ImageView erweima;



    @Override
    public int createView() {
        return R.layout.activity_zhihui_erweima;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar.setWidgetBarTitle(title);
    }

    @Override
    public void init() {
        loadData();

    }

    private void loadData() {
        OkGo.<BaseResponse<ErweimaBean>>post(Constants.BASE_URL + Constants.MAKE_QRCODE_API)
                .tag(this)
                .execute(new DialogCallback<BaseResponse<ErweimaBean>>(this,Constants.WITH_TOKEN,Constants.MAKE_QRCODE_API) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ErweimaBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                          RxToast.showShort(body.getMsg());
                            ErweimaBean data = (ErweimaBean) body.getData();
                            storePlace.setText(data.getStoreName());
                          Bitmap bitmap = ZXingUtils.createQRImage(data.getUrl(), 180,  180);
                           erweima.setImageBitmap(bitmap);
                        }
                    }
                });

    }
    /** 生成新二维码 */
    @OnClick(R.id.new_erweima)
    public void NewErweima() {
        loadData();
    }


}
