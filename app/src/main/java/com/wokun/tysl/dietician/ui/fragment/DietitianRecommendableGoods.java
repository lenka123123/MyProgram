package com.wokun.tysl.dietician.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.classic.common.MultipleStatusView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.shantoo.widget.imageview.SelectorImageView;
import com.shantoo.widget.recyclerview.MItemDecoration;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseRefreshAndLoadMoreFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.adapter.RecommendableGoodsAdapter;
import com.wokun.tysl.dietician.bean.RecGoodsBean;
import com.wokun.tysl.model.bean.TuiGoods;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

//可推荐商品
public class DietitianRecommendableGoods extends BaseRefreshAndLoadMoreFragment<RecGoodsBean> {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.tv_sort_sales) TextView tvSortSales;//新发布

    @BindViews({R.id.drawable_btn_sort_down_sales,
            R.id.drawable_btn_goods_sort_gpa,
            R.id.drawable_btn_goods_sort_gpd,
            R.id.drawable_btn_goods_sort_gca,
            R.id.drawable_btn_goods_sort_gcd})
    SelectorImageView[] mSelectorImageViews;

    private boolean current_goods_sort_gp = true;//默认商品价格降序排序
    private boolean current_goods_sort_gc = true;//默认荐购奖励降序排序
    private String dietitianId;

    @Override
    public int createView() {
        return R.layout.fragment_recommended_goods;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    @Override
    public RecyclerView initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.addItemDecoration(new MItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        return mRecyclerView;
    }

    @Override
    public Request initRequest() {
        dietitianId = TyslApp.getInstance().getUserInfo().getDietitianId()+"";
        return OkGo.<BaseResponse<TuiGoods>>post(Constants.BASE_URL + Constants.DIETITIAN_TUI_GOODS_URL)
                .tag(this)
                .params(Constants.GOODS_SORT, Constants.GID)
                .params(Constants.TYPE, 1)
                .params(Constants.DIETITIAN_ID, dietitianId);
    }

    @Override
    public void loadData(final boolean isRefresh) {
        mRequest.execute(new JsonCallback<BaseResponse<TuiGoods>>(Constants.WITH_TOKEN,Constants.DIETITIAN_TUI_GOODS_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<TuiGoods>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                if(body.isState()){
                    TuiGoods data = (TuiGoods) body.getData();
                    if(data==null){return;}
                    setData(isRefresh, data.getRecGoods());
                }
            }
        });
    }

    @Override
    public BaseQuickAdapter<RecGoodsBean, BaseViewHolder> initAdapter() {
        final RecommendableGoodsAdapter mAdapter = new RecommendableGoodsAdapter(R.layout.item_recommendable_goods, null);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                RecGoodsBean recGoodsBean = (RecGoodsBean) adapter.getData().get(position);
                if(R.id.action_join == view.getId()){ //加入推荐
                    actionDietitianAddCutTui(dietitianId,recGoodsBean.getGoodsId(),"1",mAdapter,position);
                }
            }
        });
        return mAdapter;
    }

    /**
     * 营养师个人中心-添加或取消推荐商品
     * @param dietitian_id 营养师id
     * @param goods_id 	商品id
     * @param type type 1是添加推荐2是取消推荐
     */
    public void actionDietitianAddCutTui(String dietitian_id, String goods_id, String type, final BaseQuickAdapter adapter, final int position){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_ADD_CUT_TUI_URL)
            .tag(this)
            .params(Constants.DIETITIAN_ID, dietitian_id)
            .params(Constants.GOODS_ID, goods_id)
            .params(Constants.TYPE, type)
            .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ADD_CUT_TUI_URL) {
                @Override
                public void onSuccess(Response<BaseResponse<Object>> response) {
                    BaseResponse body = response.body();
                    if(body == null)return;

                    RxToast.showShort(body.getMsg());
                    if(body.isState()){
                        //adapter.remove(position);
                        //adapter.notifyDataSetChanged();
                        onRefresh();
                    }
                }
        });
    }

    @Override
    public void loadData() {
        tvSortSales.setText("新发布");
        mSelectorImageViews[0].toggle(true);
        mSelectorImageViews[1].toggle(false);
        mSelectorImageViews[2].toggle(false);
        mSelectorImageViews[3].toggle(false);
        mSelectorImageViews[4].toggle(false);
    }

    @OnClick({R.id.action_goods_sort_gid, R.id.action_goods_sort_gp, R.id.action_goods_sort_gc})
    public void action(View v){
        if(R.id.action_goods_sort_gid == v.getId()){//新发布
            mSelectorImageViews[0].toggle(true);
            mSelectorImageViews[1].toggle(false);
            mSelectorImageViews[2].toggle(false);
            mSelectorImageViews[3].toggle(false);
            mSelectorImageViews[4].toggle(false);
            mRequest.params(Constants.GOODS_SORT, Constants.GID); //新发布
            onRefresh();
        }else if(R.id.action_goods_sort_gp == v.getId()){//商品价格
            mSelectorImageViews[0].toggle(false);
            mSelectorImageViews[3].toggle(false);
            mSelectorImageViews[4].toggle(false);
            if (current_goods_sort_gp) {
                mSelectorImageViews[1].toggle(false);
                mSelectorImageViews[2].toggle(true);
                mRequest.params(Constants.GOODS_SORT, Constants.GPD); //商品价格从高到低 降序
                current_goods_sort_gp = false;
            } else {
                mSelectorImageViews[1].toggle(true);
                mSelectorImageViews[2].toggle(false);
                mRequest.params(Constants.GOODS_SORT, Constants.GPA); //商品价格从低到高 升序
                current_goods_sort_gp = true;
            }
            onRefresh();
        }else if(R.id.action_goods_sort_gc == v.getId()){// 荐购奖励
            mSelectorImageViews[0].toggle(false);
            mSelectorImageViews[1].toggle(false);
            mSelectorImageViews[2].toggle(false);
            if (current_goods_sort_gc) {
                mSelectorImageViews[3].toggle(false);
                mSelectorImageViews[4].toggle(true);
                mRequest.params(Constants.GOODS_SORT, Constants.GCD); //佣金从高到低 降序
                current_goods_sort_gc = false;
            } else {
                mSelectorImageViews[3].toggle(true);
                mSelectorImageViews[4].toggle(false);
                mRequest.params(Constants.GOODS_SORT, Constants.GCA); //佣金从低到高 升序
                current_goods_sort_gc = true;
            }
            onRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}