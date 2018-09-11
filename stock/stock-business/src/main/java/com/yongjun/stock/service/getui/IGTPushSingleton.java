package com.yongjun.stock.service.getui;

import com.gexin.rp.sdk.http.IGtPush;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by lyl on 2017/10/27.
 * 静态内部类
 */
public class IGTPushSingleton {

    public static String GETUI_APPID;

    public static String GETUI_APPKEY;

    public static String GETUI_MASTER;

    public static String GETUI_HOST;

    @Value("${getui.appId}")
    public void setAppId(String appId) {
        IGTPushSingleton.GETUI_APPID = appId;
    }

    @Value("${getui.appkey}")
    public void setAppkey(String appkey) {
        IGTPushSingleton.GETUI_APPKEY = appkey;
    }

    @Value("${getui.master}")
    public void setMaster(String master) {
        IGTPushSingleton.GETUI_MASTER = master;
    }

    @Value("${getui.host}")
    public void setHost(String host) {
        IGTPushSingleton.GETUI_HOST = host;
    }

    private IGTPushSingleton(){}

    private static class IGTPushSingletonHolder{
        private static final IGtPush singleton = new IGtPush(IGTPushSingleton.GETUI_HOST, IGTPushSingleton.GETUI_APPKEY, IGTPushSingleton.GETUI_MASTER);
    }

    public static final IGtPush getInstance(){
        return IGTPushSingletonHolder.singleton;
    }


}
