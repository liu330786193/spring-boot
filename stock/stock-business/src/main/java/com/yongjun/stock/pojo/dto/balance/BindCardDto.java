package com.yongjun.stock.pojo.dto.balance;

import com.yongjun.stock.pojo.dto.BaseRequestDto;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 绑卡请求参数
 * Created by 姚磊 on 2017/10/28.
 */
@Data
public class BindCardDto extends BaseRequestDto {

  private String name;
  private String idCard;
  private String cardNo;
  private BigDecimal chargeMoney;


}
