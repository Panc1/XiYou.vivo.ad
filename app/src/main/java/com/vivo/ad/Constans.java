package com.vivo.ad;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 80059209 on 2017-05-19.
 */

public final class Constans {
    /**
     * 应用ID和广告位ID；如果没有，可以去OPPO联盟管理后台申请
     */
//    public static final String APP_ID = "1c3d7ac560e38a7567b4ca996e6e3239";// 应用ID
    public static final String APP_ID = "553b6e0f5019427b9ef04a92525fcd4a";// 应用ID
    public static final String Ad_OF_OPEN = "d056ff2d0b90496885d16458353ea39f";//开屏
    public static final int Clock = 15;
    public static final Map<Integer, String> Ad_List = new HashMap<Integer, String>() {
        {
            put(1, "91ea5a459bd9480bb3dca1e8d90e193e");  // 1 按指定在线时间弹出
            put(2, "960ee26b432e49ca9d73ee434c195d8c"); // 2 进入签到页领取奖励后弹出
            put(3, "d586937e30524f9f9f147a4a1d3b45ed");  // 3 进入宝箱页弹出
            put(4, "b5279efaf11b4b039a77e51e0ba84c5e");   // 4 进入背包页弹出
            put(5, "e5b956092009450ea2c0fa7e1fe02600");  // 5 进入抽奖页弹出
            put(6, "1a4a5018e270443e9ef7f15c74196318");  // 6 进入角色信息页弹出
            put(7, "250714da98c9459b94dd69a2e6359636");   // 7 进入角色进阶页弹出
            put(8, "31c30693e52a4f2d9a9e57b515c11357");   // 8 游戏暂停原生广告
            put(9, "203298f5f6714b6ea6d93c5c2ebc5737");   // 9 用户按退出游戏弹出
        }
    };

}
