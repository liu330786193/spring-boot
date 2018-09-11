package com.yongjun.stock.pojo.dto.balance;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 充值请求参数
 * Created by 姚磊 on 2017/10/28.
 */
@Data
public class ChargeDto {

  private String cardNo ;
  private BigDecimal chargeMoney;

}
