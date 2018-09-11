package com.yongjun.stock.pojo.dto;

import lombok.Data;

/**
 * APP分页请求公共参数
 * Created by 姚磊 on 2017/10/28.
 */
@Data
public class BasePageRequestDto {

  private Integer pageNo;

  private Integer pageSize;

}
