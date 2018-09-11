/*
 * @(#)ReqMesLogThread.java 2014-3-10下午05:00:44
 * Copyright 2013 sinovatech, Inc. All rights reserved.
 */
package com.yongjun.stock.service.getui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 个推线程
 * 
 */
public class Push2SingleThread implements Runnable {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private String content;
	private String clientid;
	private String payload;
	
	/**
	 * 
	 * 创建一个新的实例Push2SingleThread
	 *
	 */
	public Push2SingleThread(String content, String clientid, String payload) {
		super();
		this.content = content;
		this.clientid = clientid;
		this.payload = payload;
	}

	@Override
	public void run() {
		try {
			PushToSingle.pushGetui(content, clientid, payload);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
	}

}
