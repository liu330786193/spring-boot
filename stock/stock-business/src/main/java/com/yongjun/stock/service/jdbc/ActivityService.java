package com.yongjun.stock.service.jdbc;

import com.yongjun.stock.entity.Activity;
import java.util.List;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public interface ActivityService {

  /**
   * 根据商户号获取该商户下的活动信息
   * @param companyId
   * @return
   */
  List<Activity> getAcitivityByCompany(Long companyId);

}
