package com.yongjun.stock.pojo.dto.customer;

import lombok.Data;

/**
 * APP用户注册请求参数
 * Created by 姚磊 on 2017/10/26.
 */
@Data
public class RegisterDto {

  private String phoneNo;
  private String password;
  private String clientid;
  private String verificationCode;
  private String phoneModel;

}
