package com.wokun.tysl.order.adapter;
       import android.support.annotation.LayoutRes;
        import android.support.annotation.Nullable;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.chad.library.adapter.base.BaseQuickAdapter;
        import com.chad.library.adapter.base.BaseViewHolder;
        import com.shantoo.common.utils.UIUtil;
        import com.wokun.tysl.R;
        import com.wokun.tysl.dietician.bean.ServiceOrderBean;
       import com.wokun.tysl.order.bean.MyOrderBean;
       import com.wokun.tysl.utils.ImageLoader;


        import java.util.List;

public class MyOrderServiceAdapter extends BaseQuickAdapter<MyOrderBean, BaseViewHolder> {

    public MyOrderServiceAdapter(@LayoutRes int layoutResId, @Nullable List<MyOrderBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyOrderBean item) {
        TextView state = helper.getView(R.id.order_click);


        ImageLoader.loadImage(item.getAvatar(), (ImageView) helper.getView(R.id.logo));

        helper.setText(R.id.name, item.getUserName())
                .setText(R.id.order_price, "单价:  ¥" + item.getorderPrice())
                .setText(R.id.order_profit, "收益:  ¥" + item.getOrderProfit())
                .setText(R.id.price, "￥ " + item.getOrderAmount())
                .setText(R.id.user_phone,item.getUserPhone())
                .setText(R.id.user_name,item.getUserName())
                .addOnClickListener(R.id.order_click);

        if ("2".equals(item.getOrderState())) {
            state.setText("待确认");
            state.setTextColor(UIUtil.getColor(R.color.colorPrimary));
        } else if ("6".equals(item.getOrderState())) {
            state.setText("已关闭");
        } else if ("3".equals(item.getOrderState())) {
            state.setText("服务中");
        } else if ("4".equals(item.getOrderState())) {
            state.setText("服务结束未评价");
        } else if ("5".equals(item.getOrderState())) {
            state.setText("服务结束已评价");
        }else if ("0".equals(item.getOrderState())){
            state.setVisibility(View.VISIBLE);
            helper.getView(R.id.order_time).setVisibility(View.GONE);
        }
    }
}
