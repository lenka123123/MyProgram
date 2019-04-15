package com.wokun.tysl.config;

import android.os.Environment;

import java.io.File;

public class Constants {

    public static final String APP_ID = "wxd670b2e2b07f5175";
    public static String BASE_URL = "http://api.tyitop.com/";
    //public static String BASE_URL = "http://192.168.8.222/";

    public static final String IMAGE_DIR = Environment.getExternalStorageDirectory() + File.separator + "Android截屏";
    public static final String SCREEN_SHOT ="screenshot.png";
    /** 短信*/
    //获取短信验证码
    public static String GET_CODE_URL = "v1/register/get-code";

    /** 登陆注册*/
    //app用户登录
    public static String CHECK_LOGIN_URL = "v1/login/check-login";
    //用户注册
    public static String REGISTER_URL = "v1/register/index";
    //忘记密码（修改）
    public static String ALTER_PASSWORD_URL = "v1/login/alter-password";
    //退出登录
    public static String LOGOUT_URL = "v1/login/logout";
    //注册协议
    public static String XIE_YI_URL = "v1/login/xieyi";

    //判断是否领过红包
    public static String INDEX_IS_SIGNIN = "v1/index/is-signin";

    /** 个人中心*/
    //修改用户昵称（普通用户营养师通用）
    public static String UCENTER_ALTER_NAME_URL = "/v1/ucenter/alter-name";
    //获取个人中心数据
    public static String UCENTER_URL = "v1/ucenter/index";
    //密码更改（更新密码）
    public static String UCENTER_PASSWORD_CHANGE_URL = "v1/ucenter/password-change";
    //修改手机号
    public static String UCENTER_CHANGE_MOBILE_URL = "v1/ucenter/change-mobile";
    //用户头像上传
    public static String UCENTER_PICTURE_UPLOAD_URL = "v1/ucenter/picture-upload";
    //意见反馈
    public static String UCENTER_ADD_FEEDBACK_URL = "v1/ucenter/add-feedback";
    //联系我们
    public static String UCENTER_ABOUT_US_URL = "v1/ucenter/about-us";
    //获取营养师基本信息
    public static String UCENTER_GET_USER_INFO_URL = "v1/ucenter/get-user-info";
    //营养师修改个人简介
    public static String UCENTER_ALTER_PROFILE_URL = "v1/ucenter/alter-profile";
    //获取普通用户我的健康（营养师服务订单）
    public static String UCENTER_MY_HEALTHY_URL = "v1/ucenter/my-healthy";
    //我的申请
    public static String UCENTER_MY_APPLY_URL = "v1/ucenter/my-apply";
    //营养师个人中心—可推荐商品列表
    public static String DIETITIAN_TUI_GOODS_URL = "v1/dietitian/tui-goods";
    //营养师个人中心-添加或取消推荐商品
    public static String DIETITIAN_ADD_CUT_TUI_URL = "v1/dietitian/add-cut-tui";
    //修改昵称
    public static String UCENTER_ALTER_USERNAME_URL = "v1/ucenter/alter-username";

    /** 地址管理*/
    //获取用户地址列表
    public static String ADDRESS_INDEX_URL = "v1/address/index";
    //获取省市区
    public static String ADDRESS_GET_AREA_URL = "v1/address/get-area";
    //设置用户默认地址
    public static String ADDRESS_SET_DEFAULT_URL = "v1/address/set-default";
    //删除用户地址
    public static String ADDRESS_DELETE_ADDRESS_URL = "v1/address/delete-address";
    //添加用户地址
    public static String ADDRESS_ADD_ADDRESS_URL = "v1/address/add-address";
    //修改用戶地址
    public static String ADDRESS_ALTER_ADDRESS_URL = "v1/address/alter-address";
    //上传位置信息（经纬度）
    public static String ADDRESS_GET_POSITION_URL = "v1/address/get-position";
    //获取用户默认地址
    public static String ADDRESS_GET_DEFAULT_URL = "v1/address/get-default";
    //订单确认地址选择接口（自提、快递）
    public static String ORDER_CONFIRM = "v1/retail/order-confirm";





