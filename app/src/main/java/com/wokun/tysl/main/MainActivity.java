package com.wokun.tysl.main;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.Logger;
import com.shantoo.common.utils.StatusBarUtil;
import com.shantoo.widget.toast.RxToast;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.bean.ServiceNoticeBean;
import com.wokun.tysl.home.ui.HomeFragment;
import com.wokun.tysl.home.ui.HomeFragment2;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.QiandaoResponse;
import com.wokun.tysl.model.bean.User;
import com.wokun.tysl.model.cache.AppCache;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.model.response.ServiceMessageResponse;
import com.wokun.tysl.shopcart.ShopCartFragment;
import com.wokun.tysl.ucenter.ui.UCenterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import io.rong.imageloader.utils.L;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;

import static com.wokun.tysl.TyslApp.getContext;

public class MainActivity extends AppCompatActivity implements
        RongIM.UserInfoProvider, ViewPager.OnPageChangeListener, View.OnClickListener {

    private String TAG = MainActivity.class.getSimpleName();

    @BindColor(R.color.color77)
    int colorUnSelect;
    @BindColor(R.color.colorPrimary)
    int colorSelect;

    @BindViews({R.id.tab_img_home, R.id.tab_img_zb, R.id.tab_img_shop_cart, R.id.tab_img_ucenter})
    ImageView[] mImageViews;

    @BindViews({R.id.tab_text_home, R.id.tab_text_zb, R.id.tab_text_shop_cart, R.id.tab_text_ucenter, R.id.tv_shop_cart_point})//购物车小红点
            TextView[] mTextViews;

    @BindViews({R.id.main_home, R.id.main_zb, R.id.main_shop_cart, R.id.main_ucenter})
    RelativeLayout mRelativeLayout[];

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.main_tab)
    LinearLayout mainTab;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private List<ServiceNoticeBean> userIdList;
    private List<Fragment> mFragment = new ArrayList<>();

    private static Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContentView(root);
        ButterKnife.bind(this);
        //StatusBarUtil.setColor(this, colorSelect);
