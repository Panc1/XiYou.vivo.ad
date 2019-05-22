package com.gugame.othersdk;

import com.vivo.unionsdk.open.VivoExitCallback;
import com.vivo.unionsdk.open.VivoUnionSDK;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SDK_VIVO {
	private static final String TAG = ">>> SDK_VIVO";
	private static Activity mActivity = null;
	private static Context cContext = null;

	// 必须配置的参数,根据每个游戏进行设置
	public static String appId = "1c3d7ac560e38a7567b4ca996e6e3239";
	public static String cpId = "20151231120936039938";
	public static String cpKey = "bcf0564c01b80d3d9b4e5ba50ee1573d";
	public static VivoCallBack mVivoCallback;

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

	public static void pay(String payName, String payPrice,
			final OtherPayCallback callback) {
		Log.e(TAG, "app-pay : " + payName + " ; " + payPrice);
		VIVOPayActivity.mPayName = payName;
		VIVOPayActivity.mPayPrice = payPrice;

		mVivoCallback = new VivoCallBack() {
			@Override
			public void Success() {
				Log.e(TAG, "app-pay-VivoCallBack-Success");
				callback.paySusses();
			}

			@Override
			public void Failed() {
				Log.e(TAG, "app-pay-VivoCallBack-Failed");
				callback.payFaild("支付失败");
			}

			@Override
			public void Cancel() {
				Log.e(TAG, "app-pay-VivoCallBack-Cancel");
				callback.payFaild("支付失败");
				// callback.payCancal();
			}
		};

		mActivity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				try {
					Log.e(TAG, "app-pay-start VIVOPayActivity");
					Intent intent = new Intent(mActivity, VIVOPayActivity.class);
					mActivity.startActivity(intent);
				} catch (Throwable e) {
					// TODO: handle exception
					Log.i(TAG, "app-vivo支付异常-e=" + e);
					mVivoCallback.Failed();
				}
			}
		});
	}

	// 退出
	public static void exit(final Activity activity,
			final OtherExitCallback callback) {

		activity.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				VivoUnionSDK.exit(activity, new VivoExitCallback() {

					@Override
					public void onExitCancel() {
						Log.i(TAG, "app-Vivo Exit Cancel");
						// TODO Auto-generated method stub

					}

					@Override
					public void onExitConfirm() {
						Log.i(TAG, "app-Vivo Exit Confirm");
						// TODO Auto-generated method stub
						callback.GameExit();
					}

				});
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
		VIVOPayActivity.onDestroy(activity);
	}

	public interface VivoCallBack {
		public void Success();

		public void Failed();

		public void Cancel();
	}
}
