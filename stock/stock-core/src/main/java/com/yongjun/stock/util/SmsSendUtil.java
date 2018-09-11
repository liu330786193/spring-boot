package com.yongjun.stock.util;

import java.util.Map;
import java.util.Random;

import com.yongjun.stock.submail.config.AppConfig;
import com.yongjun.stock.submail.lib.MESSAGEXsend;
import com.yongjun.stock.submail.utils.ConfigLoader;

public class SmsSendUtil {
	
	public static int TYPE_OF_VERIFICATION = 1;  // 获取验证码
	
	@SuppressWarnings("rawtypes")
	public static boolean sendSms(String phoneNo, int type, Map data) {
		boolean sendFlag = false;
		
		String mName = getSubmailModelName(type);
		if (mName != null && !"".equals(mName.trim())){
			sendFlag = sendBySubMail(phoneNo, mName, data);
		}
		
		return sendFlag;
		
	}
	
	/**
	 * 生成四位动态验证码
	 * @return
	 */
	public static String generateSmsCode() {
		return new Random().nextInt(9000) + 1000 + "";
	}

	@SuppressWarnings("rawtypes")
	public static boolean sendBySubMail(String phoneNo, String modelName, Map data) {
		AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
		MESSAGEXsend submail = new MESSAGEXsend(config);
		submail.addTo(phoneNo);
		submail.setProject(modelName);
		for (Object key : data.keySet()){
			submail.addVar(key.toString(), data.get(key).toString());
		}
		return submail.xsend();
	}	
	
	public static String getSubmailModelName(int type){
		if (type == TYPE_OF_VERIFICATION)
			return "aWsRl2";
		
		return "";
	}
}
