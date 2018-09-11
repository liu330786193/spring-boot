package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.ActivityDao;
import com.yongjun.stock.entity.Activity;
import com.yongjun.stock.service.jdbc.ActivityService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class ActivityServiceImpl implements ActivityService {

  @Resource
  private ActivityDao activityDao;

  @Override
  public List<Activity> getAcitivityByCompany(Long companyId) {
    return activityDao.selectByCompanyId(companyId);
  }
}
