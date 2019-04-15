package com.wokun.tysl.ucenter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.roundedimageview.RoundedImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.shantoo.widget.view.ProgressWebView;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.address.ui.AddressListActivity;
import com.wokun.tysl.article.ui.ArticleFavoritesActivity;
import com.wokun.tysl.base.BaseFragment;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dialogview.CircleProgressView;
import com.wokun.tysl.dietician.ui.activity.DieticianDetailActivity;
import com.wokun.tysl.dietician.ui.activity.DieticianFavoritesActivity;
import com.wokun.tysl.dietician.ui.activity.DietitianRecommendGoodsActivity;
import com.wokun.tysl.dietician.ui.activity.DietitianServiceOrderActivity;
import com.wokun.tysl.earnings.ui.MyEarningsActivity;
import com.wokun.tysl.goods.ui.activity.GoodsFavoritesActivity;
import com.wokun.tysl.goods.ui.activity.GoodsOrderActivity;
import com.wokun.tysl.goodslinshou.ui.GoodslinshouOrderActivity;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.bean.QiandaoResponse;
import com.wokun.tysl.model.bean.UserInfo;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.mypersonmoney.ui.MylastmoneyActivity;
import com.wokun.tysl.mypersonmoney.ui.MyyueActivity;
import com.wokun.tysl.mypinjia.ui.MysendmessageActivity;
import com.wokun.tysl.myyijian.ui.MyyijianActivity;
import com.wokun.tysl.other.controler.ActionMgr;
import com.wokun.tysl.other.ui.MessageControlActivity;
import com.wokun.tysl.serviceinto.ui.ServiceAuthenticationaginActivity;
import com.wokun.tysl.shoucang.ui.MyshoucangActivity;
import com.wokun.tysl.store.ui.StoreFavoritesActivity;
import com.wokun.tysl.utils.ImageLoader;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 主界面个人中心页面
 */

public class UCenterFragment extends BaseFragment {

    @BindView(R.id.user_head_img)
    RoundedImageView userHeadImg;
    @BindView(R.id.user_head_img2)
    RoundedImageView userHeadImg2;
    @BindView(R.id.yinyangshi_sex)
    ImageView yinyangshi_sex;
    @BindView(R.id.action_login)
    TextView tvUserLogin;
    @BindView(R.id.user_info)
    RelativeLayout llUserInfo;
    @BindView(R.id.user_name)
    TextView userName;

 /*   @BindView(R.id.action_edit_user_info)
    ImageView editUserInfo;*/
  @BindView(R.id.type_name)
    TextView typeName;
    @BindView(R.id.action_pay)
    TextView actionPay;//充值按钮

 /*   //普通用户
    @BindView(R.id.ucenter_part_user_about)
    LinearLayout ucenterPartUserAbout;*/
    @BindView(R.id.action_my_dietician)
    LinearLayout myDietician;
    @BindView(R.id.action_my_apply_for)
    LinearLayout myApplyFor;
    @BindView(R.id.user_sex)
    ImageView user_sex;

    //营养师
    @BindView(R.id.ucenter_part_dietician_about)
    LinearLayout ucenterPartDieticianAbout;
    @BindView(R.id.dietician_integral)
    LinearLayout dieticianIntegral;
    @BindView(R.id.dietician_earnings)
    LinearLayout dieticianEarnings;
 /*   @BindView(R.id.dietician_integral_content)
    TextView dieticianIntegralContent;
    @BindView(R.id.dietician_earnings_content)
    TextView dieticianEarningsContent;*/

    @BindView(R.id.yingyangshi_service_price)
    TextView yingyangshi_service_price;
    @BindView(R.id.yingyangshi_yinyee)
    TextView yingyangshi_yinyee;
    @BindView(R.id.yingyangshi_name)
    TextView yingyangshi_name;
    @BindView(R.id.goods_order_wait_pay)
    TextView goods_order_wait_pay;
    @BindView(R.id.qiandao)
    TextView mqianDao;
    @BindView(R.id.putong_yue)
    TextView putong_yue;
    @BindView(R.id.putong_money)
    TextView putong_money;


