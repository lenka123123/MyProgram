package com.wokun.tysl.serviceplace;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wokun.tysl.R;
import com.wokun.tysl.home.OnItemClickListener;
import com.wokun.tysl.home.bean.JigouBean;
import com.wokun.tysl.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/8/22 0022.
 */

public class MyServicePlaceadapter extends RecyclerView.Adapter<MyServicePlaceadapter.MyViewHolder> {
    private List<JigouBean> mData;
    private OnItemClickListener mListener=null;
    //添加数据
    public void setData(List<JigouBean> data) {
      this.mData=data;
      notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     //此处吧布局写上
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_home_place, parent, false);
        return new MyViewHolder(convertView);
    }
    public void setOnItemclickListener(OnItemClickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.home_place_title.setText(mData.get(position).getTitle());
        holder.home_place_content.setText(mData.get(position).getDesc());
        ImageLoader.loadImage(mData.get(position).getImgurl(),holder.home_place_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    mListener.onItemClick(view,position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView home_place_title,home_place_content;
        private ImageView home_place_img;

        public MyViewHolder(View itemView) {
            super(itemView);
            home_place_img = (ImageView) itemView.findViewById(R.id.home_place_img);
            home_place_title = (TextView) itemView.findViewById(R.id.home_place_title);
            home_place_content = (TextView) itemView.findViewById(R.id.home_place_content);
        }
    }
}
