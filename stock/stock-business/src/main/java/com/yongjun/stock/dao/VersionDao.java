package com.yongjun.stock.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Param;

public interface VersionDao {

    /**
     * 检测客户端版本
     * @param appVersion
     * @param appName
     * @param appOs
     * @return
     */
    Map<String,Object> selectVersion(@Param("appVersion") String appVersion, @Param("appName") String appName, @Param("appOs") String appOs);
}