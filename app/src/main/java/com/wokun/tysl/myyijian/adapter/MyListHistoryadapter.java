package com.wokun.tysl.myyijian.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wokun.tysl.R;
import com.wokun.tysl.home.OnItemClickListener;
import com.wokun.tysl.home.bean.ArticleBean;
import com.wokun.tysl.myyijian.HistoryBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.List;

/**
 * 添加数据  历史建议
 */

public class MyListHistoryadapter extends RecyclerView.Adapter<MyListHistoryadapter.MyViewHolder> {
    private List<HistoryBean> mData;
    private OnItemClickListener mListener=null;
    //添加数据
    public void setData(List<HistoryBean> data) {
      this.mData=data;
      notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     //此处吧布局写上
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_history, parent, false);
        return new MyViewHolder(convertView);
    }
    public void setOnItemclickListener(OnItemClickListener listener){
        this.mListener=listener;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        HistoryBean historyBean = mData.get(position);
        holder.his_content.setText(historyBean.getContent());
        holder.his_msg.setText(historyBean.getStatus());
        holder.his_time.setText(historyBean.getCreate_time());

       //没有图片
        if(historyBean.getImages().size()==0){
            holder.his_imgs.setVisibility(View.GONE);
        }else {
                holder.his_imgs.setVisibility(View.VISIBLE);
          if(historyBean.getImages().size()==1){
            holder.his_img1.setVisibility(View.VISIBLE);
            ImageLoader.loadImage((String) historyBean.getImages().get(0), holder.his_img1);

            holder.his_img2.setVisibility(View.INVISIBLE);
            holder.his_img3.setVisibility(View.INVISIBLE);
        }
        else    if(historyBean.getImages().size()==2){
            holder.his_img1.setVisibility(View.VISIBLE);
            holder.his_img2.setVisibility(View.VISIBLE);
            ImageLoader.loadImage((String) historyBean.getImages().get(0), holder.his_img1);
            ImageLoader.loadImage((String) historyBean.getImages().get(1), holder.his_img2);
            holder.his_img3.setVisibility(View.INVISIBLE);
        }
        else  if (historyBean.getImages().size() >= 3){
            holder.his_img1.setVisibility(View.VISIBLE);
            holder.his_img2.setVisibility(View.VISIBLE);
            holder.his_img3.setVisibility(View.VISIBLE);
            ImageLoader.loadImage((String) historyBean.getImages().get(0), holder.his_img1);
            ImageLoader.loadImage((String) historyBean.getImages().get(1), holder.his_img2);
            ImageLoader.loadImage((String) historyBean.getImages().get(2), holder.his_img3);
        }}

           if(historyBean.getStatus().equals("已处理")){
               holder.his_kefu.setVisibility(View.VISIBLE);
               holder.his_kefu.setText( historyBean.getDo_result());
           }else {
               holder.his_kefu.setVisibility(View.GONE);

           }

    /*    holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onItemClick(view,position);
                }
            }
        });*/


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView his_content,his_msg,his_time,his_kefu;
        private ImageView his_img1,his_img2,his_img3;
        private LinearLayout his_imgs;

        public MyViewHolder(View itemView) {
            super(itemView);
       //     home_place_img = (ImageView) itemView.findViewById(R.id.home_health_img);
            his_content = (TextView) itemView.findViewById(R.id.his_content);
            his_msg = (TextView) itemView.findViewById(R.id.his_msg);
            his_time = (TextView) itemView.findViewById(R.id.his_time);
            his_kefu = (TextView) itemView.findViewById(R.id.his_kefu);
            his_img1 = (ImageView) itemView.findViewById(R.id.his_img1);
            his_img2 = (ImageView) itemView.findViewById(R.id.his_img2);
            his_img3 = (ImageView) itemView.findViewById(R.id.his_img3);
            his_imgs = (LinearLayout) itemView.findViewById(R.id.his_imgs);


        }
    }
}