    //收藏
   /* @BindView(R.id.action_check_goods_favorites)
    LinearLayout goodsFavorites;
    @BindView(R.id.action_check_shop_favorites)
    LinearLayout shopFavorites;
    @BindView(R.id.action_check_article_favorites)
    LinearLayout articleFavorites;
    @BindView(R.id.action_check_dietician_favorites)
    LinearLayout dieticianFavorites;
*/
/*   @BindView(R.id.favorite_good_total)
    TextView favoriteGoodTotal;
    @BindView(R.id.favorite_store_total)
    TextView favoriteStoreTotal;
    @BindView(R.id.favorite_article_total)
    TextView favoriteArticleTotal;
    @BindView(R.id.favorite_dietitian_total)
    TextView favoriteDietitianTotal;*/

    //服务订单
    @BindView(R.id.ucenter_part_service_order)
    LinearLayout ucenterPartServiceOrder;

    //商品订单
    @BindView(R.id.ucenter_part_goods_order)
    LinearLayout ucenterPartGoodsOrder;

    //智慧零售
    @BindView(R.id.ucenter_part_goods_order1)
    LinearLayout ucenterPartGoodsOrder1;

    //快捷工具
    @BindView(R.id.ucenter_part_goods_order2)
    LinearLayout ucenterPartGoodsOrder2;

    @BindView(R.id.action_preview_home_page2)
    LinearLayout actionUserPreviewYourHomePage2;
    @BindView(R.id.action_preview_home_page)
    RelativeLayout actionUserPreviewYourHomePage;

    @BindView(R.id.action_earnings)
    RelativeLayout actionUserEarnings;
    @BindView(R.id.action_recommend_goods)
    RelativeLayout actionUserRank;
/*    @BindView(R.id.action_ship_address)
    RelativeLayout actionUserShippingAddress;*/
    @BindView(R.id.action_ship_address2)
   RelativeLayout actionUserShippingAddress2;
  /*  @BindView(R.id.action_settings)
    RelativeLayout actionUserSettings;*/
    @BindView(R.id.action_settings2)
    ImageView actionUserSettingstwo;
    @BindView(R.id.ucenter_top)
    RelativeLayout ucenterTop;
    @BindView(R.id.ucenter_top2)
    RelativeLayout ucenterTop2;
    @BindView(R.id.yingyangshi_top)
    LinearLayout yingyangshi_top;
    @BindView(R.id.putong_top)
    LinearLayout putong_top;
    //快捷工具
    @BindView(R.id.my_kj_zichan)
    RelativeLayout mykjZichan;
    @BindView(R.id.ucenter_part_shoucang)
    RelativeLayout UcenterPartShoucang;
//dialog
    @BindView(R.id.circle_view)
    CircleProgressView circle_view;
    @BindView(R.id.circle_view1)
    CircleProgressView circle_view1;
    @BindView(R.id.circle_view2)
    CircleProgressView circle_view2;



    @BindView(R.id.ucenter_part_mymessage)
    RelativeLayout UcenterPartMymessage;
    private    UserInfo userInfo;
    public static UCenterFragment newInstance() {
        return new UCenterFragment();
    }
    private int currentProgress = 0;
    @Override
    public int createView() {
        return R.layout.fragment_main_ucenter;
    }

