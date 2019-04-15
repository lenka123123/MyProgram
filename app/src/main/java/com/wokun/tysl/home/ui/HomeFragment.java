package com.wokun.tysl.home.ui;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.loopviewpager.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.ResourceUtil;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.OnSearchListener;
import com.shantoo.widget.toolbar.WidgetBar;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.ArticleListActivity;
import com.wokun.tysl.asset.ui.activity.AssetIndexActivity;
import com.wokun.tysl.base.BaseFragment1;
import com.wokun.tysl.base.SimpleWebViewActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.ui.activity.DieticianIndexActivity;
import com.wokun.tysl.goods.ui.activity.GoodsDetailActivity;
import com.wokun.tysl.goods.ui.activity.GoodsListActivity;
import com.wokun.tysl.home.adapter.RecommendAdapter;
import com.wokun.tysl.home.bean.BannerBean;
import com.wokun.tysl.home.bean.HeadDataBean;
import com.wokun.tysl.home.bean.HomeBodyBean;
import com.wokun.tysl.home.bean.NoticeBean;
import com.wokun.tysl.home.bean.StoreTypeBean;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.cache.AppCache;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.other.ui.MessageControlActivity;
import com.wokun.tysl.serviceinto.ui.ServiceAuthenticationaginActivity;
import com.wokun.tysl.smartretail.adapter.RetailStorageRackAdapter;
import com.wokun.tysl.smartretail.bean.GoodsDataBean;
import com.wokun.tysl.smartretail.bean.RetailShop;
import com.wokun.tysl.smartretail.ui.activity.SmartRetailIndexActivity;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.widget.shopcartdialog.ShopCart;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import qiu.niorgai.StatusBarCompat;

/**
 * 首页
 */

public class HomeFragment extends BaseFragment1 {

