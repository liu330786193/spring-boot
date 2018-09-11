package com.yongjun.stock.pojo.dto.customer;

import com.yongjun.stock.pojo.dto.BaseRequestDto;
import lombok.Data;

/**
 * 修改密码请求参数
 * Created by 姚磊 on 2017/10/26.
 */

@Data
public class ModifyPwdDto extends BaseRequestDto {

  private String oldPassword;

  private String newPassword;

}
