package com.wokun.tysl.article.ui;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.toolbar.WidgetBar;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.bean.ArticleDetailBean;
import com.wokun.tysl.article.controller.ArticleMgr;
import com.wokun.tysl.base.BaseWebViewActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.utils.ShareSDKUtil;

import java.util.HashMap;

import butterknife.BindString;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

//文章详情
public class ArticleDetailActivity extends BaseWebViewActivity {

    @BindString(R.string.tysl_article_detail)String title;
    //favorites 是否收藏 1 收藏 0未收藏
    private boolean hasFavorites = false;
    private String source_id,shareTitle,shareText,shareUrl,shareImagePath;

    @Override
    public WidgetBar createToolBar() {
        return mWidgetBar
                .setWidgetBarTitle(title)
                .setMenu(R.drawable.ic_share_black,0)
                .setOnMenuClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //shareTitle, shareText, shareUrl,shareImagePath

      // ShareSDKUtil.showShare(TyslApp.getContext(), "标题", "文本", "http://www.baidu.com","http://f1.webshare.mob.com/dimgs/1c950a7b02087bf41bc56f07f7d3572c11dfcf36.jpg");
                            Log.e("1234322","onComplete"+shareTitle+"####"+shareText+"####"+shareUrl+"####"+shareImagePath);
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
                                                Toast.makeText(ArticleDetailActivity.this,"成功了",Toast.LENGTH_SHORT).show();
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
                                oks.show(ArticleDetailActivity.this);



                        }},
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            source_id = getIntent().getStringExtra(Constants.SOURCE_ID);
                            ArticleMgr.getInstance().articleFavorites(ArticleDetailActivity.this,hasFavorites,source_id,mWidgetBar);
                        }
                });
    }

    @Override
    public void init() {
        super.init();
        ArticleMgr.getInstance().setArticleFavoritesLister(
            new ArticleMgr.OnArticleFavoritesLister() {
                @Override
                public void updateHasFavorites(boolean isFavorites) {
                    hasFavorites = isFavorites;
                }
        });
    }

    @Override
    public void loadUrl() {
        OkGo.<BaseResponse<ArticleDetailBean>>post(Constants.BASE_URL + Constants.ARTICLE_CONTENT_URL)//
                .tag(this)
                .params(Constants.ID, getIntent().getStringExtra(Constants.SOURCE_ID))
                .execute(new JsonCallback<BaseResponse<ArticleDetailBean>>(Constants.LOGIN_WITH_TOKEN,Constants.ARTICLE_CONTENT_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ArticleDetailBean>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;

                        if(body.isState()){
                            ArticleDetailBean data = (ArticleDetailBean) body.getData();
                            if(data==null){return;}
                            shareTitle = data.getTitle();
                            shareText = data.getDescription();
                            shareUrl = data.getShare_url();
                            shareImagePath = data.getArticle_image();

                            mWebView.loadUrl(shareUrl);
                            if (data.getFavorites() == 1) {
                                mWidgetBar.setMenu(null,getResources().getDrawable(R.drawable.ic_faved));
                                hasFavorites = true;
                            } else {
                                mWidgetBar.setMenu(null,getResources().getDrawable(R.drawable.ic_fav));
                                hasFavorites = false;
                            }
                        }
                    }
                });
    }
}