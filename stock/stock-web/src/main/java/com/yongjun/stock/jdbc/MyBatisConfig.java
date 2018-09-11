package com.yongjun.stock.jdbc;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by lyl on 2017/10/29.
 */
@Configuration
@ComponentScan
public class MyBatisConfig {

    @Autowired
    private DataSource mysqlDataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(ApplicationContext applicationContext) throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(mysqlDataSource);
        bean.setPlugins(new Interceptor[]{new PageInterceptor()});
        bean.setMapperLocations(applicationContext.getResources("classpath*:mapper/*.xml"));
        return bean;
    }

}
