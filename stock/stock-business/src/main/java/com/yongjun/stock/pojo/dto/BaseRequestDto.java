package com.yongjun.stock.pojo.dto;

import lombok.Data;

/**
 * APP请求公共参数
 * Created by 姚磊 on 2017/10/26.
 */

@Data
public class BaseRequestDto {

  private String interId;
  private String ver;
  private Long companyId;

}
