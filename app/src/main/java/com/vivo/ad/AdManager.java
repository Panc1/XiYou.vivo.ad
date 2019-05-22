package com.vivo.ad;

import android.app.Activity;
import android.util.Log;

public class AdManager {

    public static AdManager Instance;
    final String TAG = "AdManager >>>";
    private Activity MyActivity;

    public static AdManager getInstance(Activity activity) {
        if (Instance == null)
            Instance = new AdManager(activity);
        return Instance;
    }

    public AdManager(Activity activity) {
        this.MyActivity = activity;
    }

    private void showInterstitial(int index) {
        InterstialAd mInterstitial = new InterstialAd(MyActivity);
        mInterstitial.initAd(index);
    }

    private BannerAd BNow;

    public void showBanner() {
        BannerAd mBanner = new BannerAd(MyActivity);
        mBanner.initAd();
        BNow = mBanner;
    }

    public void ClearnBanner() {
        if (BNow != null)
            BNow.CloseAd();
    }

    public void showAd(int id) {
        Log.e(TAG, "app-AD_ID:[" + id + "]");
        if (id != 0) {
            if (ServiceControler.isAutoClick != 1 && ServiceControler.bannerTick >= 15000)
                showInterstitial(id);
            else
                Log.e(TAG, "app-isAutoClick[" + ServiceControler.isAutoClick + "] Not To Show Interstitial");
        }
    }

}
