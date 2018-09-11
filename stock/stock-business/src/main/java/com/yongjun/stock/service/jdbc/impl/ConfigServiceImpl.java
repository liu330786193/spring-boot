package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.ConfigDao;
import com.yongjun.stock.entity.Config;
import com.yongjun.stock.service.jdbc.ConfigService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class ConfigServiceImpl implements ConfigService {

  @Resource
  private ConfigDao configDao;

  @Override
  public List<Config> getConfigByCompany(Long companyId) {
    return configDao.selectConfigByCompany(companyId);
  }
}
