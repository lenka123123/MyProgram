package com.wokun.tysl.myview;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;
import com.wokun.tysl.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017-10-15.
 */

public class ImageGridAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Object> mBitmaps;
    public ImageGridAdapter(Context context, ArrayList<Object> bitmaps){
        mContext=context;
        mBitmaps=bitmaps;
    }
    @Override
    public int getCount() {
        return mBitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return mBitmaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.griditem_addpic, parent, false);
            holder = new Holder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.imageView1);


            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        Object bitmap=mBitmaps.get(position);
        if (bitmap instanceof Bitmap){
            holder.mImageView.setImageBitmap((Bitmap)bitmap);
        }else if(bitmap instanceof String){
            String path=(String)bitmap;
            if(!path.startsWith("file://")){
                path="file://"+path;
            }
            Picasso.with(mContext).load(path).fit().centerCrop().into(holder.mImageView);
        }

        return convertView;
    }

    public class Holder{
        public Holder(){
        }
        public ImageView mImageView;
    }
}
