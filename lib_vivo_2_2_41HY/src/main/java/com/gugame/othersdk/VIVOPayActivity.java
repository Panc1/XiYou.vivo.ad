package com.gugame.othersdk;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.paydemo.HTTPSTrustManager;
import com.example.paydemo.JsonParser;
import com.other.sdk.R;
import com.vivo.unionsdk.open.VivoPayCallback;
import com.vivo.unionsdk.open.VivoPayInfo;
import com.vivo.unionsdk.open.VivoUnionSDK;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

public class VIVOPayActivity extends Activity {
	private static final String TAG = ">>> VIVOPayActivity";

	public static Context mContext;
	public static Activity mActivity;
	// public static String mPayCode;
	public static String mPayName;
	public static String mPayPrice;

	static boolean isfirst = false;
	// vivo插件包名
	static String pkgName = "com.vivo.sdkplugin";
	// 是否安装状态 1是游戏安装前已安装插件，2是游戏购买是安装插件
	static int stay;
	private static final String URL_INITIAL_PAYMENT = "https://pay.vivo.com.cn/vivoPay/getVivoOrderNum";
	private VivoPayInfo mVivoPayInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG, "app-onCreate");
		setContentView(R.layout.activity_pay_ed);

		mContext = this;
		mActivity = this;

		if (isPkgInstalled(pkgName) == true) {
			stay = 1;
			// 游戏安装前已安装插件
		} else {
			stay = 2;
			// 游戏购买是安装插件
		}
		// mActivity, mPayName, mPayPrice
		setVivoBuyInfo();
	}

	// 应用程序判断vivo插件是否存在
	private boolean isPkgInstalled(String pkgName) {
		PackageInfo packageInfo = null;
		try {
			packageInfo = mActivity.getPackageManager().getPackageInfo(pkgName, 0);
		} catch (NameNotFoundException e) {
			packageInfo = null;
			e.printStackTrace();
		}
		if (packageInfo == null) {
			return false;
		} else {
			return true;
		}
	}

	public void setVivoBuyInfo() {
		Log.i(TAG, "app-vivo-setVivoBuyInfo:" + mPayName + "," + mPayPrice);
		Log.i(TAG, "app-vivo-setVivoBuyInfo-price:" + prices.get(mPayPrice));

		final Map<String, String> param_map = new HashMap<String, String>();
		param_map.put("notifyUrl", "http://58.67.193.164:9448/sync/vivo.php");
		param_map.put("orderAmount", prices.get(mPayPrice));
		param_map.put("orderDesc", mPayName);
		param_map.put("orderTitle", mPayName);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		param_map.put("orderTime", format.format(new Date()));
		param_map.put("storeId", SDK_VIVO.cpId);
		param_map.put("appId", SDK_VIVO.appId);
		String orderNum = UUID.randomUUID().toString().replaceAll("-", "");
		param_map.put("storeOrder", orderNum);
		param_map.put("version", "1.0.0");
		String signature = VivoSignUtils.getVivoSign(param_map, SDK_VIVO.cpKey);
		param_map.put("signature", signature);
		param_map.put("signMethod", "MD5");

		RequestQueue mQueue = Volley.newRequestQueue(this);
		HTTPSTrustManager.allowAllSSL();
		StringRequest jsonObjectRequest = new StringRequest(Method.POST, URL_INITIAL_PAYMENT, mResponseListener,
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e(TAG, "app-onErrorResponse-" + error.toString());
						Toast.makeText(VIVOPayActivity.this, "获取参数错误", Toast.LENGTH_SHORT).show();
						SDK_VIVO.mVivoCallback.Failed();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return param_map;
			}
		};
		mQueue.add(jsonObjectRequest);
//		mQueue.start();

	}

	private VivoPayCallback mVivoPayCallback = new VivoPayCallback() {
		// 客户端返回的支付结果不可靠，请以服务器端最终的支付结果为准；
		public void onVivoPayResult(String arg0, boolean arg1, String arg2) {
			Log.e(TAG, "app-onVivoPayResult-arg0:" + arg0 + ", arg1" + arg1 + ", arg2:" + arg2);
			if (arg1) {
				SDK_VIVO.mVivoCallback.Success();
				VIVOPayActivity.this.finish();
			} else {
				SDK_VIVO.mVivoCallback.Failed();
				VIVOPayActivity.this.finish();
			}
		};
	};
	private Response.Listener<String> mResponseListener = new Response.Listener<String>() {
		@Override
		public void onResponse(String response) {
			Log.e(TAG, "app-onResponse-response:" + response.toString());
			JSONObject jsonObject = null;
			try {
				jsonObject = new JSONObject(response);
			} catch (JSONException e) {
			}
			if (JsonParser.getString(jsonObject, "respCode").equals("200")) {
				mVivoPayInfo = new VivoPayInfo(mPayName, mPayName, JsonParser.getString(jsonObject, "orderAmount"),
						JsonParser.getString(jsonObject, "vivoSignature"), SDK_VIVO.appId,
						JsonParser.getString(jsonObject, "vivoOrder"), null);
				Log.e(TAG, "mVivoPayInfo :" + mVivoPayInfo.toString());
				Log.e(TAG, "mVivoPayCallback :" + mVivoPayCallback.toString());
				VivoUnionSDK.pay(mActivity, mVivoPayInfo, mVivoPayCallback);

			} else {
				Toast.makeText(mActivity, "获取订单错误", Toast.LENGTH_LONG).show();
				SDK_VIVO.mVivoCallback.Failed();
			}

		}
	};

	@Override
	protected void onStart() {
		super.onStart();
		Log.e(TAG, "app-vivo-onStart");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.i(TAG, "app-vivo-onRestart isfirst=" + isfirst + " stay=" + stay);

		if (isfirst == false) {
			switch (stay) {
			case 1:// 游戏之前安装有
				isfirst = true;
				break;
			case 2:// 游戏中安装
				if (isPkgInstalled(pkgName) == true) {
					SDK_VIVO.mVivoCallback.Cancel();
					VIVOPayActivity.this.finish();
				}
				// isfirst=true;
				break;
			}
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e(TAG, "app-vivo-onResume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e(TAG, "app-vivo-onPause");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e(TAG, "app-vivo-onStop");
	}

	public static void onDestroy(Activity activity) {
		Log.e(TAG, "app-vivo-onDestroy");
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODOAuto-generated method stub
		// 重写方法后，我们就调用父类的方法，这样以便系统的方法可以调用,这句一肯不能忘记
		super.onKeyDown(keyCode, event);
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 现在返回:true,代表让系统能继续处理此按键的操作
			// 返回false:代表该按键的处理到此结束，不响应系统的处理
			SDK_VIVO.mVivoCallback.Cancel();
			VIVOPayActivity.this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private static Map<String, String> prices = new HashMap<String, String>() {
		{
			put("1", "0.01");
			put("2", "0.02");
			put("10", "0.10");
			put("200", "2.00");
			put("600", "6.00");
			put("800", "8.00");
			put("1000", "10.00");
			put("1200", "12.00");
			put("1500", "15.00");
			put("2000", "20.00");
			put("2500", "25.00");
			put("2900", "29.00");
			put("3000", "30.00");
		}
	};

}