package com.wokun.tysl.home.ui;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AppOpsManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.provider.Settings;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.itheima.loopviewpager.LoopViewPager;
import com.itheima.loopviewpager.listener.OnItemClickListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.common.utils.JSONUtil;
import com.shantoo.common.utils.Logger;
import com.shantoo.common.utils.ResourceUtil;
import com.shantoo.common.utils.UIUtil;
import com.shantoo.widget.toolbar.OnSearchListener;
import com.shantoo.widget.toolbar.WidgetBar;
import com.sunfusheng.marqueeview.MarqueeView;
import com.wokun.tysl.R;
import com.wokun.tysl.TyslApp;
import com.wokun.tysl.article.ui.ArticleDetailActivity;
import com.wokun.tysl.article.ui.ArticleListActivity;
import com.wokun.tysl.asset.ui.activity.AssertDsytActivity;
import com.wokun.tysl.asset.ui.activity.AssertDsytfindmoney;
import com.wokun.tysl.asset.ui.activity.DsytYaoqingActivity;
import com.wokun.tysl.base.BaseFragment1;
import com.wokun.tysl.base.SimpleWebViewActivity;
import com.wokun.tysl.callback.JsonCallback;
import com.wokun.tysl.config.Constants;
import com.wokun.tysl.dietician.ui.activity.DieticianDetailActivity;
import com.wokun.tysl.dietician.ui.activity.DieticianIndexActivity;
import com.wokun.tysl.goods.adapter.GoodsListAdapter;
import com.wokun.tysl.goods.bean.GoodsListBean;
import com.wokun.tysl.goods.response.GoodsListResponse;
import com.wokun.tysl.goods.ui.activity.GoodsDetailActivity;
import com.wokun.tysl.goods.ui.activity.GoodsListActivity;
import com.wokun.tysl.health.MyHealthapter;
import com.wokun.tysl.home.HomoSecondAdapter;
import com.wokun.tysl.home.MyGridView;
import com.wokun.tysl.home.adapter.DieticianIndexServiceStarAdapter2;
import com.wokun.tysl.home.adapter.RecommendAdapter;
import com.wokun.tysl.home.bean.ArticleBean;
import com.wokun.tysl.home.bean.BannerBean;
import com.wokun.tysl.home.bean.HeadDataBean;
import com.wokun.tysl.home.bean.HomeBean;
import com.wokun.tysl.home.bean.HomeBodyBean;
import com.wokun.tysl.home.bean.JigouBean;
import com.wokun.tysl.home.bean.NoticeBean;
import com.wokun.tysl.home.bean.ServiceStarBean;
import com.wokun.tysl.home.bean.StoreTypeBean;
import com.wokun.tysl.login.ui.LoginActivity;
import com.wokun.tysl.model.cache.AppCache;
import com.wokun.tysl.model.response.BaseResponse;
import com.wokun.tysl.other.ui.MessageControlActivity;
import com.wokun.tysl.serviceplace.MyServicePlaceadapter;
import com.wokun.tysl.shopcart.model.RecyclerItemDecoration;
import com.wokun.tysl.smartretail.adapter.RetailStorageRackAdapter;
import com.wokun.tysl.smartretail.bean.GoodsDataBean;
import com.wokun.tysl.smartretail.bean.RetailShop;
import com.wokun.tysl.smartretail.ui.activity.SmartRetailIndexActivity;
import com.wokun.tysl.utils.ImageLoader;
import com.wokun.tysl.widget.shopcartdialog.ShopCart;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;
import q.rorbin.badgeview.DisplayUtil;
import qiu.niorgai.StatusBarCompat;

/**
 * 首页
 */

public class HomeFragment2 extends BaseFragment1 implements View.OnTouchListener {

    @BindView(R.id.njk_ad)
    ImageView njk_ad;

    @BindView(R.id.miaosha_gridview)
    MyGridView miaosha_gridview;

    @BindView(R.id.recycler_view_foot)
    RecyclerView recycler_view_foot;

