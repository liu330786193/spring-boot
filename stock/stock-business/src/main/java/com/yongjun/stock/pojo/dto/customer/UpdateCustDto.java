package com.yongjun.stock.pojo.dto.customer;

import lombok.Data;

/**
 * 更新客户信息
 * Created by 姚磊 on 2017/10/28.
 */
@Data
public class UpdateCustDto {

  private String headimgUrl;
  private String nickName;
  private String tradePassword;
  private Integer noticeSwitch;

}
