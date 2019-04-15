package com.wokun.tysl.shoucang.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wokun.tysl.R;

/**
 * Created by Administrator on 2018/7/10/010.
 */
//店铺收藏页面
public class StoreFavoritesFragment  extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_shoucang,container,false);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        tv.setText("FragMent22" );
        return view;
    }
}
