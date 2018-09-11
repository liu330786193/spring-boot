package com.yongjun.stock.controller;

import com.github.pagehelper.PageHelper;
import com.yongjun.stock.dao.CustomerDao;
import com.yongjun.stock.entity.Customer;
import com.yongjun.stock.pojo.page.PageBean;
import com.yongjun.stock.request.LoginRequest;
import com.yongjun.stock.service.rabbitmq.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lyl on 2017/10/24.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    /*@Autowired
    private SecurityManager securityManager;*/

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private Producer producer;

    /*@Autowired
    private MongodbService mongodbService;*/

//    @Value("${rabbitmq.queue}")
//    private String stockQueue;
//
//    @Value("${rabbitmq.exchange}")
//    private String stockQueueExchange;
//
//    @Value("${rabbitmq.pattern}")
//    private String stockQueuePattern;

    /**
     * Comment is created by lyl on 2017/10/24 下午4:43.
     *
     * 返回用户的基本信息
     */
    /*@ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private Boolean login(@RequestBody LoginRequest request){
        System.out.println(request);
        UsernamePasswordToken token = new UsernamePasswordToken(request.getUsername(), request.getPassword());
        String username = token.getUsername();
        token.setRememberMe(true);
        System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        //应为调用的静态方法 所以需要这里设置securityManager
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            System.out.println("对用户[" + username + "]进行登录验证..验证开始");
            currentUser.login(token);
            System.out.println("对用户[" + username + "]进行登录验证..验证通过");
            RedisUtils.put("lyl:abc","百尺竿头");
            return true;
        }catch(UnknownAccountException uae){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
            throw CExceptionFactory.create("BASESTOCK_LOGIN_1201");
        }catch(IncorrectCredentialsException ice){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
            throw CExceptionFactory.create("BASESTOCK_LOGIN_1202");
        }catch(LockedAccountException lae){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
            throw  CExceptionFactory.create("BASESTOCK_LOGIN_1203");
        }catch(ExcessiveAttemptsException eae){
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
            throw  CExceptionFactory.create("BASESTOCK_LOGIN_1204");
        }catch(AuthenticationException ae){
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
            ae.printStackTrace();
            throw CExceptionFactory.create("BASESTOCK_LOGIN_1205");
        }
    }*/

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    private PageBean<Customer> info(@RequestBody LoginRequest request){
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        return new PageBean<Customer>(customerDao.findCustomerInfo());
    }

    /**
     * Comment is created by lyl on 2017/10/26 上午11:18.
     *
     * 测试消息队列
     */
    @ResponseBody
    @RequestMapping(value = "/mq", method = RequestMethod.POST)
    private Boolean mq(){
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", "hello rabbitmq");
            producer.sendQueue("stock_queue_exchange", "stock_queue_pattern", map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Comment is created by lyl on 2017/10/26 上午11:18.
     *
     * 测试mongodb
     */
    /*@ResponseBody
    @RequestMapping(value = "/mongodb/insert", method = RequestMethod.POST)
    private Boolean insertMongodb(){
        mongodbService.insert();
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/mongodb/find", method = RequestMethod.POST)
    private List<Customer> findMongodb(){
        return mongodbService.find();
    }*/

    @ResponseBody
    @RequestMapping(value = "/boot", method = RequestMethod.POST)
    private String boot(){
        System.out.println("进入了方法");
        return "ddddd";
    }

}