    /** 商品*/
    //商品一级分类
    public static String TOP_CATEGORY_URL = "v1/goods/top-category";
    //商品二三级分类
    public static String SUB_CATEGORY_URL = "v1/goods/sub-category";
    //商品详细信息
    public static String  GOODS_DETAIL_URL = "v1/goods/detail";
    //智慧商品详细信息
    public static String  GOODS_DETAIL_INFO = "v1/retail/goods-detail-info";
    //商品订单确认页面数据显示接口
    public static String  ORDER_SHOW= "v1/cart/order-show";
    //商品列表
    public static String GOODS_LIST_URL = "v1/goods/list";
  //支付成功
    public static String RETAIL_PAY_RESULT = "v1/retail/pay-result";

    //商品展示
    public static String GOODS_CLASS = "v1/goods/goods-class";
    //邀请码接口
    public static String INDEX_INVITE = "v1/index/invite";
    //发现余额接口
    public static String INTEGRAL_FIND_MONEY = "v1/integral/find-money";
    //商品评价列表页接口
    public static String GOODS_EVAL_LIST_URL = "v1/goods/eval-list";

    /** 收藏*/
    //取消收藏（商品、店铺、营养师、文章）
    public static String FAVORITES_DELETE_URL = "v1/favorites/delete";
    //添加收藏（商品、店铺、营养师、文章）
    public static String FAVORITES_ADD_URL = "v1/favorites/add";
    //获取收藏列表
    public static String FAVORITES_LIKE_LIST_URL = "v1/favorites/like-list";

    /** 首页*/
    //首页头部（banner和公告）数据接口
    public static String INDEX_HEAD_DATA_URL = "v1/index/head-data";
    //首页主体数据
    public static String INDEX_BODY_DATA_URL = "v1/index/body-data";
    //新首页
    public static String INDEX_HEAD_INDEX = "v1/index/index";

    /** 营养师*/
    //问服务首页（营养师筛选页面）接口
    public static String DIETITIAN_INDEX_URL = "v1/dietitian/index";
    //获取营养师擅长领域
    public static String DIETITIAN_GET_FIELD_TYPE_URL = "v1/dietitian/get-field";
    //获取职业类型
    public static String DIETITIAN_GET_JOB_TYPE_URL = "v1/dietitian/get-job-type";
    //购买服务支付接口
    public static String PAYMENT_BUY_SERVICE_URL = "v1/payment/buy-service";
    //营养师列表
    public static String DIETITIAN_LIST_URL = "v1/dietitian/list";
    //营养师个人主页
    public static String DIETITIAN_DETAIL_URL = "v1/dietitian/detail";
    //购买服务生成订单接口
    public static String DIETITIAN_SERVICE_ORDER_URL = "v1/dietitian/service-order";
    //营养师服务评价
    public static String DIETITIAN_EVAL_URL = "v1/dietitian/eval";
    //个人中心服务订单几种状态
    public static String DIETITIAN_ORDER_LIST_URL = "v1/dietitian/order-list";
    //我的收益-收益说明
    public static String DIETITIAN_PROFIT_REMARK_URL = "v1/dietitian/profit-remark";
    //我的收益-获取收益（收益主页接口）
    public static String DIETITIAN_MY_PROFIT_URL = "v1/dietitian/my-profit";
    //我的收益-提现页面接口
    public static String DIETITIAN_WITHDRAW_CASH_URL = "v1/dietitian/withdraw-cash";
    //我的收益-添加结算账户
    public static String DIETITIAN_ADD_ACCOUNT_URL = "v1/dietitian/add-account";
    //我的收益-获取银行数据
    public static String OTHER_GET_BANK_URL = "v1/other/get-bank";
    //我的收益-获取收益账号列表
    public static String DIETITIAN_ACCOUNT_LIST_URL = "v1/dietitian/account-list";
    //我的收益-收益明细
    public static String DIETITIAN_PROFIT_LOG_URL = "v1/dietitian/profit-log";
    //我的收益-立即提现接口
    public static String DIETITIAN_WITHDRAW_URL = "v1/dietitian/withdraw";
    //我的收益-提现明细
    public static String DIETITIAN_WITHDRAW_CASH_LOG_URL = "v1/dietitian/withdraw-cash-log";
    //营养师服务订单拒绝服务
      public static String DIETITIAN_REFUSE_SERVICE_URL = "v1/dietitian/refuse-service";
    //申请解绑

