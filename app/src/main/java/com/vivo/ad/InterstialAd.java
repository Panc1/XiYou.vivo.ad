package com.vivo.ad;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.util.Log;

import com.gu.vivo.mobilead.AdCallback;
import com.gu.vivo.mobilead.AdResult;
import com.gu.vivo.mobilead.GudaAd;
import com.vivo.mobilead.interstitial.VivoInterstialAd;
import com.vivo.mobilead.listener.IAdListener;
import com.vivo.mobilead.model.VivoAdError;

public class InterstialAd implements IAdListener {
    private static final String TAG = "Interstial  >>>";
    private Activity myActivity;
    private String Ad_id;
    private VivoInterstialAd mVivoInterstialAd;
    public boolean isAdShow = false;
    private int insertTick;

    public InterstialAd(Activity activity) {
        this.myActivity = activity;
        insertTick = ServiceControler.insertTick;
    }

    private Timer timer;

    public void initAd(int index) {
        this.Ad_id = Constans.Ad_List.get(index);
        timer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                ShowAd();
            }

        };
        timer.schedule(task, 500, insertTick);

    }

    private void ShowAd() {
        GudaAd.showAdInsert(myActivity, Ad_id, false, false,false, new AdCallback() {

            @Override
            public void result(AdResult arg0) {
                // TODO Auto-generated method stub
                if (AdResult.COMPLETE.compareTo(arg0) == 0) {
                    Log.e(TAG, "app-app-onAdReady");
                } else if (AdResult.OPEN.compareTo(arg0) == 0) {
                    Log.e(TAG, "app-onAdShow");
                    isAdShow = true;
                } else if (AdResult.CLICK.compareTo(arg0) == 0) {
                    Log.e(TAG, "app-onAdClick");
                } else if (AdResult.CLOSE.compareTo(arg0) == 0) {
                    Log.e(TAG, "app-onAdClosed");
                    isAdShow = false;
                    timer.cancel();
                } else if (AdResult.ERROR.compareTo(arg0) == 0) {
                    Log.e(TAG, "app-onAdFailed");
                    isAdShow = false;
                    timer.cancel();
                } else if (AdResult.UNDEFINED.compareTo(arg0) == 0) {
                    timer.cancel();
                }
                Log.e(TAG, "app-AdResult arg 0 : " + arg0.name());
            }
        });
    }

    //
    // public void initAd(String index) {
    // this.Ad_id = Constans.adList.get(index);
    // myActivity.runOnUiThread(new Runnable() {
    //
    // @Override
    // public void run() {
    // // TODO Auto-generated method stub
    // mVivoInterstialAd = new VivoInterstialAd(myActivity, Ad_id,
    // InterstialAd.this);
    // mVivoInterstialAd.load();
    // }
    // });
    // }

    @Override
    public void onAdClick() {
        // TODO Auto-generated method stub
        Log.e(TAG, "app-onAdClick");
    }

    @Override
    public void onAdClosed() {
        // TODO Auto-generated method stub
        Log.e(TAG, "app-onAdClosed");
        isAdShow = false;
    }

    @Override
    public void onAdFailed(VivoAdError arg0) {
        // TODO Auto-generated method stub
        Log.e(TAG, "app-onAdFailed [" + arg0.toString() + "]");
    }

    @Override
    public void onAdReady() {
        // TODO Auto-generated method stub
        Log.e(TAG, "app-onAdReady");
        mVivoInterstialAd.showAd();
    }

    @Override
    public void onAdShow() {
        // TODO Auto-generated method stub
        Log.e(TAG, "app-onAdShow");
        isAdShow = true;
    }

}
