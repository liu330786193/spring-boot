package com.yongjun.stock.service.jdbc;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public interface BlacklistService {

  /**
   * 查询手机号是否在黑名单内
   * @param phone
   * @return
   */
  boolean isBlack(String phone);

}
