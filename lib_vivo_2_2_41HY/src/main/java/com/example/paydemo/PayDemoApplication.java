package com.example.paydemo;

import com.vivo.unionsdk.open.VivoUnionSDK;

import android.app.Application;

public class PayDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //SDK初始化, 请传入自己游戏的appid替换demo中的appid。
        VivoUnionSDK.initSdk(this, "1007", false);
    }
}
