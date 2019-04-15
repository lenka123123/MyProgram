package com.wokun.tysl.store.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.popupwindow.ActionItem;
import com.shantoo.widget.popupwindow.OnPopupItemClickListener;
import com.shantoo.widget.popupwindow.WidgetPopup;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.OnSearchListener;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.ArticleDetailActivity;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.GoodsListAdapter;
import com.wokun.tysl.goods.bean.GoodsListBean;
import com.wokun.tysl.goods.bean.StoreIndex;
import com.wokun.tysl.goods.response.GoodsListResponse;
import com.wokun.tysl.goods.ui.activity.GoodsDetailActivity;
import com.wokun.tysl.goods.ui.activity.GoodsListActivity;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.Store;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.store.bean.StoreIndexActionItemBean;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.utils.ShareSDKUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

//店铺首页
public class StoreIndexActivity extends BaseRefreshAndLoadMoreActivity<GoodsListBean> implements BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;

    @BindViews({R.id.action_sort_by_z, R.id.action_sort_by_c})
    RelativeLayout[] mRelativeLayout;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.store_logo)
    ImageView storeLogo;

    @BindViews({R.id.siv_sort_by_z, R.id.siv_sort_by_c, R.id.siv_polygon_collect})
    SelectorImageView[] mSelectorImageView;

    @BindViews({R.id.store_name, R.id.tv_sort_by_z, R.id.tv_sort_by_c})
    TextView[] mTextView;

    private StoreIndex data;
    private BaseQuickAdapter<GoodsListBean, BaseViewHolder> adapter;
    private int favorite;
    private final static int Z = 0;
    private final static int S = 1;
    private final static int PU = 2;
    private final static int PD = 3;
    private final static int N = 4;
    private WidgetPopup zPopup, cPopup;
    private String storeId,shareTitle,shareText,shareUrl,shareImagePath;
    private boolean hasFavorites = false;

    @Override
    public int createView() {
        return R.layout.activity_store_index;
    }

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
            .showSearchView()
            .setOnSearchListener(new OnSearchListener() {
            @Override
            public void onSearch() {
                String keywords = mWidgetBar.getSearchView().getText().toString().trim();
                Intent intent = new Intent();
                intent.setClass(StoreIndexActivity.this, GoodsListActivity.class);
                intent.putExtra(Constants.KEYWORDS, keywords);
                startActivity(intent);
            }
        })
        .setMenu(R.drawable.ic_share_black,0)
        .setOnMenuClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("12343222","onComplete"+shareTitle+"####"+shareText+"####"+shareUrl+"####"+shareImagePath);

                //   ShareSDKUtil.showShare(TyslApp.getContext(), shareTitle, shareText, shareUrl, shareImagePath);
                OnekeyShare oks = new OnekeyShare();
                oks.disableSSOWhenAuthorize();
                oks.setSilent(true);
                oks.setImageUrl(shareImagePath);
                oks.setText(shareText);
                oks.setTitle(shareTitle);
                oks.setTitleUrl(shareUrl);
                oks.setCallback(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        Log.i("1234","onComplete");
                        Toast.makeText(StoreIndexActivity.this,"成功了",Toast.LENGTH_SHORT).show();
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
                        Log.i("1234",throwable.getMessage());
                        throwable.printStackTrace();

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {
                        Log.i("1234","onCancel");

                    }
                });

                 // 启动分享GUI
                oks.show(StoreIndexActivity.this);

            }
        },null);
    }

    @Override
    public void init() {
        //StatusBarUtil.setColor(this, UITool.getColor(R.color.colorWhite));
        //StatusBarUtil.setTransparent(this);
        storeId = getIntent().getStringExtra(Constants.STORE_ID);
        loadData();
    }

    private void initPopupView() {
        //初始化综合排序
        zPopup = new WidgetPopup(this)
        .addAction(new ActionItem("综合排序", R.color.color77, R.color.colorPrimary,true))
        .addAction(new ActionItem("销量优先", R.color.color77, R.color.colorPrimary))
        .addAction(new ActionItem("价格从低到高", R.color.color77, R.color.colorPrimary))
        .addAction(new ActionItem("价格从高到低", R.color.color77, R.color.colorPrimary))
        .addAction(new ActionItem("最新发布商品", R.color.color77, R.color.colorPrimary))
        .setItemOnClickListener(new ZSortItemClickListener());

        //初始化分类查找
        List<StoreIndexActionItemBean> mActionItems = data.getStoreClass();
        List<ActionItem> actionItems = new ArrayList<>();
        for(int index=0;index<mActionItems.size();index++){
            StoreIndexActionItemBean ai = mActionItems.get(index);
            if(0==index){
                actionItems.add(new ActionItem(ai.getSgcName(), R.color.color77, R.color.colorPrimary,true));
            }else{
                actionItems.add(new ActionItem(ai.getSgcName(), R.color.color77, R.color.colorPrimary));
            }
        }
        cPopup = new WidgetPopup(this,2)
        .addAction(actionItems)
        .setItemOnClickListener(new CSortItemClickListener());
    }

    private void loadData() {
        multipleStatusView.showLoading();
        OkGo.<BaseResponse<StoreIndex>>post(Constants.BASE_URL + Constants.STORE_INDEX_URL)
                .tag(this)
                .params(Constants.STORE_ID, storeId)
                .execute(new JsonCallback<BaseResponse<StoreIndex>>(Constants.LOGIN_WITH_TOKEN,Constants.STORE_INDEX_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<StoreIndex>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if (body.isState()) {
                            multipleStatusView.showContent();
                            data = (StoreIndex) body.getData();
                            if(data == null){return;}
                            Store store = data.getStoreBaseInfo();
                            favorite = data.getFavState();
                            shareUrl = data.getShareUrl();
                            if(store!=null){
                                shareTitle = store.getStore_name();
                                shareImagePath = store.getStore_logo();
                                ImageLoader.loadImage(store.getStore_logo(), storeLogo);
                                mTextView[0].setText(store.getStore_name());
                            }
                            if (data.getFavState() == 1) {//已收藏
                                hasFavorites = true;
                            } else {//未收藏
                                hasFavorites = false;
                            }
                            mSelectorImageView[2].toggle(hasFavorites);
                            initPopupView();
                        }
                    }
                });
    }

    @Override
    public BaseQuickAdapter<GoodsListBean, BaseViewHolder> initAdapter() {
        adapter = new GoodsListAdapter(R.layout.item_goods_list, new ArrayList<GoodsListBean>());
        adapter.setOnItemClickListener(this);
        return adapter;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    @Override
    public RecyclerView initRecyclerView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        return mRecyclerView;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent = new Intent();
        intent.setClass(this, GoodsDetailActivity.class);
        intent.putExtra(Constants.GOODS_ID, mDataList.get(position).getGoodsId());
        startActivity(intent);
        finish();
    }

    @Override
    public Request initRequest() {
        return OkGo.<BaseResponse<GoodsListResponse>>post(Constants.BASE_URL + Constants.GOODS_LIST_URL)
                .tag(this).params(Constants.STORE_ID, storeId);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<GoodsListResponse>>() {
            @Override
            public void onSuccess(Response<BaseResponse<GoodsListResponse>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    GoodsListResponse data = (GoodsListResponse) body.getData();
                    if(data==null){return;}
                    setData(isRefresh, data.getGoods_list());
                }
            }
        });
    }

    /**
     * 综合排序
     * */
    @OnClick(R.id.action_sort_by_z)
    public void actionSortByZ(View view){
        if(mSelectorImageView[0].isChecked()){
            mSelectorImageView[0].toggle(false);
            zPopup.dismiss();
        }else{
            mSelectorImageView[0].toggle(true);
            mSelectorImageView[1].toggle(false);
            zPopup.show(mRelativeLayout[0]);
            if(cPopup.isShowing()){
                cPopup.dismiss();
            }
        }
    }

    /**
     * 分类查找
     * */
    @OnClick(R.id.action_sort_by_c)
    public void actionSortByC(View view){
        if (data != null && data.getStoreClass() == null) {
            RxToast.showShort("该商家暂无分类");
            return;
        }
        if(mSelectorImageView[1].isChecked()){
            mSelectorImageView[1].toggle(false);
            cPopup.dismiss();
        }else{
            mSelectorImageView[1].toggle(true);
            mSelectorImageView[0].toggle(false);
            cPopup.show(mRelativeLayout[1]);
            if(zPopup.isShowing()){
                zPopup.dismiss();
            }
        }
    }

    /**
     * 收藏
     * */
    @OnClick(R.id.action_polygon_collect)
    public void actionPolygonCollect(View view){
        favorites(Constants.FAVORITES_TYPE_ARTICLE, storeId,mSelectorImageView[2]);
    }

    /* //商品关键词查找
    public void sortByKeywords(String keywords) {
        mRequest.params("keywords", keywords);
        onRefresh();
    }*/

    /**
     * 综合排序
     * */
    private class ZSortItemClickListener implements OnPopupItemClickListener {

        @Override
        public void onItemClick(ActionItem item, View view, int position) {
            mSelectorImageView[0].toggle(false);
            mTextView[1].setText(item.getChildTitle());
            if (Z == position) { //综合排序
                mRequest.params(Constants.ORDER, Constants.N);
            } else if (S == position) {//销量优先
                mRequest.params(Constants.ORDER, Constants.S);
            } else if (PU == position) { //价格从低到高
                mRequest.params(Constants.ORDER, Constants.PU);
            } else if (PD == position) {//价格从高到低
                mRequest.params(Constants.ORDER, Constants.PD);
            } else if (N == position) {//最新发布产品
                mRequest.params(Constants.ORDER, Constants.N);
            }
            onRefresh();
        }
    }

    /**
     * 分类查找 按店铺商品分类
     * */
    private class CSortItemClickListener implements OnPopupItemClickListener {
        @Override
        public void onItemClick(ActionItem item, View view, int position) {
            mSelectorImageView[1].toggle(false);
            mTextView[2].setText(item.getChildTitle());
            mRequest.params(Constants.SGC_ID, data.getStoreClass().get(position).getSgcId());
            onRefresh();
        }
    }

    //收藏
    private void favorites(int type, String sourceId, SelectorImageView sivCollect){
        if(0==favorite){//未收藏
            addFavorites(type,sourceId,sivCollect);
        }else if(1==favorite){//已收藏
            deleteFavorites(type,sourceId,sivCollect);
        }
    }

    //添加收藏
    public void addFavorites(final int type, String sourceId, final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivityForResult(intent,11);
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_ADD_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, sourceId)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_ADD_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()&&sivCollect!=null){
                            favorite = 1;
                            sivCollect.toggle(true);
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    //取消收藏
    public void deleteFavorites(int type,String source_id,final SelectorImageView sivCollect){
        if(!TyslApp.getInstance().isLogin()){
            Intent intent = new Intent();
            intent.setClass(this,LoginActivity.class);
            startActivityForResult(intent,11);
            return;
        }
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.FAVORITES_DELETE_URL)
                .tag(this)
                .params(Constants.TYPE, type)
                .params(Constants.SOURCE_ID, source_id)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.FAVORITES_DELETE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()&&sivCollect!=null){
                            favorite = 0;
                            sivCollect.toggle(false);
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 11){
            loadData();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}