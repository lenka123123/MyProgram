package com.wokun.tysl.dietician.controler;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.ServiceOrderBean;
import com.wokun.tysl.dietician.ui.fragment.DietitianCheckEvaluate;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.ucenter.bean.UserServiceOrder;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class DieticianServiceOrderMgr {

    private DieticianServiceOrderMgr() {
    }

    private static class DieticianMgrHolder {
        private static DieticianServiceOrderMgr instance = new DieticianServiceOrderMgr();
    }

    public static DieticianServiceOrderMgr getInstance() {
        return DieticianMgrHolder.instance;
    }

    /**
     * 同意服务
     */
    public void onAccept(final BaseRefreshAndLoadMoreFragment fragment, ServiceOrderBean item) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_ACCEPT_SERVICE_URL)
                .params(Constants.ORDER_ID, item.getOrderId())
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.DIETITIAN_ACCEPT_SERVICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        RxToast.showShort(body.getMsg());
                        if (body.isState()) {
                            fragment.onRefresh();
                        }
                    }
                });
    }

    /**
     * 拒绝服务
     */
    public void onRefuse(final BaseRefreshAndLoadMoreFragment fragment, ServiceOrderBean item) {
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_REFUSE_SERVICE_URL)
                .params(Constants.ORDER_ID, item.getOrderId())
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.DIETITIAN_REFUSE_SERVICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        RxToast.showShort(body.getMsg());
                        if (body.isState()) {
                            fragment.onRefresh();
                        }
                    }
                });
    }

    /**
     * 发送消息
     */
    public void onSendMessage(ServiceOrderBean item) {
        if (RongIM.getInstance() != null) {
            Log.e("融云id:2", item.getUserId() + "融云NAME2:" + item.getUserName());

          //  RongIM.getInstance().startPrivateChat(TyslApp.getContext(), item.getUserId(), item.getUserName());
            RongIM.getInstance();
            startPrivateChat2(TyslApp.getContext(),item.getUserId(), item.getUserName());



        }
    }

    public void onSendMessage2(UserServiceOrder item) {//UserServiceOrder
        if (RongIM.getInstance() != null) {
            Log.e("融云id:", item.getDietitianId() + "融云NAME:" + item.getTrueName());
        //    RongIM.getInstance().startPrivateChat(TyslApp.getContext(), item.getDietitianId(), item.getTrueName());
            RongIM.getInstance();
            startPrivateChat2(TyslApp.getContext(), item.getDietitianId(), item.getTrueName());

        }
    }


    /**
     * 申请解绑
     */
    public void getUnite(final BaseRefreshAndLoadMoreFragment fragment, UserServiceOrder item) {

        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.UCENTER_UNTIE)
                .params(Constants.ORDER_ID, item.getServiceOrderId())
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.UCENTER_UNTIE) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        RxToast.showShort(body.getMsg());
                        if (body.isState()) {
                            fragment.onRefresh();

                        }
                    }
                });


    }


    /**
     * 查看评论
     */
    public void onCheckEvaluate(final BaseRefreshAndLoadMoreFragment fragment, ServiceOrderBean item) {
        Intent intent = new Intent();
        intent.putExtra(Constants.ORDER_ID, item.getOrderId());
        intent.setClass(fragment.getContext(), DietitianCheckEvaluate.class);
        fragment.startActivity(intent);
    }

    public void startPrivateChat2(Context context, String targetUserId, String title) {
        if(context != null && !TextUtils.isEmpty(targetUserId)) {
            if(RongContext.getInstance() == null) {
                throw new ExceptionInInitializerError("RongCloud SDK not init");
            } else {
                Uri uri = Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(Conversation.ConversationType.PRIVATE.getName().toLowerCase()).appendQueryParameter("targetId", targetUserId).appendQueryParameter("title", title).build();
                Intent intent = new Intent("android.intent.action.VIEW", uri);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

}
