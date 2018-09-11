package com.yongjun.stock.service.getui;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.yongjun.stock.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PushToSingle {

	private static Logger logger = LoggerFactory.getLogger(PushToSingle.class);

	private static final String APPNAME = "全民投顾";


    public static void pushGetui(String content, String clientId, String payload) {
    	if (CommonUtil.isEmpty(content) || CommonUtil.isEmpty(clientId)){
            return;
        }
    	if (!CommonUtil.isEmpty(payload)){
    		payload = payload.replace("'", "\"");
    	}

        TransmissionTemplate template = transmissionTemplateDemo(content, payload, IGTPushSingleton.GETUI_APPID, IGTPushSingleton.GETUI_APPKEY);
        SingleMessage message = new SingleMessage();
        message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 3600 * 1000);
        message.setData(template);
        message.setPushNetWorkType(0); //可选。判断是否客户端是否wifi环境下推送，1为在WIFI环境下，0为不限制网络环境。
        Target target = new Target();

        target.setAppId(IGTPushSingleton.GETUI_APPKEY);
        target.setClientId(clientId);
        IPushResult ret = null;
        try{
            ret = IGTPushSingleton.getInstance().pushMessageToSingle(message, target);
        }catch(RequestException e){
        	logger.error("推送失败",e);
            ret = IGTPushSingleton.getInstance().pushMessageToSingle(message, target, e.getRequestId());
        }
        if(ret != null){
            logger.info(ret.getResponse().toString());
        }else{
        	logger.info("服务器响应异常");
        }
    }

    public static boolean pushMessageToApp(String content, String payload) {
    	if (CommonUtil.isEmpty(content)){
            return false;
        }
    	if (!CommonUtil.isEmpty(payload)){
    		payload = payload.replace("'", "\"");
    	}
        TransmissionTemplate template = transmissionTemplateDemo(content, payload, IGTPushSingleton.GETUI_APPID, IGTPushSingleton.GETUI_APPKEY);
		AppMessage message = new AppMessage();
	    message.setData(template);
	    message.setOffline(true);
        //离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        //推送给App的目标用户需要满足的条件
        List<String> appIdList = new ArrayList<String>();
        appIdList.add(IGTPushSingleton.GETUI_APPKEY);
        message.setAppIdList(appIdList);
        IPushResult ret = IGTPushSingleton.getInstance().pushMessageToApp(message, APPNAME);
        if(ret != null && ret.getResponse() != null){
        	if ("ok".equals(ret.getResponse().get("result"))){
                return true;
            }
        	else{
        		logger.error("app消息群推失败："+ret.getResponse().toString());
        		return false;
        	}
        }else{
        	logger.info("app消息群推失败：服务器响应异常");
        	return false;
        }
    }


    public static TransmissionTemplate transmissionTemplateDemo(String content, String payload, String appId, String appkey) {
    	TransmissionTemplate template = new TransmissionTemplate();
    	template.setAppId(appId);
    	template.setAppkey(appkey);
    	// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
    	template.setTransmissionType(2);
    	String transmissionContent = "{\"title\":\""+APPNAME+"\",\"content\":\""+content+"\",\"payload\":"+payload+"}";
    	template.setTransmissionContent(transmissionContent);
    	APNPayload apnpayload = new APNPayload();
        apnpayload.setSound("");
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setTitle(APPNAME);
        alertMsg.setBody(content);
        alertMsg.setTitleLocKey(APPNAME);
        apnpayload.setAlertMsg(alertMsg);
        apnpayload.setBadge(1);
        template.setAPNInfo(apnpayload);
    	return template;
    }
}
