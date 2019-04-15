package com.wokun.tysl.smartretail.ui.activity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.PointF;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreActivity;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.smartretail.adapter.RetailStorageRackAdapter;
import com.wokun.tysl.widget.shopcartdialog.FakeAddImageView;
import com.wokun.tysl.widget.shopcartdialog.PointFTypeEvaluator;
import com.wokun.tysl.widget.shopcartdialog.PopupDishAdapter;
import com.wokun.tysl.widget.shopcartdialog.Product;
import com.wokun.tysl.widget.shopcartdialog.ShopCart;
import com.wokun.tysl.widget.shopcartdialog.ShopCartDialog;
import com.wokun.tysl.widget.shopcartdialog.ShopCartImp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 智慧零售-零售货架
 * */

public class SmartRetailStorageRackActivity extends BaseRefreshAndLoadMoreActivity<Product>
        implements View.OnClickListener,ShopCartImp{

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout) SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.shopping_cart_total_tv)TextView shoppingCartTotalTv;
    @BindView(R.id.shopping_cart_total_num)TextView shoppingCartTotalNum;

    @BindView(R.id.action_back)ImageView actionBack;
    @BindView(R.id.shopping_cart)ImageView shoppingCart;
    @BindView(R.id.main_layout)RelativeLayout mainLayout;

    private ShopCart mShopCart;
    private BottomSheetDialog dialog;
    private RetailStorageRackAdapter adapter;

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    @Override
    public RecyclerView initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new MItemDecoration(this));
        return mRecyclerView;
    }

    @Override
    public int createView() {
        return R.layout.activity_smart_retail_storage_rack;
    }

    @Override
    public WidgetBar createToolBar() {
        //StatusBarCompat.translucentStatusBar(this,true);
        /*return mWidgetBar
                .setWidgetBarTitle("零售货架")
                .setMenu(R.drawable.ic_ucenter,0)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(SmartRetailUCenterActivity.class);
                    }
                },null);*/
        mWidgetBar.setVisibility(View.GONE);
        return null;
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<Product>>post(Constants.BASE_URL + Constants.INTEGRAL_GET_EXCHANGE_RECORD_URL).tag(this);
    }

    @Override
    public BaseQuickAdapter<Product, BaseViewHolder> initAdapter() {
      //  adapter = new RetailStorageRackAdapter(R.layout.item_smart_retail_storage_rack,null,mShopCart);

        adapter = new RetailStorageRackAdapter(R.layout.item_smart_retail_storage_rack,null,mShopCart);
        /*adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(R.id.iv_shop_cart == view.getId()){
                    //dialog.show();
                    //showTotalPrice();
                    RxToast.showShort("加入购物车");
                }
            }
        });*/
        adapter.setShopCartImp(this);
        return null;
    }

    @Override
    public void loadData(boolean isRefresh) {
        mMultipleStatusView.showLoading();
        List<Product> list = new ArrayList<>();
        Product bean1 = new Product("","酵梅",1.00d,0);
        Product bean2 = new Product("","酵梅",1.00d,0);
        list.add(bean1);
        list.add(bean2);
        mMultipleStatusView.showContent();
        setData(true,list);
    }

    @Override
    public void init() {
        super.init();
        mShopCart = new ShopCart();
        PopupDishAdapter dishAdapter = new PopupDishAdapter(this, mShopCart);
        mRecyclerView.setAdapter(dishAdapter);
        dishAdapter.setShopCartImp(this);
        showTotalPrice();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void add(View view, int position) {
        Log.e(TAG,"position: " + position);
            int[] addLocation = new int[2];
            int[] cartLocation = new int[2];
            int[] recycleLocation = new int[2];
            view.getLocationInWindow(addLocation);
            shoppingCart.getLocationInWindow(cartLocation);
            mRecyclerView.getLocationInWindow(recycleLocation);

            PointF startP = new PointF();
            PointF endP = new PointF();
            PointF controlP = new PointF();

            startP.x = addLocation[0];
            startP.y = addLocation[1]-recycleLocation[1];
            endP.x = cartLocation[0];
            endP.y = cartLocation[1]-recycleLocation[1];
            controlP.x = endP.x;
            controlP.y = startP.y;

            final FakeAddImageView fakeAddImageView = new FakeAddImageView(this);
            mainLayout.addView(fakeAddImageView);
            fakeAddImageView.setImageResource(R.drawable.ic_shop_cart1);
            fakeAddImageView.getLayoutParams().width = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
            fakeAddImageView.getLayoutParams().height = getResources().getDimensionPixelSize(R.dimen.item_dish_circle_size);
            fakeAddImageView.setVisibility(View.VISIBLE);
            ObjectAnimator addAnimator = ObjectAnimator.ofObject(fakeAddImageView, "mPointF", new PointFTypeEvaluator(controlP), startP, endP);
            addAnimator.setInterpolator(new AccelerateInterpolator());
            addAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {
                    fakeAddImageView.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    fakeAddImageView.setVisibility(View.GONE);
                    mainLayout.removeView(fakeAddImageView);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            ObjectAnimator scaleAnimatorX = new ObjectAnimator().ofFloat(shoppingCart,"scaleX", 0.6f, 1.0f);
            ObjectAnimator scaleAnimatorY = new ObjectAnimator().ofFloat(shoppingCart,"scaleY", 0.6f, 1.0f);
            scaleAnimatorX.setInterpolator(new AccelerateInterpolator());
            scaleAnimatorY.setInterpolator(new AccelerateInterpolator());
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(scaleAnimatorX).with(scaleAnimatorY).after(addAnimator);
            animatorSet.setDuration(800);
            animatorSet.start();

            showTotalPrice();
    }

    @Override
    public void remove(View view, int position) {
        showTotalPrice();
    }

    @Override
    public void dismiss() {
        showTotalPrice();
        adapter.notifyDataSetChanged();
    }

    private void showTotalPrice(){
        if(mShopCart!=null && mShopCart.getShoppingTotalPrice()>0){
            shoppingCartTotalTv.setVisibility(View.VISIBLE);
            shoppingCartTotalTv.setText("￥ "+ mShopCart.getShoppingTotalPrice()+"");
            shoppingCartTotalNum.setVisibility(View.VISIBLE);
            shoppingCartTotalNum.setText(""+ mShopCart.getShoppingAccount()+"");
        }else {
            shoppingCartTotalTv.setVisibility(View.GONE);
            shoppingCartTotalNum.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.shopping_cart_layout)
    public void showShopCart(){
        if(mShopCart!=null && mShopCart.getShoppingAccount()>0){
            ShopCartDialog dialog = new ShopCartDialog(this,mShopCart,R.style.ShopCartDialog);
            Window window = dialog.getWindow();
            dialog.setShopCartDialogImp(this);
            dialog.setCanceledOnTouchOutside(true);
            dialog.setCancelable(true);
            dialog.show();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.gravity = Gravity.BOTTOM;
            params.dimAmount =0.5f;
            window.setAttributes(params);
        }
    }

    /*@Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        super.onItemClick(adapter, view, position);
        RxToast.showShort("商品详情页");
    }*/

    /** 返回*/
    @OnClick(R.id.action_back)
    public void actionBack(){
        finish();
    }

    /** 个人中心*/
    @OnClick(R.id.action_ucenter)
    public void actionUcenter(){
        startActivity(SmartRetailUCenterActivity.class);
    }
}