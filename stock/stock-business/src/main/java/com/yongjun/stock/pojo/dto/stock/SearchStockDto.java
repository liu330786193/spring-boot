package com.yongjun.stock.pojo.dto.stock;

import com.yongjun.stock.pojo.dto.BaseRequestDto;
import java.util.List;
import lombok.Data;

/**
 * 查询股票行情请求参数
 * Created by 姚磊 on 2017/10/28.
 */
@Data
public class SearchStockDto extends BaseRequestDto {

  private List<String> searchKey;

}
