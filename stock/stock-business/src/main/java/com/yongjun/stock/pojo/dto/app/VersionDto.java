package com.yongjun.stock.pojo.dto.app;

import com.yongjun.stock.pojo.dto.BaseRequestDto;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public class VersionDto extends BaseRequestDto {

  private String appVersion;

  private String appName;

  private String appOs;

  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getAppName() {
    return appName;
  }

  public void setAppName(String appName) {
    this.appName = appName;
  }

  public String getAppOs() {
    return appOs;
  }

  public void setAppOs(String appOs) {
    this.appOs = appOs;
  }
}
