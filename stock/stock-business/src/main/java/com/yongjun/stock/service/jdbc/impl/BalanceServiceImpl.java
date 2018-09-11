package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.CustomerCardDao;
import com.yongjun.stock.dao.CustomerDao;
import com.yongjun.stock.entity.Customer;
import com.yongjun.stock.entity.CustomerBalanceLog;
import com.yongjun.stock.entity.CustomerCard;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.pojo.dto.BasePageRequestDto;
import com.yongjun.stock.pojo.dto.balance.BindCardDto;
import com.yongjun.stock.pojo.dto.balance.CashDto;
import com.yongjun.stock.pojo.dto.balance.ChargeDto;
import com.yongjun.stock.pojo.page.PageBean;
import com.yongjun.stock.service.jdbc.BalanceService;
import com.yongjun.stock.util.DateUtils;
import com.yongjun.stock.util.RedisUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * Created by 姚磊 on 2017/10/28.
 */
@Service
public class BalanceServiceImpl implements BalanceService {

  @Resource
  private CustomerDao customerDao;

  @Resource
  private CustomerCardDao customerCardDao;

  @Override
  public boolean bindCard(String token, BindCardDto bindCardDto) throws CBusinessException {
    // fixme 是否为customer类型?需要在app登录的时候put到redis的时候确定
    Customer customer = RedisUtils.get(token, Customer.class);

    CustomerCard customerCard = new CustomerCard();
    customerCard.setCompanyId(customer.getCompanyId());
    customerCard.setCustomerId(customer.getId());
    customerCard.setCardNumber(bindCardDto.getCardNo());
    customerCard.setAuditId(-1L);
    customerCard.setHistory(0);

    List<CustomerCard> customerCards = customerCardDao.selectByCondition(customerCard);
    if(customerCards.size()>0){
      //todo 绑卡失败
      throw CExceptionFactory.create("*****");
    }

    //todo 调用连连认证支付API
    boolean success = true;
    customerCard.setSubmitTime(DateUtils.getCurrent());
    if (success) {
      String cardNo = bindCardDto.getIdCard();
      String bitrh;
      String sexNo;
      if (cardNo.length() == 18) {
        bitrh = StringUtils.substring(cardNo, 6, 14);
        sexNo = StringUtils.substring(cardNo, 16, 17);
      } else {
        bitrh = "19" + StringUtils.substring(cardNo, 6, 12);
        sexNo = StringUtils.substring(cardNo, 14);
      }
      Integer sex = Integer.valueOf(sexNo) % 2 == 0 ? 2 : 1;
      Date birthDay = DateUtils.parse(bitrh, DateUtils.FORMAT_INT_DATE);
      Integer age = getAge(birthDay);

      customer.setName(bindCardDto.getName());
      customer.setCardNumber(cardNo);
      customer.setBirthday(birthDay);
      customer.setSex(sex);
      customer.setAge(age);
      customer.setMoneyUsable(bindCardDto.getChargeMoney());
      customer.setMoneyTotal(bindCardDto.getChargeMoney());
      customerDao.updateByPrimaryKeySelective(customer);

      //todo 更新银行信息
      customerCard.setBankId(0L);
      customerCard.setBankName("");
      customerCard.setAuditStatus(1);
      customerCardDao.insert(customerCard);

      //todo insert customerBalance customerBalanceLog

      //todo redis 更新客户信息
    } else {

      customerCard.setAuditStatus(1);
      customerCardDao.insert(customerCard);
      //todo 绑卡失败
      throw CExceptionFactory.create("*****");
    }
    return true;
  }

  @Override
  public boolean charge(String token, ChargeDto chargeDto) throws CBusinessException {
    return false;
  }

  @Override
  public boolean cash(String token, CashDto cashDto) throws CBusinessException {
    return false;
  }

  @Override
  public CustomerCard getCardBind(String token) {
    return null;
  }

  @Override
  public PageBean<CustomerBalanceLog> getBalanceLog(String token,
      BasePageRequestDto basePageRequestDto) {
    return null;
  }


  /**
   * 获取年龄
   **/
  private int getAge(Date birth) {
    Calendar birthCalendar = Calendar.getInstance();
    birthCalendar.setTime(birth);

    Calendar nowCalendar = Calendar.getInstance();
    nowCalendar.setTime(new Date());

    int year = nowCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
    int month = nowCalendar.get(Calendar.MONTH) - birthCalendar.get(Calendar.MONTH);
    int date = nowCalendar.get(Calendar.DATE) - birthCalendar.get(Calendar.DATE);
    if (date < 0) {
      month--;
    }
    if (month < 0) {
      year--;
    }
    return year;

  }
}
