package com.wokun.tysl.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;

public class ImageLoader {

    public static void loadBanner(String imgUrl, ImageView imageView){
        Glide.with(TyslApp.getContext())
                .load(imgUrl)
                //.placeholder(R.mipmap.ic_placeholder)
                .into(imageView);
    }

    public static void loadImageWithPlaceholder(String imgUrl, ImageView imageView) {
        Glide.with(TyslApp.getContext())
                .load(imgUrl)
                .fitCenter()
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);
    }

    public static void loadImageWP(String imgUrl, ImageView imageView) {
        Glide.with(TyslApp.getContext())
                .load(imgUrl)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView);
    }

    public static void loadImage(String imgUrl, ImageView imageView) {
        Glide.with(TyslApp.getContext())
                .load(imgUrl)
                .into(imageView);
    }
}
