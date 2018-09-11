package com.yongjun.stock.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by lyl on 2017/10/29.
 */
@Configuration
@ComponentScan
public class TransactionConfig {

    @Autowired
    private DataSource mysqlDataSource;

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(mysqlDataSource);
    }

}