    public static String UCENTER_UNTIE = "v1/ucenter/untie";
    //分享送积分
    public static String INTEGRAL_SHARE = "v1/integral/share";


    //营养师发布的文章列表
    public static String ARTICLE_DIETITIAN_RELEASE_URL = "v1/article/dietitian-release";
    //服务评价接口
    public static String ARTICLE_DIETITIAN_EVAL_MORE = "v1/dietitian/eval-more";
    //商品评价更多
    public static String ARTICLE_RETAIL_EVAL_MORE = "v1/retail/eval-more";
    //获取达事隐私协议
    public static String INTEGRAL_XIEYI = "v1/integral/xieyi";
   // 增值护照显示页接口
   public static String INTERGRAL_HUZHAO_SHOW = "v1/integral/huzhao-show";
   //申请护照接口
    public static String INTERGRAL_HUZHAO = "v1/integral/huzhao";
    /** 文章*/
    //获取文章详情页
    public static String ARTICLE_CONTENT_URL = "v1/article/content";
    //获取文章列表
    public static String ARTICLE_LIST_URL = "v1/article/list";
    //发表文章评论
    public static String ARTICLE_EVAL_URL = "v1/article/eval";
    //文章点赞接口
    public static String ARTICLE_CLICK_ZAN_URL = "v1/article/click-zan";
    //获取文章评论列表
    public static String ARTICLE_GET_EVAL_URL = "v1/article/get-eval";

    /** 店铺*/
    //店铺主页接口
    public static String STORE_INDEX_URL = "v1/store/index";

    /** 连锁门店（营养街区）*/
    //连锁门店列表页
    public static String STREET_LIST_URL = "v1/street/list";
    //连锁门店详情页
    public static String STREET_DETAIL_URL = "v1/street/detail";
    //连锁门店活动内容页
    public static String STREET_ACTIVE_CONTENT_URL = "v1/street/active-content";

    /** 积分商城*/
    //用户签到
    public static String INTEGRAL_SIGN_IN_URL = "v1/integral/sign-in";
    //获取用户积分记录
    public static String INTEGRAL_RECORD_URL = "v1/integral/integral-record";
    //积分商品详情页
    public static String INTEGRAL_GOODS_DETAIL_URL = "v1/integral/goods-detail";
   //个人中心社交链
    public  static   String  ASSETS_INDEX  = "v1/assets/index";
    //意见反馈
    public  static   String  FEEDBACK  = "v1/ucenter/add-feedback";
    //历史反馈
    public  static   String  HISTORY_FEEDBACK  = "v1/ucenter/history-feedback";

    //积分商城首页
    public static String INTEGRAL_INDEX_URL = "v1/integral/index";
    //积分商城首页获取更多积分商品（加载更多用）
    public static String INTEGRAL_GET_GOODS_MORE_URL = "v1/integral/get-goods-more";
    //获取兑换记录
    public static String INTEGRAL_GET_EXCHANGE_RECORD_URL = "v1/integral/get-exchange-record";
    //兑换商品接口
    public static String INTEGRAL_EXCHANGE_GOODS_URL = "v1/integral/exchange-goods";
    //查看快递接口
    public static String INTEGRAL_EXPRESS_URL = "v1/integral/express";
    //兑换商品确认收货接口
    public static String INTEGRAL_ORDER_FINISH_URL = "v1/integral/order-finish";
    //获取积分规则
    public static String INTEGRAL_RULE_URL = "v1/integral/rule";

    /** 其他*/
    //上传多图片接口
    public static String OTHER_UPLOAD_FILE_MORE_URL = "v1/other/upload-file-more";
    //单图片上传通用
    public static String OTHER_UPLOAD_FILE_SINGLE_URL = "v1/other/upload-file-single";
    //判断是否是同一个设备登录
    public static String OTHER_IS_SAME_DEVICE_URL = "v1/other/is-same-device";
    //设置开关
    public static String OTHER_ON_OFF_URL = "v1/other/on-off";

    /** 订单（商品）*/
    //获取评价订单商品列表
    public static String ORDER_EVAL_LIST_URL = "v1/order/order-eval-list";
    //商品订单评价
    public static String ORDER_ADD_EVAL_URL = "v1/order/add-eval";
    //我的订单数据（几种状态）
    public static String ORDER_STATE_DATA_URL = "v1/order/order-state-data";
    //智慧零售的订单数据（几种状态）
    public static String USER_ORDER = "v1/retail/user-order";

