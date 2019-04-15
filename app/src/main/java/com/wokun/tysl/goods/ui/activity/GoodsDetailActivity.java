package com.wokun.tysl.goods.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.shantoo.common.utils.AppManager;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.PinjiaGoodsListActivity;
import com.wokun.tysl.article.ui.PinjiaServiceListActivity;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.EvaAdapter;
import com.wokun.tysl.dietician.bean.EvalDataBean;
import com.wokun.tysl.goods.bean.GoodsDetailBean;
import com.wokun.tysl.goods.controller.GoodsDetailMgr;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.Store;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.other.controler.ActionMgr;
import com.wokun.tysl.shopcart.ShopCartMgr;
import com.wokun.tysl.store.ui.StoreIndexActivity;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.utils.NoKartunLinearLayoutManager;
import com.wokun.tysl.utils.ShareSDKUtil;

import java.util.HashMap;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

//商品详情
public class GoodsDetailActivity extends BaseBindingActivity {

    @BindString(R.string.tysl_goods_detail)
    String title;
    @BindString(R.string.tysl_sold)
    String tyslStringSold;

    @BindView(R.id.txtPinjia)
    TextView txtPinjia;
    @BindView(R.id.txtShangpin)
    TextView txtShangpin;
    @BindView(R.id.txtDetails)
    TextView txtDetails;

    @BindView(R.id.txtShangpin_line)
    View txtShangpin_line;
    @BindView(R.id.txtPinjia_line)
    View txtPinjia_line;
    @BindView(R.id.txtDetails_line)
    View txtDetails_line;
    @BindView(R.id.lvp_pager)
    LoopViewPager mLoopViewPager;
    @BindView(R.id.bridge_web_view)
    BridgeWebView bridgeWebView;

    @BindView(R.id.ic_myback)
    ImageView icMyback;
    @BindView(R.id.share_goods_detail)
    ImageView share_goods_detail;


    @BindView(R.id.store_logo)
    ImageView storeLogo;

    @BindView(R.id.rela_pinjia)
    RelativeLayout relaPinjia;
    @BindView(R.id.rela_shangpin)
    RelativeLayout relaShangpin;
    @BindView(R.id.rela_tupian)
    RelativeLayout relaTupian;
    @BindView(R.id.myScrollview)
    ScrollView myScrollview;

    @BindViews({R.id.goods_name, R.id.goods_price, R.id.goods_freight, R.id.month_salesnum,
            R.id.store_address, R.id.store_name, R.id.user_evaluation, R.id.tv_collect,
            R.id.empty_evaluate, R.id.action_collection_of_store, R.id.all_messageshow})
    TextView[] mTextViews;

    @BindView(R.id.siv_collect)
    SelectorImageView sivCollect;//商品收藏

    @BindView(R.id.recycler_view_evaluate_number)
    RecyclerView recyclerViewEvaluateNumber;

    private int favorite;
    private GoodsDetailBean data;
    private String shareTitle, shareUrl, shareImagePath;
    private EvaAdapter mEvaAdapter;
    private String goodsId;

    @Override
    public int createView() {
        return R.layout.activity_goods_detail;
    }

    @Override
    public WidgetBar createToolBar() {
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;


    }

