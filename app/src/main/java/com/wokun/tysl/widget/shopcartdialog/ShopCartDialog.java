package com.wokun.tysl.widget.shopcartdialog;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wokun.tysl.R;
import com.wokun.tysl.smartretail.adapter.RightDishAdapter;
import com.wokun.tysl.smartretail.bean.RetailShop;
import com.wokun.tysl.utils.DialogUtil;

import java.util.ArrayList;

public class ShopCartDialog extends Dialog implements View.OnClickListener,ShopCartImp{

    private ShopCart mShopCart;
    private Context mContext;
    private TextView totalPriceTextView;
    private TextView totalPriceNumTextView;
    private LinearLayout linearLayout;
    private ShopCartImp shopCartDialogImp;
    private ArrayList<RetailShop> mMenuList = new ArrayList<>();
    public ShopCartDialog(Context context, ShopCart shopCart, int themeResId) {
        super(context,themeResId);
        this.mContext = context;
         this.mShopCart = shopCart;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_shop_cart_popupview);

        linearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        totalPriceTextView = (TextView)findViewById(R.id.shopping_cart_total_tv);
        totalPriceNumTextView = (TextView)findViewById(R.id.shopping_cart_total_num);

        findViewById(R.id.clear_layout).setOnClickListener(this);
        findViewById(R.id.shopping_cart_layout).setOnClickListener(this);
        findViewById(R.id.shopping_cart_bottom).setOnClickListener(this);

       PopupDishAdapter dishAdapter = new PopupDishAdapter(getContext(), mShopCart);

   //   RightDishAdapter dishAdapter = new RightDishAdapter(getContext(),mShopCart,mMenuList);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(dishAdapter);
        dishAdapter.setShopCartImp(this);
        showTotalPrice();
    }

    @Override
    public void show() {
        super.show();
        animationShow(200);
    }

    @Override
    public void dismiss() {
        animationHide(200);
    }

    private void showTotalPrice(){
        if(mShopCart !=null && mShopCart.getShoppingTotalPrice()>0){
            totalPriceTextView.setVisibility(View.VISIBLE);
            totalPriceTextView.setText("￥ "+ mShopCart.getShoppingTotalPrice()+"");
            totalPriceNumTextView.setVisibility(View.VISIBLE);
            totalPriceNumTextView.setText(""+ mShopCart.getShoppingAccount()+"");
        }else {
            totalPriceTextView.setVisibility(View.GONE);
            totalPriceNumTextView.setVisibility(View.GONE);
        }
    }

    private void animationShow(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linearLayout, "translationY",1000, 0).setDuration(mDuration)
        );
        animatorSet.start();
    }

    private void animationHide(int mDuration) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(linearLayout, "translationY",0,1000).setDuration(mDuration)
        );
        animatorSet.start();

        if(shopCartDialogImp!=null){
            shopCartDialogImp.dismiss();
        }

        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                ShopCartDialog.super.dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        if(R.id.clear_layout == view.getId()){
            clear();
        }else if(R.id.shopping_cart_bottom == view.getId()
              || R.id.shopping_cart_layout == view.getId()){
            this.dismiss();
        }
    }

    @Override
    public void add(View view, int position) {
        showTotalPrice();
    }

    @Override
    public void remove(View view, int position) {
        showTotalPrice();
        if(mShopCart.getShoppingAccount()==0){
            this.dismiss();
        }
    }

    public void setShopCartDialogImp(ShopCartImp shopCartDialogImp) {
        this.shopCartDialogImp = shopCartDialogImp;
    }

    public void clear(){
        /*new MaterialDialog.Builder(mContext)
            .content("您确定要清空购物车吗?")
            .neutralText("取消")
            .positiveText("确定")
            .onAny(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    if(which == which.POSITIVE){
                        mShopCart.clear();
                        showTotalPrice();
                        if(mShopCart.getShoppingAccount()==0){
                            ShopCartDialog.this.dismiss();
                        }
                    }
                }
            })
            .show();*/

        DialogUtil.showDialog(mContext,"您确定要清空购物车吗?","取消","确定",
            new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    if(which == which.POSITIVE){
                        mShopCart.clear();
                        showTotalPrice();
                        if(mShopCart.getShoppingAccount()==0){
                            ShopCartDialog.this.dismiss();
                        }
                    }
                }
        });
    }
}