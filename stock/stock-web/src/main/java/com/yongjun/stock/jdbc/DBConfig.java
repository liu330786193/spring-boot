package com.yongjun.stock.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.beans.PropertyVetoException;

/**
 * Created by lyl on 2017/10/29.
 */
@Configuration
public class DBConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "mysqlDataSource")
    public ComboPooledDataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(environment.getProperty("com.mysql.jdbc.Driver"));
        dataSource.setJdbcUrl(environment.getProperty("jdbc:mysql://rm-bp1g06730s0ieilj6o.mysql.rds.aliyuncs.com:3306/stock?useUnicode=true&amp;characterEncoding=UTF-8"));
        dataSource.setUser(environment.getProperty("huajinbao"));
        dataSource.setPassword(environment.getProperty("Zfjr83095520"));
        dataSource.setMaxPoolSize(20);
        dataSource.setMinPoolSize(5);
        dataSource.setInitialPoolSize(10);
        dataSource.setMaxIdleTime(300);
        dataSource.setAcquireIncrement(5);
        dataSource.setIdleConnectionTestPeriod(60);
        return dataSource;
    }

}
