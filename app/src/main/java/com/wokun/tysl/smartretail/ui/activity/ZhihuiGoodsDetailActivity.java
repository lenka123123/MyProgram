package com.wokun.tysl.smartretail.ui.activity;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.view.BridgeWebView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.PinjiaServiceListActivity2;
import com.wokun.tysl.base.BaseBindingActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.EvaAdapter;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.other.controler.ActionMgr;
import com.wokun.tysl.smartretail.adapter.RetailStorageRackAdapter;
import com.wokun.tysl.smartretail.bean.Eval;
import com.wokun.tysl.smartretail.bean.GoodsDataBean;
import com.wokun.tysl.smartretail.bean.GoodsInfo;
import com.wokun.tysl.smartretail.bean.GoodsSetteleBean;
import com.wokun.tysl.smartretail.bean.ZhihuiGoodsDetailBean;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.utils.NoKartunLinearLayoutManager;
import com.wokun.tysl.widget.shopcartdialog.ShopCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

//智慧商品详情
public class ZhihuiGoodsDetailActivity extends BaseBindingActivity {

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

    @BindView(R.id.lvp_pager)
    LoopViewPager mLoopViewPager;
    @BindView(R.id.bridge_web_view)
    BridgeWebView bridgeWebView;

    @BindView(R.id.ic_myback)
    ImageView icMyback;

    @BindView(R.id.store_logo)
    ImageView storeLogo;
    @BindView(R.id.zhihui_img_detail)
    ImageView zhihuiImgDetail;
    @BindView(R.id.rela_pinjia)
    RelativeLayout relaPinjia;
    @BindView(R.id.rela_shangpin)
    RelativeLayout relaShangpin;
    @BindView(R.id.rela_tupian)
    RelativeLayout relaTupian;
    @BindView(R.id.myScrollview)
    ScrollView myScrollview;
    @BindView(R.id.headimgurl)
    ImageView headimgurl;
    @BindView(R.id.zhihui_pinjia)
    LinearLayout zhihuiPinjia;
    @BindView(R.id.eid)
    RatingBar mRatingBar;


    @BindViews({R.id.goods_name, R.id.goods_price, R.id.goods_freight, R.id.month_salesnum, R.id.store_address, R.id.store_name, R.id.user_evaluation, R.id.tv_collect, R.id.empty_evaluate, R.id.action_collection_of_store, R.id.username, R.id.zhihui_pinjia_title, R.id.zhihui_pinjia_time,
            R.id.action_check_more_goods_order, R.id.zhihui_pinjia_all})
    TextView[] mTextViews;
/*
   @BindView(R.id.siv_collect)
    SelectorImageView sivCollect;//商品收藏*/

    @BindView(R.id.recycler_view_evaluate_number)
    RecyclerView recyclerViewEvaluateNumber;
    private RetailStorageRackAdapter dataAdapter;
    // private int favorite;
    private ZhihuiGoodsDetailBean data;
    private ShopCart mShopCart;
    //   private String shareTitle, shareUrl, shareImagePath;
    private EvaAdapter mEvaAdapter;
    private String gid;
    //  private GoodsDataBean item;

    @Override
    public int createView() {
        return R.layout.activity_goods_detail2;
    }

    @Override
    public WidgetBar createToolBar() {
     /*   return mWidgetBar
                .setWidgetBarTitle(title)
                .setMenu(R.drawable.ic_share_black,0)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShareSDKUtil.showShare(TyslApp.getContext(), shareTitle, "", shareUrl, shareImagePath);
                    }
                },null);*/
        mWidgetBar.setVisibility(View.GONE);
        return mWidgetBar;


    }