//
        root.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom - oldBottom < -1) {
                    //软键盘弹上去了,动态设置高度为0
                    mainTab.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0));
                } else if (bottom - oldBottom > 1) {
                    mainTab.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                }
            }
        });
        initMainViewPager();

        initDialogData();

    }

    private void initDialogData() {
        Log.e("进来了22324", "进来了223");
        OkGo.<BaseResponse<HongBaoBean>>post(Constants.BASE_URL + Constants.INDEX_IS_SIGNIN)//
                .tag(this)
                .execute(new JsonCallback<BaseResponse<HongBaoBean>>(Constants.WITH_TOKEN, Constants.INDEX_IS_SIGNIN) {
                    @Override
                    public void onSuccess(Response<BaseResponse<HongBaoBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            //    TyslApp.getInstance().clear();
                            HongBaoBean data = (HongBaoBean) body.getData();
                            Float integral = data.getIntegral();
                            if (data.getState() == 0) {
                                Log.e("进来了22324", "进来了223");
                                inithongbaoDialog(integral);
                                //  if (TyslApp.getInstance().isLogin()){ inithongbaoDialog(integral);}
                            }

                        }

                    }
                });

    }

    //  private AlertDialog dialog;
    private void inithongbaoDialog(Float integral) {
        Log.e("integral进来了22324", "进来了2123");
     /*    dialog = new AlertDialog.Builder(MainActivity.this, R.style.dialog).create();
         View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_diaolog_hongbao, null);
         dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
         dialog.setCanceledOnTouchOutside(false);
         dialog.show();
         dialog.getWindow().setContentView(view);
         dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
*/
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.dialog);
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_diaolog_hongbao, null);
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setContentView(view);
        //使editext可以唤起软键盘
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ImageView close_hongbao = (ImageView) dialog.findViewById(R.id.close_hongbao);
        ImageView hongbao_body2 = (ImageView) dialog.findViewById(R.id.hongbao_body2);
        ImageView dsyt_howmuch = (ImageView) dialog.findViewById(R.id.dsyt_howmuch);
        ImageView dsty_hongbao_10 = (ImageView) dialog.findViewById(R.id.dsty_hongbao_10);
        ImageView dsty_hongbao_40 = (ImageView) dialog.findViewById(R.id.dsty_hongbao_40);
        Log.e("integral", "integral:" + integral);

        if (TyslApp.getInstance().isLogin()) {

            if ((int) (integral * 100) == 10) {
                dsyt_howmuch.setVisibility(View.INVISIBLE);
                dsty_hongbao_40.setVisibility(View.INVISIBLE);
                dsty_hongbao_10.setVisibility(View.VISIBLE);
            } else if ((int) (integral * 100) == 40) {
                dsyt_howmuch.setVisibility(View.INVISIBLE);
                dsty_hongbao_40.setVisibility(View.VISIBLE);
                dsty_hongbao_10.setVisibility(View.INVISIBLE);
            }

        }

        close_hongbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        hongbao_body2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TyslApp.getInstance().isLogin()) {
                    Intent intent = new Intent();
                    dialog.dismiss();
                    intent.setClass(TyslApp.getContext(), LoginActivity.class);
                    startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
                } else {
                    OkGo.<BaseResponse<QiandaoResponse>>post(Constants.BASE_URL + Constants.INTEGRAL_SIGN_IN_URL)
                            .tag(this)
                            .execute(new JsonCallback<BaseResponse<QiandaoResponse>>(Constants.WITH_TOKEN, Constants.INTEGRAL_SIGN_IN_URL) {
                                @Override
                                public void onSuccess(Response<BaseResponse<QiandaoResponse>> response) {
                                    BaseResponse body = response.body();
                                    if (body == null) return;
                                    if (body.isState()) {
                                        QiandaoResponse data = (QiandaoResponse) body.getData();
                                    }
                                    RxToast.showShort(body.getMsg());
                                    dialog.dismiss();
                                }
                            });

                    //  finish();
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (R.id.main_home == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_HOME, false);
        } else if (R.id.main_zb == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_ZB, false);
        } else if (R.id.main_shop_cart == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_SHOP_CART, false);
        } else if (R.id.main_ucenter == v.getId()) {
            mViewPager.setCurrentItem(Constants.TAB_POSITION_UCENTER, false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("刷新状态", TyslApp.getInstance().isRefreshShopCart() + "");
        if (TyslApp.getInstance().isRefreshShopCart()) {
            changeTextViewColor();
            //   TyslApp.getInstance().setRefreshShopCart(true);
            changeSelectedTabState(TyslApp.getInstance().getTabPosition());
            mViewPager.setCurrentItem(TyslApp.getInstance().getTabPosition(), false);
            initDialogData();

        } else if (-1 != AppCache.getTabPosition()) {
            changeTextViewColor();
            changeSelectedTabState(AppCache.getTabPosition());
            mViewPager.setCurrentItem(AppCache.getTabPosition(), false);

        }
    }

    private void initMainViewPager() {
        mRelativeLayout[Constants.TAB_POSITION_HOME].setOnClickListener(this);
        mRelativeLayout[Constants.TAB_POSITION_ZB].setOnClickListener(this);
        mRelativeLayout[Constants.TAB_POSITION_SHOP_CART].setOnClickListener(this);
        mRelativeLayout[Constants.TAB_POSITION_UCENTER].setOnClickListener(this);

        mFragment.add(new HomeFragment2());
        mFragment.add(new GoodsCategoryFragment());
        mFragment.add(new ShopCartFragment());
        mFragment.add(new UCenterFragment());

        mViewPager.setAdapter(fragmentPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.addOnPageChangeListener(this);
        changeTextViewColor();
        changeSelectedTabState(getIntent().getIntExtra(Constants.TAB_POSITION, 0));
        mViewPager.setCurrentItem(getIntent().getIntExtra(Constants.TAB_POSITION, 0), false);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //  dialog.dismiss();
    }

/*
    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }
*/
/*
    @Override
    protected void onPause() {
        super.onPause();
        dialog.dismiss();
    }
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constants.LOGIN_RESULT_CODE) {
            initUserInfo();
        }

    }


    private void initUserInfo() {
        OkGo.<BaseResponse<ServiceMessageResponse>>post(Constants.BASE_URL + Constants.UCENTER_GET_SERVICE_NOTICE_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<ServiceMessageResponse>>(Constants.WITH_TOKEN, Constants.UCENTER_GET_SERVICE_NOTICE_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<ServiceMessageResponse>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            ServiceMessageResponse data = (ServiceMessageResponse) body.getData();
                            if (data == null) {
                                return;
                            }
                            User user = TyslApp.getInstance().getUser();
                            com.wokun.tysl.model.bean.UserInfo userInfo = TyslApp.getInstance().getUserInfo();
                            userIdList = data.getServiceList();
                            if (userIdList != null)
                                userIdList.add(new ServiceNoticeBean(user.getUserId(), userInfo.getUserName(), userInfo.getAvatar()));
                            //Logger.e("融云", JSONUtil.toJSON(userIdList));
                            RongIM.setUserInfoProvider(MainActivity.this, true);
                        }
                    }

                    @Override
                    public void onError(Response<BaseResponse<ServiceMessageResponse>> response) {
                        super.onError(response);
                    Log.e("退出了app","退出了app");
                    }
                });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            RxToast.showShort("再按一次退出程序");
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {
            finish();
        }
    }

    @Override
    public UserInfo getUserInfo(String s) {
        for (ServiceNoticeBean bean : userIdList) {
            if (bean.getUserId().equals(s)) {
                //Log.e("getUserInfo", bean.getUserId());
                return new UserInfo(bean.getUserId(), bean.getUserName(), Uri.parse(bean.getAvatar()));
            }
        }
        return null;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        AppCache.setTabPosition(position);
        // Logger.e(TAG,Constants.TAB_POSITION + AppCache.getTabPosition());
        changeTextViewColor();
        changeSelectedTabState(position);
    }

    private void changeTextViewColor() {
        mImageViews[0].setBackgroundResource(R.drawable.ic_main_home_unselect2);
        mTextViews[0].setTextColor(colorUnSelect);

        mImageViews[1].setBackgroundResource(R.drawable.ic_main_fenglei_unselect2);
        mTextViews[1].setTextColor(colorUnSelect);

        mImageViews[2].setBackgroundResource(R.drawable.ic_main_shop_cart_unselect2);
        mTextViews[2].setTextColor(colorUnSelect);

        mImageViews[3].setBackgroundResource(R.drawable.ic_main_ucenter_unselect2);
        mTextViews[3].setTextColor(colorUnSelect);
    }

    private void changeSelectedTabState(int position) {
        if (position == 0) {
            mTextViews[0].setTextColor(colorSelect);
            mImageViews[0].setBackgroundResource(R.drawable.ic_main_select2);
        } else if (position == 1) {
            mTextViews[1].setTextColor(colorSelect);
            mImageViews[1].setBackgroundResource(R.drawable.ic_main_fenglei_select2);
        } else if (position == 2) {
            mTextViews[2].setTextColor(colorSelect);
            mImageViews[2].setBackgroundResource(R.drawable.ic_main_shop_cart_select2);
        } else if (position == 3) {
            mTextViews[3].setTextColor(colorSelect);
            mImageViews[3].setBackgroundResource(R.drawable.ic_main_ucenter_select2);
        }
    }

    private FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            return mFragment.get(position);
        }

        @Override
        public int getCount() {
            return mFragment.size();
        }
    };
}