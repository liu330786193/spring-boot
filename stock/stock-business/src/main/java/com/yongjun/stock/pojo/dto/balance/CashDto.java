package com.yongjun.stock.pojo.dto.balance;

import java.math.BigDecimal;
import lombok.Data;

/**
 * 提现请求参数
 * Created by 姚磊 on 2017/10/28.
 */
@Data
public class CashDto {

  private String cardNo ;
  private BigDecimal money ;

}
