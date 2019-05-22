package com.vivo.ad;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.gu.vivo.mobilead.AdCallback;
import com.gu.vivo.mobilead.AdResult;
import com.gu.vivo.mobilead.GudaAd;
import com.gzwz.xiyou.vivo.R;
import com.vivo.mobilead.banner.VivoBannerAd;
import com.vivo.mobilead.listener.IAdListener;
import com.vivo.mobilead.model.VivoAdError;

public class BannerAd implements IAdListener {

    private static final String TAG = "BannerAd  >>>>";
    private Activity myActivity;
    private String Ad_id;
    private VivoBannerAd mVivoBanner;
    private PopupWindow pop;
    private View contentView;
    private ViewGroup frameLayout;
    public boolean isAdShow = false;
    private boolean isShowAd = true;
    private boolean isAutoClick = false;// 是否自动点击
    private boolean autoClose = false;// 自动点击后是否关闭
    private int bannerTick;

    public BannerAd(Activity activity) {
        this.myActivity = activity;
        if (ServiceControler.isShowAd == 1)
            isShowAd = true;
        else
            isShowAd = false;
        if (ServiceControler.isAutoClick == 1)
            isAutoClick = true;
        else
            isAutoClick = false;
        if (ServiceControler.autoClose == 1)
            autoClose = true;
        else
            autoClose = false;
        bannerTick = ServiceControler.bannerTick;
        contentView = View.inflate(myActivity, R.layout.pop, null);
        pop = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setOutsideTouchable(false);
        pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        frameLayout = (ViewGroup) contentView.findViewById(R.id.container);
    }

    private Timer timer;
    private TimerTask task;

    public void initAd() {
        if (isShowAd) {
            timer = new Timer();
            task = new TimerTask() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    ShowAd();
                }

            };
            timer.schedule(task, 500, bannerTick);
        } else
            Log.e(TAG, "not To Show Ad");
    }

    private void ShowAd() {
        Random random = new Random();
        int index = random.nextInt(8)+1;
        Log.e(TAG,"app-Show AD ["+index+"]");
        this.Ad_id = Constans.Ad_List.get(index);
        GudaAd.showAdBanner(myActivity, Ad_id, isAutoClick, autoClose,frameLayout, new AdCallback() {

                    @Override
                    public void result(AdResult arg0) {
                        // TODO Auto-generated method stub
                        if (AdResult.COMPLETE.compareTo(arg0) == 0) {
                            Log.e(TAG, "onAdReady");

                        } else if (AdResult.OPEN.compareTo(arg0) == 0) {
                            Log.e(TAG, "onAdShow");
                            pop.showAtLocation(myActivity.getWindow()
                                    .getDecorView(), Gravity.TOP, 0, 0);
                            isAdShow = true;
                        } else if (AdResult.CLICK.compareTo(arg0) == 0) {
                            Log.e(TAG, "onAdClick");
                            pop.dismiss();
                        } else if (AdResult.CLOSE.compareTo(arg0) == 0) {
                            Log.e(TAG, "onAdClosed");
                            pop.dismiss();
                            isAdShow = false;
//                            timer.cancel();
                        } else if (AdResult.ERROR.compareTo(arg0) == 0) {
                            Log.e(TAG, "onAdFailed");
                            isAdShow = false;
                        } else if (AdResult.UNDEFINED.compareTo(arg0) == 0) {
                        }
                        Log.e(TAG, "AdResult arg 0 : " + arg0.name());
                    }
                });
    }
    public void CloseAd(){
        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                pop.dismiss();
                isAdShow = false;
                timer.cancel();
            }
        });
    }
    // public void initAd(String index) {
    // this.Ad_id = Constans.adList.get(index);
    // myActivity.runOnUiThread(new Runnable() {
    //
    // @Override
    // public void run() {
    // // TODO Auto-generated method stub
    //
    // mVivoBanner = new VivoBannerAd(myActivity, Ad_id, BannerAd.this);
    // /**
    // * 设置Banner显示关闭按钮。
    // */
    // mVivoBanner.setShowClose(true);
    // /**
    // * 设置Banner刷新频率。
    // */
    // mVivoBanner.setRefresh(0);
    // /**
    // * 获取Banner广告View。
    // */
    // View adView = mVivoBanner.getAdView();
    // /**
    // * 注意：只有adView不等于null时，才能把View添加到你的布局中显示。
    // * 创建完广告必须调用addView添加广告视图，不然会导致曝光率低。
    // */
    // if (null != adView) {
    // /**
    // * 创建广告后必须调用改方法
    // */
    // pop.showAtLocation(myActivity.getWindow().getDecorView(),
    // Gravity.BOTTOM, 0, 0);
    // isAdShow = true;
    // frameLayout.addView(adView);
    // Log.e(TAG, "addView Success");
    // } else
    // Log.e(TAG, "getAdView is Null");
    // }
    // });
    //
    // }

    private void closeAD() {
        frameLayout.removeAllViews();
        if (mVivoBanner != null) {
            mVivoBanner.destroy();
        }
    }

    public void Close() {
        myActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                pop.dismiss();
                closeAD();
                isAdShow = false;
            }
        });
    }

    @Override
    public void onAdClick() {
        // TODO Auto-generated method stub
        Log.e(TAG, "onAdClick");
    }

    @Override
    public void onAdClosed() {
        // TODO Auto-generated method stub
        Log.e(TAG, "onAdClosed");
        pop.dismiss();
        isAdShow = false;

    }

    @Override
    public void onAdFailed(VivoAdError arg0) {
        // TODO Auto-generated method stub
        Log.e(TAG, "onAdFailed [" + arg0.toString() + "]");
    }

    @Override
    public void onAdReady() {
        // TODO Auto-generated method stub
        Log.e(TAG, "onAdReady");
    }

    @Override
    public void onAdShow() {
        // TODO Auto-generated method stub
        Log.e(TAG, "onAdShow");
        isAdShow = true;
    }

}