    //商品订单详情
    public static String ORDER_DETAIL_URL = "v1/order/order-detail";
    //智慧商品订单详情
    public static String RETAIL_DETAIL_URL = "v1/retail/order-detail";

    //生成商品订单并且支付
    public static String ORDER_SUBMIT_URL = "v1/order/order-submit";
    //确认订单结算接口
    public static String RETAILPAYORDER = "v1/retail/pay-order";
    //确认收货
    public static String ORDER_CONFIRM_ACCEPT_URL = "v1/order/confirm-accept";
    //取消待付款的订单（取消订单）
    public static String ORDER_CANCEL_ORDER_URL = "v1/order/cancel-order";
    //商品订单待付款，点击支付调起支付接口
    public static String ORDER_PAY_URL = "v1/order/order-pay";




    /** 购物车*/
    //商品加入购物车
    public static String CART_ADD_GOODS_URL = "v1/cart/add-goods";
    //购物车页面
    public static String CART_LIST_URL = "v1/cart/list";
    //删除商品
    public static String CART_DELETE_GOODS_URL = "v1/cart/delete-goods";
    //去结算
    public static String CART_UPDATE_CART_URL = "v1/cart/update-cart";
    //商品订单确认页面数据显示接口
    public static String CART_ORDER_SHOW_URL = "v1/cart/order-show";
    //通过地址获取运费
    public static String CART_GET_FREIGHT_PRICE_URL = "v1/cart/get-freight-price";
    //商品订单确认页面数据显示接口
    public static String GO_CART_ORDER_SHOW = "v1/order/go-order-show";

    /** 订单（营养师）*/
    //确认务（接受用户申请）
    public static String DIETITIAN_ACCEPT_SERVICE_URL = "v1/dietitian/accept-service";
    //查看服务订单评价
    public static String DIETITIAN_LOOK_EVAL_URL = "v1/dietitian/look-eval";
    //回复评价（服务订单）
    public static String DIETITIAN_REPLY_EVAL_URL = "v1/dietitian/reply-eval";
    //服务订单退款详情
    public static String DIETITIAN_REFUND_DETAIL_URL = "v1/dietitian/refund-detail";

    /** 融云即时聊天*/
    public static String IM_GET_TOKEN_URL = "v1/im/get-token";

    /** 聚实惠*/
    //订单详情
    public static String DISCOUNT_ORDER_STATE_DETAIL_URL = "v1/discount/order-state-detail";
    //聚食惠首页
    public static String DISCOUNT_INDEX_URL = "v1/discount/index";
    //聚实惠详情页
    public static String DISCOUNT_DETAIL_URL = "v1/discount/detail";
    //订单确认页面数据
    public static String DISCOUNT_ORDER_OK_URL = "v1/discount/order-ok";
    //生成订单且生成支付定金的参数
    public static String DISCOUNT_ORDER_SUBMIT_URL = "v1/discount/order-submit";
    //订单的几种状态
    public static String DISCOUNT_ORDER_STATE_URL = "v1/discount/order-state";
    //确认收货
    public static String DISCOUNT_ACCEPT_OK_URL = "v1/discount/accept-ok";
    //立即支付--直接跳转到支付类型选择
    public static String DISCOUNT_PAY_ORDER_URL = "v1/discount/pay-order";

    /** 入驻（商家or服务）*/
    //商家入驻申请
    public static String JOIN_BUSINESS_URL = "v1/join/business";
    //商家入驻 -商家展示数据
    public static String JOIN_BUSINESS_SHOW_URL = "v1/join/business-show";
    //营养师入驻
    public static String JOIN_SERVICE_URL = "v1/join/service";
    //营养师入驻协议
    public static String JOIN_XIE_YI_URL = "v1/join/xieyi";
      //智慧零售入住
      public static String JOIN_RETAIL = "v1/join/retail";


    /** 消息通知*/
    //系统通知
    public static String UCENTER_GET_MY_NOTICE_URL = "v1/ucenter/get-my-notice";
    //服务消息
    public static String UCENTER_GET_SERVICE_NOTICE_URL = "v1/ucenter/get-service-notice";

