package com.wokun.tysl.ucenter.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wokun.tysl.R;
import com.wokun.tysl.ucenter.bean.UserServiceOrder;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

public class MyApplyForAdapter extends BaseQuickAdapter<UserServiceOrder, BaseViewHolder> {

    public MyApplyForAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final UserServiceOrder item) {
        ImageLoader.loadImage(item.getHeadLogo(), (ImageView) helper.getView(R.id.head_logo));
        helper.setText(R.id.truename,item.getTrueName())
        .setText(R.id.jobType,item.getJobType())
        .setText(R.id.order_amount,item.getOrderAmount())
                .setText(R.id.finish_time3,item.getServiceDays())
      //  .setText(R.id.field,item.getField())
        .setText(R.id.finish_time, item.getFinishTime())
        .addOnClickListener(R.id.action_refund_detail)//申请解绑
        .addOnClickListener(R.id.action_evaluate)//评价
        .addOnClickListener(R.id.action_consult);//服务中

        TextView actionRefundDetail = helper.getView(R.id.action_refund_detail);
        TextView actionEvaluate = helper.getView(R.id.action_evaluate);
        TextView actionConsult = helper.getView(R.id.action_consult);
        TextView actionDingdanstate = helper.getView(R.id.dingdanstate);
        RelativeLayout mZhuangtai = helper.getView(R.id.zhuangtai);
        Log.e("Untie2",item.getOrderState()+"");
        if("5".equals(item.getOrderState())){//已评价
            actionDingdanstate.setText("已评价");
            Log.e("Untie",item.getOrderState()+"");
          if(item.getUntie().equals("0")){//未解绑
              actionRefundDetail.setVisibility(View.VISIBLE);

          }
          else if(item.getUntie().equals("1")){

              mZhuangtai.setVisibility(View.GONE);
          }


            actionEvaluate.setVisibility(View.GONE);
            actionConsult.setVisibility(View.GONE);

        }else if("4".equals(item.getOrderState())){//待评价
            actionDingdanstate.setText("待评价");
            actionRefundDetail.setVisibility(View.GONE);
            actionConsult.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.VISIBLE);
        }else if("3".equals(item.getOrderState())){//服务中
            actionDingdanstate.setText("服务中");
            actionConsult.setVisibility(View.VISIBLE);
            actionRefundDetail.setVisibility(View.GONE);
            actionEvaluate.setVisibility(View.GONE);
        }
    }
}