    @Override
    public void init() {
        mShopCart = new ShopCart();
        //  goodsDataBean = new GoodsDataBean();
        Intent intent = getIntent();
        gid = intent.getStringExtra("gid");
        Log.e("gid", gid);
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
            }
        });
        txtDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScrollview.smoothScrollTo(0, relaTupian.getTop());
            }
        });
        txtShangpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myScrollview.smoothScrollTo(0, relaShangpin.getTop());
            }
        });
     /*   mTextViews[12].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


    }

    private void loadData() {
        //     PostRequest postRequest = OkGo.<BaseResponse<GoodsDetailBean>>post(Constants.BASE_URL + Constants.GOODS_DETAIL_INFO);
        /*if (TyslApp.getInstance().isLogin()) {
            User user = TyslApp.getInstance().getUser();
            postRequest.params(Constants.USER_ID, user.getUserId());
        }*/
        OkGo.<BaseResponse<ZhihuiGoodsDetailBean>>post(Constants.BASE_URL + Constants.GOODS_DETAIL_INFO)
                .tag(this)
                .params("gid", gid)
                .params(Constants.STORE_CODE, getIntent().getIntExtra(Constants.STORE_CODE, -1))
                .execute(new JsonCallback<BaseResponse<ZhihuiGoodsDetailBean>>(mMultipleStatusView) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ZhihuiGoodsDetailBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            data = (ZhihuiGoodsDetailBean) body.getData();
                            if (data == null) {
                                return;
                            }
                            GoodsInfo store = data.getGoodsInfo();
                            //List<GoodsDataBean> goodsDataBean=new ArrayList<>();
                            GoodsDataBean goodsDataBean1 = new GoodsDataBean();
                            goodsDataBean1.setGoodsName(store.getGoods_name());
                            goodsDataBean1.setGoodsPicture(store.getGoods_picture());
                            goodsDataBean1.setGoodsDetail(store.getGoods_detail());
                            goodsDataBean1.setGoodsPrice(store.getGoods_price());
                            goodsDataBean1.setProductAmount(1);
                            goodsDataBean1.setProductRemain(100);
                            goodsDataBean1.setGid(store.getGid());
                            //goodsDataBean.add(goodsDataBean1);

                            mShopCart.addShoppingSingle(goodsDataBean1);//这里就把数据加进去了


                            if (store == null) {
                                return;
                            }

                            //  ImageLoader.loadImage(store.getGoods_picture(), storeLogo);
                            //   mTextViews[2].setText(0 == data.getGoodsFreight() ? "运费:按重量" : "运费:包邮");
                            mTextViews[2].setText("运费:包邮");
                            mTextViews[0].setText(store.getGoods_name());
                            mTextViews[1].setText("¥" + store.getGoods_price() + "");
                            mTextViews[3].setText("销量:" + store.getSale_num() + "");
                            mTextViews[13].setText("好评度：" + data.getHaoping());
                            ImageLoader.loadImage(store.getGoods_picture(), zhihuiImgDetail);
                            mLoopViewPager.setVisibility(View.GONE);
                            Log.e("jiazaitupian", data.getShare_url());
                            //用户评价
                            mTextViews[6].setText("用户评价(" + data.getNums() + ")");
                            bridgeWebView.loadUrl(store.getGoods_detail());
                            Eval eval = data.getEval();
                            if (eval == null) {
                                mTextViews[8].setVisibility(View.VISIBLE);
                                zhihuiPinjia.setVisibility(View.GONE);
                            } else {
                                ImageLoader.loadImage(eval.getAvatar(), headimgurl);
                                mTextViews[10].setText(eval.getUsername());
                                mTextViews[11].setText(eval.getEvalution_text());
                                mTextViews[12].setText(eval.getEvalution_time());
                                mRatingBar.setStarCount(Integer.parseInt(eval.getEvalution_score1()));
                            }

                        }
                    }
                });
    }

