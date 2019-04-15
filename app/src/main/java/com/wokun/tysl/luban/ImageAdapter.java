package com.wokun.tysl.luban;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wokun.tysl.R;


import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {
  private Context mContext;
  private List<ImageBean> mImageList = new ArrayList<>();

  public ImageAdapter(List<ImageBean> imageList) {
    mImageList = imageList == null ? mImageList : imageList;
  }

  @Override
  public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    mContext = parent.getContext();
    return new ImageHolder(LayoutInflater.from(mContext).inflate(R.layout.item_image2, parent, false));
  }

  @Override
  public void onBindViewHolder(ImageHolder holder, int position) {
    ImageBean image = mImageList.get(position);

    holder.originArg.setText(image.getOriginArg());
    holder.thumbArg.setText(image.getThumbArg());
    Glide.with(mContext)
        .load(image.getImage())
        .into(holder.image);
  }

  @Override
  public int getItemCount() {
    return mImageList.size();
  }

  class ImageHolder extends RecyclerView.ViewHolder {
    private TextView originArg;
    private TextView thumbArg;
    private ImageView image;

    ImageHolder(View view) {
      super(view);

      originArg = (TextView) view.findViewById(R.id.origin_arg);
      thumbArg = (TextView) view.findViewById(R.id.thumb_arg);
      image = (ImageView) view.findViewById(R.id.image);
    }
  }
}