package com.wokun.tysl.shopcart;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.Logger;
import com.shantoo.common.utils.ResourceUtil;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseFragment1;
import com.wokun.tysl.callback.DialogCallback;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.goods.adapter.GoodsListAdapter;
import com.wokun.tysl.goods.bean.GoodsBean;
import com.wokun.tysl.goods.bean.GoodsListBean;
import com.wokun.tysl.goods.response.GoodsListResponse;
import com.wokun.tysl.goods.ui.activity.GoodsDetailActivity;
import com.wokun.tysl.goods.ui.activity.GoodsOrderConfirmationActivity;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.SingleParam;
import com.wokun.tysl.model.bean.Store;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.shopcart.model.RecyclerItemDecoration;
import com.wokun.tysl.shopcart.model.ShopCartAdapter;
import com.wokun.tysl.shopcart.model.ShopCartInfo;
import com.wokun.tysl.shopcart.model.ShopCartResponse;
import com.wokun.tysl.utils.DecimalUtil;
import com.wokun.tysl.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

//购物车
public class ShopCartFragment extends BaseFragment1 {

    @BindString(R.string.tysl_shop_cart)
    String title;
    @BindView(R.id.action_edit)
    TextView actionEdit;
    @BindView(R.id.status_bar)
    View statusBar;

    @BindView(R.id.tv_title)
    TextView tvTitle;


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView2;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;//合计总价
    @BindView(R.id.action_select_all)
    TextView actionSelectAll;
    @BindView(R.id.action_settle_accounts)
    TextView actionSettleAccounts;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    private int action = -1;
    private final static int EDIT = 0;
    private final static int COMPLETE = 1;

    private boolean mSelect;
    private double mTotalPrice = 0;
    private ShopCartAdapter mAdapter;
    private List<Integer> mCartIdList = new ArrayList<>();
    private List<GoodsBean> mGoPayList = new ArrayList<>();
    private List<GoodsBean> mAllOrderList = new ArrayList<>();
    private List<ShopCartInfo> cartInfoList = new ArrayList<>();
    private GoodsListAdapter adapter;
    private RecyclerView mRecyclerHeadView;
    private ImageView img_ad;

