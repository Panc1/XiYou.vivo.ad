package com.atlastone.app.addin.billing.free;

import com.atlastone.CIL.util.Observable;
import com.atlastone.app.addin.ads_impl.AdsImplAddin;
import com.atlastone.app.entry.Entry;
import com.atlastone.engine.core.JsGameEngine;
import com.umeng.analytics.MobclickAgent;
import com.vivo.ad.AdManager;
import com.vivo.ad.Constans;
import com.vivo.ad.ServiceControler;
import com.vivo.ad.SplashActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.ViewGroup;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class AdsAddin extends AdsImplAddin {
    private static final String TAG = "ADsAddin	  >>>";
    private Handler handler = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message msg) {
            showInterstitialAdOnUIThread(msg.obj.toString());
            return false;
        }
    });

    private void showInterstitialAdOnUIThread(String adName) {
        Log.e(TAG, "AD_ID:[" + adName + "]");
        if (adName != null) {
            //调用广告sdk，展示插页广告
            if (adName.equals("gameMenu")) { // 启动游戏开屏广告
                Intent it = new Intent(myActivity, SplashActivity.class);
                myActivity.startActivity(it);
                ShowTimeAd();
//            } else if (adName.equals("gameFailed")) { //主角死亡时弹出
//            } else if (adName.equals("gameComplete")) { //过关时弹出
            } else if (adName.equals("gameSignIn")) { //进入签到页领取奖励后弹出
                AdManager.getInstance(myActivity).showAd(2);
            } else if (adName.equals("gameChest")) { //进入宝箱页弹出
                AdManager.getInstance(myActivity).showAd(3);
            } else if (adName.equals("gameBag")) { //进入背包页弹出
                AdManager.getInstance(myActivity).showAd(4);
            } else if (adName.equals("gameLottery")) { //进入抽奖页弹出
                AdManager.getInstance(myActivity).showAd(5);
            } else if (adName.equals("gameHeroInfo")) { //进入角色信息页弹出
                AdManager.getInstance(myActivity).showAd(6);
            } else if (adName.equals("gameHeroes")) { //进入角色进阶页弹出
                AdManager.getInstance(myActivity).showAd(7);
            } else if (adName.equals("gameAds")) { //游戏暂停
                AdManager.getInstance(myActivity).showAd(8);
            }
        }
    }

    /* ad */
    private Activity myActivity;


    private void ShowTimeAd() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Log.e(TAG, "TimerTask" + JsGameEngine.getInstance().getCurSceneId());
                if (JsGameEngine.getInstance().getCurSceneId() < 176 || JsGameEngine.getInstance().getCurSceneId() > 182) {
                    Log.e(TAG, "TimerTask - getCurSceneId");
                }
            }
        };
        timer.schedule(task, Constans.Clock * 1000, Constans.Clock * 1000); // 3秒后
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setBannerParentView(ViewGroup arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public void show(Activity arg0, String arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showAds(String adName) {
        Message msg = this.handler.obtainMessage();
        msg.obj = adName;
        this.handler.sendMessage(msg);
    }

    @Override
    public void delayLoad() {
        // TODO Auto-generated method stub

    }

    @Override
    public void display() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public String getInterfaceKeyName() {
        return "Ad_";
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

    }

    public static void SignUmeng(Context context) {
        String result = "";
        try {
            TelephonyManager telphonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            result = telphonyManager.getDeviceId();
            MobclickAgent.onProfileSignIn(result);
        } catch (Exception e) {
            Log.e("GameActivity", "init Umeng " + e.toString());
            result = "";
        }
    }

    @Override
    public void onCreate(Entry entry) {
        this.myActivity = entry;
        new Handler(entry.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                ServiceControler.setAdInfo(myActivity);
            }
        }, 15000);
        ServiceControler.setBuyInfo(myActivity);
        SignUmeng(entry);
        super.onCreate(entry);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        MobclickAgent.onPause(myActivity);
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        MobclickAgent.onResume(myActivity);
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        MobclickAgent.onProfileSignOff();
    }

}
