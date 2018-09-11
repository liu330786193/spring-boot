package com.yongjun.stock.service.jdbc;

import java.util.Map;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public interface VersionService {

  /**
   * 检测版本信息
   * @param appVersion
   * @param appName
   * @param appOs
   * @return
   */
  Map<String, Object> getVersionInfo(String appVersion, String appName, String appOs);

}
