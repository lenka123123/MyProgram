package com.wokun.tysl.myyijian.fragment;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.base.BaseFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.health.MyHealthapter;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.myyijian.HistoryResponse;
import com.wokun.tysl.myyijian.adapter.MyListHistoryadapter;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/7/9/009.
 */
//历史建议
public class HistoryMymessagefragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerViews;
   private  MyListHistoryadapter myHistoryapter;
    @Override
    public int createView() {
        return R.layout.fragment_my_shoucang;
    }

    @Override
    public void initViews() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViews.setLayoutManager(linearLayoutManager);
        myHistoryapter = new MyListHistoryadapter();
        mRecyclerViews.setAdapter(myHistoryapter);
  /*      myHealthapter.setOnItemclickListener(new com.wokun.tysl.home.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.e("点击了23122","进来了224");
                Intent intent = new Intent();
                intent.putExtra(Constants.SOURCE_ID, article.get(position).getArticle_id());
                intent.setClass(TyslApp.getContext(), ArticleDetailActivity.class);
                startActivity(intent);
            }
        });*/
    }

    @Override
    public void loadData() {

        OkGo.<BaseResponse<HistoryResponse>>post(Constants.BASE_URL + Constants.HISTORY_FEEDBACK)//
                .tag(this)
                .execute(new JsonCallback<BaseResponse<HistoryResponse>>(Constants.WITH_TOKEN,Constants.HISTORY_FEEDBACK) {
                    @Override
                    public void onSuccess(Response<BaseResponse<HistoryResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                  //      Toast.makeText(TyslApp.getContext(), body.getMsg(), Toast.LENGTH_SHORT).show();
                        if(body.isState()){
                            HistoryResponse data = (HistoryResponse) body.getData();
                            myHistoryapter.setData(data.getHistory());

                       //     Log.e("历史建议数据",data+"");

                        }

                    }
                });





    }


}