    @BindView(R.id.toolbar) WidgetBar mWidgetBar;
    @BindView(R.id.lvp_pager) LoopViewPager loopViewPager;
    @BindView(R.id.appbar)AppBarLayout mAppBarLayout;
    @BindView(R.id.status_bar) View statusBar;
    //@BindView(R.id.main_content)CoordinatorLayout mainContent;
    @BindView(R.id.collapsing_toolbar)CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.speaker) TextView speaker;
    @BindView(R.id.marqueeView) MarqueeView marqueeView;
    @BindView(R.id.action_tian_tuan)ImageView ivTianTuan;
    @BindView(R.id.action_faheihei)ImageView ivFaHeiHei;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerViews;
    @BindView(R.id.recycler_view3) RecyclerView mRecyclerViews3;//慧零售
    @BindViews({R.id.dietitian_picture, R.id.jk365_picture, R.id.ad_picture}) ImageView[] mImageViews;

    private HeadDataBean.LvJu lvJu;
    private String url,tianTuanUrl;
    private int faHeiHeiGoodsId; //发黑黑商品id
    private RecommendAdapter mRecommendAdapter;
    private int dietitian_trticle_type,healthy365_trticle_type;
    private boolean flag = false; //是否已经发出跳转智慧零售请求，避免发出多次请求，默认false
    //慧零售推荐
    private RetailStorageRackAdapter dataAdapter;
    private ArrayList<GoodsDataBean> dataList;
    private ShopCart mShopCart;

    @Override
    public int createView() {
        return R.layout.fragment_main_home;
    }

    @Override
    public void initToolBar() {
        /*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);
        CollapsingToolbarLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(
        CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = ResourceUtil.getStatusBarHeight(getContext());
        mWidgetBar.setLayoutParams(params);*/
        //mWidgetBar.setWidgetBarTitle("首页")
        //.setWidgetBarTitleTextColor(UIUtil.getColor(R.color.colorPrimary));
        CollapsingToolbarLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(
        CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = ResourceUtil.getStatusBarHeight(getContext());
        //mWidgetBar.setLayoutParams(params);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);

        mWidgetBar.getSearchView().setHintTextColor(UIUtil.getColor(R.color.color77));
        mWidgetBar.showSearchView()
            .setMenu(R.drawable.ic_message_white, 0)
            .setOnSearchListener(new OnSearchListener() {
                @Override
                public void onSearch() {
                    String keywords = mWidgetBar.getSearchView().getText().toString().trim();
                    Intent intent = new Intent();
                    intent.setClass(getContext(), GoodsListActivity.class);
                    intent.putExtra(Constants.KEYWORDS, keywords);
                    startActivity(intent);
                }
            })
            .setOnMenuClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(MessageControlActivity.class, !TyslApp.getInstance().isLogin());
                }
            },null);
    }

    @Override
    public void initViews() {
        setHasOptionsMenu(true);
        //StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), 0, null);
        StatusBarCompat.setStatusBarColorForCollapsingToolbar(getActivity(), mAppBarLayout, mCollapsingToolbarLayout, mWidgetBar, UIUtil.getColor(R.color.colorPrimary));
        initRecommendView();
        initZHihuiView();
        initRecyclerViewPerson();

        //慧零售推荐
        mShopCart = new ShopCart();
        dataList = new ArrayList<>();
    }


    private void initRecyclerViewPerson() {







    }

    @Override
    public void loadData() {
        loadHomeHeadData();
        loadHomeBodyData();
        loadZhiHuiData();
    }

    private void loadZhiHuiData() {
        mMultipleStatusView.showLoading();
        //Log.e(TAG,Constants.STORE_CODE + getIntent().getStringExtra(Constants.STORE_CODE));

        OkGo.<BaseResponse<RetailShop>>post(Constants.BASE_URL + Constants.RETAIL_SHOP_URL)
                .tag(this)
                .params(Constants.STORE_CODE , "000XT")
                .execute(new JsonCallback<BaseResponse<RetailShop>>(Constants.WITH_TOKEN,Constants.RETAIL_SHOP_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<RetailShop>> response) {
                        mMultipleStatusView.showContent();
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            RetailShop data = (RetailShop) body.getData();
                            Log.e(TAG, JSONUtil.toJSON(data));
                            if(data == null){
                                mMultipleStatusView.showEmpty();
                                return;
                            }
                            final String buyStoreCode = data.getBuyStoreCode();
                            final String storeCode = data.getStoreCode();
                            /*if(TextUtils.isEmpty(buyStoreCode)){//未绑定过,跳转到storeCode店铺
                                storeTitle.setText("No: "+storeCode+"");
                            }else{//绑定过
                                //storeCode与buyStoreCode不等，要弹框提示要跳转到buyStoreCode店铺里，
                                if(!storeCode.equals(buyStoreCode)){
                                    String content = "你在货架No:"+buyStoreCode+"已经购买过商品，属于该店铺的会员，即将为你跳转到该店铺";
                                    DialogUtil.showDialog(SmartRetailStorageRackActivity1.this,content,"确定",
                                            new MaterialDialog.SingleButtonCallback() {
                                                @Override
                                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                                    storeTitle.setText("No: "+buyStoreCode+"");
                                                }
                                            });
                                }else{//跳转到storeCode店铺
                                    storeTitle.setText("No: "+storeCode+"");
                                }
                            }*/

                            List<GoodsDataBean> list = data.getGoodsData();
                            List<GoodsDataBean> list2 = new ArrayList<>();
                            if(list!=null){
                                for(int i=0;i<list.size();i++){
                                    GoodsDataBean goodsDataBean = list.get(i);
                                    goodsDataBean.setProductAmount(Integer.MAX_VALUE);
                                    list2.add(goodsDataBean);
                                }
                            }
                            dataList.addAll(list2);
                        }
                    }
                });



    }

    /** 初始化智慧零售推荐 */
    private void initZHihuiView() {
        mRecyclerViews3.setLayoutManager(new GridLayoutManager(getContext(), 2));
     //   dataAdapter = new RetailStorageRackAdapter(R.layout.right_dish_item, dataList,mShopCart);
        dataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(R.id.right_product_add == view.getId()){
                    GoodsDataBean item = (GoodsDataBean) adapter.getData().get(position);
                    if(mShopCart.addShoppingSingle(item)) {
                  /*      dataAdapter.notifyItemChanged(position);
                            add(view,position);
                        if(shoppingCartLayout.getVisibility() == View.INVISIBLE){
                            shoppingCartLayout.setVisibility(View.VISIBLE);
                        }
                        if(totalPriceNumTextView.getVisibility() == View.INVISIBLE){
                            totalPriceNumTextView.setVisibility(View.VISIBLE);
                        }
                        if(bottomLayout.getVisibility() == View.INVISIBLE){
                            bottomLayout.setVisibility(View.VISIBLE);
                        }*/
                    }
                }
            }
        });
        mRecyclerViews3.setAdapter(dataAdapter);


    }



    /** 初始化营养师推荐 */
    private void initRecommendView() {
        mRecommendAdapter = new RecommendAdapter(R.layout.item_home_recommended, null);
        mRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreTypeBean bean = (StoreTypeBean) adapter.getData().get(position);
                if(bean==null)return;
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, bean.getGoodsId());
                intent.setClass(getContext(), GoodsDetailActivity.class);
                startActivity(intent);
                //RxToast.showShort("营养师推荐");
            }
        });
        mRecyclerViews.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViews.setAdapter(mRecommendAdapter);
        mRecyclerViews.setNestedScrollingEnabled(false);
    }

    private void loadHomeHeadData() {
        OkGo.<BaseResponse<HeadDataBean>>post(Constants.BASE_URL + Constants.INDEX_HEAD_DATA_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<HeadDataBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<HeadDataBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            HeadDataBean data = (HeadDataBean) body.getData();
                            if(data==null)return;
                            final List<BannerBean> bannerBeanList = data.getBanner();
                            if (bannerBeanList != null) {
                                AppCache.saveBanner(bannerBeanList);
                                loopViewPager.setImgData(AppCache.getBanner());
                                loopViewPager.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int i) {
                                        Intent intent = new Intent();
                                        intent.putExtra(Constants.TITLE, UIUtil.getString(R.string.app_name));
                                        intent.putExtra(Constants.URL, bannerBeanList.get(i%bannerBeanList.size()).getUrl());
                                        intent.setClass(getContext(), SimpleWebViewActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                loopViewPager.start();
                            }

                            lvJu = data.getLvJu();

                            List<NoticeBean> noticeBeanList = data.getNotice();
                            if (noticeBeanList != null) {
                                AppCache.saveNotice(noticeBeanList);
                            }
                            marqueeView.startWithList(AppCache.getNotice());
                        }
                    }
                }
        );
    }

    private void loadHomeBodyData() {
        OkGo.<BaseResponse<HomeBodyBean>>post(Constants.BASE_URL + Constants.INDEX_BODY_DATA_URL)//
                .tag(this)
                .execute(new JsonCallback<BaseResponse<HomeBodyBean>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<HomeBodyBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            //mMultipleStatusView.showContent();
                            HomeBodyBean homeBody = (HomeBodyBean) body.getData();
                            url = homeBody.getAd().getUrl();
                            ImageLoader.loadImage(homeBody.getDietitian().getPicture(), mImageViews[0]);
                            ImageLoader.loadImage(homeBody.getHealthy365().getPicture(), mImageViews[1]);
                            ImageLoader.loadImage(homeBody.getAd().getPicture(), mImageViews[2]);

                            ImageLoader.loadImage(homeBody.getTianTuan().getPicture(), ivTianTuan);
                            ImageLoader.loadImage(homeBody.getFaheihei().getPicture(), ivFaHeiHei);

                            tianTuanUrl = homeBody.getTianTuan().getUrl();
                            faHeiHeiGoodsId = homeBody.getFaheihei().getGoods_id();

                            mRecommendAdapter.setNewData(homeBody.getTuijian());

                            dietitian_trticle_type = homeBody.getDietitian().getType();
                            healthy365_trticle_type = homeBody.getHealthy365().getType();
                        }
                    }
                });
    }

    /**
     * 问服务
     * */
    @OnClick(R.id.action_ask_service)
    public void actionAskService(){
        startActivity(DieticianIndexActivity.class);
    }

    /**
     * 服务入驻
     *
     * */
    @OnClick(R.id.action_service_in)
    public void actionServiceIn(){
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE,1);
      //  intent.setClass(getContext(),DietitianJobTypeActivity.class);ServiceAuthenticationActivity
        intent.setClass(getContext(),ServiceAuthenticationaginActivity.class);
        startActivity(intent,!TyslApp.getInstance().isLogin());
    }

    /**
     * 智慧零售
     * */
    @OnClick(R.id.action_chain_stores)
    public void actionChainStores(){
        /*RxToast.showShort("该功能正在开发当中,敬请期待");
        return;*/

        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(),LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        }else{
            Log.e("我进来了2323215","我进来了1");
            startActivity(SmartRetailIndexActivity.class);
          /*  if(!flag){
                startActivity(SmartRetailIndexActivity.class);
                flag = false;*/
              /*  OkGo.<BaseResponse<RetailIndex>>post(Constants.BASE_URL + Constants.RETAIL_INDEX_URL)
                    .tag(this)
                    .execute(new JsonCallback<BaseResponse<RetailIndex>>(Constants.WITH_TOKEN,Constants.RETAIL_INDEX_URL) {
                        @Override
                        public void onSuccess(Response<BaseResponse<RetailIndex>> response) {
                            flag = true;
                            BaseResponse body = response.body();
                            if(body == null)return;
                            if(body.isState()){
                                RetailIndex data = (RetailIndex) body.getData();
                                Log.e("智慧零售接口接入",JSONUtil.toJSON(data));
                                Log.e(TAG, JSONUtil.toJSON(data));
                                if(Constants.SHOP.equals(data.getOpenPage())){
                                    Intent intent = new Intent();
                                    intent.setClass(getContext(),SmartRetailStorageRackActivity1.class);
                                    intent.putExtra(Constants.STORE_CODE,data.getStoreCode());
                                    startActivity(intent);
                                    flag = false;
                                }else if(Constants.STORE_LIST.equals(data.getOpenPage())){
                                    startActivity(SmartRetailIndexActivity.class);
                                    flag = false;
                                }
                            }
                        }
                    });*/
     //       }
        }
    }

    /**
     * 社交资产
     * */
    @OnClick(R.id.action_social_assets)
    public void actionSocialAssets(){
        startActivity(AssetIndexActivity.class);

    }

    /**
     * 营养师专栏
     * */
    @OnClick(R.id.dietitian_picture)
    public void dietitianPicture(){
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE, dietitian_trticle_type);
        intent.setClass(getContext(), ArticleListActivity.class);
        startActivity(intent);
    }

    /**
     * 健康365专栏
     * */
    @OnClick(R.id.jk365_picture)
    public void jk365Picture(){
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE, healthy365_trticle_type);
        intent.setClass(getContext(), ArticleListActivity.class);
        startActivity(intent);
    }

    /**
     * 太宜食聊专栏
     * */
    @OnClick(R.id.ad_picture)
    public void adPicture(){
        Intent intent = new Intent();
        intent.putExtra(Constants.TITLE, UIUtil.getString(R.string.app_name));
        intent.putExtra(Constants.URL, url);
        intent.setClass(getContext(), SimpleWebViewActivity.class);
        startActivity(intent);
    }

    /**
     * 健康服务天团
     * */
    @OnClick(R.id.action_tian_tuan)
    public void actionTianTuan(){
        Intent intent = new Intent();
        intent.putExtra(Constants.TITLE, "健康服务天团");
        intent.putExtra(Constants.URL, tianTuanUrl);
        intent.setClass(getContext(), SimpleWebViewActivity.class);
        startActivity(intent);
    }

    /**
     * 发黑黑
     * */
    @OnClick(R.id.action_faheihei)
    public void actionFaHeiHei(){
        Intent intent = new Intent();
        intent.putExtra(Constants.GOODS_ID, faHeiHeiGoodsId);
        intent.setClass(getContext(), GoodsDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loopViewPager != null){
            loopViewPager.stop();
        }
        OkGo.getInstance().cancelTag(this);
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            Logger.e(TAG,"onHiddenChanged");
        }
    }*/
}