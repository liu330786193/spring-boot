package com.yongjun.stock.controller;

import com.github.pagehelper.PageHelper;
import com.yongjun.stock.entity.Customer;
import com.yongjun.stock.entity.Order;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.pojo.dto.BasePageRequestDto;
import com.yongjun.stock.pojo.dto.order.ApplyDelayDto;
import com.yongjun.stock.pojo.dto.order.CreateOrderDto;
import com.yongjun.stock.pojo.dto.order.ModifyStopPriceDto;
import com.yongjun.stock.pojo.dto.order.SellOrderDto;
import com.yongjun.stock.pojo.page.PageBean;
import com.yongjun.stock.service.jdbc.OrderService;
import com.yongjun.stock.util.RedisUtils;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 策略相关接口
 * Created by 姚磊 on 2017/10/26.
 */
@Controller
@RequestMapping("/order")
public class OrderController {

  @Resource
  private OrderService orderService;

  /**
   * 获取用户当前策略
   */
  @ResponseBody
  @RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
  private List<Order> getOrderList(HttpServletRequest request) {

    // fixme 是否为customer类型?需要在app登录的时候put到redis的时候确定
    Customer customer = RedisUtils.get(request.getParameter("token"), Customer.class);
    if (null == customer) {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }
    Order order = new Order();
    order.setCustomerId(customer.getId());
    order.setOrderStatus(2);
    List<Order> orders = orderService.getOrderByCondition(order);
    return orders;

  }

  /**
   * 获取用户历史策略
   */
  @ResponseBody
  @RequestMapping(value = "/getHisOrderList", method = RequestMethod.POST)
  private PageBean<Order> getHisOrderList(HttpServletRequest request,
      @RequestBody BasePageRequestDto basePageRequestDto) {

    // fixme 是否为customer类型?需要在app登录的时候put到redis的时候确定
    Customer customer = RedisUtils.get(request.getParameter("token"), Customer.class);
    if (null == customer) {
      throw CExceptionFactory.create("BASESTOCK_BASE_1001");
    }
    PageHelper.startPage(basePageRequestDto.getPageNo(), basePageRequestDto.getPageSize());
    return new PageBean<Order>(orderService.getHisOrder(customer.getId()));

  }


  /**
   * 获取用户当前策略
   */
  @ResponseBody
  @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
  private Boolean createOrder(@RequestBody CreateOrderDto createOrderDto) {
    try {
      return orderService.createOrder(createOrderDto);
    } catch (CBusinessException e) {
      throw e;
    }

  }


  /**
   * 修改止损
   */
  @ResponseBody
  @RequestMapping(value = "/modifyOrder", method = RequestMethod.POST)
  private Boolean modifyOrder(@RequestBody ModifyStopPriceDto modifyOrderDto) {

    //todo 业务校验，钱包余额、止损等校验

    Order order = new Order();
    order.setId(modifyOrderDto.getId());
    order.setStopPrice(modifyOrderDto.getStopPrice());
    order.setMoney(modifyOrderDto.getMoney());

    orderService.modifyOrderStop(order);
    return true;

  }


  /**
   * 申请递延
   */
  @ResponseBody
  @RequestMapping(value = "/applyDelay", method = RequestMethod.POST)
  private Boolean applyDelay(@RequestBody ApplyDelayDto applyDelayDto) {
    try {
      return orderService.applyDelay(applyDelayDto);
    } catch (CBusinessException e) {
      throw e;
    }
  }

  /**
   * 卖出
   */
  @ResponseBody
  @RequestMapping(value = "/sellOrder", method = RequestMethod.POST)
  private Boolean sellOrder(@RequestBody SellOrderDto sellOrderDto) {
    try {
      return orderService.sellOrder(sellOrderDto);
    } catch (CBusinessException e) {
      throw e;
    }
  }
}