    @Override
    public void initViews() {

        circle_view.setListener(new CircleProgressView.ProgressedListener() {
            @Override
            public void loadEnd() {
                Toast.makeText(getContext(), "加载结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void progressLoading(int progressed) {

            }

            @Override
            public void startLoad() {

            }

        });
        mHandler.sendEmptyMessageDelayed(1, 1000);



    }


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (currentProgress <= 100) {
                circle_view.setCurrentProgress(40);
                circle_view1.setCurrentProgress(20);
                circle_view2.setCurrentProgress(60);

                Log.i("wx", currentProgress + "");
                currentProgress++;
                sendEmptyMessageDelayed(1, 10);
            }
        }
    };
    @Override
    public void loadData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //接手回调返回内容
        /*if(Constants.LOGIN_REQUEST_CODE == requestCode && resultCode == Constants.LOGIN_RESULT_CODE){

        }*/

        if (Constants.SETTING_REQUEST_CODE == requestCode && resultCode == Constants.SETTING_RESULT_CODE ) {
            clearUserInfo();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //如果用户已登陆，则加载用户信息

        if(TyslApp.getInstance().isLogin()){
            loadUserInfo();
            TyslApp.getInstance().setRefreshShopCart(true);
        }else{
            clearUserInfo();
        }
    }

    //获取普通用户信息
    public void loadUserInfo() {
        OkGo.<BaseResponse<UserInfo>>post(Constants.BASE_URL + Constants.UCENTER_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<UserInfo>>(Constants.WITH_TOKEN,Constants.UCENTER_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<UserInfo>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                         userInfo = (UserInfo) body.getData();
                        if(userInfo == null){return;}
                  Log.e("userInfo.getUserType()",""+ userInfo.getUserType());
                        TyslApp.getInstance().setUserInfo(userInfo);
                        TyslApp.getInstance().setLogin(true);
                        if(0 == userInfo.getUserType()){
                            ucenterTop.setVisibility(View.VISIBLE);
                            ucenterTop2.setVisibility(View.GONE);
                            user_sex.setVisibility(View.VISIBLE);
                                refreshUI(userInfo);

                        }else if(1 == userInfo.getUserType()){
                            user_sex.setVisibility(View.GONE);
                            ucenterTop2.setVisibility(View.VISIBLE);
                            yingyangshi_top.setVisibility(View.VISIBLE);
                            ucenterTop.setVisibility(View.GONE);
                            putong_top.setVisibility(View.GONE);
                            refreshUI(userInfo);
                        }
                    }
                });
    }

    //更新UI
    private void refreshUI(UserInfo userInfo){
      /*  ImageLoader.loadImage(userInfo.getAvatar(), userHeadImg);
        userName.setText(userInfo.getUserName());*/
     //营养师界面
        if(userInfo.getUserType() == 1){
          /*  typeName.setText(userInfo.getJobTypeName());
            dieticianIntegralContent.setText(userInfo.getIntegral());
            dieticianEarningsContent.setText(userInfo.getBalance());*/

            yingyangshi_yinyee.setText(userInfo.getIntegral());
            yingyangshi_service_price.setText(userInfo.getBalance());
            ImageLoader.loadImage(userInfo.getAvatar(), userHeadImg2);
            yingyangshi_name.setText(userInfo.getUserName());
              if(userInfo.getSex()==0){//男
                  yinyangshi_sex.setImageResource(R.drawable.yingys_man);
              }else{
                  yinyangshi_sex.setImageResource(R.drawable.yingys_girl);
              }


        }else if(userInfo.getUserType() == 0){
            ImageLoader.loadImage(userInfo.getAvatar(), userHeadImg);
            userName.setText(userInfo.getUserName());
            putong_yue.setText(userInfo.getIntegral());

            putong_money.setText(userInfo.getMoney());

           typeName.setText("普通用户" );
            if (userInfo.getSignIn()==0){
                mqianDao.setText("签到");
            }else  if(userInfo.getSignIn()==1){
                mqianDao.setText("已签到");
            }
            if(userInfo.getSex()==0){//男
                Log.e("性别",""+userInfo.getSex());
                user_sex.setImageResource(R.drawable.yingys_man);
            }else{
                user_sex.setImageResource(R.drawable.yingys_girl);
            }


        }
       //我的收藏，，数字展示
     /*   favoriteGoodTotal.setText(userInfo.getFavoriteGoodTotal());
        favoriteStoreTotal.setText(userInfo.getFavoriteStoreTotal());
        favoriteArticleTotal.setText(userInfo.getFavoriteArticleTotal());
        favoriteDietitianTotal.setText(userInfo.getFavoriteDietitianTotal());*/

     isShowDieticianViews(userInfo.getUserType()==1);
        //  actionPay.setVisibility(View.VISIBLE);
        llUserInfo.setVisibility(View.VISIBLE);
        user_sex.setVisibility(View.VISIBLE);
        tvUserLogin.setVisibility(View.GONE);
    }

    //是否显示营养师UI界面
    private void isShowDieticianViews(boolean isDietician) {
      //  ucenterPartUserAbout.setVisibility(isDietician ? View.GONE : View.VISIBLE);
      //  ucenterPartDieticianAbout.setVisibility(isDietician ? View.VISIBLE : View.GONE);
      //  ucenterPartServiceOrder.setVisibility(isDietician ? View.VISIBLE : View.GONE);
   //     actionUserPreviewYourHomePage.setVisibility(isDietician ? View.VISIBLE : View.GONE);
        actionUserEarnings.setVisibility(isDietician ? View.VISIBLE : View.GONE);
        actionUserRank.setVisibility(isDietician ? View.VISIBLE : View.GONE);
        actionUserPreviewYourHomePage.setVisibility(isDietician ? View.VISIBLE : View.GONE);
        actionUserPreviewYourHomePage2.setVisibility(isDietician ? View.VISIBLE : View.GONE);
        ucenterPartGoodsOrder1.setVisibility(isDietician ? View.GONE : View.VISIBLE );
        ucenterPartGoodsOrder2.setVisibility(isDietician ? View.GONE : View.VISIBLE );


    }

    //清空用户信息
    private void clearUserInfo() {
        ucenterTop.setVisibility(View.VISIBLE);
        yingyangshi_top.setVisibility(View.GONE);
        ucenterTop2.setVisibility(View.GONE);
        tvUserLogin.setVisibility(View.VISIBLE);
        llUserInfo.setVisibility(View.GONE);
        user_sex.setVisibility(View.GONE);
        actionPay.setVisibility(View.GONE);
        userHeadImg.setImageResource(R.drawable.ic_default_head_image);
        putong_money.setText("0");
        putong_yue.setText("0");
        isShowDieticianViews(false);

/*        favoriteGoodTotal.setText("0");
        favoriteStoreTotal.setText("0");
        favoriteArticleTotal.setText("0");
        favoriteDietitianTotal.setText("0");*/
    }

    /** 查看收藏 */
   /* @OnClick({R.id.action_check_goods_favorites,
            R.id.action_check_shop_favorites,
            R.id.action_check_article_favorites,
            R.id.action_check_dietician_favorites})
    public void actionCheckFavorites(View view){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_check_goods_favorites == view.getId()){
            startActivity(GoodsFavoritesActivity.class, !isNeedLogin);//查看商品收藏
        } else if(R.id.action_check_shop_favorites == view.getId()){
            startActivity(StoreFavoritesActivity.class, !isNeedLogin);//查看店铺收藏
        } else if(R.id.action_check_article_favorites == view.getId()){
            startActivity(ArticleFavoritesActivity.class, !isNeedLogin);//查看文章收藏
        } else if(R.id.action_check_dietician_favorites == view.getId()){
            startActivity(DieticianFavoritesActivity.class, !isNeedLogin);//查看营养师收藏
        }
    }*/

    /** 查看商品订单 */
    @OnClick({R.id.action_check_more_goods_order,
            R.id.goods_order_wait_pay,
            R.id.action_goods_order_wait_shipments,
            R.id.action_goods_order_take_delivery_of_goods,
            R.id.action_goods_order_wait_evaluate})
    public void actionCheckGoodsOrder(View view){
        Intent intent = new Intent();
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_check_more_goods_order == view.getId()){
            intent.putExtra(Constants.STATE, 0);//查看全部商品订单
        } else if(R.id.goods_order_wait_pay == view.getId()){
            intent.putExtra(Constants.STATE, 1);//查看待付款商品订单
        } else if(R.id.action_goods_order_wait_shipments == view.getId()){
            intent.putExtra(Constants.STATE, 2);//查看待发货商品订单
        } else if(R.id.action_goods_order_take_delivery_of_goods == view.getId()){
            intent.putExtra(Constants.STATE, 3);//查看待收货商品订单
        } else if(R.id.action_goods_order_wait_evaluate == view.getId()){
            intent.putExtra(Constants.STATE, 4);//查看待评价商品订单
        }
        intent.setClass(getContext(), GoodsOrderActivity.class);
        startActivity(intent, !isNeedLogin);
    }

    /** 查看智慧零售订单 */

    @OnClick({R.id.action_check_more_goods_order2,
            R.id.action_goods_order_wait_self2,
            R.id.action_goods_order_wait_shipments2,
            R.id.action_goods_order_take_delivery_of_goods2,
            R.id.action_goods_order_wait_evaluate2,

    })
    public void actionCheckGoodslinshouOrder(View view){
        Intent intent = new Intent();
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_check_more_goods_order2 == view.getId()){
            intent.putExtra(Constants.STATE, 0);//查看全部智慧商品订单
        } else if(R.id.action_goods_order_wait_self2 == view.getId()){
            intent.putExtra(Constants.STATE, 4);//查看 待自提 智慧商品订单
        } else if(R.id.action_goods_order_wait_shipments2 == view.getId()){
            intent.putExtra(Constants.STATE, 2);//查看 待发货 智慧商品订单
        } else if(R.id.action_goods_order_take_delivery_of_goods2 == view.getId()){
            intent.putExtra(Constants.STATE, 3);//查看 待收货 智慧商品订单
        } else if(R.id.action_goods_order_wait_evaluate2 == view.getId()){
            intent.putExtra(Constants.STATE, 5);//查看 待评价 智慧商品订单
        }
        intent.setClass(getContext(), GoodslinshouOrderActivity.class);
        startActivity(intent, !isNeedLogin);
    }







    /** 查看服务订单 */
    @OnClick({R.id.action_check_more_service_order,
            R.id.action_service_order_wait_verify,
            R.id.action_service_order_refused,
            R.id.action_service_order_service_ing,
            R.id.action_service_order_have_expired,
            R.id.yinyangshi_allservice,
            R.id.yinyangshi_service_ing,
            R.id.yinyangshi_service_timeover
    })
    public void actionCheckServiceOrder(View view){
        Intent intent = new Intent();
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_check_more_service_order == view.getId()){
            intent.putExtra(Constants.STATE, 0);//查看全部服务订单
        } else if(R.id.action_service_order_wait_verify == view.getId()){
            intent.putExtra(Constants.STATE, 1);//查看待确认服务订单
        } else if(R.id.action_service_order_refused == view.getId()){
            intent.putExtra(Constants.STATE, 2);//查看已拒绝服务订单
        } else if(R.id.action_service_order_service_ing == view.getId()){
            intent.putExtra(Constants.STATE, 3);//查看服务中服务订单
        } else if(R.id.action_service_order_have_expired == view.getId()){
            intent.putExtra(Constants.STATE, 4);//查看已过期服务订单
        }else if(R.id.yinyangshi_allservice == view.getId()){
            intent.putExtra(Constants.STATE, 0);//查看已拒绝服务订单
        } else if(R.id.yinyangshi_service_ing == view.getId()){
            intent.putExtra(Constants.STATE, 3);//查看服务中服务订单
        } else if(R.id.yinyangshi_service_timeover == view.getId()){
            intent.putExtra(Constants.STATE, 4);//查看已过期服务订单
        }
        intent.setClass(getContext(), DietitianServiceOrderActivity.class);
        startActivity(intent, !isNeedLogin);
    }

    /** 登陆 */
    @OnClick(R.id.action_login)
    public void actionLogin(){
        Intent intent = new Intent();

        intent.setClass(getContext(), LoginActivity.class);
        intent.putExtra(Constants.TAB_POSITION,Constants.TAB_POSITION_UCENTER);
        startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
    }

    /** 充值 */
    @OnClick(R.id.action_pay)
    public void actionPay() {
        startActivity(PayActivity.class);
    }

    /** 签到 */
    @OnClick(R.id.qiandao)
    public void SingqianDao() {

        OkGo.<BaseResponse<QiandaoResponse>>post(Constants.BASE_URL + Constants.INTEGRAL_SIGN_IN_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<QiandaoResponse>>(Constants.WITH_TOKEN,Constants.INTEGRAL_SIGN_IN_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<QiandaoResponse>> response) {
                        BaseResponse body = response.body();
                        if(body == null)return;
                        if(body.isState()){
                            QiandaoResponse data = (QiandaoResponse) body.getData();
                            mqianDao.setText("已签到");
                        }
                        RxToast.showShort(body.getMsg());

                    }
                });




    }



    /** 设置 */
