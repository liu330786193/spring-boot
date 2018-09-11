package com.yongjun.stock.pojo.dto.order;

import com.yongjun.stock.pojo.dto.BaseRequestDto;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 申请递延请求参数
 * Created by 姚磊 on 2017/10/27.
 */
@Data
public class ApplyDelayDto extends BaseRequestDto {

  private Long id;
  private BigDecimal delayMoney;
  private BigDecimal money;


}
