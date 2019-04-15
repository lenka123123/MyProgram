package com.wokun.tysl.widget.shopcartdialog;

import android.view.View;

public interface ShopCartImp {

    void add(View view, int position);

    void remove(View view, int position);

    void dismiss();
}
