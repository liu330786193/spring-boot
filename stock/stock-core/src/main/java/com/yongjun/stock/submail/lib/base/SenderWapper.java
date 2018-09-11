package com.yongjun.stock.submail.lib.base;

import com.yongjun.stock.submail.entity.DataStore;

public abstract class SenderWapper {

	protected DataStore requestData = new DataStore();

	public abstract ISender getSender();
}