    /** 社交资产*/
    //交易市场数据（买进卖出）
    public static String ASSETS_RELEASE_LIST_URL = "v1/assets/release-list";
    //我的交易记录
    public static String ASSETS_MY_DEAL_URL = "v1/assets/my-deal";
    //我的发布记录数据
    public static String ASSETS_MY_RELEASE_RECORD_URL = "v1/assets/my-release-record";
    //发布买卖需求页面所需的数据
    public static String ASSETS_RELEASE_URL = "v1/assets/release";
    //处理发布买卖社交资产的需求
    public static String ASSETS_DO_RELEASE_URL = "v1/assets/do-release";
    //我要买入操作
    public static String ASSETS_DO_MY_BUY_URL = "v1/assets/do-my-buy";
    //我要出售操作
    public static String ASSETS_DO_MY_SELL_URL = "v1/assets/do-my-sell";
    //编辑发布记录
    public static String ASSETS_EDIT_URL = "v1/assets/edit";
    //删除发布记录
    public static String ASSETS_DEL_RELEASE_URL = "v1/assets/del-release";

    /** 余额充值 */
    //会员充值
    public static String UCENTER_RECHARGE_URL = "v1/ucenter/recharge";
    //余额明细
    public static String UCENTER_MONEY_DETAIL_URL = "v1/ucenter/money-detail";

    /** 智慧零售 */
    //获取店铺信息
    public static String RETAIL_SHOP_URL = "v1/retail/shop";
    //智慧零售按钮页面跳转
    public static String RETAIL_INDEX_URL = "v1/retail/index";
    //获取附近的店铺
    public static String RETAIL_STORE_LIST_URL = "v1/retail/store-list";
    //扫描后页面跳转
    public static String RETAIL_SAOMA_URL = "v1/retail/saoma";
    //货架绑定
    public static String RETAIL_STORE_BIND_URL = "v1/retail/store-bind";
    //商家管理页面
    public static String RETAIL_STORE_MANAGE_URL = "v1/retail/store-manage";
    //账号提现页面
    public static String RETAIL_WITHDRAW = "v1/retail/withdraw";
    //处理账户提现
     public static String RETAIL_DO_WITHDRAW = "v1/retail/do-withdraw";
   //智慧零售添加提现银行卡
     public static String RETAIL_ADD_BANKCARD = "v1/retail/add-bankcard";
   //提现账号列表接口
     public static String RETAIL_BANK_LIST = "v1/retail/bank-list";
    //提现明细
     public static String RETAIL_WITHDRAW_RECORD= "v1/retail/withdraw-record";
     //创客二维码接口
      public static String MAKE_QRCODE_API= "v1/retail/make-qrcode-api";
    //	创客管理
     public static String RETAIL_MAKER_LIST= "/v1/retail/maker-list";
     //创客订单
     public static String MAKER_ORDER_API= "/v1/retail/maker-order-api";
     //店铺会员管理
     public static String RETAIL_STORE_USER= "v1/retail/store-user";
     //店铺管理界面订单管理（线上订单、自提订单） 商家管理界面订单管理（跟进订单）
     public static String RETAIL_STORE_ORDER= "v1/retail/store-order";
     // 标记跟进
     public static String RETAIL_STORE_FOLLOW= "v1/retail/store-order-follow";
    // 确认自提
    public static String RETAIL_SELF_LIFTING= "v1/retail/self-lifting";



    /** 字符串常量*/
    public static final String KEY = "ysd3w6ei2asd8h423jsa2sdf"; //秘钥
    public static final String ANDROID = "ANDROID";
    public static final String TOKEN = "token";
    public static final String USER_JSON = "user_json";
    public static final String USER_INFO_JSON = "userinfo_json";
    public static final String DIETITIAN_INFO_JSON = "dietitianinfo_json";
    public static final String USER = "user";
    public static final String CART_INFO = "cart_info";
    public static final String TOP_CATEGORY_JSON = "top_category_json";
    public static final String SUB_CATEGORY_JSON = "sub_category_json";
    public static final String GOODS_LIST_JSON = "goods_list_json";
    public static final String PAGE = "page";
    public static final String PAGE_SIZE = "page_size";

