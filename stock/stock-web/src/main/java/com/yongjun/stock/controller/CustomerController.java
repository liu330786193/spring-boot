package com.yongjun.stock.controller;

import com.gexin.rp.sdk.base.uitls.MD5Util;
import com.yongjun.stock.entity.Customer;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.pojo.dto.customer.GetCustByTkDto;
import com.yongjun.stock.pojo.dto.customer.GetVerificationCodeDto;
import com.yongjun.stock.pojo.dto.customer.LoginDto;
import com.yongjun.stock.pojo.dto.customer.ModifyPwdDto;
import com.yongjun.stock.pojo.dto.customer.RegisterDto;
import com.yongjun.stock.pojo.dto.customer.ResetPwdDto;
import com.yongjun.stock.pojo.dto.customer.UpdateCustDto;
import com.yongjun.stock.service.jdbc.CustomerService;
import com.yongjun.stock.util.RedisUtils;

import com.yongjun.stock.util.StringUtils;
import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lyl on 2017/10/24.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

  @Resource
  private CustomerService customerService;


  /**
   * 获取验证码
   */
  @ResponseBody
  @RequestMapping(value = "/getVerificationCode", method = RequestMethod.POST)
  private Boolean getVerificationCode(@RequestBody GetVerificationCodeDto getVerificationCodeDto) {

    //todo sms send
    //todo redis cache sms code
    return true;
  }

  /**
   * APP客户注册
   */
  @ResponseBody
  @RequestMapping(value = "/registerAppCust", method = RequestMethod.POST)
  private Customer registerAppCust(@RequestBody RegisterDto registerDto) {
    try {
      return customerService.register(registerDto);
    } catch (CBusinessException e) {
      throw e;
    }

  }

  /**
   * APP 登录
   */
  @ResponseBody
  @RequestMapping(value = "/loginByPhone", method = RequestMethod.POST)
  private Customer loginByPhone(@RequestBody LoginDto loginDto) {
    try {
      return customerService.login(loginDto);
    } catch (CBusinessException e) {
      throw e;
    }
  }

  /**
   * APP 修改密码
   */
  @ResponseBody
  @RequestMapping(value = "/modifyCustPassword", method = RequestMethod.POST)
  private Boolean modifyCustPassword(HttpServletRequest request,
      @RequestBody ModifyPwdDto modifyPwdDto) {

    // fixme 是否为customer类型?需要在app登录的时候put到redis的时候确定
    Customer customer = RedisUtils.get(request.getParameter("token"), Customer.class);
    if (null == customer) {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }
    String oldPassword = MD5Util
        .getMD5Format(modifyPwdDto.getOldPassword() + customer.getPhoneNo().substring(6))
        .toLowerCase();
    String password = MD5Util
        .getMD5Format(modifyPwdDto.getNewPassword() + customer.getPhoneNo().substring(6))
        .toLowerCase();

    int result = customerService.updatePwd(customer.getPhoneNo(), oldPassword, password);
    if (result >= 1) {
      return true;
    } else {
      throw CExceptionFactory.create("BASESTOCK_LOGIN_1202");
    }

  }

  /**
   * APP 重置密码
   */
  @ResponseBody
  @RequestMapping(value = "/resetCustPassword", method = RequestMethod.POST)
  private Boolean resetCustPassword(HttpServletRequest request,
      @RequestBody ResetPwdDto resetPwdDto) {

    // fixme 是否为customer类型?需要在app登录的时候put到redis的时候确定
    Customer customer = RedisUtils.get(request.getParameter("token"), Customer.class);
    if (null == customer) {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }

    //todo 验证验证码是否正确？
    //todo 如果正确，redis删除验证码

    String password = MD5Util
        .getMD5Format(resetPwdDto.getPassword() + resetPwdDto.getPhoneNo().substring(6))
        .toLowerCase();

    Customer updateCustomer = new Customer();
    updateCustomer.setUpdateTime(new Date());
    updateCustomer.setPhoneNo(resetPwdDto.getPhoneNo());
    updateCustomer.setPassword(password);
    int result = customerService.updateByPhoneNo(updateCustomer);
    if (result >= 1) {
      return true;
    } else {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }
  }

  /**
   * 根据token获取客户信息
   */
  @ResponseBody
  @RequestMapping(value = "/getCustInfoByToken", method = RequestMethod.POST)
  private Customer getCustInfoByToken(@RequestBody GetCustByTkDto getCustByTkDto) {

    // fixme 是否为customer类型?需要在app登录的时候put到redis的时候确定
    Customer customer = RedisUtils.get(getCustByTkDto.getToken(), Customer.class);
    if (null == customer) {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }

    Customer customerInfo = customerService.getCustomerByPhone(customer.getPhoneNo());
    if (null != customerInfo) {
      return customerInfo;
    } else {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }
  }

  /**
   * 更新客户信息
   */
  @ResponseBody
  @RequestMapping(value = "/updateCustInfo", method = RequestMethod.POST)
  private Boolean updateCustInfo(HttpServletRequest request,
      @RequestBody UpdateCustDto updateCustDto) {

    // fixme 是否为customer类型?需要在app登录的时候put到redis的时候确定
    Customer customer = RedisUtils.get(request.getParameter("token"), Customer.class);
    if (null == customer) {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }

    Customer updateCust = new Customer();
//    updateCust.setId(customer.getId());
    updateCust.setPhoneNo(customer.getPhoneNo());

    if (StringUtils.isNoneEmpty(updateCustDto.getHeadimgUrl())) {
      updateCust.setHeadimgUrl(updateCustDto.getHeadimgUrl());
    }
    if (StringUtils.isNoneEmpty(updateCustDto.getNickName())) {
      updateCust.setNickname(updateCustDto.getNickName());
    }
    if (StringUtils.isNoneEmpty(updateCustDto.getTradePassword())) {
      updateCust.setTradePassword(updateCustDto.getTradePassword());
    }
    if (updateCustDto.getNoticeSwitch() >= 0) {
      updateCust.setNoticeSwitch(updateCustDto.getNoticeSwitch());
    }

    customerService.updateByPhoneNo(updateCust);

    return true;

  }


}

