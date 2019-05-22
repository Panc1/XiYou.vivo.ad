package com.vivo.ad;

import java.security.MessageDigest;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

public final class MD5 {

	public static String MD5Code;

	// 获取MD5的�?
	public static String getSign(Context context) {
		String packname = context.getPackageName();
		Signature[] arrayOfSignature = getRawSignature(context, packname);
		if ((arrayOfSignature == null) || (arrayOfSignature.length == 0)) {
			Log.i("ysj", "signs is null");
		} else {
			int i = arrayOfSignature.length;
			for (int j = 0; j < i; j++)
				MD5Code = MD5.getMessageDigest(arrayOfSignature[j]
						.toByteArray());
		}
		return MD5Code;
	}

	// 获取包的签名
	private static Signature[] getRawSignature(Context paramContext,
			String paramString) {
		if ((paramString == null) || (paramString.length() == 0)) {
			return null;
		}
		PackageManager localPackageManager = paramContext.getPackageManager();
		PackageInfo localPackageInfo;
		try {
			localPackageInfo = localPackageManager.getPackageInfo(paramString,
					64);
			if (localPackageInfo == null) {
				return null;
			}
		} catch (PackageManager.NameNotFoundException localNameNotFoundException) {
			return null;
		}
		return localPackageInfo.signatures;
	}

	public static final String getMessageDigest(byte[] paramArrayOfByte) {
		char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98,
				99, 100, 101, 102 };
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramArrayOfByte);
			byte[] arrayOfByte = localMessageDigest.digest();
			int i = arrayOfByte.length;
			char[] arrayOfChar2 = new char[i * 2];
			int j = 0;
			int k = 0;
			while (true) {
				if (j >= i)
					return new String(arrayOfChar2);
				int m = arrayOfByte[j];
				int n = k + 1;
				arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
				k = n + 1;
				arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
				j++;
			}
		} catch (Exception localException) {
		}
		return null;
	}

	public static final byte[] getRawDigest(byte[] paramArrayOfByte) {
		try {
			MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
			localMessageDigest.update(paramArrayOfByte);
			byte[] arrayOfByte = localMessageDigest.digest();
			return arrayOfByte;
		} catch (Exception localException) {
		}
		return null;
	}
}