package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.CustomerDao;
import com.yongjun.stock.entity.Customer;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.pojo.dto.customer.LoginDto;
import com.yongjun.stock.pojo.dto.customer.RegisterDto;
import com.yongjun.stock.service.jdbc.BlacklistService;
import com.yongjun.stock.service.jdbc.CustomerService;
import com.yongjun.stock.util.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lyl on 2017/10/24.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerDao customerDao;

  @Resource
  private BlacklistService blacklistService;

  @Value("${companyId}")
  private Long companyId;

  /**
   * Comment is created by lyl on 2017/10/24 下午3:07.
   *
   * 测试
   */
  @Override
  public List<Customer> findCustomerInfo() {
    return customerDao.findCustomerInfo();
  }

  @Override
  public List<Customer> getCustomerByCondition(Customer customer) {
    return customerDao.selectByCondition(customer);
  }

  @Override
  public Customer getCustomerByPhone(String phone) {
    return customerDao.selectByPhone(phone);
  }

  @Override
  @Transactional
  public Customer register(RegisterDto registerDto) {
    String phoneNo = registerDto.getPhoneNo();
    boolean black = blacklistService.isBlack(phoneNo);
    if (black) {
      throw CExceptionFactory.create("BASESTOCK_REGIST_1101");
    }
    Customer existCustomer = getCustomerByPhone(phoneNo);
    if (null != existCustomer) {
      throw CExceptionFactory.create("BASESTOCK_REGIST_1102");
    }
    //todo 验证验证码是否一致
    //todo 如果一致，redis删除验证码
    Customer registerCustomer = new Customer();
    setCustomer(registerCustomer, registerDto);
    int result = customerDao.insert(registerCustomer);
    if (result != 1) {
      throw CExceptionFactory.create("BASESTOCK_REGIST_1104");
    }
    return registerCustomer;
  }


  @Override
  public int updatePwd(String phoneNo, String oldPassword, String newPassword) {
    return customerDao.updatePassword(phoneNo, oldPassword, newPassword);

  }

  @Override
  public int updateByPhoneNo(Customer customer) {
    return customerDao.updateByPhone(customer);
  }

  @Override
  @Transactional
  public Customer login(LoginDto loginDto) {
    Customer customer = customerDao.selectByPhone(loginDto.getPhoneNo());
    if (null == customer) {
      throw CExceptionFactory.create("BASESTOCK_LOGIN_1201");
    }
    if (StringUtils.equals(customer.getPassword(), loginDto.getPassword())) {
      customer.setLastLoginTime(new Date());
      if (null == customer.getActiveDate()) {
        customer.setActiveDate(new Date());
        if (null != customer.getAgentId()) {
          //todo 处理代理商分润
        }
      }
      customerDao.updateByPrimaryKeySelective(customer);

      //todo 强制刷新掉原来的缓存
      //todo redis刷新用户信息
      //fixme 返回登录接口数据?是否需要重新定义返回数据格式
      return customer;
    } else {
      throw CExceptionFactory.create("BASESTOCK_LOGIN_1202");
    }
  }

  /**
   * 初始化注册用户数据
   */
  private void setCustomer(Customer customer, RegisterDto registerDto) {
    BigDecimal initMoney = new BigDecimal(0);
    customer.setId(0L);
    customer.setCompanyId(companyId);
    customer.setPhoneNo(registerDto.getPhoneNo());
    customer.setPassword(registerDto.getPassword());
    customer.setNickname(registerDto.getPhoneNo());
    customer.setSex(0);
    customer.setActiveDate(new Date());
    customer.setLastLoginTime(new Date());
    customer.setDeviceId(registerDto.getClientid());
    customer.setPhoneModel(registerDto.getPhoneModel());
    customer.setIsBlack(0);
    customer.setMoneyTotal(initMoney);
    customer.setMoneyFrozen(initMoney);
    customer.setMoneyUsable(initMoney);
    customer.setMoneyRecharge(initMoney);
    customer.setMoneyWithdraw(initMoney);
    customer.setIsOpen(1);
    customer.setNoticeSwitch(1);
    customer.setCreateTime(new Date());
    customer.setUpdateTime(new Date());
  }
}
