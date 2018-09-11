package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.CustomerDao;
import com.yongjun.stock.dao.OrderDao;
import com.yongjun.stock.dao.OrderDelayDao;
import com.yongjun.stock.dao.StockBlackDao;
import com.yongjun.stock.entity.Customer;
import com.yongjun.stock.entity.Order;
import com.yongjun.stock.entity.OrderDelay;
import com.yongjun.stock.entity.StockBlack;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.pojo.dto.order.ApplyDelayDto;
import com.yongjun.stock.pojo.dto.order.CreateOrderDto;
import com.yongjun.stock.pojo.dto.order.SellOrderDto;
import com.yongjun.stock.constants.StockConstants;
import com.yongjun.stock.service.jdbc.OrderService;
import com.yongjun.stock.service.jdbc.StockBlackService;
import com.yongjun.stock.service.jdbc.StockService;
import com.yongjun.stock.util.DateUtils;
import com.yongjun.stock.util.RedisUtils;
import com.yongjun.stock.util.StringUtils;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class OrderServiceImpl implements OrderService {

  @Resource
  private OrderDao orderDao;

  @Resource
  private CustomerDao customerDao;

  @Resource
  OrderDelayDao orderDelayDao;

  @Resource
  private StockService stockService;

  @Value("${companyId}")
  private Long companyId;

  @Override
  public List<Order> getOrderByCondition(Order order) {
    return orderDao.selectByCondition(order);
  }

  @Override
  public int modifyOrderStop(Order order) {
    return orderDao.updateStopPrice(order);
  }

  @Override
  @Transactional
  public boolean applyDelay(ApplyDelayDto applyDelayDto) {
    //todo 业务校验，钱包余额、止损等校验
    Order order = new Order();
    order.setId(applyDelayDto.getId());
    Order delayOrder = orderDao.selectByPrimaryKey(applyDelayDto.getId());

    if (null != delayOrder) {

      //todo 计算下个交易日 节假日怎么处理？
      Date sellTime = null;
      delayOrder.setMoney(delayOrder.getDelayMoney().add(applyDelayDto.getMoney()));
      delayOrder.setOrderSellTime(sellTime);

      Customer customer = customerDao.selectByPrimaryKey(delayOrder.getCustomerId());
      BigDecimal useable = customer.getMoneyUsable().subtract(applyDelayDto.getDelayMoney())
          .subtract(applyDelayDto.getMoney());

      if (useable.doubleValue() < 0) {
        customer.setMoneyUsable(useable);
        //fixme 扣款逻辑？
        BigDecimal frozen = customer.getMoneyFrozen().add(applyDelayDto.getDelayMoney())
            .add(applyDelayDto.getMoney());
        customer.setMoneyFrozen(frozen);
      } else {
        //todo error code
        throw CExceptionFactory.create("****");
      }

      orderDao.updateByPrimaryKeySelective(delayOrder);
      customerDao.updateByPrimaryKeySelective(customer);
      OrderDelay orderDelay = new OrderDelay();
      orderDelay.setOrderId(delayOrder.getId());
      orderDelay.setDelayDate(sellTime);
      orderDelay.setDelayMoney(applyDelayDto.getDelayMoney());
      orderDelayDao.insert(orderDelay);

    } else {
      //todo error code
      throw CExceptionFactory.create("****");

    }
    return true;
  }

  @Override
  public boolean sellOrder(SellOrderDto sellOrderDto) {

    Order order = orderDao.selectByPrimaryKey(sellOrderDto.getId());
    if (null != order) {

      order.setOrderStatus(3);
      order.setSellPrice(sellOrderDto.getSellPrice());
      orderDao.updateByPrimaryKeySelective(order);
      //todo 发送卖出通知到队列
    }

    return true;
  }

  @Override
  @Transactional
  public boolean createOrder(CreateOrderDto createOrderDto) {

    boolean canBuy = stockService.canBuy(createOrderDto.getStockCode());
    if (!canBuy) {
      return false;
    }
    if (createOrderDto.getTradeNumber() <= 0 || createOrderDto.getTradeNumber() % 100 != 0) {
      //todo 购买数量必须是100的倍数 error code
      throw CExceptionFactory.create("****");
    }
    //todo 根据stockCode获取行情，如果涨跌幅> ?，抛出异常

    //todo redis获取customer phoneNo
    String phoneNo = "";
    Customer customer = customerDao.selectByPhone(phoneNo);

    BigDecimal useable = customer.getMoneyUsable().subtract(createOrderDto.getMoney());

    if (useable.doubleValue() < 0) {
      customer.setMoneyUsable(useable);
      //fixme 扣款逻辑？
      BigDecimal frozen = customer.getMoneyFrozen().add(createOrderDto.getMoney());
      customer.setMoneyFrozen(frozen);
    } else {
      //todo error code
      throw CExceptionFactory.create("****");
    }

    Order order = new Order();
    order.setCompanyId(customer.getCompanyId());
    order.setCustomerId(customer.getId());
    setOrder(order, createOrderDto);
    orderDao.insertSelective(order);
    customerDao.updateByPrimaryKeySelective(customer);

    //todo 发送买入通知到队列
    return true;
  }

  @Override
  public List<Order> getHisOrder(Long customerId) {
    return orderDao.selectHisOrder(companyId, customerId);
  }

  /**
   * 初始化策略数据
   */
  private void setOrder(Order order, CreateOrderDto createOrderDto) {
    //todo 订单号生成规则？
    order.setOrderNo("");
    order.setStockCode(createOrderDto.getStockCode());
    order.setStockName(createOrderDto.getStockName());
    order.setOrderType(createOrderDto.getOrderType());
    order.setTradeNumber(createOrderDto.getTradeNumber());
    order.setTradeMoney(createOrderDto.getTradeMoney());
    order.setMoney(createOrderDto.getMoney());
    order.setMoney(createOrderDto.getBuyPrice());
    order.setStopPrice(createOrderDto.getStopPrice());
    order.setTargetPrice(createOrderDto.getTargetPrice());
    order.setCreateTime(DateUtils.getCurrent());
    order.setBuyType(StockConstants.INIT_BUY_TYPE);
    order.setOrderStatus(StockConstants.INIT_ORDER_TYPE);

  }

}
