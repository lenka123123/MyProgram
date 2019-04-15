package com.wokun.tysl.utils;


import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;



import org.json.JSONObject;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * @author yangshuai
 * @version 创建时间：2015-4-13 下午4:19:06 类说明 :工具类
 */
public class MyUtil {


	public static class CustomNotificationBuilder {
		private Context context;
		private int number;
		private int layoutId;
		private int iconTipId = -1;
		private int iconShowId = -1;
		private int notificationFlags = -1;
		private int notificationDefaults = 0;

		/**
		 *
		 * @param context
		 *            Context
		 */
		public CustomNotificationBuilder(Context context) {
			super();
			this.context = context;
		}

		/**
		 * 设置编号
		 *
		 * @param number
		 *            编号
		 * @return
		 */
		public CustomNotificationBuilder setNumber(int number) {
			this.number = number;
			return this;
		}

		/**
		 * 设置通知栏显示布局
		 *
		 * @param layoutId
		 *            布局id
		 * @return
		 */
		public CustomNotificationBuilder setLayout(int layoutId) {
			this.layoutId = layoutId;
			return this;
		}

		/**
		 * 设置最顶层状态栏小图标
		 *
		 * @param iconTipId
		 *            图片id
		 * @return
		 */
		public CustomNotificationBuilder setIconTip(int iconTipId) {
			this.iconTipId = iconTipId;
			return this;
		}

		/**
		 * 设置下拉状态栏时显示的通知图标
		 *
		 * @param iconShowId
		 *            图片id
		 * @return
		 */
		public CustomNotificationBuilder setIconShow(int iconShowId) {
			this.iconShowId = iconShowId;
			return this;
		}

		/**
		 * 设置行为
		 *
		 * @param flags
		 *            例如 Notification.FLAG_AUTO_CANCEL; 自动消失
		 * @return
		 */
		public CustomNotificationBuilder setFlags(int flags) {
			this.notificationFlags = flags;
			return this;
		}

		/**
		 * 设置铃声，震动，提示灯
		 *
		 * @param defaults
		 *            铃声 Notification.DEFAULT_SOUND; 震动
		 *            Notification.DEFAULT_VIBRATE ; 提示灯
		 *            Notification.DEFAULT_LIGHTS
		 * @return
		 */
		public CustomNotificationBuilder setDefaults(int... defaults) {
			for (int i = 0; i < defaults.length; i++) {
				notificationDefaults |= defaults[i];
			}
			return this;
		}


	}





	public static final String KEY_APP_KEY = "JPUSH_APPKEY";

	public static boolean isEmpty(String s) {
		if (null == s)
			return true;
		if (s.length() == 0)
			return true;
		if (s.trim().length() == 0)
			return true;
		return false;
	}

	// 校验Tag Alias 只能是数字,英文字母和中文
	public static boolean isValidTagAndAlias(String s) {
		Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	// 取得AppKey
	public static String getAppKey(Context context) {
		Bundle metaData = null;
		String appKey = null;
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			if (null != ai)
				metaData = ai.metaData;
			if (null != metaData) {
				appKey = metaData.getString(KEY_APP_KEY);
				if ((null == appKey) || appKey.length() != 24) {
					appKey = null;
				}
			}
		} catch (PackageManager.NameNotFoundException e) {

		}
		return appKey;
	}

	// 取得版本号
	public static String GetVersion(Context context) {
		try {
			PackageInfo manager = context.getPackageManager().getPackageInfo(
					context.getPackageName(), 0);
			return manager.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			return "Unknown";
		}
	}

	public static void showToast(final String toast, final Context context) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Looper.prepare();
				Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();
				Looper.loop();
			}
		}).start();
	}

	public static boolean isConnected(Context context) {
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = conn.getActiveNetworkInfo();
		return (info != null && info.isConnected());
	}



	/**
	 * 判断是否包含SIM卡
	 *
	 * @return 状态
	 */
	public static boolean ishasSimCard(Context context) {
		TelephonyManager telMgr = (TelephonyManager)
				context.getSystemService(Context.TELEPHONY_SERVICE);
		int simState = telMgr.getSimState();
		boolean result = true;
		switch (simState) {
			case TelephonyManager.SIM_STATE_ABSENT:
				result = false; // 没有SIM卡
				break;
			case TelephonyManager.SIM_STATE_UNKNOWN:
				result = false;
				break;
		}
		Log.e("sim卡判断", result ? "有SIM卡" : "无SIM卡");
		return result;
	}


	/**
	 * 判断某个界面是否在前台
	 *
	 * @param activity 要判断的Activity
	 * @return 是否在前台显示
	 */
	public static boolean isForeground(Activity activity) {
		return isForeground(activity, activity.getClass().getName());
	}

	/**
	 * 判断某个界面是否在前台
	 *
	 * @param context   Context
	 * @param className 界面的类名
	 * @return 是否在前台显示
	 */
	public static boolean isForeground(Context context, String className) {
		if (context == null || TextUtils.isEmpty(className))
			return false;
		ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
		if (list != null && list.size() > 0) {
			ComponentName cpn = list.get(0).topActivity;
			if (className.equals(cpn.getClassName()))
				return true;
		}
		return false;

	}
	}