    @BindView(R.id.toolbar)
    WidgetBar mWidgetBar;
    @BindView(R.id.lvp_pager)
    LoopViewPager loopViewPager;
    @BindView(R.id.appbar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.status_bar)
    View statusBar;
    //@BindView(R.id.main_content)CoordinatorLayout mainContent;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.speaker)
    TextView speaker;
    /*    @BindView(R.id.ad_image)
        ImageView ad_image;*/
    @BindView(R.id.marqueeView)
    MarqueeView marqueeView;
    @BindView(R.id.action_tian_tuan)
    ImageView ivTianTuan;
    @BindView(R.id.action_faheihei)
    ImageView ivFaHeiHei;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerViews;
    @BindView(R.id.id_recyclerview_horizontal)
    RecyclerView mRecyclerView4;//服务之星
    @BindView(R.id.recycler_view3)
    RecyclerView mRecyclerViews3;//慧零售
    @BindView(R.id.recyclerview5)
    RecyclerView mRecyclerViews5;//服务机构
    @BindView(R.id.recyclerview6)
    RecyclerView mRecyclerViews6;//健康专栏
    @BindViews({R.id.dietitian_picture, R.id.jk365_picture, R.id.ad_picture, R.id.ad_image, R.id.home_zhihui_1
            , R.id.home_zhihui_2, R.id.home_zhihui_3, R.id.home_zhihui_4})
    ImageView[] mImageViews;
    // private HeadDataBean.LvJu lvJu;
    private String url, tianTuanUrl;
    private int faHeiHeiGoodsId; //发黑黑商品id
    private RecommendAdapter mRecommendAdapter;
    private int dietitian_trticle_type, healthy365_trticle_type;
    private boolean flag = false; //是否已经发出跳转智慧零售请求，避免发出多次请求，默认false
    //慧零售推荐
    private RetailStorageRackAdapter dataAdapter;
    private List<GoodsDataBean> dataList;
    private List<JigouBean> serviceData;
    private List<ArticleBean> article;
    // private ArrayList<HealthBean> healthData;
    private ShopCart mShopCart;
    //服务之星
    private RecyclerView mRecyclerView;
    private GalleryAdapter mAdapter;
    private List<Integer> mDatas;
    private MyServicePlaceadapter myServicePlaceadapter;//服务机构适配器
    private List<JigouBean> jigou;

    private WindowManager wm = null;
    private WindowManager.LayoutParams wmParams = null;
    private FloatScanView fsv = null;
    private float lastY = 0;
    private AlertDialog dialog;
    private GoodsListAdapter adapter;
    private HomoSecondAdapter secondAdapter;
    private List<HomeBean.NaojiankangEntity> naojiankangEntityList = null;

    @Override
    public int createView() {
        return R.layout.fragment_main_home1;
    }

    @Override
    public void initToolBar() {
        CollapsingToolbarLayout.LayoutParams params = new CollapsingToolbarLayout.LayoutParams(
                CollapsingToolbarLayout.LayoutParams.MATCH_PARENT, CollapsingToolbarLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = ResourceUtil.getStatusBarHeight(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.height = ResourceUtil.getStatusBarHeight(getContext());
        statusBar.setLayoutParams(layoutParams);
        mWidgetBar.getSearchView().setHintTextColor(UIUtil.getColor(R.color.color77));
        mWidgetBar.getSearchView().setTextColor(UIUtil.getColor(R.color.black));
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
                }, null);
    }

    private List<GoodsListBean> goods_list;


    private void loadaDataGoods() {
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
    public void initViews() {
//        recycler_view_foot.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        recycler_view_foot.setLayoutManager(layoutManager);
         recycler_view_foot.addItemDecoration(new RecyclerItemDecoration(0, 0, 0, 0, 0, 0));
        adapter = new GoodsListAdapter(R.layout.item_goods_list, null);
        recycler_view_foot.setAdapter(adapter);

        loadaDataGoods();

        setHasOptionsMenu(true);
        StatusBarCompat.setStatusBarColorForCollapsingToolbar(getActivity(), mAppBarLayout, mCollapsingToolbarLayout, mWidgetBar, UIUtil.getColor(R.color.colorPrimary));
        initXuanfu();
        initRecommendView();
        initZHihuiView();
        initFuwu();
        initHealth();
        initRecyclerViewPerson();
        initDatas();
        //慧零售推荐
        mShopCart = new ShopCart();
        dataList = new ArrayList<>();

//        createFloatView();
    /* if (MyUtil.isForeground(getActivity())){
            createFloatView();
        }*/

        //服务之星
        //设置布局管理器
    /*    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView4.setLayoutManager(linearLayoutManager);
        //设置适配器
        mAdapter = new GalleryAdapter(getContext(), mDatas);
        mRecyclerView4.setAdapter(mAdapter);*/

    }


    /**
     * 初始化健康专栏
     */
    private MyHealthapter myHealthapter;

    private void initHealth() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViews6.setLayoutManager(linearLayoutManager);
        myHealthapter = new MyHealthapter();
        mRecyclerViews6.setAdapter(myHealthapter);
        myHealthapter.setOnItemclickListener(new com.wokun.tysl.home.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent();
                intent.putExtra(Constants.SOURCE_ID, article.get(position).getArticle_id());
                intent.setClass(TyslApp.getContext(), ArticleDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    //悬浮窗
    private TyslApp app;

    //判断是否打开了权限
    private void initXuanfu() {
        Log.e("我走了initXuanfu", "我走了initXuanfu");
        app = (TyslApp) getActivity().getApplication();
        dialog = new AlertDialog.Builder(getActivity())
                .setTitle("悬浮窗权限管理")
                .setMessage("开启悬浮窗权限？")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //打开权限设置
                        openSetting();
                    }
                })
                .setNegativeButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();

    }

