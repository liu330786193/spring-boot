package com.yongjun.stock.dao;

import com.yongjun.stock.entity.Blacklist;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BlacklistDao {

    /**
     * 根据手机号查询黑名单
     * @param phoneNo
     * @return
     */
    List<Blacklist> selectByPhone(@Param("phoneNo") String phoneNo);
}