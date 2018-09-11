package com.yongjun.stock.pojo.dto.order;

import com.yongjun.stock.pojo.dto.BaseRequestDto;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 创建策略请求参数
 * Created by 姚磊 on 2017/10/28.
 */
@Data
public class CreateOrderDto extends BaseRequestDto {

  private String stockCode;
  private String stockName;
  private Integer orderType;
  private Integer tradeNumber;
  private BigDecimal tradeMoney;
  private BigDecimal money;
  private BigDecimal buyPrice;
  private BigDecimal stopPrice;
  private BigDecimal targetPrice;
  private Integer buyType;
  private Integer autoDelay;


}
