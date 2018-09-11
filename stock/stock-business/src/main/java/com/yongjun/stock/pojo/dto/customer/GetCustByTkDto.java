package com.yongjun.stock.pojo.dto.customer;

import com.yongjun.stock.pojo.dto.BaseRequestDto;
import lombok.Data;

/**
 * 根据token获取用户信息请求参数
 * Created by 姚磊 on 2017/10/26.
 */
@Data
public class GetCustByTkDto extends BaseRequestDto {

  private String token;

}