/*  @OnClick(R.id.action_settings)
    public void actionSettings(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        Intent intent = new Intent();
        intent.setClass(getContext(), SettingsActivity.class);
        startActivityForResult(intent, Constants.SETTING_REQUEST_CODE, !isNeedLogin);
    }*/
    /** 设置 */
    @OnClick(R.id.action_settings2)
    public void actionSettingstwo(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        Intent intent = new Intent();
        intent.setClass(getContext(), SettingsActivity.class);
        intent.putExtra("changemobile", userInfo.getUserName());
        startActivityForResult(intent, Constants.SETTING_REQUEST_CODE, !isNeedLogin);
    }

    /** 设置 */
    @OnClick(R.id.action_settings3)
    public void actionSettingsthree(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        Intent intent = new Intent();
        intent.setClass(getContext(), SettingsActivity.class);
        startActivity(intent, !isNeedLogin);
    }

    /** 设置 */
    @OnClick(R.id.action_settings4)
    public void actionSettingsfour(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        Intent intent = new Intent();
        intent.setClass(getContext(), SettingsActivity.class);
        startActivity(intent, !isNeedLogin);
    }



    /** 云链健康*/
    @OnClick(R.id.my_kj_zichan)
        public  void  actionKuaijieSetting(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
         startActivity(MymoneyToyalActivity.class, !isNeedLogin);
    }
    /** 达事余额
     * MymoneyToyalActivity
     * */
    @OnClick(R.id.lin_dsyue)
    public  void  actionDsyue(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(MylastmoneyActivity.class, !isNeedLogin);
    }
    /** 我的钱包*/
    @OnClick(R.id.lin_dsmoney)
    public  void  actionMymoney(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(MyyueActivity.class, !isNeedLogin);
    }



