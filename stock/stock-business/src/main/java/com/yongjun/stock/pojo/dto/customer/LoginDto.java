package com.yongjun.stock.pojo.dto.customer;

import com.yongjun.stock.pojo.dto.BaseRequestDto;
import lombok.Data;

/**
 * 登录请求参数
 * Created by 姚磊 on 2017/10/26.
 */

@Data
public class LoginDto extends BaseRequestDto {

  private String phoneNo;

  private String password;

  private String clientid;

}
