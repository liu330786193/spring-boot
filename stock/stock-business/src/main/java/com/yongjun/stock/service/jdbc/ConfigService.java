package com.yongjun.stock.service.jdbc;

import com.yongjun.stock.entity.Config;
import java.util.List;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public interface ConfigService {

  /**
   * 根据商户号获取商户配置信息
   * @param companyId
   * @return
   */
  List<Config> getConfigByCompany(Long companyId);

}
