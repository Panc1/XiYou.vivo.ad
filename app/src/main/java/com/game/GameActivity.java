package com.game;

import android.os.Build;
import android.os.Bundle;

import com.atlastone.app.addin.anti_cheating.AntiCheatingAddin;
import com.atlastone.app.addin.billing.BillingAddin;
import com.atlastone.app.entry.Entry;
import com.atlastone.app.entry.Reporting;
import com.atlastone.engine.configuration.Configuration;
import com.atlastone.engine.debugger.Debug;
import com.gugame.othersdk.OtherClass;
import com.gzwz.xiyou.vivo.R;

public class GameActivity extends Entry {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.initializeR();
		Class<?>[] classes = new Class<?>[] {
				com.atlastone.app.addin.billing.free.AdsAddin.class,
				com.atlastone.app.addin.billing.free.FreeSDKAddin.class,
				com.atlastone.app.addin.anti_cheating.AntiCheatingAddin.class };
		this.registerAddins(classes);

		// if (this.getPackageName().endsWith(".hw")) {
		// AntiCheatingAddin.exitIfNotGenuine = false;
		// } else {
		// AntiCheatingAddin.exitIfNotGenuine = true;
		// }
		Debug.DRAW_FPS = false;
		BillingAddin.isRelease = true;
		AntiCheatingAddin.pushIfNotGenuine = false;
		Entry.ReportEnable = false;
		if (Build.VERSION.SDK_INT < 23) { // Android 6.0
			Configuration.BACKUP_ARCHIVE = true;
		}

