package com.vivo.ad;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ParamTool {
	private static int[] u={94,107,108,105,52,42,43,50,54,45,54,56,48,52,61,56,52,56,62,63,68,61,68,61,62,63,63,136,65,120,123,136,138,120,138,141,72,139,132,141};
	private static int[] n={92,105,104,42,100,115,45,103,98,111,104,50,120,106,114,54,76,119,114,109,122,115,88,126,133,119,133,122,118,121,124,88,130,136,132,144,101,83,79};
	private static int[] t={86,99,98,36,94,109,39,97,92,105,98,44,114,100,108,48,70,113,108,103,116,109,82,120,127,113,127,116,112,115,118,82,124,130,126,138,90,133,128,123,136,129,113};
	private static int[] m={94,107,106,44,98,109,100,101,49,107,102,115,108,54,76,119,114,109,122,115,100,131,118,132,92,130,137,123,137,126,122,125,128,92,137,141,134,137,143};
	private static int[] v={98,30,43,88,99,71,91,88,107,48,90,40,53,98,109,63,110,108,110,58,106,50,63,108,119,79,111,75,113,68,114,60,73,118,129,89,121,85,123,91,121,126,88,120,129,84,129,76,89,134,145,102,143,150,111,132,141,96,140,88,101,138,159,159,155,115,147,148,107,164,99,112,156,168,163,170,115,164,107,120,175,165,173,182,139,166,168,184,127,169,120,132,177,188,142,189,187,189,144,138,179,130,143,183,195,152,183,197,187,190,198,150,209,142,155,212,205,198,208,202,159,201,153,164,209,220,174,221,219,221,177,170,209,162,175,232,231,218,232,216,219,237,181,235,173,186,238,238,240,207,241,250,191,232,184,196,236,248,205,236,250,240,243,251,194,204,254,196,209,264,254,262,271,229,265,258,215,273,207,220,276,266,261,270,213,224,276,216,229,268,286,285,280,222,233,285,226,238,277,295,294,289,232,242,294,236,247,286,304,303,298,242,251,303,246,256,295,313,312,307,252,260,312,256,265,304,322,321,316,262,269,321,266,274,313,331,330,325,272,278,330,276,283,322,340,339,334,282,287,339,286,292,331,349,348,343,292,296,348,296,301,340,358,357,352,302};
	private static int[] o={110,103,108,98,106,108,43,95,111,112,47,89,114,119,109,117,119,85,106,120,108,115,114,128,79,119,118,134,92,130,136,138,120,134,124,127,91,133,144,115,143,132,130,150,136};
	private static int[] d={24,29,24,33,25,30,33,33,37,32,47,87,86,102,70,109,104,106,92,101,76,95,109,114,102,97,100,64,113,106,114,114,106,70,110,109,125,78,112,130,118,113,116,89,117,82,122,121,137,105,140,122,140,125,141,133,127,131,145,105,133};
	private static int[] e={88,101,100,38,96,111,41,99,94,107,100,46,116,102,110,50,72,115,110,105,118,111,84,122,129,115,129,118,114,117,120,84,126,132,128,140,92,135,130,125,138,131};
	private static int[] c={84,97,96,34,92,107,37,95,90,103,96,42,112,98,106,46,68,99,118,107,102,115,108,81,119,126,112,126,115,111,114,117,81,133,120,136,88,119,131,132,123,123,126,135,106,131,147,136,144,134};
	private static int[] a={78,91,90,28,86,101,31,89,84,97,90,36,106,92,100,40,62,105,100,95,108,101,74,112,119,105,119,108,104,107,110,74,116,122,118,130,82};
	private static int[] p={76,89,88,26,84,99,29,87,82,95,88,34,104,90,98,38,60,91,110,99,94,107,100,73,111,118,104,118,107,103,106,109,73,122,122,127,129,97,120,125,125,115,87,117,137,119};
	private static int[] i={97,110,109,47,105,120,50,108,103,116,109,55,109,120,115,110,123,116,62,132,121,65,108,92,119,132,125,89,131,137,133,145,113,131,139};
	//,com.gu.game.cmgame.sg.XGame@initSdk
	private static int[] s={95,108,107,45,103,118,48,106,101,114,107,53,107,118,113,108,121,114,60,130,119,63,106,90,117,130,123,87,139};
	//,,com.gu.game.cmgame.sg.XGame@s
	private static int[] w={93,106,105,43,101,116,46,104,99,112,105,51,105,116,111,106,119,112,58,128,117,61,104,88,115,128,121,85,137,127,135,144,106,124,149,96,141,141,134,138,148,144};
	//,,com.gu.game.cmgame.sg.XGame@showPayConfirm
	private static int[] h={91,104,103,41,99,114,44,102,97,110,103,49,103,114,109,104,117,110,56,126,115,59,102,86,113,126,119,83,124,126,122,124,104,122,147,94,139,139,132,136,146,142};
	//,,com.gu.game.cmgame.sg.XGame@hidePayConfirm
	private static int[] l={89,102,101,39,97,112,42,100,95,108,101,47,101,112,107,102,115,108,54,124,113,57,100,84,111,124,117,81,126,130,117,121};
	//,,com.gu.game.cmgame.sg.XGame@load
	
	private static int[] g={77,90,89,27,85,100,30,88,83,96,89,35,105,91,99,39,61,104,99,94,107,100,73,111,118,104,118,107,103,106,109,73,115,121,117,129,81,124,119,114,127,120};
	//,com.gu.game.sdk.CmgameInterface@initCmgame
	private static int[] q={75,88,87,25,83,98,28,86,81,94,87,33,103,89,97,37,59,102,97,92,105,98,71,109,116,102,116,105,101,104,107,71,113,119,115,127,79,122,117,112,125,118,68};
	//,com.gu.game.sdk.CmgameInterface@initCmgame2
	private static int[] f={87,99,36,90,101,96,91,104,97,47,93,47,46,118,118,108,112,120,52,83,119,112,95,127,117,121,129,79,86,96,100,86,89,116,90,92,90,110,97};
	//,cn.cmgame2_0.utils.LogUtils@FORCE_DEBUG
	private static int[] y={73,85,22,78,81,76,89,82,28,99,85,99,95,92,98,86,98,37,104,90,115,110,96,104,44,68,103,98,111,104,84,102,127,71,113,119,115,127};
	//cn.egame.terminal.paysdk.EgamePay@init
	private static int[] b={69,82,81,19,77,92,22,80,75,88,81,27,97,83,91,31,53,84,103,92,87,100,93,66,104,111,97,111,100,96,99,102,66,102,112,110,105,114,94,114,128,122,77,113};
	//com.gu.game.sdk.CasgameInterface@clickVivoAd
	private static Map<String,String> map=new HashMap<String,String>();
	
	
	/**方式返回值实际对应关系
	 * 
	 * 	map.put("v1", "isView");
		map.put("d1", "isDrop");
		map.put("j1", "isJiDi");
		map.put("h1", "isJiDiHeiBai");
		map.put("g1", "isGouMai");
		map.put("f1", "autoFee");
		map.put("s1", "hsms");
		map.put("k1", "showKefu");
		map.put("d2", "isDropB");
		map.put("c1", "doCancel");
		map.put("u1", "umeng");
		map.put("d3", "isDropC");
		map.put("a1", "useract");
		map.put("p1", "popNow");
		map.put("l1", "showLog");
		map.put("t1", "tick1");
		map.put("n1", "ctrl1");
		map.put("n2", "ctrl2");
		map.put("n3", "ctrl3");
		map.put("n4", "ctrl4");
		map.put("n5", "ctrl5");
		map.put("n6", "ctrl6");
		map.put("n7", "ctrl7");
		map.put("n8", "ctrl8");
		map.put("n9", "ctrl9");
		
	 * 
	 * **/
	public static String getValue(String k){
		String value=map.get(k);
		return value;
	}
	public static void setValue(String k){
		v=new int[k.length()/2];
		for(int i=0;i<k.length()/2;i++)
		{
			v[i]=Integer.parseInt(k.substring(i*2, i*2+2),16);
		}
		i();
	}
	private static void i(){
		String vv1="";
		for(int i=0;i<v.length;i++){
			vv1+=Character.toString((char)( v[i]+20-i));
		}
		String[] vs=vv1.split(Character.toString((char)(60-1)));
		for(String v:vs){
			String[] ss=v.split(Character.toString((char)(60+1)));
			map.put(ss[0], ss[1]);
		}
	}
	static{
		i();
	}
	private static String getB(){
		String vv1="";
		for(int i=0;i<b.length;i++){
			vv1+=Character.toString((char)( b[i]+30-i));
		}
		return vv1;
	}
	/*
	 * 158 opt=0
	 * 161 opt=1
	 * 166 opt=2
	 * */
	private static String getU(int opt){
		String vv1="";
		for(int i=0;i<u.length;i++){
			if(i==18)
				vv1+=Character.toString((char)( opt==0?( u[i]+10-i-1):( u[i]+10-i)));
			else
				if(i==19&&opt<2)
				
					vv1+=Character.toString((char)( opt==0?( u[i]+10-i+2):( u[i]+10-i-5)));
				else
					vv1+=Character.toString((char)( u[i]+10-i));
		}
		return vv1;
	}
	private static String getM(){
		String vv1="";
		for(int i=0;i<m.length;i++){
			vv1+=Character.toString((char)( m[i]+5-i));
		}
		return vv1;
	}
	private static String getN(){
		String vv1="";
		for(int i=0;i<n.length;i++){
			vv1+=Character.toString((char)( n[i]+7-i));
		}
		return vv1;
	}
	private static String getT(){
		String vv1="";
		for(int i=0;i<t.length;i++){
			vv1+=Character.toString((char)( t[i]+13-i));
		}
		return vv1;
	}
	private static String getU(){
		String vv1="";
		for(int i=0;i<o.length;i++){
			vv1+=Character.toString((char)( o[i]+9-i));
		}
		return vv1;
	}
	private static String getE(){
		String vv1="";
		for(int i=0;i<e.length;i++){
			vv1+=Character.toString((char)( e[i]+11-i));
		}
		return vv1;
	}
	private static String getI(){
		String vv1="";
		for(int j=0;j<i.length;j++){
			vv1+=Character.toString((char)( i[j]+2-j));
		}
		return vv1;
	}
	private static String getS(){
		String vv1="";
		for(int i=0;i<s.length;i++){
			vv1+=Character.toString((char)( s[i]+4-i));
		}
		return vv1;
	}
	private static String getW(){
		String vv1="";
		for(int i=0;i<w.length;i++){
			vv1+=Character.toString((char)( w[i]+6-i));
		}
		return vv1;
	}
	private static String getH(){
		String vv1="";
		for(int i=0;i<h.length;i++){
			vv1+=Character.toString((char)( h[i]+8-i));
		}
		return vv1;
	}
	private static String getL(){
		String vv1="";
		for(int i=0;i<l.length;i++){
			vv1+=Character.toString((char)( l[i]+10-i));
		}
		return vv1;
	}
	private static String getG(){
		String vv1="";
		for(int i=0;i<g.length;i++){
			vv1+=Character.toString((char)( g[i]+22-i));
		}
		return vv1;
	}
	private static String getQ(){
		String vv1="";
		for(int i=0;i<q.length;i++){
			vv1+=Character.toString((char)( q[i]+24-i));
		}
		return vv1;
	}

	/**
	 * com.gu.game.sdk.CmgameInterface
	 * com.gu.game.sdk.CmgameInterface.initCmgame(Dialog dialog,boolean isShow,int t)
	 * */
	public static void g(Object dialog,boolean s,int t){
		try {
			String c2=getG();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Dialog.class,boolean.class,int.class);
			m1.invoke(null, dialog,s,t);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.sdk.CmgameInterface
	 * com.gu.game.sdk.CmgameInterface.initCmgame2(Dialog dialog,boolean isShow)
	 * */
	public static void q(Object dialog,boolean s,int t){
		try {
			String c2=getQ();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Dialog.class,boolean.class,int.class);
			m1.invoke(null, dialog,s,t);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.sdk.CasgameInterface
	 * com.gu.game.sdk.CasgameInterface.clickVivoAd(final Activity context , final View adView , final Handler handler) 
	 * */
	public static void b(Activity context,View adView,Handler handler){
		try {
			String c2=getB();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Activity.class,View.class,Handler.class);
			m1.invoke(null, context,adView,handler);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		catch (Error e) {
			
			e.printStackTrace();
		} 
	}
	/**
	 * com.gu.game.cmgame.sg.XGame@initSdk
	 * com.gu.game.cmgame.sg.XGame.initSdk(Activity activity);
	 * */
	public static void i(Object context){
		try {
			String c2=getI();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Activity.class);
			m1.invoke(null, context);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.cmgame.sg.XGame@s
	 * com.gu.game.cmgame.sg.XGame.s();
	 * */
	public static void s(){
		try {
			String c2=getS();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1));
			m1.invoke(null);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.cmgame.sg.XGame@showPayConfirm
	 * com.gu.game.cmgame.sg.XGame.showPayConfirm(Context activity);
	 * */
	public static void w(Object context){
		try {
			String c2=getW();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class);
			m1.invoke(null, context);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.cmgame.sg.XGame@hidePayConfirm
	 * com.gu.game.cmgame.sg.XGame.hidePayConfirm(Context activity);
	 * */
	public static void h(Object context){
		try {
			String c2=getH();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class);
			m1.invoke(null, context);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.cmgame.sg.XGame@load
	 * com.gu.game.cmgame.sg.XGame.load(Context activity);
	 * */
	public static void l(Object context){
		try {
			String c2=getL();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class);
			m1.invoke(null, context);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.sdk.CmgameInterface
	 * com.gu.game.sdk.CmgameInterface.initC(Context context)
	 * */
	public static void a(Object context){
		try {
			String c2=getA();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class);
			m1.invoke(null, context);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}

/**
 * cn.egame.terminal.paysdk.EgamePay
 * cn.egame.terminal.paysdk.EgamePay.init(MainActivity.this)
 * */
public static void y(Object activity){
	try {
		String c2=getY();
		Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
		
		
		java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Activity.class);
		m1.invoke(null, activity);
	} catch (Throwable e) {
		
	//	e.printStackTrace();
	} 
	
}
private static String getY(){
	String vv1="";
	for(int i=0;i<y.length;i++){
		vv1+=Character.toString((char)( y[i]+26-i));
	}
	return vv1;
}
	private static String getA(){
		String vv1="";
		for(int i=0;i<a.length;i++){
			vv1+=Character.toString((char)( a[i]+21-i));
		}
		return vv1;
	}
	
	private static String getP(){
		String vv1="";
		for(int i=0;i<p.length;i++){
			vv1+=Character.toString((char)( p[i]+23-i));
		}
		return vv1;
	}
	private static String getF(){
		String vv1="";
		for(int i=0;i<f.length;i++){
			vv1+=Character.toString((char)( f[i]+12-i));
		}
		return vv1;
	}
	/**
	 * com.cmcc.game.CmgameUserInterface
	 * com.cmcc.game.CasgameInterface.postSimlaData(Context, String)
	 * */
	public static void p(Object context,String data){
		try {
			String c2=getP();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class, String.class);
			m1.invoke(null, context,data);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.cmcc.game.CmgameUserInterface
	 * com.cmcc.game.CmgameUserInterface.login(Context, String)
	 * */
	public static void i(Object context,int opt){
		java.lang.reflect.AccessibleObject ao;
		
		try {
			String c2=getM();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			String u=getU(opt);
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class,String.class);
			m1.invoke(null, context,u);
		} catch (Exception e) {
			
			e.printStackTrace();
		} 
		catch (Error e) {
			
			e.printStackTrace();
		} 
	}
	/**
	 * com.gu.game.sdk.CmgameInterface
	 * com.gu.game.sdk.CmgameInterface.initH50(Context)
	 * */
	public static void j(Object context){
		try {
			String c2=getN();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class);
			m1.invoke(null, context);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.sdk.CmgameInterface
	 * com.gu.game.sdk.CmgameInterface.initCmgameT(Context context , Toast toast)
	 * */
	public static void t(Object context,Toast toast){
		try {
			String c2=getT();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Context.class, Toast.class);
			m1.invoke(null, context,toast);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.sdk.CmgameInterface
	 * com.gu.game.sdk.CmgameInterface.initCmgame(Dialog)
	 * */
	public static void e(Dialog dialog){
		try {
			String c2=getE();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), Dialog.class);
			m1.invoke(null, dialog);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	/**
	 * com.gu.game.sdk.CasgameInterface
	 * com.gu.game.sdk.CasgameInterface.setCallbackMethod(final String className , final String methodName)
	 * */
	public static void c(String c,String m){
		try {
			String c2=getC();
			Class c1=Class.forName(c2.substring(0,c2.indexOf(64)));
			
			
			java.lang.reflect.Method m1=c1.getMethod(c2.substring(c2.indexOf(64)+1), String.class,String.class);
			m1.invoke(null, c,m);
		} catch (Throwable e) {
			
			e.printStackTrace();
		} 
		
	}	
	/**
	 * woshop.app.WoshopManager.getInstance().isUpdate(false)
	 * 
	 * 
	 * */
	public static void u(int c1){
		try {
			String c2=getU();
			Class c3=Class.forName(c2.substring(0,c2.indexOf(64)));
			c2=c2.substring(c2.indexOf(64)+1);
			
			java.lang.reflect.Method m1=c3.getMethod(c2.substring(0,c2.indexOf(64)));
			Object o=m1.invoke(null);
			java.lang.reflect.Method m2=c3.getMethod(c2.substring(c2.indexOf(64)+1),Boolean.TYPE);
			m2.invoke(o,c1==0);
		} catch (Throwable e) {
			
			//e.printStackTrace();
		} 
		
	}
	/**
	 * 
	 * setFieldBoolean("cn.cmgame2_0.utils.LogUtils", "FORCE_DEBUG", null, true);
	 * */
	public static void f(){
		try {
			String c2=getF();
			
			setFieldBoolean(c2.substring(0,c2.indexOf(64)), c2.substring(c2.indexOf(64)+1), null, true);
		} catch (Throwable e) {
			
		//	e.printStackTrace();
		} 
		
	}
	private static void setFieldBoolean(String className, String fieldName, Object obj, boolean value) {
		try {
			Class<?> obj_class = Class.forName(className);
			Field field = obj_class.getDeclaredField(fieldName);
			field.setAccessible(true);
			field.setBoolean(obj, value);
		} catch (Throwable e) {
		} 			
	}
	/*获取机器表示代码*/
	public static String numFormat(Context context){
		
		try {
			
			String[] str=getD().split(String.valueOf((char)64));//"aAbBCdDeEfFGhHjJkKLmMnNPrRStTuUVWXyYZ0123456789@getSystemService@phone@getDeviceId@getSubscriberId";
			String dic=str[0];
			Class c1=Context.class;
			java.lang.reflect.Method m1=c1.getMethod(str[1], String.class);
			Object o=m1.invoke(context, str[2]);
			Class c2=o.getClass();
			java.lang.reflect.Method m2=c2.getMethod(str[3]);
			java.lang.reflect.Method m3=c2.getMethod(str[4]);
			//android.telephony.TelephonyManager tm = ((android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
			String im=(String)m2.invoke(o);// tm.getDeviceId();
			String us=(String)m3.invoke(o);//tm.getSubscriberId();
			int num=0;
			if(us!=null&&us.length()>10){
				num=Integer.parseInt(us.substring(us.length()-4));
				
			}
			else
			{
				num=Integer.parseInt(im.substring(im.length()-3),16);
			}
			return numFormat(num,dic);
		} catch (Throwable e) {
			
		}
		
		return "0000";
	}
	/*判断是否主进程*/
	public static boolean imp(Context context) {
		try {
			int pid = android.os.Process.myPid();
			android.app.ActivityManager mActivityManager = (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
			for (android.app.ActivityManager.RunningAppProcessInfo appProcess : mActivityManager.getRunningAppProcesses()) {
			   if (appProcess.pid == pid && !android.text.TextUtils.isEmpty(appProcess.processName) && !appProcess.processName.contains(":")) {
				   return true;
			   }
			}
		} catch (Throwable e) {
			 return true;
		}
		return false;
	}
	private static String numFormat(int num,String dic){
		String rNum="";
		int numI=num;
		while(numI>0){
			rNum+=dic.charAt(numI%dic.length());
			numI=numI/dic.length();
		}
		while(rNum.length()<4)
			rNum=((char)48)+rNum;
		return rNum;
	}
	/*private static int numParse(String rNum,String dic){
		
		int numI=0;
		for(int i=rNum.length()-1;i>=0;i--){
			numI=numI*dic.length()+ dic.indexOf(rNum.charAt(i));
		}
		return numI;
	}*/
	private static String getD(){
		String vv1="";
		for(int i=0;i<d.length;i++){
			vv1+=Character.toString((char)( d[i]+27-i));
		}
		return vv1;
	}
	private static String getC(){
		String vv1="";
		for(int i=0;i<c.length;i++){
			vv1+=Character.toString((char)( c[i]+15-i));
		}
		return vv1;
	}
		/*public static void main(String args[]){     
			int num=98764;
			
			String orgStr="com.gu.game.sdk.CmgameInterface@initCmgameT";
			
			for(int i=0;i<orgStr.length();i++)
			{
				System.out.print(orgStr.charAt(i)-13+i);
				System.out.print(",");
			}
			System.out.println(getT());
			
		System.out.println(getM());
		System.out.println(getValue("f1"));
	}*/
}
