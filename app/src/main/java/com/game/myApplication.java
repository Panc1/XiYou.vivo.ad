package com.game;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.gugame.othersdk.OtherClass;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.vivo.ad.Constans;
import com.vivo.mobilead.manager.VivoAdManager;
import com.vivo.mobilead.util.VADLog;

public class myApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
//        new Handler(getMainLooper()).post(new Runnable() {
//                @Override
//                public void run() {
                try {
                    if (Build.VERSION.SDK_INT >= 23) {
                        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                            System.loadLibrary("gugame108"); // 加载我们的so，与服务器相�?
                            Log.e("", "gugame108 Loaded");
                        } else {
                            Log.e("", "gugame108 No Permission");
                        }
                    } else {
                        System.loadLibrary("gugame108"); // 加载我们的so，与服务器相�?
                        Log.e("", "gugame108 Loaded");
                    }
                } catch (Exception e) {
                    Log.e("", "gugame108 失败");
                }
//            }
//        });
        UMConfigure.init(this, "5ce209a3570df3300d000b50", "VIVO", UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.setSecret(this,"s10bacedtyz");
        OtherClass.getInstance().init(this);
        VADLog.fullLog(true);
        VivoAdManager.getInstance().init(this, Constans.APP_ID);
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
