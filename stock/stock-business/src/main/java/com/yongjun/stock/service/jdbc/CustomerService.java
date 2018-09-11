package com.yongjun.stock.service.jdbc;

import com.yongjun.stock.entity.Customer;

import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.pojo.dto.customer.LoginDto;
import com.yongjun.stock.pojo.dto.customer.RegisterDto;
import java.util.List;

/**
 * Created by lyl on 2017/10/24.
 */
public interface CustomerService {

    /**
     * Comment is created by lyl on 2017/10/24 下午3:08.
     *
     * 测试
     */
    List<Customer> findCustomerInfo();

    /**
     * 根据查询条件查询客户信息
     * @param customer
     * @return
     */
    List<Customer> getCustomerByCondition(Customer customer);

    /**
     * 根据手机号查询客户信息
     * @param phone
     * @return
     */

    Customer getCustomerByPhone(String phone);

    /**
     * APP客户注册
     * @param registerDto
     * @return
     */
    Customer register(RegisterDto registerDto) throws CBusinessException;

    /**
     * 修改密码
     * @param phoneNo
     * @param oldPassword
     * @param newPassword
     * @return
     */
    int updatePwd(String phoneNo, String oldPassword, String newPassword);

    /**
     * 根据手机号更新客户信息
     * @param customer
     * @return
     */
    int updateByPhoneNo(Customer customer);


    /**
     * 登录
     * @return
     */
    Customer login(LoginDto loginDto) throws CBusinessException;

}
