package com.wokun.tysl.duotupianshangchuan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.shantoo.rxpermissions.OnCheckPermissionListener;
import com.shantoo.rxpermissions.RxPermissions;
import com.shantoo.widget.multiplephotoselector.MultiplePhotoSelector;
import com.shantoo.widget.multiplephotoselector.OnMultiplePhotoUpLoadListener;
import com.shantoo.widget.multiplephotoselector.adapter.GridImageAdapter;
import com.shantoo.widget.multiplephotoselector.ui.AlbumActivity;
import com.shantoo.widget.multiplephotoselector.ui.PhotoDeleteActivity;
import com.shantoo.widget.multiplephotoselector.util.Constants;
import com.wokun.tysl.R;
import com.wokun.tysl.myyijian.fragment.SendMymessagefragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/9/12.
 */

public class MyMultiplePhotoSelector extends MultiplePhotoSelector {
    private Context mContext;
    private Activity mActivity;
    private GridImageAdapter mAdapter;
    private boolean isOnMeasure;
    private OnMultiplePhotoUpLoadListener mOnPhotoUpLoadListener;
    private ArrayList<String> mDataList;
    private ArrayList<String> tDataList;
    private String TAG;

    public MyMultiplePhotoSelector(Context context) {
        this(context, (AttributeSet)null, 0);
    }

    public MyMultiplePhotoSelector(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyMultiplePhotoSelector(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.isOnMeasure = false;
        this.tDataList = new ArrayList();
        this.TAG = this.getClass().getSimpleName();
        this.mContext = context;
        this.mActivity = (Activity)context;
        this.init();
    }

    private void initImageLoader(Context context) {
        ImageLoaderConfiguration config = (new ImageLoaderConfiguration.Builder(context)).threadPriority(3).denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new Md5FileNameGenerator()).diskCacheSize(52428800).tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
    }

    private void init() {
        new LayoutParams(-1, -1);
        this.initImageLoader(this.mContext);
        ImageLoader mImageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = (new DisplayImageOptions.Builder()).imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565).showImageOnLoading(R.mipmap.ic_home_empty).cacheInMemory(true).cacheOnDisc(true).build();
        this.mDataList = new ArrayList();
        this.mDataList.add("camera_default");
        this.mAdapter = new GridImageAdapter(this.mContext, this, this.mDataList, mImageLoader, options);
        this.setAdapter(this.mAdapter);
        this.setOnItemClickListener(this);
    }

    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == -1) {
            int i;
            if(2178 == requestCode) {
                i = data.getIntExtra("position", -1);
                this.mDataList.remove(i);
                if(this.mDataList.size() < Constants.PHOTO_MAX_SELECT_NUMBER) {
                    for(int var7 = 0; var7 < this.mDataList.size(); ++var7) {
                        String path = (String)this.mDataList.get(var7);
                        if(path.contains("default")) {
                            this.mDataList.remove(this.mDataList.size() - 1);
                        }
                    }

                    this.mDataList.add(this.mDataList.size(), "camera_default");
                }

                this.mAdapter.notifyDataSetChanged();
            } else if(2177 == requestCode) {
                this.mDataList.clear();
                this.tDataList = data.getStringArrayListExtra("dataList");
                if(this.tDataList != null) {
                    for(i = 0; i < this.tDataList.size(); ++i) {
                        String string = (String)this.tDataList.get(i);
                        this.mDataList.add(string);
                    }

                    if(this.mDataList.size() < Constants.PHOTO_MAX_SELECT_NUMBER) {
                        this.mDataList.add("camera_default");
                    }

                    this.mAdapter.notifyDataSetChanged();
                }
            }

            if(this.mOnPhotoUpLoadListener != null) {
                this.mOnPhotoUpLoadListener.onMultiplePhotoUpLoad(this.mDataList);
            }
        }

        return true;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String path = (String)this.mDataList.get(position);
        Log.e("测试类",path+"ss");
        if(path.contains("default") && position == this.mDataList.size() - 1 && this.mDataList.size() - 1 != 9) {
            Log.e("测试类",1111111+"ss");
            Intent intent = new Intent(MyMultiplePhotoSelector.this.mContext, AlbumActivity.class);
            MyMultiplePhotoSelector.this.mActivity.startActivityForResult(intent, 2177);

            RxPermissions.getInstance().requestPermission(((Activity) mContext), "android.permission.READ_EXTERNAL_STORAGE").setOnCheckPermissionListener(new OnCheckPermissionListener() {
                public void onSuccess() {
                    Log.e("测试类权限","onSuccess");
                    Intent intent = new Intent(MyMultiplePhotoSelector.this.mContext, AlbumActivity.class);
                    MyMultiplePhotoSelector.this.mActivity.startActivityForResult(intent, 2177);
                }

                public void onError() {
                    Log.e("测试类权限","onError");
                }
            });

        } else {
            Log.e("测试类",22222222+"ss");
            Intent intent = new Intent(this.mContext, PhotoDeleteActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("path", (String)this.mDataList.get(position));
            this.mActivity.startActivityForResult(intent, 2178);
        }

    }

    public void setOnMultiplePhotoUpLoadListener(OnMultiplePhotoUpLoadListener listener) {
        this.mOnPhotoUpLoadListener = listener;
    }

    public boolean isMeasure() {
        return this.isOnMeasure;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.isOnMeasure = true;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        this.isOnMeasure = false;
        super.onLayout(changed, l, t, r, b);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        RxPermissions.getInstance().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
