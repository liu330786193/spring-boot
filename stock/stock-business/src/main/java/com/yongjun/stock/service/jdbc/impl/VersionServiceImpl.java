package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.VersionDao;
import javax.annotation.Resource;

import com.yongjun.stock.service.jdbc.VersionService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class VersionServiceImpl implements VersionService {

  @Resource
  private VersionDao versionDao;

  @Override
  public Map<String, Object> getVersionInfo(String appVersion, String appName, String appOs) {
    return versionDao.selectVersion(appVersion, appName, appOs);
  }
}