    /**
     * 打开权限设置界面
     */
    public void openSetting() {
        try {
            Intent localIntent = new Intent(
                    "miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter",
                    "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", getContext().getPackageName());
            startActivityForResult(localIntent, 11);
            //      LogUtil.E("启动小米悬浮窗设置界面");
        } catch (ActivityNotFoundException localActivityNotFoundException) {
//            Intent intent1 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//            Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
//            intent1.setData(uri);
            //8.0设置成功后悔依旧返回false，必需离开这页面再进才行，所以得分开设置
            if (Build.VERSION.SDK_INT >= 26) {//8.0以上
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivityForResult(intent, 11);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0-8.0
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                intent.setData(Uri.parse("package:com.wokun.tysl"));
                startActivityForResult(intent, 11);
            }

//            Intent intent1 = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
//                    Uri.parse("package:com.zhuyinsuo"));
//            startActivityForResult(intent1, 11);
            Log.e("首页打开权限", "打开权限");
        }
    }

    /**
     * 请求用户给予悬浮窗的权限
     */
    //创建悬浮框
    private void createFloatView() {

        Log.e("我走了createFloatView", "我走了createFloatView");
        //开启悬浮窗前先请求权限
        if ("Xiaomi".equals(Build.MANUFACTURER)) {//小米手机
            //      LogUtil.E("小米手机");
            requestPermission();
        } else if ("Meizu".equals(Build.MANUFACTURER)) {//魅族手机
            //      LogUtil.E("魅族手机");
            requestPermission();
        } else {//其他手机
            //      LogUtil.E("其他手机");
//            if (Build.VERSION.SDK_INT >= 23) {
//                if (!Settings.canDrawOverlays(getContext())) {
//                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
//                    startActivityForResult(intent, 12);
//                } else {
//                    //
//                }
//            } else {
//            }

            //    if (Build.VERSION.SDK_INT >= 23) {


            if (Build.VERSION.SDK_INT >= 23) {
                //   Log.e("权限canDrawOverlays", Settings.canDrawOverlays(getContext())+"");

                Context context = TyslApp.getContext();
                PackageManager packageManager = context.getPackageManager();
                //  if (packageManager == null){
                try {
                    Log.e("packagemanage2r", packageManager.getPackageInfo(context.getPackageName(), 0).packageName);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                //  }
//                  if(Settings.canDrawOverlays(getContext()))
//                    {
//                    dialog.show();
//                    return;
//                } else {
//
//            }} else {
            }
        }

        if (fsv == null) {
            wm = (WindowManager) TyslApp.getContext().getSystemService(Context.WINDOW_SERVICE);
            //    wmParams = new WindowManager.LayoutParams();
            wmParams = ((TyslApp) TyslApp.getContext().getApplicationContext()).getMywmParams();
            //    wmParams.type=2002;     //type是关键，这里的2002表示系统级窗口
//            if (Build.VERSION.SDK_INT>=26) {//8.0新特性
//                wmParams.type= WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
//            }else{
//                wmParams.type= WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//            }

            wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//            wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;
            wmParams.format = PixelFormat.RGBA_8888;//设置图片格式，效果为背景透明
            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;//
            wmParams.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;//
            wmParams.x = 0;
            wmParams.y = 0;
            wmParams.width = 140;
            wmParams.height = 349;
            fsv = new FloatScanView(TyslApp.getContext());
            fsv.setImageResource(R.drawable.find_yue);
            fsv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.e("发现钱点击了","发现钱点击了");
                    // 发现余额
                    Intent intent = new Intent();
                    intent.putExtra(Constants.TYPE, 1);
                    intent.setClass(getContext(), AssertDsytfindmoney.class);
                    startActivity(intent, !TyslApp.getInstance().isLogin());

                }
            });

