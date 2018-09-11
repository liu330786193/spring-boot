package com.yongjun.stock.dao;

import com.yongjun.stock.entity.Config;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigDao {

    /**
     * 根据商户号查询配置
     * @param companyId
     * @return
     */
    List<Config> selectConfigByCompany(@Param("companyId") Long companyId);
}