package com.wokun.tysl.home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.ui.activity.GoodsDetailActivity;
import com.wokun.tysl.home.bean.HomeBean;
import com.wokun.tysl.home.ui.HomeWebActivity;
import com.wokun.tysl.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomoSecondAdapter extends BaseAdapter {

    private Context context;
    //    private List<Picture> pictures;
//    private static int ROW_NUMBER = 3;
    private List<HomeBean.NaojiankangEntity> cateListBeans = new ArrayList<>();


    public HomoSecondAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if (null != cateListBeans) {
            return cateListBeans.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return cateListBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeBean.NaojiankangEntity bean = cateListBeans.get(position);
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_miaosha_info, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.dec = (TextView) convertView.findViewById(R.id.dec);
            holder.img = (ImageView) convertView.findViewById(R.id.img);

            ImageLoader.loadImage(bean.getGoods_pic(), holder.img);
            holder.title.setText(bean.getGoods_name());
            holder.title.setText(bean.getGoods_name());
            holder.dec.setText(bean.getGoods_desc());


            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        return convertView;
    }


    public void setData(List<HomeBean.NaojiankangEntity> dataes) {
        cateListBeans.addAll(dataes);
        notifyDataSetChanged();
    }

    public void setOnClick(int position) {
        Intent intent = new Intent();
        intent.putExtra(Constants.GOODS_ID, Integer.valueOf(cateListBeans.get(position).getGoods_id()));
        intent.setClass(context, GoodsDetailActivity.class);
        context.startActivity(intent);

    }

    class Holder {
        TextView title;
        TextView dec;
        ImageView img;
    }

//    public void updata(int isClick) {
//        typeBeanList.clear();
//        notifyDataSetChanged();
//
//        System.out.println("=TypeBeanTypeBean==== " + cateListBeans.size());
//
//        for (int i = 0; i < cateListBeans.size(); i++) {
//            TypeBean typeBean = new TypeBean();
//            typeBean.name = cateListBeans.get(i).name;
//            System.out.println("=TypeBean====" + cateListBeans.get(i).name);
//            typeBean.intro = cateListBeans.get(i).intro;
//            typeBean.isClick = isClick == i ? 1 : 0;
//            typeBeanList.add(i, typeBean);
//        }
//    }
}
