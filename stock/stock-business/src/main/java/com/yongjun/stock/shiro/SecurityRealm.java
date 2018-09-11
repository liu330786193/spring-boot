package com.yongjun.stock.shiro;

import com.yongjun.stock.dao.CustomerDao;
import com.yongjun.stock.entity.Customer;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lyl on 2017-05-11.
 */
@Component(value = "securityRealm")
public class SecurityRealm extends AuthorizingRealm {

	@Autowired
	private CustomerDao customerDao;

	private static Logger logger = LoggerFactory.getLogger(SecurityRealm.class);

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
		logger.info("验证当前Subject获取到的token：" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(token.getUsername());
		//用户是否存在
		System.out.println("username:" + token.getUsername() + "password:" + StringUtils.join(token.getPassword()));
		Customer customer = customerDao.findCustomerLoginInfo(token.getUsername());
		if (customer != null){
			return new SimpleAuthenticationInfo(customer.getPhoneNo(), customer.getPassword(), getName());
		}
		return null;
	}

}
