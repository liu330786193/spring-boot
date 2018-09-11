package com.yongjun.stock.service.jdbc;

import com.yongjun.stock.entity.Order;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.pojo.dto.order.ApplyDelayDto;
import com.yongjun.stock.pojo.dto.order.CreateOrderDto;
import com.yongjun.stock.pojo.dto.order.SellOrderDto;
import java.util.List;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public interface OrderService {

  List<Order> getOrderByCondition(Order order);

  /**
   * 修改止盈
   * @param order
   * @return
   */
  int modifyOrderStop(Order order);

  /**
   * 申请递延
   * @return
   */
  boolean applyDelay(ApplyDelayDto applyDelayDto) throws CBusinessException;

  /**
   * 卖出策略
   * @return
   */
  boolean sellOrder(SellOrderDto sellOrderDto) throws CBusinessException;

  /**
   * 创建策略
   * @param createOrderDto
   * @return
   */
  boolean createOrder(CreateOrderDto createOrderDto) throws CBusinessException;

  /**
   * 查询历史策略
   * @param customerId
   * @return
   */
  List<Order> getHisOrder(Long customerId);

}
