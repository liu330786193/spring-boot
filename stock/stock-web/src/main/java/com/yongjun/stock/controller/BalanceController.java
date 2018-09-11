package com.yongjun.stock.controller;


import com.yongjun.stock.entity.CustomerBalanceLog;
import com.yongjun.stock.entity.CustomerCard;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.pojo.dto.BasePageRequestDto;
import com.yongjun.stock.pojo.dto.BaseRequestDto;
import com.yongjun.stock.pojo.dto.balance.BindCardDto;
import com.yongjun.stock.pojo.dto.balance.ChargeDto;
import com.yongjun.stock.pojo.page.PageBean;
import com.yongjun.stock.service.jdbc.BalanceService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 资金相关接口
 * Created by 姚磊 on 2017/10/26.
 */
@Controller
@RequestMapping("/balance")
public class BalanceController {

  @Resource
  private BalanceService balanceService;

  /**
   * 绑卡
   */
  @ResponseBody
  @RequestMapping(value = "/bindCard", method = RequestMethod.POST)
  private Boolean bindCard(HttpServletRequest request, @RequestBody BindCardDto bindCardDto) {
    String token = request.getParameter("token");
    try {
      return balanceService.bindCard(token, bindCardDto);

    } catch (CBusinessException e) {
      throw e;
    }
  }

  /**
   * 充值
   */
  @ResponseBody
  @RequestMapping(value = "/charge", method = RequestMethod.POST)
  private Boolean charge(HttpServletRequest request, @RequestBody ChargeDto chargeDto) {
    String token = request.getParameter("token");
    try {
      return balanceService.charge(token, chargeDto);

    } catch (CBusinessException e) {
      throw e;
    }
  }

  /**
   * 提现
   */
  @ResponseBody
  @RequestMapping(value = "/cash", method = RequestMethod.POST)
  private Boolean cash(HttpServletRequest request, @RequestBody ChargeDto chargeDto) {
    String token = request.getParameter("token");
    try {
      return balanceService.charge(token, chargeDto);

    } catch (CBusinessException e) {
      throw e;
    }
  }

  /**
   * 获取绑定银行卡
   */
  @ResponseBody
  @RequestMapping(value = "/getCardBind", method = RequestMethod.POST)
  private CustomerCard getCardBind(HttpServletRequest request,
      @RequestBody BaseRequestDto baseRequestDto) {
    String token = request.getParameter("token");

    return balanceService.getCardBind(token);


  }

  /**
   * 获取资金流水
   */
  @ResponseBody
  @RequestMapping(value = "/getbalanceList", method = RequestMethod.POST)
  private PageBean<CustomerBalanceLog> getbalanceList(HttpServletRequest request,
      @RequestBody BasePageRequestDto basePageRequestDto) {
    String token = request.getParameter("token");
    return balanceService.getBalanceLog(token, basePageRequestDto);

  }


}