    public static final String BANNER = "banner";
    public static final String Notice = "notice";
    public static final String ADDRESS_JSON = "address.json";
    public static final String ORDER_ID = "order_id";
    public static final String STATE = "state";
    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String FIELD_ID = "field_id";
    public static final String TYPE_NAME = "type_name";
    public static final String NA_ID = "na_id";
    public static final String NS_ID = "ns_id";
    public static final String PROFILE = "profile";
    public static final String DIETITIAN_ID = "dietitian_id";
    public static final String DIETITIAN_USER_ID = "dietitian_user_id";
    public static final String NUMBER = "number";
    public static final String PAY_PRICE = "pay_price";
    public static final String ONLINE_PAYMENT_URL = "online_payment_url";
    public static final String STORE_ID = "store_id";
    public static final String ACCOUNT_ID = "account_id";
    public static final String ACCOUNT_TYPE_NAME = "account_type_name";
    public static final String ACCOUNT_TYPE = "account_type";
    public static final String DATA = "data";
    public static final String CONTACTS = "contacts";
    public static final String TEL = "tel";
    public static final String PROVINCE_ID = "province_id";
    public static final String ADDRESS = "address";
    public static final String LONG = "long";
    public static final String LAT = "lat";

    public static final String LinkMan = "linkman";
    public static final String Store_Picture = "storepicture";
    public static final String Store_Name = "storename";
    public static final String MOBILE = "mobile";
    public static final String PASSWORD = "password";
    public static final String CODE = "code";
    public static final String MOBILE_CODE = "mobile_code";
    public static final String SMS_CODE = "smscode";

    public static final String ADDRESS_ID = "address_id";
    public static final String AREA = "area";
    public static final String CITY_ID = "city_id";
    public static final String DISTRICT_ID = "district_id";
    public static final String DISCOUNT_ID = "discount_id";
    public static final String GOODS_IMAGE = "goods_image";
    public static final String GOODS_NAME = "goods_name";
    public static final String GOODS_ID = "goods_id";
    public static final String PAY_INTEGRAL = "pay_integral";
    public static final String NUMS = "nums";
    public static final String REQUEST_CODE = "request_code";
    public static final String HEAD_LOGO = "head_logo";
    public static final String KEYWORDS = "keywords";
    public static final String SOURCE_ID = "source_id";
    public static final String USER_TYPE = "user_type";
    public static final String SOURCE_TYPE = "source_type";
    public static final String URL = "url";
    public static final String DQR = "dqr";
    public static final String YJJ = "yjj";
    public static final String FWZ = "fwz";
    public static final String YGQ = "ygq";
    public static final String R_ID = "r_id";
    public static final String NUMBERS = "numbers";

    public static final String ORDER_NUMBER = "order_number";
    public static final String SERVICE_PRICE = "service_price";
    public static final String SERVICE_TIME = "service_time";
    public static final String SERVICE_TOTAL_PRICE = "service_total_price";
    public static final String PAY_TYPE = "pay_type";
    public static final String TAB_POSITION = "tab_position";
    public static final String ORDER_STATE = "order_state";
    public static final String STATUS = "status";

    public static final String ACCOUNT_NAME = "account_name";
    public static final String ACCOUNT_CODE = "account_code";
    public static final String WITHDRAW_CASH = "withdraw_cash";
    public static final String NEW_MOBILE = "new_mobile";
    public static final String SERVICE_DAYS = "service_days";
    public static final String SERVICE_FEE = "service_fee";
    public static final String PAY_MONEY = "pay_money";
    public static final String LOGIN_TYPE = "login_type";
    public static final String INVITE_CODE = "invite_code";
    public static final String CONTENT = "content";
    public static final String OLD_PASSWORD = "old_password";
    public static final String NEW_PASSWORD = "new_password";
    public static final String CART_ID_STR = "cart_id_str";
    public static final String ORDER_TOTAL = "order_total";
    public static final String ORDER = "order";
    public static final String N = "n";
    public static final String PD = "pd";
    public static final String PU = "pu";
    public static final String S = "s";
    public static final String Z = "z";
    public static final String USER_ID = "user_id";
    public static final String TIME_STAMP = "time_stamp";
    public static final String SIGN = "sign";
    public static final String SGC_ID = "sgc_id";
    public static final String GOOD_CLASS_ID = "good_class_id";
    public static final String CAN_USE = "can_use";
    public static final String UNIT_PRICE = "unit_price";

