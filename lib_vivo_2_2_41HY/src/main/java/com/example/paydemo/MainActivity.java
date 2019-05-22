
package com.example.paydemo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import com.gugame.othersdk.VivoSignUtils;
import com.other.sdk.R;
import com.vivo.unionsdk.open.VivoConstants;
import com.vivo.unionsdk.open.VivoPayCallback;
import com.vivo.unionsdk.open.VivoPayInfo;
import com.vivo.unionsdk.open.VivoUnionSDK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
    private ProgressDialog mProgressDialog;
    private VivoPayInfo mVivoPayInfo;
    private int mPayType = 0;  

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button payBtn = (Button) findViewById(R.id.vivo_pay_btn);
        Button weiXinBtn = (Button) findViewById(R.id.front_weixin_pay_btn);
        Button aliPayBtn = (Button) findViewById(R.id.front_ali_pay_btn);
        payBtn.setOnClickListener(this);
        weiXinBtn.setOnClickListener(this);
        aliPayBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.vivo_pay_btn) {
            mPayType = 1;           //普通单机支付
        } else if (view.getId() == R.id.front_weixin_pay_btn) {
            mPayType = 2;           //微信直付
        } else if (view.getId() == R.id.front_ali_pay_btn) {
            mPayType = 3;           //支付宝直付
        }
        pay();
    }

    //支付流程
    private void pay() {
        //订单推送接口请在服务器端访问
        final HashMap<String, String> params = new HashMap<String, String>();
        params.put("notifyUrl", "http://113.98.231.125:8051/vcoin/notifyStubAction");
        params.put("orderAmount", "0.01");  //注意：精确到小数点后两位；
        params.put("orderDesc", "新年特惠 adidas 阿迪达斯走珠 香体止汗走珠 多种香型可选");
        params.put("orderTitle", "魅力香水_2");
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        params.put("orderTime", format.format(new Date()));
        params.put("storeId", "20131030114035786189");//商户ID
        params.put("appId", "1007");                  //APPID
        params.put("storeOrder", UUID.randomUUID().toString().replaceAll("-", ""));//商户订单号
        params.put("version", "1.0");
        String str = VivoSignUtils.getVivoSign(params, "20131030114035565895");//signkey
        params.put("signature", str);
        params.put("signMethod", "MD5");
//
//        RequestQueue mQueue = Volley.newRequestQueue(this);  
//        HTTPSTrustManager.allowAllSSL();
//        StringRequest jsonObjectRequest = new StringRequest(Method.POST, "https://pay.vivo.com.cn/vivoPay/getVivoOrderNum",  
//                mResponseListener, new Response.ErrorListener() {
//            @Override  
//            public void onErrorResponse(VolleyError error) {  
//                Toast.makeText(MainActivity.this, "获取参数错误", Toast.LENGTH_SHORT).show();
//            }  
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return params;
//            }
//        };  
//        mQueue.add(jsonObjectRequest);
//        mQueue.start();
//    }
//
//    private Response.Listener<String> mResponseListener = new Response.Listener<String>() {
//        @Override
//        public void onResponse(String response) {
//            JSONObject jsonObject = null;
//            try {
//                jsonObject = new JSONObject(response);
//            } catch (JSONException e) {
//            }
//            if (JsonParser.getString(jsonObject, "respCode").equals("200")) {
//                mVivoPayInfo = new VivoPayInfo("魅力香水_2", "新年特惠 adidas 阿迪达斯走珠 香体止汗走珠 多种香型可选",
//                        JsonParser.getString(jsonObject, "orderAmount"),
//                        JsonParser.getString(jsonObject, "vivoSignature"), "1007",
//                        JsonParser.getString(jsonObject, "vivoOrder"), null);
//                if (mPayType == 1) {
//                    //微信为VivoConstants.SINGLE_FRONT_PAY_WEIXIN， 支付宝为VivoConstants.SINGLE_FRONT_PAY_ALI
//                    VivoUnionSDK.pay(MainActivity.this, mVivoPayInfo, mVivoPayCallback);
//                } else if (mPayType == 2) {
//                    //微信前置支付
//                    VivoUnionSDK.payNow(MainActivity.this, mVivoPayInfo, mVivoPayCallback, VivoConstants.SINGLE_FRONT_PAY_WEIXIN);
//                } else if (mPayType == 3) {
//                    //支付宝前置支付
//                    VivoUnionSDK.payNow(MainActivity.this, mVivoPayInfo, mVivoPayCallback, VivoConstants.SINGLE_FRONT_PAY_ALI);
//                }
//            } else {
//                Toast.makeText(MainActivity.this, "获取订单错误", Toast.LENGTH_LONG).show();
//            }
//
//        }  
    };


    private VivoPayCallback mVivoPayCallback = new VivoPayCallback() {
        //客户端返回的支付结果不可靠，请以服务器端最终的支付结果为准；
        public void onVivoPayResult(String arg0, boolean arg1, String arg2) {
            if (arg1) {
                Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
            }
        };
    };


    public void showProgress() {
        ProgressDialog dialog = null;
        dialog = new ProgressDialog(this);
        dialog.setMessage("正在初始化支付。。。");
        dialog.setCancelable(false);
        dialog.show();
    }

}
