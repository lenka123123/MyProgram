package com.wokun.tysl.duotupianshangchuan;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.hedgehog.ratingbar.RatingBar;

/**
 * Created by Administrator on 2018/9/13.
 */

public class MyRatingBar extends RatingBar {
    private int starCount;
    private Context context;

    public MyRatingBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;

        int i;
        ImageView imageView;
        for (i = 0; i < this.starCount; ++i) {
            imageView = this.getStarImageView(context, false);
            this.addView(imageView);
        }


    }


    private ImageView getStarImageView(Context context, boolean isEmpty) {
        ImageView imageView = new ImageView(context);
        LayoutParams para = new LayoutParams(Math.round(60.0F), Math.round(120F));
        imageView.setLayoutParams(para);
        imageView.setPadding(0, 0, Math.round(5.0F), 0);
        if (isEmpty) {
            imageView.setImageDrawable(context.getResources().getDrawable(com.hedgehog.ratingbar.R.mipmap.ic_star_empty));
        } else {
            imageView.setImageDrawable(context.getResources().getDrawable(com.hedgehog.ratingbar.R.mipmap.ic_star_fill));
        }

        return imageView;
    }
}
