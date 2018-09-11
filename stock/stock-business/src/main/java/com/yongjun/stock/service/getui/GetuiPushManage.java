package com.yongjun.stock.service.getui;

import com.yongjun.stock.util.CommonUtil;
import com.yongjun.stock.util.DateUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service(value = "getuiPushManage")
public class GetuiPushManage {
	// 推送消息类别，根据类别不同消息内容不同
	public static final int TYPE_OF_CERTIFY_SUC=2; // 实名认证审核通过


	@Async
	public void push2Single(String content, String clientid, String payload) throws Exception {
		if (CommonUtil.isEmpty(clientid) || CommonUtil.isEmpty(content)){
			return;
		}
		new Push2SingleThread(content, clientid, payload);
	}

	public void push2Single(int type,String clientid) throws Exception {
		String[] ret = this.getContent(type);
		push2Single(ret[0], clientid, ret[1]);
	}

	// 根据类别生成推送内容和payload
	private String[] getContent(int type){
		String[] ret = new String[2];
		if (TYPE_OF_CERTIFY_SUC == type){
			ret[0] = "恭喜您，您的实名认证已通过，请继续完成其他认证吧～";
			ret[1] = "{'appUrl':'huajinbao://auth.list','mTitle':'实名认证成功','logo':'http://huajinbao.b0.upaiyun.com/logo/m_id.png','bText':'继续认证','content':'"+ret[0]+"','type':'"+type+"','createTime':'"+DateUtils.format(new Date(), DateUtils.FORMAT_LONG)+"'}";
		}

		return ret;
	}
}