/*

    */
/** 个人信息*//*

    @OnClick(R.id.personal_message)
    public  void  personalSetting(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
*/
/*        Intent intent = new Intent(getContext(), PersonalInfoActivity.class);
            Bundle bundle = new Bundle();

        bundle.putString("key_username",TyslApp.getInstance().getUserInfo().getUserName());
        *//*

        //startActivity(intent);
      startActivity(PersonalInfoActivity.class, !isNeedLogin);
        //EditUserInfoActivity  PersonalInfoActivity
    }
*/
/** 我要入驻*/
@OnClick(R.id.personal_message)
public  void  personalSetting(){
    boolean isNeedLogin = TyslApp.getInstance().isLogin();
    startActivity(ServiceAuthenticationaginActivity.class, !isNeedLogin);

}

    /** 我的收藏*/
    @OnClick(R.id.ucenter_part_shoucang)
    public  void  actionSoucangSetting(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(MyshoucangActivity.class, !isNeedLogin);
    }
    /** 意见反馈*/
    @OnClick(R.id.ucenter_part_mymessage)
    public  void  actionMymessageSetting(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(MyyijianActivity.class, !isNeedLogin);
    }

    /** 消息中心 */
    @OnClick(R.id.action_message)
    public void actionMessage(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(MessageControlActivity.class, !isNeedLogin);
    }

    /** 修改用户信息 */
