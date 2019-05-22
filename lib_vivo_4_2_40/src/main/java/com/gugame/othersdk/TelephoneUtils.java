package com.gugame.othersdk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

public class TelephoneUtils {

	public static final int TYPE_NONE = 0;// 无卡
	public static final int TYPE_CM = 1;// 移动
	public static final int TYPE_CU = 2;// 联通
	public static final int TYPE_CT = 3;// 电信

	public static int getProvidersType(Context context) {
		try {
			String imsi = getIMSI(context);
			if (TextUtils.isEmpty(imsi) || !imsi.startsWith("460")) {
				return TYPE_CM;
			}
			String op = imsi.substring(0, 5);
			if (TextUtils.isEmpty(op)) {
				return TYPE_CM;
			}

			switch (Integer.parseInt(op)) {
			case 46000:
			case 46002:
			case 46007:
				return TYPE_CM;
			case 46001:
			case 46006:
			case 46020:
				return TYPE_CU;
			case 46003:
			case 46005:
			case 46011:
			case 46099:
				return TYPE_CT;
			default:
				return TYPE_CM;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return TYPE_CM;
	}

	public static String getIMSI(Context context) {
		String result = "";
		try {
			TelephonyManager telphonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			result = telphonyManager.getSubscriberId();
		} catch (Exception e) {
			result = "";
		}

		try {
			// 无法取代imsi是，自动拼接imsi,使用imei或者android_id ， imei优先
			if (TextUtils.isEmpty(result) || !result.startsWith("460")) {
				// String imei = getIMEI(context);
				// if (TextUtils.isEmpty(imei)) {
				// imei = getAndroidId(context);
				// }
				result = getNetworkOperator(context);
				if (TextUtils.isEmpty(result) || !result.startsWith("460")) {
					result = "46000";
				}
				result += randomImsi(context);
				if (result.length() < 15) {
					result += "000000000000000";
				}
				if (result.length() > 15) {
					result = result.substring(0, 15);
				}
			}
		} catch (Exception e) {
			result = "";
		}
		return (result != null ? result : "");
	}

	public static String getIMEI(Context context) {
		String result = "";
		try {
			TelephonyManager telphonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			result = telphonyManager.getDeviceId();
		} catch (Exception e) {
			result = "";
		}
		return (result != null ? result : "");
	}

	public static String getNetworkOperator(Context context) {
		String result = "";
		try {
			TelephonyManager telphonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			result = telphonyManager.getNetworkOperator();
		} catch (Exception e) {
			result = "";
		}
		return (result != null ? result : "");
	}

	private static String randomImsi(Context context) {
		try {
			// 见imei中的字符替换为0
			String imei = getIMEI(context);
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < imei.length(); i++) {
				char c = imei.charAt(i);
				if (!Character.isDigit(c)) {
					buffer.append("0");
				} else {
					buffer.append(c);
				}
			}
			return buffer.toString();
		} catch (Exception e) {
		}
		return "2561158629";
	}

	/**
	 * 读取txt文件里的json字符串 根据name获取value值
	 * 
	 * @param context
	 * @param name
	 * @return
	 */
	public static String getStrValue(Context context, String name) {
		String nameStr = "\"" + name + "\"";
		String content = gettxt(context);
		int index = content.indexOf(nameStr);
		if (index > 0) {
			int valueStartIndex = content.indexOf(":", index) + 1;
			int endIndex = content.indexOf(",", index);
			int end2Index = content.indexOf("}", index);
			if (endIndex < 0)
				endIndex = content.length();
			int valueEndIndex = Math.min(endIndex, end2Index);
			String valueStr = content.substring(valueStartIndex, valueEndIndex);
			if (valueStr.startsWith("\""))
				valueStr = valueStr.substring(1);
			if (valueStr.endsWith("\""))
				valueStr = valueStr.substring(0, valueStr.length() - 1);
			return valueStr;
		}
		return null;
	}

	/**
	 *     * 通过一个InputStream获取内容    *     * @param inputStream     @return   
	 **/
	public static String getString(InputStream inputStream) {
		InputStreamReader inputStreamReader1 = null;
		try {
			inputStreamReader1 = new InputStreamReader(inputStream, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(inputStreamReader1);
		StringBuffer sb = new StringBuffer("");
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String gettxt(Context context) {
		AssetManager assetManager = context.getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open("OtherWords.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getString(inputStream);
	}
}