    @Override
    public void init() {
        mMultipleStatusView.showLoading();
        mEvaAdapter = new EvaAdapter(R.layout.item_eval, null);
        recyclerViewEvaluateNumber.setLayoutManager(new NoKartunLinearLayoutManager(this));
        recyclerViewEvaluateNumber.setAdapter(mEvaAdapter);
        mLoopViewPager.setMinimumHeight(UIUtil.getScreenWidth());
        loadData();
        icMyback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        txtPinjia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScrollview.smoothScrollTo(0, relaPinjia.getTop());
                myScrollview.smoothScrollTo(0, relaShangpin.getTop());
                txtShangpin_line.setBackgroundColor(getResources().getColor(R.color.white));
                txtPinjia_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                txtDetails_line.setBackgroundColor(getResources().getColor(R.color.white));


            }
        });
        txtDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScrollview.smoothScrollTo(0, relaTupian.getTop());
                txtShangpin_line.setBackgroundColor(getResources().getColor(R.color.white));
                txtPinjia_line.setBackgroundColor(getResources().getColor(R.color.white));
                txtDetails_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });
        txtShangpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScrollview.smoothScrollTo(0, relaShangpin.getTop());
                txtShangpin_line.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                txtPinjia_line.setBackgroundColor(getResources().getColor(R.color.white));
                txtDetails_line.setBackgroundColor(getResources().getColor(R.color.white));

            }
        });
    }

    private void loadData() {

        System.out.println("=TypeBean设置=positionloadData== "+ getIntent().getIntExtra(Constants.GOODS_ID, -1));
        PostRequest postRequest = OkGo.<BaseResponse<GoodsDetailBean>>post(Constants.BASE_URL + Constants.GOODS_DETAIL_URL);
        if (TyslApp.getInstance().isLogin()) {
            User user = TyslApp.getInstance().getUser();
            postRequest.params(Constants.USER_ID, user.getUserId());
        }
        OkGo.<BaseResponse<GoodsDetailBean>>post(Constants.BASE_URL + Constants.GOODS_DETAIL_URL)
                .tag(this)
                .params(Constants.GOODS_ID, getIntent().getIntExtra(Constants.GOODS_ID, -1))
                .execute(new JsonCallback<BaseResponse<GoodsDetailBean>>(mMultipleStatusView) {
                    @Override
                    public void onSuccess(Response<BaseResponse<GoodsDetailBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            data = (GoodsDetailBean) body.getData();
                            if (data == null) {
                                return;
                            }
                            Store store = data.getStore();
                            if (store == null) {
                                return;
                            }
                            ImageLoader.loadImage(store.getStore_logo(), storeLogo);
                            mTextViews[2].setText(0 == data.getGoodsFreight() ? "运费:按重量" : "运费:包邮");
                            mTextViews[0].setText(data.getGoodsName());
                            mTextViews[1].setText("¥" + data.getGoodsPrice() + "");
                            mTextViews[3].setText("销量:" + data.getMonthSalesNum() + "");
                            mTextViews[4].setText(store.getAddress());
                            mTextViews[5].setText(store.getStore_name());

                            if (1 == data.getFavorite()) {
                                sivCollect.toggle(true);
                                mTextViews[7].setText("已收藏");
                            } else {
                                sivCollect.toggle(false);
                                mTextViews[7].setText("收藏");
                            }

                            favorite = data.getStoreFavorite();

                            mLoopViewPager.setImgData(data.getGoodMobileImages());
                            mLoopViewPager.start();
                            bridgeWebView.loadUrl(data.getGoodMobileDetail());

                            //用户评价
                            mTextViews[6].setText("用户评价(" + data.getEvalutions().getNums() + ")");

                            List<EvalDataBean> list = data.getEvalutions().getContent();
                            if (list == null || list.size() == 0) {
                                mTextViews[8].setVisibility(View.VISIBLE);
                                mTextViews[10].setVisibility(View.GONE);
                            } else {
                                mEvaAdapter.setNewData(list);
                            }
                            goodsId = data.getGoodsId();
                            shareTitle = data.getGoodsName();
                            shareUrl = data.getShareUrl();
                            shareImagePath = data.getGoodsImage();
                        }
                    }
                });
    }


    /**
     * 查看全部评价
     * 商品评价
     */
    @OnClick({R.id.all_messageshow})
    public void actionCheckShoppingCart(View view) {
        Log.e("1112423", "312");
        Intent intent = new Intent();
        intent.putExtra(Constants.GOODS_ID, getIntent().getIntExtra(Constants.GOODS_ID, -1));
        intent.setClass(this, PinjiaGoodsListActivity.class);
        startActivity(intent);

    }


    /**
     * 加入购物车
     */
    @OnClick({R.id.action_join_shopping_cart})
    public void actionJoinShoppingCart(View view) {
        if (ShopCartMgr.getInstance().isCanJoinShopCart()) {
            ShopCartMgr.getInstance().cartAddGoods(this, getIntent().getIntExtra(Constants.GOODS_ID, -1), "");
        } else {
            RxToast.showShort("请稍后再试");
        }
    }

    /**
     * 跳转购物车
     */
    @OnClick({R.id.action_shopping_cart})
    public void actionShoppingCart(View view) {

        if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            AppManager.getAppManager().finishAllActivity();
            TyslApp.getInstance().setRefreshShopCart(true);
            TyslApp.getInstance().setTabPosition(Constants.TAB_POSITION_SHOP_CART);

        }

        //    GoodsDetailMgr.getInstance().actionGoShopCart();


    }

    /**
     * 分享商品
     */

    @OnClick({R.id.share_goods_detail})
    public void actionShareGoods(View view) {
        //  ShareSDKUtil.showShare(TyslApp.getContext(), shareTitle, "", shareUrl, shareImagePath);

        Log.e("12343222", "onComplete" + shareTitle + "####" + "####" + shareUrl + "####" + shareImagePath);

        //   ShareSDKUtil.showShare(TyslApp.getContext(), shareTitle, shareText, shareUrl, shareImagePath);
        OnekeyShare oks = new OnekeyShare();
        oks.disableSSOWhenAuthorize();
        oks.setSilent(true);
        oks.setImageUrl(shareImagePath);
        oks.setText("");
        oks.setTitle(shareTitle);
        oks.setTitleUrl(shareUrl);
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.i("1234", "onComplete");
                Toast.makeText(GoodsDetailActivity.this, "成功了", Toast.LENGTH_SHORT).show();
                OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.INTEGRAL_SHARE)
                        .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.INTEGRAL_SHARE) {
                            @Override
                            public void onSuccess(Response<BaseResponse<Object>> response) {
                                BaseResponse body = response.body();
                                if (body == null) return;
                                RxToast.showShort(body.getMsg());
                                if (body.isState()) {
                                }
                            }
                        });
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Log.i("1234", throwable.getMessage());
                throwable.printStackTrace();

            }

            @Override
            public void onCancel(Platform platform, int i) {
                Log.i("1234", "onCancel");

            }
        });

        // 启动分享GUI
        oks.show(GoodsDetailActivity.this);

    }


    /**
     * 进入店铺,R.id.action_store
     */
    @OnClick({R.id.action_go_store})
    public void actionGoStore(View view) {
        GoodsDetailMgr.getInstance().actionGoStore(this, data.getStore().getStore_id());
    }

    /**
     * 立即购买
     */
    @OnClick({R.id.now_buy})
    public void actionNowBuy(View view) {
        if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            Intent intent = new Intent(GoodsDetailActivity.this, GoodsOrderConfirmationActivity4.class);
            intent.putExtra(Constants.GOODS_ID, goodsId);
            startActivity(intent);
        }
    }


    /**
     * 拨打客服
     */
    @OnClick(R.id.action_service)
    public void actionService(View view) {
        ActionMgr.getInstance().callService(this);
    }

    /**
     * 收藏商品
     */
    @OnClick(R.id.action_collection_of_goods)
    public void actionCollectionOfGoods(View view) {
        GoodsDetailMgr.getInstance().actionFavorites(this, Constants.FAVORITES_TYPE_GOODS, getIntent().getIntExtra(Constants.GOODS_ID, -1), sivCollect, mTextViews[7]);
    }

    /**
     * 收藏店铺
     */
    @OnClick(R.id.action_collection_of_store)
    public void actionCollectionOfStore(View view) {
        actionCollectionOfStore(data.getStore().getStore_id());
    }

    private void actionCollectionOfStore(String storeId) {
        if (!TyslApp.getInstance().isLogin()) {
            startActivity(LoginActivity.class);
            return;
        }
        if (1 == favorite) {//已收藏店铺,则取消收藏
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)
                    .tag(this)
                    .params(Constants.TYPE, Constants.FAVORITES_TYPE_STORE)
                    .params(Constants.SOURCE_ID, storeId)
                    .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.FAVORITES_DELETE_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<Object>> response) {
                            BaseResponse body = response.body();
                            if (body == null) return;
                            RxToast.showShort(body.getMsg());
                            favorite = 0;
                            /*if(body.isState()){
                                Drawable drawable= getResources().getDrawable(R.drawable.ic_un_polygon_collect);
                                /// 这一步必须要做,否则不会显示.
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                mTextViews[9].setCompoundDrawables(drawable,null,null,null);
                            }*/
                        }
                    });
        } else {//未收藏店铺,则添加收藏
            OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)
                    .tag(this)
                    .params(Constants.TYPE, Constants.FAVORITES_TYPE_STORE)
                    .params(Constants.SOURCE_ID, storeId)
                    .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.FAVORITES_ADD_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<Object>> response) {
                            BaseResponse body = response.body();
                            if (body == null) return;
                            RxToast.showShort(body.getMsg());
                            favorite = 1;
                            /*if(body.isState()){
                                Drawable drawable= getResources().getDrawable(R.drawable.ic_polygon_collect);
                                /// 这一步必须要做,否则不会显示.
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                mTextViews[9].setCompoundDrawables(drawable,null,null,null);
                            }*/
                        }
                    });
        }
    }

    @Override
    protected void onDestroy() {
        if (mLoopViewPager != null) {
            mLoopViewPager.stop();
        }
        OkGo.getInstance().cancelTag(this);
        super.onDestroy();
    }
}