            wm.addView(fsv, wmParams);
        }
    }

    /**
     * 请求用户给予悬浮窗的权限
     */
    public void requestPermission() {
        if (isFloatWindowOpAllowed(TyslApp.getContext())) {//已经开启
        } else {
            if (dialog != null) {
                dialog.show();
            }
        }
    }

    @Override
    public void onPause() {
        Log.e("首页onPause", "onPause");
        super.onPause();
        removeControlView(TyslApp.getContext());
    }

    /**
     * 判断悬浮窗权限
     *
     * @param context
     * @return
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isFloatWindowOpAllowed(Context context) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            return checkOp(context, 24); // AppOpsManager.OP_SYSTEM_ALERT_WINDOW
        } else {
            if ((context.getApplicationInfo().flags & 1 << 27) == 1 << 27) {
                return true;
            } else {
                return false;
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            try {
                Class<?> spClazz = Class.forName(manager.getClass().getName());
                Method method = manager.getClass().getDeclaredMethod("checkOp", int.class, int.class, String.class);
                int property = (Integer) method.invoke(manager, op,
                        Binder.getCallingUid(), context.getPackageName());
                Log.e("权限399", " property: " + property);
                if (AppOpsManager.MODE_ALLOWED == property) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
//            Log.e("399", "Below API 19 cannot invoke!");
        }
        return false;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 11) {
            if (Build.VERSION.SDK_INT >= 23) {
                Log.e("首页回来的权限1", isFloatWindowOpAllowed(TyslApp.getContext()) + "");
                Log.e("首页回来的权限2", Settings.canDrawOverlays(TyslApp.getContext()) + "");
            }
            if (isFloatWindowOpAllowed(TyslApp.getContext())) {//已经开启
            } else {
                Toast.makeText(TyslApp.getContext(), "尚未开启悬浮窗", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == 12) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(TyslApp.getContext())) {
                    Toast.makeText(TyslApp.getContext(), "权限授予失败,无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                }
            }
        } else if (requestCode == 1234) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(TyslApp.getContext())) {
                    Toast.makeText(TyslApp.getContext(), "权限授予失败，无法开启悬浮窗", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TyslApp.getContext(), "权限授予成功！", Toast.LENGTH_SHORT).show();
                    //启动FxService
//                    startService(floatWinIntent);
                }

            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("首页onStop", "onStop");
        removeControlView(TyslApp.getContext());
    }

    public void removeControlView(Context context) {
        if (fsv != null) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            windowManager.removeView(fsv);
            fsv = null;
        }
    }

   /* @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            removeControlView(getContext());
        //    System.out.println("不可见");

        } else {
            if (MyUtil.isForeground(getActivity())){
                createFloatView();
            }
       //     System.out.println("当前可见");
        }
    }*/


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            //相当于Fragment的onResume
//            createFloatView();
       /*  if (MyUtil.isForeground(getActivity())){
                createFloatView();
            }*/
        } else {
            //相当于Fragment的onPause、
            removeControlView(TyslApp.getContext());
        }

    }

   /* @Override
    public void onResume() {
        super.onResume();
        if (MyUtil.isForeground(getActivity())){
            createFloatView();
        }

        Log.e("首页onResume", "onResume");


    }*/


    /**
     * 初始化服务机构
     */
    private void initFuwu() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViews5.setLayoutManager(linearLayoutManager);
        myServicePlaceadapter = new MyServicePlaceadapter();
        mRecyclerViews5.setAdapter(myServicePlaceadapter);
        myServicePlaceadapter.setOnItemclickListener(new com.wokun.tysl.home.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.e("点击了1122", "进来了224");
                Intent intent = new Intent();
                intent.putExtra("jigouurl", jigou.get(position).getUrl());
                intent.setClass(TyslApp.getContext(), HomeWebActivity.class);
                startActivity(intent);

            }
        });

    }

    //服务之星
    private DieticianIndexServiceStarAdapter2 serviceStarAdapter;

    private void initDatas() {
        serviceStarAdapter = new DieticianIndexServiceStarAdapter2(R.layout.item_dietician_service_star, null);
        mRecyclerView4.setAdapter(serviceStarAdapter);
        mRecyclerView4.setNestedScrollingEnabled(false);
        //  mRecyclerViews[2].setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView4.setLayoutManager(new GridLayoutManager(getContext(), 4));

        serviceStarAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ServiceStarBean bean = (ServiceStarBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.DIETITIAN_ID, bean.getDietitian_id());
                intent.setClass(TyslApp.getContext(), DieticianDetailActivity.class);
                startActivity(intent);
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsListBean bean = (GoodsListBean) adapter.getData().get(position);
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, goods_list.get(position).getGoodsId());
                intent.setClass(getActivity().getApplicationContext(), GoodsDetailActivity.class);
                getActivity().startActivity(intent);
            }
        });


        secondAdapter = new HomoSecondAdapter(getActivity());
        miaosha_gridview.setAdapter(secondAdapter);

        miaosha_gridview.setOnRadioItemClickListener(new MyGridView.OnRadioItemClickListener() {
            @Override
            public void onItemClick(int gridViewId, int position) {
                secondAdapter.setOnClick(position);


            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastY = motionEvent.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float disY = motionEvent.getY() - lastY;
                //垂直方向滑动
//                if (Math.abs(disY) > viewSlop)
//                    isUpSlide = disY < 0;
//                if (isUpSlide) {
//                    if (!MainActivity.isToolsHid) {
//                        MainActivity.hideLayout();
//                    }
//                } else {
//                    if (MainActivity.isToolsHid) {
//                        MainActivity.showLayout();
//                    }
//                }
                break;
        }
        return false;
    }


    public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
        private LayoutInflater mInflater;
        private List<Integer> mDatas;

        public GalleryAdapter(Context context, List<Integer> datats) {
            mInflater = LayoutInflater.from(context);
            mDatas = datats;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(View arg0) {
                super(arg0);
            }

            ImageView mImg;
            TextView mTxt;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = mInflater.inflate(R.layout.activity_recycler_item,
                    viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);

            viewHolder.mImg = (ImageView) view
                    .findViewById(R.id.id_index_gallery_item_image);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
            viewHolder.mImg.setImageResource(mDatas.get(i));
        }

    }


    private void initRecyclerViewPerson() {


    }

    @Override
    public void loadData() {
        // loadHomeHeadData();
        //  loadHomeBodyData();
        loadHomeData();
        //   loadZhiHuiData();
    }


    private void loadZhiHuiData() {
        //   mMultipleStatusView.showLoading();
        OkGo.<BaseResponse<RetailShop>>post(Constants.BASE_URL + Constants.RETAIL_SHOP_URL)
                .tag(this)
                .params(Constants.STORE_CODE, "000XT")
                .execute(new JsonCallback<BaseResponse<RetailShop>>(Constants.WITH_TOKEN, Constants.RETAIL_SHOP_URL) {
                    @Override
                    public void onSuccess(Response<BaseResponse<RetailShop>> response) {
                        mMultipleStatusView.showContent();
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            RetailShop data = (RetailShop) body.getData();
                            Log.e(TAG, JSONUtil.toJSON(data));
                            if (data == null) {
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
                            if (list != null) {
                                for (int i = 0; i < list.size(); i++) {
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

    /**
     * 初始化智慧零售推荐
     */
    private void initZHihuiView() {
        mRecyclerViews3.setLayoutManager(new GridLayoutManager(getContext(), 2));
        // dataAdapter = new RetailStorageRackAdapter(R.layout.right_dish_item, dataList, mShopCart);
        dataAdapter = new RetailStorageRackAdapter(R.layout.right_dish_item, dataList, mShopCart);
        dataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (R.id.right_product_add == view.getId()) {
                    GoodsDataBean item = (GoodsDataBean) adapter.getData().get(position);
                    if (mShopCart.addShoppingSingle(item)) {
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


    /**
     * 初始化营养师推荐
     */
    private void initRecommendView() {
        mRecommendAdapter = new RecommendAdapter(R.layout.item_home_recommended, null);
        mRecommendAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StoreTypeBean bean = (StoreTypeBean) adapter.getData().get(position);
                if (bean == null) return;
                Intent intent = new Intent();
                intent.putExtra(Constants.GOODS_ID, bean.getGoodsId());
                intent.setClass(getActivity().getApplicationContext(), GoodsDetailActivity.class);
                getActivity().startActivity(intent);
                //RxToast.showShort("营养师推荐");
            }
        });
        mRecyclerViews.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerViews.setAdapter(mRecommendAdapter);
        mRecyclerViews.setNestedScrollingEnabled(false);
    }


    private void loadHomeData() {
        Log.e("广告图片", "=========================================================");
        OkGo.<BaseResponse<HomeBean>>post(Constants.BASE_URL + Constants.INDEX_HEAD_INDEX)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<HomeBean>>() {


                    @Override
                    public void onSuccess(Response<BaseResponse<HomeBean>> response) {
                        BaseResponse body = response.body();
                        if (body == null) return;
                        if (body.isState()) {
                            final HomeBean data = (HomeBean) body.getData();
                            if (data == null) return;
                            if (data.getNjk_ad() != null)
                                ImageLoader.loadImage(data.getNjk_ad(), njk_ad);
                            if (data.getNaojiankang() != null) {
                                naojiankangEntityList = data.getNaojiankang();
                                System.out.println("= sssssss==123 " + naojiankangEntityList.size());
                                updataColumnWidth(data.getNaojiankang().size(), miaosha_gridview);
                                secondAdapter.setData(data.getNaojiankang());
                            }
                            final List<BannerBean> bannerBeanList = data.getBanner();
                            if (bannerBeanList != null) {
                                AppCache.saveBanner(bannerBeanList);
                                loopViewPager.setImgData(AppCache.getBanner());
                                loopViewPager.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick(View view, int i) {
                                        Intent intent = new Intent();
                                        intent.putExtra(Constants.TITLE, UIUtil.getString(R.string.app_name));
                                        intent.putExtra(Constants.URL, bannerBeanList.get(i % bannerBeanList.size()).getUrl());
                                        intent.setClass(getContext(), SimpleWebViewActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                loopViewPager.start();
                            }
                            serviceStarAdapter.setNewData(data.getServiceStar());

                            //健康列表文章
                            article = data.getArticle();
                            myHealthapter.setData(article);
                            //广告
                            Log.e("广告图片", "" + data.getAd().getImage());

                            ImageLoader.loadImage(data.getAd().getImage(), mImageViews[3]);
                            mImageViews[3].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getContext(), HomeADWebActivity.class);
                                    intent.putExtra("homead", data.getAd().getUrl());
                                    startActivity(intent);
                                }
                            });

                            //智慧零售
                            //  List<RetailBean> retail = data.getRetail();
                            ImageLoader.loadImage(data.getRetail().getImage1(), mImageViews[4]);
                            ImageLoader.loadImage(data.getRetail().getImage2(), mImageViews[5]);
                            ImageLoader.loadImage(data.getRetail().getImage3(), mImageViews[6]);
                            ImageLoader.loadImage(data.getRetail().getImage4(), mImageViews[7]);
                            mImageViews[4].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getContext(), HomeZhihuiWebActivity.class);
                                    intent.putExtra("zhihuilinshou", data.getRetail().getUrl1());
                                    startActivity(intent);
                                }
                            });

                            mImageViews[5].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getContext(), HomeZhihuiWebActivity.class);
                                    intent.putExtra("zhihuilinshou", data.getRetail().getUrl2());
                                    startActivity(intent);
                                }
                            });
                            mImageViews[6].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getContext(), HomeZhihuiWebActivity.class);
                                    intent.putExtra("zhihuilinshou", data.getRetail().getUrl3());
                                    startActivity(intent);
                                }
                            });

                            mImageViews[7].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getContext(), HomeZhihuiWebActivity.class);
                                    intent.putExtra("zhihuilinshou", data.getRetail().getUrl4());
                                    startActivity(intent);
                                }
                            });

                            // 机构
                            jigou = data.getJigou();
                            myServicePlaceadapter.setData(jigou);
                            //公告
                            List<NoticeBean> noticeBeanList = data.getNotice();
                            if (noticeBeanList != null) {
                                AppCache.saveNotice(noticeBeanList);
                            }
                            marqueeView.startWithList(AppCache.getNotice());
                        }
                    }
                });

    }


    private void updataColumnWidth(int size, MyGridView gridView) {
        int length = 120;
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;  //2.0
        Log.i(TAG, "updataColumnWidth:111 " + density);
        int gridviewWidth = (int) (size * (length + 4) * density);
        int itemWidth = (int) (length * density); //200
        itemWidth = DisplayUtil.dp2px(getActivity(), itemWidth);
        Log.i(TAG, "updataColumnWidth:222 " + itemWidth);

        final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                gridviewWidth, LinearLayout.LayoutParams.MATCH_PARENT);
        gridView.setLayoutParams(params); // 设置GirdView布局参数,横向布局的关键
        gridView.setColumnWidth(itemWidth); // 设置列表项宽
        gridView.setHorizontalSpacing(20); // 设置列表项水平间距
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setNumColumns(size); // 设置列数量=列表集合数
    }

    private void loadHomeHeadData() {
        OkGo.<BaseResponse<HeadDataBean>>post(Constants.BASE_URL + Constants.INDEX_HEAD_DATA_URL)
                .tag(this)
                .execute(new JsonCallback<BaseResponse<HeadDataBean>>() {
                             @Override
                             public void onSuccess(Response<BaseResponse<HeadDataBean>> response) {
                                 BaseResponse body = response.body();
                                 if (body == null) return;
                                 if (body.isState()) {
                                     HeadDataBean data = (HeadDataBean) body.getData();
                                     if (data == null) return;
                                     final List<BannerBean> bannerBeanList = data.getBanner();
                                     if (bannerBeanList != null) {
                                         AppCache.saveBanner(bannerBeanList);
                                         loopViewPager.setImgData(AppCache.getBanner());
                                         loopViewPager.setOnItemClickListener(new OnItemClickListener() {
                                             @Override
                                             public void onItemClick(View view, int i) {
                                                 Intent intent = new Intent();
                                                 intent.putExtra(Constants.TITLE, UIUtil.getString(R.string.app_name));
                                                 intent.putExtra(Constants.URL, bannerBeanList.get(i % bannerBeanList.size()).getUrl());
                                                 intent.setClass(getContext(), SimpleWebViewActivity.class);
                                                 startActivity(intent);
                                             }
                                         });
                                         loopViewPager.start();
                                     }
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
                        if (body == null) return;
                        if (body.isState()) {
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
     * 问健康
     */
    @OnClick(R.id.action_ask_service)
    public void actionAskService() {
        startActivity(DieticianIndexActivity.class);
    }

    /*

     */

    /**
     * 服务之星更多
     */

    @OnClick(R.id.home_fuwuzhixin_more)
    public void actionhomefuwuzhixin_more() {
        startActivity(DieticianIndexActivity.class);
    }

    /**
     * 健康专栏更多
     */
    @OnClick(R.id.home_health_more)
    public void actionhomHealth_more() {
        Intent intent = new Intent(getContext(), ArticleListActivity.class);
        intent.putExtra(Constants.TYPE, 5);

        startActivity(intent);
    }


    /**
     * 服务入驻 原来intent.setClass(getContext(), ServiceAuthenticationaginActivity.class);
     * 邀请码
     */
    @OnClick(R.id.action_service_in)
    public void actionServiceIn() {
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE, 1);
        intent.setClass(getContext(), DsytYaoqingActivity.class);
        startActivity(intent, !TyslApp.getInstance().isLogin());
    }

    /**
     * 智慧零售
     */
    @OnClick(R.id.action_chain_stores)
    public void actionChainStores() {
        /*RxToast.showShort("该功能正在开发当中,敬请期待");
        return;*/

        if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            startActivity(SmartRetailIndexActivity.class);
           /* if (!flag) {
                OkGo.<BaseResponse<RetailIndex>>post(Constants.BASE_URL + Constants.RETAIL_INDEX_URL)
                        .tag(this)
                        .execute(new JsonCallback<BaseResponse<RetailIndex>>(Constants.WITH_TOKEN, Constants.RETAIL_INDEX_URL) {
                            @Override
                            public void onSuccess(Response<BaseResponse<RetailIndex>> response) {
                                flag = true;
                                BaseResponse body = response.body();
                                if (body == null) return;
                                if (body.isState()) {
                                    RetailIndex data = (RetailIndex) body.getData();
                                    Log.e("智慧零售接口接入", JSONUtil.toJSON(data));
                                    Log.e(TAG, JSONUtil.toJSON(data));
                                    if (Constants.SHOP.equals(data.getOpenPage())) {
                                        Intent intent = new Intent();
                                        intent.setClass(getContext(), SmartRetailStorageRackActivity1.class);
                                        intent.putExtra(Constants.STORE_CODE, data.getStoreCode());
                                        startActivity(intent);
                                        flag = false;
                                    } else if (Constants.STORE_LIST.equals(data.getOpenPage())) {
                                        startActivity(SmartRetailIndexActivity.class);
                                        flag = false;
                                    }
                                }
                            }
                        });
            }*/
        }
    }

    /**
     * 达事链
     * 云链健康
     */
    @OnClick(R.id.action_social_assets)
    public void actionSocialAssets() {
        //    startActivity(AssetIndexActivity.class);
        if (!TyslApp.getInstance().isLogin()) {
            Intent intent = new Intent();
            intent.setClass(TyslApp.getContext(), LoginActivity.class);
            startActivityForResult(intent, Constants.LOGIN_REQUEST_CODE);
        } else {
            //   startActivity(AssertDsytActivity.class);
            startActivity(AssertDsytActivity.class);
/*
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    //  Initialize SharedPreferences
                    SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
                    //  Create a new boolean and preference and set it to truefirstAgree
                    boolean isFirstStart = getPrefs.getBoolean("firstAgreen2", true);
                    Log.e("isFirstStart", isFirstStart + "");
                    //  If the activity has never started before...
                    if (isFirstStart) {
                        Log.e("isFirstStart进来了2", "进来了2");
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(100);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            showCustomizeDialog();
                                        }
                                    });

                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }


                            }
                        }).start();
                        SharedPreferences.Editor e = getPrefs.edit();
                        e.putBoolean("firstAgreen2", false);

                        e.apply();
                    } else {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(100);
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            //    原来交易界面 AssetIndexActivity.class

                                 final Intent i = new Intent(getContext(), AssertDsytActivity.class);
                                        runOnUiThread(new Runnable() {
                                            @Override public void run() {
                                                startActivity(i);
                                            }
                                        });
                                        }
                                    });
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
            });
            t.start();*/
        }
    }

    private void showCustomizeDialog() {
        Log.e("isFirstStart进来了2", "进来了21");
        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(getContext());
        final View dialogView = LayoutInflater.from(getContext())
                .inflate(R.layout.dialog_delete, null);
        TextView del_sure = (TextView) dialogView.findViewById(R.id.del_sure);
        customizeDialog.setView(dialogView);
        final AlertDialog s = customizeDialog.show();
        del_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s.dismiss();
            }
        });

    }


    /**
     * 营养师专栏
     */
    @OnClick(R.id.dietitian_picture)
    public void dietitianPicture() {
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE, dietitian_trticle_type);
        intent.setClass(getContext(), ArticleListActivity.class);
        startActivity(intent);
    }

    /**
     * 健康365专栏
     */
    @OnClick(R.id.jk365_picture)
    public void jk365Picture() {
        Intent intent = new Intent();
        intent.putExtra(Constants.TYPE, healthy365_trticle_type);
        intent.setClass(getContext(), ArticleListActivity.class);
        startActivity(intent);
    }

    /**
     * 太宜食聊专栏
     */
    @OnClick(R.id.ad_picture)
    public void adPicture() {
        Intent intent = new Intent();
        intent.putExtra(Constants.TITLE, UIUtil.getString(R.string.app_name));
        intent.putExtra(Constants.URL, url);
        intent.setClass(getContext(), SimpleWebViewActivity.class);
        startActivity(intent);
    }

    /**
     * 健康服务天团
     */
    @OnClick(R.id.action_tian_tuan)
    public void actionTianTuan() {
        Intent intent = new Intent();
        intent.putExtra(Constants.TITLE, "健康服务天团");
        intent.putExtra(Constants.URL, tianTuanUrl);
        intent.setClass(getContext(), SimpleWebViewActivity.class);
        startActivity(intent);
    }

    /**
     * 发黑黑
     */
    @OnClick(R.id.action_faheihei)
    public void actionFaHeiHei() {
        Intent intent = new Intent();
        intent.putExtra(Constants.GOODS_ID, faHeiHeiGoodsId);
        intent.setClass(getActivity().getApplicationContext(), GoodsDetailActivity.class);
        getActivity().startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("首页onDestroy", "onDestroy");
        if (loopViewPager != null) {
            loopViewPager.stop();
        }


        removeControlView(getContext());
        OkGo.getInstance().cancelTag(this);
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        if(!hidden){
            Logger.e(TAG,"onHiddenChanged");
        }
    }*/
}