    @Override
    public int createView() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    public void initViews() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);
        initData();

   /*     if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            getShopCartList();
        }*/
    }

    /*  @Override
      public void setUserVisibleHint(boolean isVisibleToUser) {
          super.setUserVisibleHint(isVisibleToUser);
          loadData();
      }
  */
    private void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        mRecyclerView2.setLayoutManager(layoutManager);
        mRecyclerView2.addItemDecoration(new RecyclerItemDecoration(0, 0, 0, 0, 0, 0));
        adapter = new GoodsListAdapter(R.layout.item_goods_list, null);
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.head_shooping_cart, null);
        mRecyclerHeadView = (RecyclerView) header.findViewById(R.id.recycler_view);
        img_ad = (ImageView) header.findViewById(R.id.img_ad);

        adapter.addHeaderView(header);

        mRecyclerView2.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsListBean bean = (GoodsListBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, goods_list.get(position).getGoodsId());
                intent.setClass(getContext(), GoodsDetailActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (RecyclerView.SCROLL_STATE_IDLE == newState) {
                    smoothMoveToPosition(recyclerView, newState);
                }
            }
        });
    }

    private void smoothMoveToPosition(RecyclerView mRecyclerView, final int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));

        Log.i(TAG, "onScrollStateChanged: 12>>" + firstItem + "====" + lastItem);
        if (firstItem == 0) {
            llBottom.setVisibility(View.VISIBLE);
        } else {
            llBottom.setVisibility(View.GONE);
        }
    }


    @Override
    public void initToolBar() {
        action = EDIT;
         /* mWidgetBar.setWidgetBarTitle(title)
                .setMenu("编辑",null)
                .setOnMenuClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actionEdit();
                    }
                },null); */
    }


    /**
     * 去结算
     */
    @OnClick(R.id.action_settle_accounts)
    public void actionSettleAccounts(View v) {
        if (!TyslApp.getInstance().isLogin()) {
            startActivity(LoginActivity.class);
            return;
        }
        if (mAllOrderList == null || mAllOrderList.size() == 0) {
            RxToast.showShort("购物车数量为空!");
            return;
        }
        for (GoodsBean goods : mGoPayList) {
            ShopCartInfo cartInfo = new ShopCartInfo();
            cartInfo.setCart_id(goods.getCartId());
            cartInfo.setGoods_id(goods.getGoodsId());
            cartInfo.setGoods_num(goods.getGoodsNum());
            cartInfoList.add(cartInfo);
        }
        if (cartInfoList != null && mTotalPrice > 0) {
            cartUpdate(cartInfoList, mTotalPrice);
        }


    }

    @Override
    public void loadData() {


        mAdapter = new ShopCartAdapter(R.layout.item_shop_cart, null);
        // mAdapter.setEmptyView(R.layout.layout_no_data_shop_cart_view, (ViewGroup) mRecyclerHeadView.getParent());
        mRecyclerHeadView.setAdapter(mAdapter);
        mRecyclerHeadView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerHeadView.addItemDecoration(new MItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        //删除商品接口
        mAdapter.setOnDeleteClickListener(new ShopCartAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View v, int position, int cartId) {
                mCartIdList.add(cartId);
                mAdapter.notifyDataSetChanged();
            }
        });
        //实时监控全选按钮
        mAdapter.setRefreshListener(new ShopCartAdapter.OnRefreshListener() {
            @Override
            public void onRefresh(boolean isSelect) {
                mSelect = isSelect;
                if (isSelect) {
                    Drawable left = getResources().getDrawable(R.drawable.ic_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                } else {
                    Drawable left = getResources().getDrawable(R.drawable.ic_un_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                }
                mTotalPrice = 0;
                int mTotalNum = 0;
                mGoPayList.clear();
                for (int i = 0; i < mAllOrderList.size(); i++)
                    if (mAllOrderList.get(i).isSelect()) {
                        double v = DecimalUtil.mul(mAllOrderList.get(i).getGoodsPrice(), mAllOrderList.get(i).getGoodsNum());
                        mTotalPrice = DecimalUtil.add(mTotalPrice, v);
                        mTotalNum += 1;
                        mGoPayList.add(mAllOrderList.get(i));
                    }
                tvTotalPrice.setText("¥ " + mTotalPrice + "");
                actionSettleAccounts.setText("去结算(" + mTotalNum + ")");
            }
        });
        actionSelectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelect = !mSelect;
                if (mSelect) {
                    Drawable left = getResources().getDrawable(R.drawable.ic_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    for (int i = 0; i < mAllOrderList.size(); i++) {
                        mAllOrderList.get(i).setSelect(true);
                        mAllOrderList.get(i).setShopSelect(true);
                    }
                } else {
                    Drawable left = getResources().getDrawable(R.drawable.ic_un_selected);
                    actionSelectAll.setCompoundDrawablesWithIntrinsicBounds(left, null, null, null);
                    for (int i = 0; i < mAllOrderList.size(); i++) {
                        mAllOrderList.get(i).setSelect(false);
                        mAllOrderList.get(i).setShopSelect(false);
                    }
                }
                mAdapter.notifyDataSetChanged();
            }
        });


        loadaDataGoods();


    }

    private List<GoodsListBean> goods_list;

    private void loadaDataGoods() {
        mMultipleStatusView.showLoading();
        OkGo.<BaseResponse<GoodsListResponse>>post(Constants.BASE_URL + Constants.GOODS_LIST_URL)
                .tag(this)
                .params("order", "s")
                .execute(new JsonCallback<BaseResponse<GoodsListResponse>>() {
                    @Override
                    public void onSuccess(Response<BaseResponse<GoodsListResponse>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            Logger.e(TAG, JSONUtil.toJSON(body));
                            Log.e("商品列表2", JSONUtil.toJSON(body));
                            mMultipleStatusView.showContent();
                            GoodsListResponse data = (GoodsListResponse) body.getData();
                            goods_list = data.getGoods_list();
                            if (goods_list != null && goods_list.size() > 0) {
                                adapter.setNewData(goods_list);
                            }
                        }

                    }
                });


    }


    @Override
    public void onResume() {
        super.onResume();


/*        if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            getShopCartList();}*/

        llBottom.setVisibility(View.VISIBLE);
        mRecyclerHeadView.setVisibility(View.VISIBLE);
        if (TyslApp.getInstance().isLogin() && TyslApp.getInstance().isRefreshShopCart()) {
            //Logger.e(TAG,"ShopCart onResume 已登录 并可以刷新");
            getShopCartList();
            llBottom.setVisibility(View.VISIBLE);
            mRecyclerHeadView.setVisibility(View.VISIBLE);
        } else if (TyslApp.getInstance().isLogin()) {
            //Logger.e(TAG,"ShopCart onResume 已登录 单不可以刷新");
            getShopCartList();
            llBottom.setVisibility(View.VISIBLE);
            mRecyclerHeadView.setVisibility(View.VISIBLE);
        } else if (!TyslApp.getInstance().isLogin()) {
            llBottom.setVisibility(View.GONE);
            mRecyclerHeadView.setVisibility(View.GONE);
            TyslApp.getInstance().setRefreshShopCart(false);
        }
    }

    /**
     * 获取购物车数据列表
     */
    private void getShopCartList() {
        mMultipleStatusView.showLoading();
        OkGo.<BaseResponse<ShopCartResponse>>post(Constants.BASE_URL + Constants.CART_LIST_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<ShopCartResponse>>(Constants.WITH_TOKEN, Constants.CART_LIST_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ShopCartResponse>> response) {
                        TyslApp.getInstance().setRefreshShopCart(false);
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            mMultipleStatusView.showContent();
                            ShopCartResponse data = (ShopCartResponse) body.getData();
                            if (data == null) {
                                return;
                            }
                            ImageLoader.loadImage(data.getAd(), img_ad);

                            if (data.getCartList() == null) {
                                mMultipleStatusView.showEmpty();
                            }
                            List<GoodsBean> list1 = new ArrayList<>();
                            List<GoodsBean> list2 = new ArrayList<>();
                            if (data.getCartList() != null) {
                                for (Store store : data.getCartList()) {
                                    if (store.getGoods_list() != null) {
                                        for (GoodsBean goods : store.getGoods_list()) {
                                            goods.setStoreName(store.getStore_name());
                                            list2.add(goods);
                                        }
                                    }
                                    list1.addAll(list2);
                                    list2.clear();
                                }
                                mAllOrderList = list1;
                                mAdapter.setNewData(mAllOrderList);
                                mRecyclerHeadView.setVisibility(View.VISIBLE);
                            }
                            isSelectFirst(list1);
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<ShopCartResponse>> response) {
                        super.onError(response);
                        if (ShopCartFragment.this.isVisible()) {
                            dismissLoadingProgress();
                        }
                        TyslApp.getInstance().setRefreshShopCart(false);
                    }
                });
    }

    public static void isSelectFirst(List<GoodsBean> list) {
        if (list.size() > 0) {
            list.get(0).setIsFirst(1);
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i).getStoreId() == list.get(i - 1).getStoreId()) {
                    list.get(i).setIsFirst(2);
                } else {
                    list.get(i).setIsFirst(1);
                }
            }
        }
    }

    /**
     * 购物车编辑功能
     */
    @OnClick(R.id.action_edit)
    public void actionEdit() {
        if (!TyslApp.getInstance().isLogin()) {
            startActivity(LoginActivity.class);
            return;
        }
        if (action == EDIT) {
            List<TextView> list = mAdapter.getDeleteList();
            if (list == null) return;
            for (TextView t : list) {
                t.setVisibility(View.VISIBLE);
            }
            tvTitle.setText("完成", null);
            action = COMPLETE;
        } else if (action == COMPLETE) {
            if (mCartIdList == null) return;
            int[] arr = new int[mCartIdList.size()];
            if (mCartIdList != null) {
                for (int i = 0; i < mCartIdList.size(); i++) {
                    arr[i] = mCartIdList.get(i);
                }
            }
            deleteGoods(JSONUtil.toJSON(arr));
            List<TextView> list = mAdapter.getDeleteList();
            for (TextView t : list) {
                t.setVisibility(View.GONE);
            }
            tvTitle.setText("编辑", null);
            action = EDIT;
        }
    }

    /**
     * 购物车删除商品
     */
    private void deleteGoods(String cartIdStr) {
        if (TextUtils.isEmpty(cartIdStr)) return;
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.CART_DELETE_GOODS_URL)
                .tag(this)
                .params(Constants.CART_ID_STR, cartIdStr)
                .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN, Constants.CART_DELETE_GOODS_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<Object>> response) {
                        //RxToast.showShort(response.getException().getMessage());
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            RxToast.showShort(body.getMsg());
                        }
                    }
                });
    }

    /**
     * 购物车去结算
     */
    public void cartUpdate(List<ShopCartInfo> list, final double order_total) {
        ShopCartInfo[] arr = new ShopCartInfo[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        cartInfoList.clear();
        Log.e("shangping订单", JSONUtil.toJSON(arr) + "!!!" + order_total);
        OkGo.<BaseResponse<SingleParam>>post(Constants.BASE_URL + Constants.CART_UPDATE_CART_URL)
                .tag(this)
                .params(Constants.CART_INFO, JSONUtil.toJSON(arr))
                .params(Constants.ORDER_TOTAL, order_total)
                .execute(new DialogCallback<BaseResponse<SingleParam>>(getActivity(), Constants.WITH_TOKEN, Constants.CART_UPDATE_CART_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<SingleParam>> response) {
                        BaseResponse body = response.body();
                        Log.e("shangping订单", body + "");
                        if (body == null) return;
                        mTotalPrice = 0;
                        if (body.isState()) {
                            SingleParam data = (SingleParam) body.getData();
                            Intent intent = new Intent();
                            intent.putExtra(Constants.CART_ID_STR, data.getCart_id_str());
                            intent.putExtra(Constants.ORDER_TOTAL, order_total);
                            Log.e("订单", data.getCart_id_str() + "//%%" + order_total);

                            intent.setClass(getContext(), GoodsOrderConfirmationActivity.class);
                            startActivity(intent);
                        }
                        RxToast.showShort(body.getMsg());
                    }

                    @Override
                    public void onError(Response<BaseResponse<SingleParam>> response) {
                        super.onError(response);
                        Log.e("订单", response.message() + "");

                    }
                });
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            //StatusBarCompat.setStatusBarColor(getActivity(), ContextCompat.getColor(getContext(), R.color.colorAccent));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}