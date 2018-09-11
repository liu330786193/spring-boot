package com.yongjun.stock.controller;

import com.yongjun.stock.entity.Activity;
import com.yongjun.stock.entity.Config;
import com.yongjun.stock.pojo.dto.BaseRequestDto;
import com.yongjun.stock.pojo.dto.app.VersionDto;
import com.yongjun.stock.service.jdbc.ActivityService;
import com.yongjun.stock.service.jdbc.ConfigService;
import com.yongjun.stock.service.jdbc.VersionService;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 版本信息相关接口
 * Created by 姚磊 on 2017/10/26.
 */
@Controller
@RequestMapping("/app")
public class AppController {

  @Resource
  private VersionService versionService;

  @Resource
  private ActivityService activityService;

  @Resource
  private ConfigService configService;

  /**
   * 检测新版本
   */
  @ResponseBody
  @RequestMapping(value = "/checkUpdate", method = RequestMethod.POST)
  private Map<String, Object> checkUpdate(@RequestBody VersionDto versionDto) {
    return versionService
        .getVersionInfo(versionDto.getAppVersion(), versionDto.getAppName(), versionDto.getAppOs());
  }

  /**
   * 获取活动图片
   * @param baseRequestDto
   */
  @ResponseBody
  @RequestMapping(value = "/getActivityImgs", method = RequestMethod.POST)
  private List<Activity> getActivityImgs(@RequestBody BaseRequestDto baseRequestDto){

    return activityService.getAcitivityByCompany(baseRequestDto.getCompanyId());

  }

  /**
   * 获取活动图片
   * @param baseRequestDto
   */
  @ResponseBody
  @RequestMapping(value = "/getConfig", method = RequestMethod.POST)
  private List<Config> getConfig(@RequestBody BaseRequestDto baseRequestDto){

    return configService.getConfigByCompany(baseRequestDto.getCompanyId());

  }

}
