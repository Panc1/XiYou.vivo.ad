package com.gugame.othersdk;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class OtherClass {
	private static OtherClass instance;
	private static Context mContext;
	private static Activity mActivity;

	public static OtherClass getInstance() {
		if (instance == null) {
			synchronized (OtherClass.class) {
				instance = new OtherClass();
			}
		}
		return instance;
	}

	// Appliction的初始化
	public void init(Application app) {
		SDK_VIVO.init(app);
	}

	// Activity的初始化
	public void init(Context context, Activity activity) {
		mContext = context;
		mActivity = activity;
		SDK_VIVO.init(context, activity);
	}


	// 第三方退出
	public void exit(final OtherExitCallback exitCallback) {
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				SDK_VIVO.exit(exitCallback);
			}
		});
	}

	public void onAttachedToWindow() {
	}

	// 执行onResume方法
	public void onResume() {
		SDK_VIVO.onResume(mActivity);
	}

	// 执行onPuase方法,super方法之前调用
	public void onPauseEx() {
	}

	// 执行onPuase方法,super方法之后调用
	public void onPause() {
		SDK_VIVO.onPause();
	}

	// 执行onDestroy方法
	public void onDestory() {
		SDK_VIVO.onDestory(mActivity);
	}
	
	
	public static Map<String, String> goodNames = new HashMap<String, String>() {
		{
			put("001", "新手激励");
			put("002", "20体力");
			put("003", "5体力");
			put("004", "神秘金币宝箱");
			put("005", "大型金币宝箱");
			put("006", "中型金币宝箱");
			put("007", "金币宝箱");
			put("008", "极速燃料箱");
			put("009", "限时大宝箱");
			put("010", "哈雷蓝鸟套装");
			put("011", "导弹补给箱");
			put("012", "侠盗战利品");
			put("013", "侠盗补给品");
			put("014", "极限豪华补给箱");
			put("015", "三星补给装备");
		}
	};
	public static Map<String, String> prices = new HashMap<String, String>() {
		{
			put("001", "100"); //    新手激励
			put("002", "2000"); // 20体力
			put("003", "800"); //  5体力
			put("004", "2800"); // 神秘金币宝箱
			put("005", "1600"); // 大型金币宝箱
			put("006", "800"); // 中型金币宝箱
			put("007", "200"); //  金币宝箱
			put("008", "400"); //  极速燃料箱
			put("009", "800"); //  限时大宝箱
			put("010", "2800"); // 哈雷蓝鸟套装
			put("011", "800"); // 导弹补给箱
			put("012", "1600"); // 侠盗战利品
			put("013", "2000"); // 侠盗补给品
			put("014", "3000"); // 极限豪华补给箱
			put("015", "3000"); // 三星补给装备
		}
	};

}
