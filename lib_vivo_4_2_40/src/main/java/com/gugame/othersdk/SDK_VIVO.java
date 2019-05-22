package com.gugame.othersdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.vivo.unionsdk.open.VivoExitCallback;
import com.vivo.unionsdk.open.VivoUnionSDK;

public class SDK_VIVO {
	private static final String TAG = ">>> SDK_VIVO";
	private static Activity mActivity = null;
	private static Context cContext = null;

	// 必须配置的参数,根据每个游戏进行设置
	public final static String appId = "1c3d7ac560e38a7567b4ca996e6e3239";
	public final static String cpId = "20151231120936039938";
	public final static String cpKey = "bcf0564c01b80d3d9b4e5ba50ee1573d";

	public static VivoCallBack mVivoCallback = new VivoCallBack() {
		@Override
		public void Success() {
			Log.e(TAG, "app-pay-VivoCallBack-Success");
			payCallback.paySusses();
		}

		@Override
		public void Failed() {
			Log.e(TAG, "app-pay-VivoCallBack-Failed");
			payCallback.payFaild("支付失败");
		}

		@Override
		public void Cancel() {
			Log.e(TAG, "app-pay-VivoCallBack-Cancel");
			payCallback.payFaild("取消支付");
			// callback.payCancal();
		}
	};
	private static OtherPayCallback payCallback;
	

	// 初始化SDK
	public static void init(Application app) {
		Log.e(TAG, "app-init-appId:" + appId);
		Log.e(TAG, "app-init-cpId:" + cpId);
		Log.e(TAG, "app-init-cpKey:" + cpKey);
		VivoUnionSDK.initSdk(app, appId, false);
	}

	public static void init(Context context, Activity activity) {
		mActivity = activity;
		cContext = context;
	}

	public static void pay(String payName, String payPrice, final OtherPayCallback callback) {
	}

	// 退出
	public static void exit( OtherExitCallback callback) {
		final OtherExitCallback exitCallback = callback;
				VivoUnionSDK.exit(mActivity, new VivoExitCallback() {
					@Override
					public void onExitConfirm() {
						exitCallback.GameExit();
					}
					@Override
					public void onExitCancel() {
					}
				});
	}

	/** 在super之后调用 */
	public static void onResume(Activity activity) {
	}

	/** 在super之前调用 */
	public static void onPause() {
	}

	public static void onDestory(Activity activity) {
	}

	public interface VivoCallBack {
		public void Success();

		public void Failed();

		public void Cancel();
	}
	
}