		// com.atlastone.engine.factory.ActorFactory.ActorFactoryClass =
		// NewActorFactory.class;
		OtherClass.getInstance().init(this,this);
		super.onCreate(savedInstanceState);
	}

	@Override
	protected Reporting newInstance(Entry entry) {
		return null;
	}

	public static int fullscreenAdView;
	public static int fullscreenad_view;
	public static int fullscreenAd_Close;

	private void initializeR() {
		fullscreenad_view = R.layout.fullscreenad_view;
		fullscreenAdView = R.id.fullscreenAdView;
		fullscreenAd_Close = R.id.fullscreenAd_Close;
		// anim
		com.atlastone.app.entry.R.anim.brower_back_selector = R.anim.brower_back_selector;
		com.atlastone.app.entry.R.anim.brower_forward_selector = R.anim.brower_forward_selector;
		com.atlastone.app.entry.R.anim.brower_quit_selector = R.anim.brower_quit_selector;
		com.atlastone.app.entry.R.anim.brower_refresh_selector = R.anim.brower_refresh_selector;
		com.atlastone.app.entry.R.anim.brower_setting_selector = R.anim.brower_setting_selector;
		com.atlastone.app.entry.R.anim.checkbox_checked = R.anim.checkbox_checked;
		com.atlastone.app.entry.R.anim.checkbox_selector = R.anim.checkbox_selector;
		com.atlastone.app.entry.R.anim.checkbox_unchecked = R.anim.checkbox_unchecked;
		com.atlastone.app.entry.R.anim.comment_selector = R.anim.comment_selector;
		com.atlastone.app.entry.R.anim.slide_in_left = R.anim.slide_in_left;
		com.atlastone.app.entry.R.anim.slide_in_right = R.anim.slide_in_right;
		com.atlastone.app.entry.R.anim.slide_out_left = R.anim.slide_out_left;
		com.atlastone.app.entry.R.anim.slide_out_right = R.anim.slide_out_right;
		com.atlastone.app.entry.R.anim.switch_fade = R.anim.switch_fade;
		com.atlastone.app.entry.R.anim.switch_hold = R.anim.switch_hold;
		com.atlastone.app.entry.R.anim.switch_hyperspace_in = R.anim.switch_hyperspace_in;
		com.atlastone.app.entry.R.anim.switch_hyperspace_out = R.anim.switch_hyperspace_out;
		com.atlastone.app.entry.R.anim.switch_my_alpha_action = R.anim.switch_my_alpha_action;
		com.atlastone.app.entry.R.anim.switch_my_scale_action = R.anim.switch_my_scale_action;
		com.atlastone.app.entry.R.anim.switch_push_left_in = R.anim.switch_push_left_in;
		com.atlastone.app.entry.R.anim.switch_push_left_out = R.anim.switch_push_left_out;
		com.atlastone.app.entry.R.anim.switch_push_up_in = R.anim.switch_push_up_in;
		com.atlastone.app.entry.R.anim.switch_push_up_out = R.anim.switch_push_up_out;
		com.atlastone.app.entry.R.anim.switch_scale_rotate = R.anim.switch_scale_rotate;
		com.atlastone.app.entry.R.anim.switch_scale_translate = R.anim.switch_scale_translate;
		com.atlastone.app.entry.R.anim.switch_scale_translate_rotate = R.anim.switch_scale_translate_rotate;
		com.atlastone.app.entry.R.anim.switch_slide_down_out = R.anim.switch_slide_down_out;
		com.atlastone.app.entry.R.anim.switch_slide_left = R.anim.switch_slide_left;
		com.atlastone.app.entry.R.anim.switch_slide_right = R.anim.switch_slide_right;
		com.atlastone.app.entry.R.anim.switch_slide_up_in = R.anim.switch_slide_up_in;
		com.atlastone.app.entry.R.anim.switch_wave_scale = R.anim.switch_wave_scale;
		com.atlastone.app.entry.R.anim.switch_zoom_enter = R.anim.switch_zoom_enter;
		com.atlastone.app.entry.R.anim.switch_zoom_exit = R.anim.switch_zoom_exit;
		// color
		com.atlastone.app.entry.R.color.background = R.color.background;
		com.atlastone.app.entry.R.color.black = R.color.black;
		com.atlastone.app.entry.R.color.black_end = R.color.black_end;
		com.atlastone.app.entry.R.color.black_start = R.color.black_start;
		com.atlastone.app.entry.R.color.blue_badges_mayor = R.color.blue_badges_mayor;
		com.atlastone.app.entry.R.color.blue_end = R.color.blue_end;
		com.atlastone.app.entry.R.color.blue_start = R.color.blue_start;
		com.atlastone.app.entry.R.color.brower_button_bg = R.color.brower_button_bg;
		com.atlastone.app.entry.R.color.color_bright_green = R.color.color_bright_green;
		com.atlastone.app.entry.R.color.color_score_list_adapter_highlight = R.color.color_score_list_adapter_highlight;
		com.atlastone.app.entry.R.color.color_score_mayor_section_background = R.color.color_score_mayor_section_background;
		com.atlastone.app.entry.R.color.dgrey_end = R.color.dgrey_end;
		com.atlastone.app.entry.R.color.dgrey_start = R.color.dgrey_start;
		com.atlastone.app.entry.R.color.grey_end = R.color.grey_end;
		com.atlastone.app.entry.R.color.grey_start = R.color.grey_start;
		com.atlastone.app.entry.R.color.item_grey_end = R.color.item_grey_end;
		com.atlastone.app.entry.R.color.item_grey_start = R.color.item_grey_start;
		com.atlastone.app.entry.R.color.lgrey_end = R.color.lgrey_end;
		com.atlastone.app.entry.R.color.lgrey_start = R.color.lgrey_start;
		com.atlastone.app.entry.R.color.purple_end = R.color.purple_end;
		com.atlastone.app.entry.R.color.purple_start = R.color.purple_start;
		com.atlastone.app.entry.R.color.tip_detail_actions_background = R.color.tip_detail_actions_background;
		com.atlastone.app.entry.R.color.titletextcolor = R.color.titletextcolor;
		com.atlastone.app.entry.R.color.transparent = R.color.transparent;
		com.atlastone.app.entry.R.color.user_details_activity_general = R.color.user_details_activity_general;
		com.atlastone.app.entry.R.color.vlgrey = R.color.vlgrey;
		com.atlastone.app.entry.R.color.white = R.color.white;
		com.atlastone.app.entry.R.color.window_bg = R.color.window_bg;
		// drawable
		com.atlastone.app.entry.R.drawable.brower_back_pressed = R.drawable.brower_back_pressed;
		com.atlastone.app.entry.R.drawable.brower_back_unpressed = R.drawable.brower_back_unpressed;
		com.atlastone.app.entry.R.drawable.brower_forward_pressed = R.drawable.brower_forward_pressed;
		com.atlastone.app.entry.R.drawable.brower_forward_unpressed = R.drawable.brower_forward_unpressed;
		com.atlastone.app.entry.R.drawable.brower_quit_pressed = R.drawable.brower_quit_pressed;
		com.atlastone.app.entry.R.drawable.brower_quit_unpressed = R.drawable.brower_quit_unpressed;
		com.atlastone.app.entry.R.drawable.brower_refresh_pressed = R.drawable.brower_refresh_pressed;
		com.atlastone.app.entry.R.drawable.brower_refresh_unpressed = R.drawable.brower_refresh_unpressed;
		com.atlastone.app.entry.R.drawable.brower_setting_pressed = R.drawable.brower_setting_pressed;
		com.atlastone.app.entry.R.drawable.brower_setting_unpressed = R.drawable.brower_setting_unpressed;
		com.atlastone.app.entry.R.drawable.comment_pressed = R.drawable.comment_pressed;
		com.atlastone.app.entry.R.drawable.comment_unpressed = R.drawable.comment_unpressed;
		com.atlastone.app.entry.R.drawable.ic_launcher = R.drawable.main_ic_launcher;
		// id
		com.atlastone.app.entry.R.id.adLinearLayout = R.id.adLinearLayout;
		com.atlastone.app.entry.R.id.adRelativeLayout = R.id.adRelativeLayout;
		com.atlastone.app.entry.R.id.browserButtonGroup = R.id.browserButtonGroup;
		com.atlastone.app.entry.R.id.browserView = R.id.browserView;
		// com.atlastone.app.entry.R.id.displayView = R.id.displayView;
		com.atlastone.app.entry.R.id.gameView = R.id.gameView;
		com.atlastone.app.entry.R.id.webViewClose = R.id.webViewClose;
		com.atlastone.app.entry.R.id.webViewContainer = R.id.webViewContainer;
		com.atlastone.app.entry.R.id.webViewGoBack = R.id.webViewGoBack;
		com.atlastone.app.entry.R.id.webViewGoForward = R.id.webViewGoForward;
		com.atlastone.app.entry.R.id.webViewProgressBar = R.id.webViewProgressBar;
		com.atlastone.app.entry.R.id.webViewRefresh = R.id.webViewRefresh;
		com.atlastone.app.entry.R.id.webViewSetting = R.id.webViewSetting;
		// layout
		com.atlastone.app.entry.R.layout.admob_view = R.layout.admob_view;
		com.atlastone.app.entry.R.layout.browser_view = R.layout.browser_view;
		com.atlastone.app.entry.R.layout.game_view = R.layout.game_view;
		// raw
		com.atlastone.app.entry.R.raw.alert = R.raw.alert;
		// string
		com.atlastone.app.entry.R.string.app_name = R.string.app_name;
		com.atlastone.app.entry.R.string.get_it_on_googleplay = R.string.get_it_on_googleplay;
		com.atlastone.app.entry.R.string.cancel = R.string.cancel;
		com.atlastone.app.entry.R.string.detectCheatingApp = R.string.detectCheatingApp;
		com.atlastone.app.entry.R.string.exit_message = R.string.exit_message;
		com.atlastone.app.entry.R.string.ok = R.string.ok;
		com.atlastone.app.entry.R.string.prompt = R.string.prompt;
		com.atlastone.app.entry.R.string.exit_game = R.string.exit_game;
		com.atlastone.app.entry.R.string.exit_game_outofmemory = R.string.exit_game_outofmemory;
		// style
		com.atlastone.app.entry.R.style.CustomCheckBox = R.style.CustomCheckBox;
		com.atlastone.app.entry.R.style.CustomWindowTitleBackground = R.style.CustomWindowTitleBackground;
		com.atlastone.app.entry.R.style.CustomWindowTitleText = R.style.CustomWindowTitleText;
		com.atlastone.app.entry.R.style.Default = R.style.Default;
		com.atlastone.app.entry.R.style.Default_NoTitleBar = R.style.Default_NoTitleBar;
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