/*    @OnClick(R.id.action_edit_user_info)
    public void edit_user_info(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(EditUserInfoActivity.class, !isNeedLogin);
    }*/

    /** 管理收货地址 */
/*    @OnClick(R.id.action_ship_address)
    public void actionShipAddress(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(AddressListActivity.class, !isNeedLogin);
    }*/
    /** 管理收货地址2 */
    @OnClick(R.id.action_ship_address2)
    public void actionShipAddresstwo(){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(AddressListActivity.class, !isNeedLogin);
    }

/*
    */
/** 我的评价 *//*

    @OnClick(R.id.my_pinjia)
    public void actionPinjia() {
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        startActivity(MysendmessageActivity.class, !isNeedLogin);
    }
*/

/*    *//** 拨打客服 *//*
    @OnClick(R.id.action_call_service)
    public void actionCallService(){
        ActionMgr.getInstance().callService(getContext());
    }*/
    /** 拨打客服2 */
    @OnClick(R.id.action_call_service2)
    public void actionCallServicetwo(){
        ActionMgr.getInstance().callService(getContext());
    }
    /**
     * 营养师特有功能：
     * 1, 预览个人主页
     * 2, 我的收益
     * 3, 我的推荐商品
     * 4, 我的收藏
     * 5, 地址
     * 6, 客服
     *
     * */
    @OnClick({R.id.action_preview_home_page,
            R.id.action_earnings,
            R.id.action_recommend_goods,
            R.id.yys_my_shoucang,
            R.id.action_ship_address,
            R.id.action_call_service

    })
    public void actionDietitian(View view){
        Intent intent = new Intent();
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_preview_home_page == view.getId()){//预览个人主页
            intent.putExtra(Constants.DIETITIAN_ID, TyslApp.getInstance().getUserInfo().getDietitianId() + "");
            intent.setClass(getContext(), DieticianDetailActivity.class);
            startActivity(intent);
        } else if(R.id.action_earnings == view.getId()){//我的收益
            startActivity(MyEarningsActivity.class, !isNeedLogin);
        } else if(R.id.action_recommend_goods == view.getId()){//我的推荐商品
            startActivity(DietitianRecommendGoodsActivity.class, !isNeedLogin);
        }else if(R.id.yys_my_shoucang == view.getId()){//我的收藏
            startActivity(MyshoucangActivity.class, !isNeedLogin);
        }
        else if(R.id.action_ship_address == view.getId()){//我的
            startActivity(AddressListActivity.class, !isNeedLogin);
        } else if(R.id.action_call_service == view.getId()){//我的
            ActionMgr.getInstance().callService(getContext());
        }
    }

    /** 普通用户特有功能
     * 1, 我的健康
     * 2, 我的申请
     * 专属顾问
     * 服务订单
     * */
    @OnClick({R.id.action_my_dietician,R.id.action_my_apply_for})
    public void actionUser(View view){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_my_dietician == view.getId()){
             if(1== userInfo.getHave_service()) {
                 Intent intent = new Intent(getContext(), MyHealthActivity2.class);
                  intent.putExtra("dietitian_id",userInfo.getService_dietitian());
                 startActivity(intent);//我的健康}
             }
             else{
                 Toast.makeText(getContext(), "您还没有专属顾问，请先购买专属顾问", Toast.LENGTH_SHORT).show();
             }


        } else if(R.id.action_my_apply_for == view.getId()){
            startActivity(MyApplyForActivity.class, !isNeedLogin);//我的申请
        }
    }


    /** 普通用户特有功能
     * 1, 我的健康
     * 2, 我的申请
     * 专属顾问
     * 服务订单
     * */
    @OnClick({R.id.action_my_dietician2,R.id.action_my_apply_for2})
    public void actionUser2(View view){
        boolean isNeedLogin = TyslApp.getInstance().isLogin();
        if(R.id.action_my_dietician2 == view.getId()){
            if(1== userInfo.getHave_service()) {
                Intent intent = new Intent(getContext(), MyHealthActivity2.class);
                intent.putExtra("dietitian_id",userInfo.getService_dietitian());
                startActivity(intent);//我的健康}
            }
            else{
                Toast.makeText(getContext(), "您还没有专属顾问，请先购买专属顾问", Toast.LENGTH_SHORT).show();
            }


        } else if(R.id.action_my_apply_for2 == view.getId()){
            startActivity(MyApplyForActivity.class, !isNeedLogin);//我的申请
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }
}