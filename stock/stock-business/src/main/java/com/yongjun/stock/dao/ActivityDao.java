package com.yongjun.stock.dao;

import com.yongjun.stock.entity.Activity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityDao {


    List<Activity> selectByCompanyId(@Param("companyId") Long companyId);
}