/*    *//**
     * 加入购物车
     *//*
    @OnClick({R.id.action_join_shopping_cart})
    public void actionJoinShoppingCart(View view) {
        if (ShopCartMgr.getInstance().isCanJoinShopCart()) {
            ShopCartMgr.getInstance().cartAddGoods(this, getIntent().getIntExtra(Constants.GOODS_ID, -1), "");
        } else {
            RxToast.showShort("请稍后再试");
        }
    }*/

    /**
     * 智慧零售评价
     */
    @OnClick({R.id.zhihui_pinjia_all})
    public void actionZhuipinjia(View view) {
        Intent intent1 = new Intent(ZhihuiGoodsDetailActivity.this, PinjiaServiceListActivity2.class);
        intent1.putExtra("gid", gid);
        startActivity(intent1);
    }

    /**
     * 跳转店铺
     */
    @OnClick({R.id.action_store})
    public void actionShoppingCart(View view) {
        //   GoodsDetailMgr.getInstance().actionGoStore();
        finish();
    }

    /**
     * 进入店铺,R.id.action_store
     */
 /*   @OnClick({R.id.action_go_store})
    public void actionGoStore(View view) {
        GoodsDetailMgr.getInstance().actionGoStore(this, data.getStore().getStore_id());
    }*/


    /**
     * 智慧零售立即购买,R.id. buy_now
     */
    @OnClick({R.id.buy_now})
    public void actionGoBuy(View view) {
        //在这传值（你选择的商品数据呢,x选择的商品单条和集合，都要能打印出来啊）

        Log.e("2232213622222", "11111121112212131" + gid);
        ArrayList<GoodsSetteleBean> myData = new ArrayList<>();
        Set<GoodsDataBean> goodsKey = mShopCart.getShoppingSingleMap().keySet();
        for (GoodsDataBean key :
                goodsKey) {
            Integer integer = mShopCart.getShoppingSingleMap().get(key);
            GoodsSetteleBean goodsSetteleBean = new GoodsSetteleBean(key, integer);
            myData.add(goodsSetteleBean);
        }
        Intent intent = new Intent(ZhihuiGoodsDetailActivity.this, SmartRetailOrderListActivity.class);
        Log.e("2232252", "" + myData.size());
        intent.putExtra("data", myData);
        // intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) myData);
        String stringExtra = getIntent().getStringExtra(Constants.STORE_CODE);
        intent.putExtra(Constants.STORE_CODE, stringExtra);
        startActivity(intent);


    }


    /**
     * 拨打客服
     */
    @OnClick(R.id.action_service)
    public void actionService(View view) {
        ActionMgr.getInstance().callService(this);
    }

 /*   *//**
     * 收藏商品
     *//*
    @OnClick(R.id.action_collection_of_goods)
    public void actionCollectionOfGoods(View view) {
        GoodsDetailMgr.getInstance().actionFavorites(this, Constants.FAVORITES_TYPE_GOODS, getIntent().getIntExtra(Constants.GOODS_ID, -1), sivCollect, mTextViews[7]);
    }*/

 /*   */

    /**
     * 收藏店铺
     *//*
    @OnClick(R.id.action_collection_of_store)
    public void actionCollectionOfStore(View view) {
        actionCollectionOfStore(data.getStore().getStore_id());
    }*/
    private void actionCollectionOfStore(String storeId) {
        if (!TyslApp.getInstance().isLogin()) {
            startActivity(LoginActivity.class);
            return;
        }
     /*   if (1 == favorite) {//已收藏店铺,则取消收藏
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
                            *//*if(body.isState()){
                                Drawable drawable= getResources().getDrawable(R.drawable.ic_un_polygon_collect);
                                /// 这一步必须要做,否则不会显示.
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                mTextViews[9].setCompoundDrawables(drawable,null,null,null);
                            }*//*
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
                            *//*if(body.isState()){
                                Drawable drawable= getResources().getDrawable(R.drawable.ic_polygon_collect);
                                /// 这一步必须要做,否则不会显示.
                                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                mTextViews[9].setCompoundDrawables(drawable,null,null,null);
                            }*//*
                        }
                    });
        }*/
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