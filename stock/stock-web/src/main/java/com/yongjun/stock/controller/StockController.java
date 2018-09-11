package com.yongjun.stock.controller;

import com.yongjun.stock.constants.StockConstants;
import com.yongjun.stock.entity.StockBlack;
import com.yongjun.stock.entity.StockRecommend;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.pojo.dto.BaseRequestDto;
import com.yongjun.stock.pojo.dto.stock.CheckStockDto;
import com.yongjun.stock.pojo.dto.stock.SearchStockDto;
import com.yongjun.stock.service.jdbc.StockBlackService;
import com.yongjun.stock.service.jdbc.StockRecommendService;
import com.yongjun.stock.service.jdbc.StockService;
import com.yongjun.stock.util.RedisUtils;
import com.yongjun.stock.util.StringUtils;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 股票相关接口
 * Created by 姚磊 on 2017/10/26.
 */
@Controller
@RequestMapping("/stock")
public class StockController {

  @Resource
  private StockRecommendService stockRecommendService;

  @Resource
  private StockService stockService;

  @Value("${companyId}")
  private Long companyId;

  /**
   * 搜索股票
   */
  @ResponseBody
  @RequestMapping(value = "/searchStock", method = RequestMethod.POST)
  private void searchStock(@RequestBody SearchStockDto searchStockDto) {
    // todo 根据searchkey查询股票行情，list.size()==1 ,模糊查询,并忽略字符大小写，
    // todo 如果list.size()>1 则根据stockCode精确查询

  }

  /**
   * 查询用户历史搜索记录+推荐股票
   */
  @ResponseBody
  @RequestMapping(value = "/getStockHis", method = RequestMethod.POST)
  private void getStockHis(@RequestBody SearchStockDto searchStockDto) {
    // todo 根据searchkey的list的stockCode查询股票行情

    List<StockRecommend> stockRecommends = stockRecommendService.getStockRecommend(companyId);

    // todo 根据stockRecommends的list的stockCode查询股票行情

    // todo 2个行情成2个对象合并输出到前端

  }

  /**
   * 查询热门推荐股票
   */
  @ResponseBody
  @RequestMapping(value = "/getHotStock", method = RequestMethod.POST)
  private void getHotStock(@RequestBody BaseRequestDto baseRequestDto) {

    List<StockRecommend> stockRecommends = stockRecommendService.getStockRecommend(companyId);

    // todo 根据stockRecommends的list的stockCode查询股票行情

  }

  /**
   * 查询股票是否可购买
   */
  @ResponseBody
  @RequestMapping(value = "/checkStock", method = RequestMethod.POST)
  private Boolean checkStock(@RequestBody CheckStockDto checkStockDto) {
    try {
      stockService.canBuy(checkStockDto.getStockCode());

    } catch (CBusinessException e) {
      throw e;
    }
    return true;

  }


}
