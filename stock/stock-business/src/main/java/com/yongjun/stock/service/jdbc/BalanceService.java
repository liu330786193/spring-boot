package com.yongjun.stock.service.jdbc;

import com.yongjun.stock.entity.CustomerBalanceLog;
import com.yongjun.stock.entity.CustomerCard;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.pojo.dto.BasePageRequestDto;
import com.yongjun.stock.pojo.dto.balance.BindCardDto;
import com.yongjun.stock.pojo.dto.balance.CashDto;
import com.yongjun.stock.pojo.dto.balance.ChargeDto;
import com.yongjun.stock.pojo.page.PageBean;
import java.util.List;

/**
 * Created by 姚磊 on 2017/10/28.
 */
public interface BalanceService {


  /**
   *
   * @param token
   * @param bindCardDto
   * @return
   * @throws CBusinessException
   */
  boolean bindCard(String token, BindCardDto bindCardDto) throws CBusinessException;

  /**
   * 充值
   * @param token
   * @param chargeDto
   * @return
   * @throws CBusinessException
   */
  boolean charge(String token, ChargeDto chargeDto) throws CBusinessException;


  /**
   * 提现
   * @param token
   * @param cashDto
   * @return
   * @throws CBusinessException
   */
  boolean cash(String token, CashDto cashDto) throws CBusinessException;

  /**
   * 获取银行卡信息
   * @param token
   * @return
   */
  CustomerCard getCardBind(String token);

  PageBean<CustomerBalanceLog> getBalanceLog(String token, BasePageRequestDto basePageRequestDto);


}
