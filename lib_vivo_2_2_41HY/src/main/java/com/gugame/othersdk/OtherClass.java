package com.gugame.othersdk;

import java.util.HashMap;
import java.util.Map;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

public class OtherClass {
	private static OtherClass instance;
	private static Context mContext;
	private static Activity mActivity;
	private static OtherPayCallback payCallback;
	private static OtherExitCallback exitCallback;

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

	// 使用code得到名字
	public String getName(String payCode) {
		return goodNames.get(payCode);
	}

	// 使用Code得到价格
	public String getPrice(String payCode) {
		return prices.get(payCode).toString();
	}

	// 封装Pay方法
	public void pay(String payCode, OtherPayCallback callback) {
		String payName = getName(payCode);
		String payPrice = getPrice(payCode);
		;
		int type = TelephoneUtils.getProvidersType(mActivity);
		if (type == TelephoneUtils.TYPE_CM) {
			if (payCode.equals("015"))
				payPrice = "2";
		}

		pay(payCode, payName, payPrice, callback);
	}

	// 第三方支付
	public void pay(String payCode, String payName, String payPrice, OtherPayCallback callback) {
		payCallback = callback;
		SDK_VIVO.pay(payName, payPrice, callback);
	}

	// 第三方退出
	public void exit(final OtherExitCallback callback) {
		Log.e(">>> OtherClass", "app-exit");
		exitCallback = callback;
		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Log.e(">>> OtherClass", "app-Sdk_vivo Exit");
				SDK_VIVO.exit(mActivity, callback);
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

	// 道具名称（道具编号，道具名称）
	public static Map<String, String> goodNames = new HashMap<String, String>() {
		{
			put("001", "钻石小福袋");     
			put("002", "钻石中福袋");     
			put("003", "钻石大福袋");   
			put("004", "超级钻石福袋");     
			put("005", "金币银牌包");    
			put("006", "金币黄金包");    
			put("007", "一键满级");    
			put("008", "索里道具大礼");       
			put("009", "森美拉超值大礼");     
			put("010", "卡维力黄金大礼");       
			put("011", "雷古曼豪华大礼");      
			put("012", "雷古曼复活大礼");      
			put("013", "召唤卡维力");     
			put("014", "补满体力");      
			put("015", "特惠大礼"); 

		}
	};
	// 道具价格（道具编号，道具价格），小数点后面保持2位,单位元
	public static Map<String, String> prices = new HashMap<String, String>() {
		{
			put("001", "600"); // 20奖杯
			put("002", "1000"); // 120奖杯
			put("003", "2000"); // 280奖杯
			put("004", "2900"); // 480奖杯
			put("005", "1000"); // 300足球
			put("006", "2900"); // 550足球
			put("007", "1000"); // 2500友情点
			put("008", "1000"); // 10000金币
			put("009", "600"); // 60000金币
			put("010", "2000"); // 140000金币
			put("011", "2900"); // 700000金币
			put("012", "800"); // 新手礼包
			put("013", "2900"); // 极限豪华补给包
			put("014", "600"); // 全明星豪情礼包
			put("015", "10"); // 足球传奇礼包
		}
	};

}
