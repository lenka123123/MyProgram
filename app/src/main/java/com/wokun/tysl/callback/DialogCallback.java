package com.wokun.tysl.callback;

import android.app.Activity;
import android.os.SystemClock;

import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.dialog.loaddialog.LoadDialog;
import com.wokun.tysl.R;

public abstract class DialogCallback<T> extends JsonCallback<T> {

    private LoadDialog dialog;

    private void initDialog(Activity activity) {
        dialog = new LoadDialog(activity,true,"", R.color.colorPrimary);
        //dialog = new ProgressDialog(activity);
        //dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCanceledOnTouchOutside(false);
        //dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //dialog.setMessage("请求网络中...");
    }

    public DialogCallback(Activity activity) {
        super();
        initDialog(activity);
    }

    public DialogCallback(Activity activity,int state,String url) {
        super(state,url);
        initDialog(activity);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    @Override
    public void onFinish() {
        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onError(Response<T> response) {
        super.onError(response);
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
