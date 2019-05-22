package com.atlastone.app.addin.billing.free;

import android.util.Log;

import com.atlastone.CIL.charge.ChargeCallback;
import com.atlastone.app.addin.billing.BillingAddin;
import com.atlastone.app.addin.billing.BillingArgs;
import com.atlastone.app.entry.Entry;
import com.gugame.othersdk.OtherClass;
import com.gugame.othersdk.OtherExitCallback;
import com.vivo.ad.AdManager;
import com.vivo.ad.InterstialAd;
import com.vivo.ad.ServiceControler;

import java.util.Random;

/**
 * FreeSDK
 *
 * @author WangHandong
 * @2014.11.12
 */
public class FreeSDKAddin extends BillingAddin {
    private static final String TAG = "FreeSDKAddin	  >>>";
    public static boolean isRelease = true;
    private BillingArgs billingArgs;

    @Override
    public void onCreate(Entry entry) {
        super.onCreate(entry);

        this.billingArgs = new BillingArgs();
    }

    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void showExitDialog(final Entry entry) {
        AdManager.getInstance(entry).showAd(9);
        OtherClass.getInstance().exit( new OtherExitCallback() {
            //
            @Override
            public void GameExit() {
                // TODO Auto-generated method stub
                entry.finish();
            }
        });
    }

    private boolean getGudaShowAd() {
        boolean isShowAd = true;
        if (ServiceControler.isShowAd == 1)
            isShowAd = true;
        else
            isShowAd = false;
        return isShowAd;
    }

    private boolean isShow(final int number) {
        Random random = new Random();
        boolean isShow = random.nextInt(100 + 1) <= number;
        if (isShow) {
            Log.e(TAG, "app-Ad Show [" + number + "/100]");
        } else {
            Log.e(TAG, "app-Ad no Show [" + number + "/100]");
        }
        return isShow;
    }

    @Override
    public void delayLoad() {

    }

    // private Handler handler = new Handler(new Handler.Callback() {
    // public boolean handleMessage(Message msg) {
    // switch (msg.what) {
    // case 1:
    // showChargeDialog(billingArgs);
    // break;
    // case 2:
    // break;
    // }
    // return false;
    // }
    // });

    @Override
    public void doCharge(String title, String message, String payCode,
                         ChargeCallback chargeCallback, int payType, Object obj, boolean free) {
        try {
            this.billingArgs.obj = obj;
            this.billingArgs.title = "模拟计费";
            this.billingArgs.message = message;
            this.billingArgs.payType = payType;
            this.billingArgs.chargeCallback = chargeCallback;
            this.billingArgs.payCode = payCode;
            this.entry.postRunnable(this.paySussessRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Message msg = new Message();
        // msg.what = 1;
        // msg.obj = this.billingArgs;
        // this.handler.sendMessage(msg);
    }

    // private void showChargeDialog(BillingArgs billingArgs) {
    // Dialog alertDialog = new AlertDialog.Builder(this.entry)
    // .setTitle(billingArgs.title)
    // .setMessage(billingArgs.message)
    // .setPositiveButton("yes",
    // new DialogInterface.OnClickListener() {
    // public void onClick(DialogInterface dialog,
    // int which) {
    // entry.postRunnable(paySussessRunnable);
    // }
    // })
    // .setNegativeButton("no", new DialogInterface.OnClickListener() {
    // public void onClick(DialogInterface dialog, int which) {
    // entry.postRunnable(payFailedRunnable);
    // }
    // }).create();
    // alertDialog.setCancelable(false);
    // alertDialog.setCanceledOnTouchOutside(false);
    // alertDialog.show();
    // }

    @Override
    public boolean watchIncentivizedAds(String payCode, int payType,
                                        Object obj, ChargeCallback chargeCallback) {
        try {
            this.billingArgs.obj = obj;
            this.billingArgs.payCode = payCode;
            this.billingArgs.payType = payType;
            this.billingArgs.title = "视频广告";
            this.billingArgs.message = "模拟观看视频广告";
            this.billingArgs.chargeCallback = chargeCallback;
            this.entry.postRunnable(this.paySussessRunnable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Message msg = new Message();
        // msg.what = 1;
        // msg.obj = this.billingArgs;
        // this.handler.sendMessage(msg);
        return true;
    }

    @Override
    public void dispose() {
        this.billingArgs = null;
        this.paySussessRunnable = null;
    }

    private Runnable paySussessRunnable = new Runnable() {
        public void run() {
            billingArgs.chargeCallback.receiveChargeResult(CHARGE_SUCCESS,
                    billingArgs.payType, billingArgs.obj);
        }
    };

    // private Runnable payFailedRunnable = new Runnable() {
    // public void run() {
    // billingArgs.chargeCallback.receiveChargeResult(CHARGE_FAILED,
    // billingArgs.payType, billingArgs.obj);
    // }
    // };

    @Override
    public String getInterfaceKeyName() {
        return null;
    }
}