    public static final String STORE_CODE = "store_code";
    public static final String STORE_LIST = "store-list";

    public static final String GOODS_SORT = "goods_sort";
    public static final String GID = "gid";
    public static final String GPD = "gpd";
    public static final String GPA = "gpa";
    public static final String GCD = "gcd";
    public static final String GCA = "gca";

    public static final String FAVORITES_TYPE = "favorites_type";
    public static final String ALIPAY = "alipay";
    public static final String WXPAY = "wxpay";

    public static final String UPLOAD_TYPE_EVAL_GOODS = "eval_goods";     //多图片上传类型-评价商品
    public static final String UPLOAD_TYPE_EVAL_SERVICE = "eval_service"; //多图片上传类型-评价营养师
    public static final String UPLOAD_TYPE_ENCLOSURE = "enclosure";       //多图片上传类型-认证资料上传
    public static final String UPLOAD_FEEDBACK = "feedback";
    public static final String STORE_NAME = "store_name";
    public static final String STORE_ADDRESS = "store_address";
    public static final String UPLOAD_TYPE_AVATAR = "avatar";             //单图片上传类型-头像
    public static final String UPLOAD_TYPE_ID_CARD = "id_card";           //单图片上传类型-身份证
    public static final String ALL = "all";

    public static final String SHOP = "shop";
    public static final String MANAGE = "manage";
    public static final String BIND = "bind";

    public static final String UPLOAD_FILE = "UploadForm[file]";

    public static final String ID = "id";
    public static final String TYPE_ID = "type_id";
    public static final String NAME = "name";
    public static final String SEX = "sex";
    public static final String BIRTHDAY = "birthday";
    public static final String IDC_NO = "idc_no";
    public static final String FIELD_IDS = "field_ids";
    public static final String WORK_LIFE = "work_life";
    public static final String CREDENTIALS = "credentials";
    public static final String HEADIMG = "headimg";
    public static final String IDC_FRONT = "idc_front";
    public static final String IDC_BACK = "idc_back";
    public static final String IDC_PIC = "idc_pic";
    public static final String AREA_INFO = "area_info";
    public static final String NULL_MOBILE_MESSAGE = "手机号码不能为空";
    public static final String NULL_PWD_MESSAGE = "密码不能为空";
    public static final String NULL_CODE_MESSAGE = "验证码不能为空";
    public static final String LOGIN_FAILURE_MESSAGE = "登录失败";
    public static final String NET_WORK_ERROR = "网络请求出错，请检查您的网络";

    /** 数值常量*/
    public static final int TAB_POSITION_HOME = 0;           //首页位置
    public static final int TAB_POSITION_ZB = 1;             //直播室位置
    public static final int TAB_POSITION_SHOP_CART = 2;      //购物车位置
    public static final int TAB_POSITION_UCENTER = 3;        //个人中心位置

    public static final int FAVORITES_TYPE_GOODS = 0;         //商品收藏
    public static final int FAVORITES_TYPE_STORE = 1;         //店铺收藏
    public static final int FAVORITES_TYPE_DIETICIAN = 2;     //营养师收藏
    public static final int FAVORITES_TYPE_ARTICLE = 3;       //文章收藏

    //requestCode
    public static final int LOGIN_REQUEST_CODE = 13353;       //登录请求码
    public static final int SETTING_REQUEST_CODE = 13354;     //设置请求码
    public static final int SIGN_IN_REQUEST_CODE = 13355;     //签到请求码
    public static final int ASSET_INDEX_REQUEST_CODE = 13356;     //社交资产码

    public static final int NO_TOKEN = 1;                     //请求状态，不带Token
    public static final int WITH_TOKEN = 2;                    //请求状态，需要携带Token
    public static final int LOGIN_WITH_TOKEN = 3;             //请求状态，如果为登录状态则携带Token

    //resultCode
    public static final int LOGIN_RESULT_CODE = 13391;        //登录响应码

    public static final int SETTING_RESULT_CODE = 13390;      //设置响应码

    public static final int PG_SIZE = 20;
}