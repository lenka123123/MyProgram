package com.wokun.tysl.dietician.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
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
import com.wokun.tysl.dietician.adapter.RecommendedGoodsAdapter;
import com.wokun.tysl.dietician.bean.RecGoodsBean;
import com.wokun.tysl.model.bean.TuiGoods;
import com.wokun.tysl.model.response.BaseResponse;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

//已推荐
public class DietitianRecommendedGoods extends BaseRefreshAndLoadMoreFragment<RecGoodsBean> implements RecommendedGoodsAdapter.OnCutListener{

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindViews({R.id.drawable_btn_sort_down_sales, R.id.drawable_btn_goods_sort_gpa, R.id.drawable_btn_goods_sort_gpd, R.id.drawable_btn_goods_sort_gca, R.id.drawable_btn_goods_sort_gcd})
    SelectorImageView[] mSelectorImageViews;

    private boolean current_goods_sort_gp = false;//默认商品价格降序排序
    private boolean current_goods_sort_gc = false;//默认荐购奖励降序排序
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
    public void loadData() {
        dietitianId = TyslApp.getInstance().getUserInfo().getDietitianId()+"";

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
            mRequest.params("goods_sort", "gid"); //新发布
            onRefresh();
        }else if(R.id.action_goods_sort_gp == v.getId()){//商品价格
            mSelectorImageViews[0].toggle(false);
            mSelectorImageViews[3].toggle(false);
            mSelectorImageViews[4].toggle(false);
            if (current_goods_sort_gp) {
                mSelectorImageViews[1].toggle(false);
                mSelectorImageViews[2].toggle(true);
                mRequest.params("goods_sort", "gpd"); //商品价格从高到低 降序
                current_goods_sort_gp = false;
            } else {
                mSelectorImageViews[1].toggle(true);
                mSelectorImageViews[2].toggle(false);
                mRequest.params("goods_sort", "gpa"); //商品价格从低到高 升序
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
                mRequest.params("goods_sort", "gcd"); //佣金从高到低 降序
                current_goods_sort_gc = false;
            } else {
                mSelectorImageViews[3].toggle(true);
                mSelectorImageViews[4].toggle(false);
                mRequest.params("goods_sort", "gca"); //佣金从低到高 升序
                current_goods_sort_gc = true;
            }
            onRefresh();
        }
    }

    @Override
    public Request initRequest() {
        mRequest = OkGo.<BaseResponse<TuiGoods>>post(Constants.BASE_URL + Constants.DIETITIAN_TUI_GOODS_URL)
                .tag(this)
                .params("goods_sort", "gid")
                .params("type", 2)
                .params("dietitian_id", dietitianId);
        return mRequest;
    }

    @Override
    public BaseQuickAdapter<RecGoodsBean, BaseViewHolder> initAdapter() {
        RecommendedGoodsAdapter mAdapter = new RecommendedGoodsAdapter(R.layout.item_recommended_goods, null);
        mAdapter.setOnCutListener(this);
        return mAdapter;
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
    public void onCut(RecGoodsBean recGoodsBean, int position) {
        actionDietitianAddCutTui(dietitianId,recGoodsBean.getGoodsId(),"2",mAdapter,position);
    }

    /**
     * 营养师个人中心-添加或取消推荐商品
     * @param  dietitian_id 营养师id
     * @param goods_id 	商品id
     * @param type type 1是添加推荐2是取消推荐
     */
    public void actionDietitianAddCutTui(String dietitian_id, String goods_id, String type, final BaseQuickAdapter adapter, final int position){
        OkGo.<BaseResponse<Object>>post(Constants.BASE_URL + Constants.DIETITIAN_ADD_CUT_TUI_URL)
            .params("dietitian_id", dietitian_id)
            .params("goods_id", goods_id)
            .params("type", type)
            .execute(new JsonCallback<BaseResponse<Object>>(Constants.WITH_TOKEN,Constants.DIETITIAN_ADD_CUT_TUI_URL) {
            @Override
            public void onSuccess(Response<BaseResponse<Object>> response) {
                BaseResponse body = response.body();
                if(body == null)return;
                RxToast.showShort(body.getMsg());
                if(body.isState()){
                    onRefresh();
                }
            }
        });
    }
}
