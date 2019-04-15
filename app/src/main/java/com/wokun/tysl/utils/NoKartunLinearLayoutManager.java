package com.wokun.tysl.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

//解决ScrollView嵌套RecyclerView滑动卡顿问题
public class NoKartunLinearLayoutManager extends LinearLayoutManager {

    public NoKartunLinearLayoutManager(Context context) {
        super(context);
    }

    public NoKartunLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, false);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
