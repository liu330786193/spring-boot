package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.CompanyDao;
import com.yongjun.stock.service.jdbc.CompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class CompanyServiceImpl implements CompanyService {

  @Resource
  private CompanyDao companyDao;

}
