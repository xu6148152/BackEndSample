package com.binea.config;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * Created by binea
 * Date: 29/10/2017
 * TIME: 6:13 PM
 */
public abstract class DataSourceBaseConfig {
    protected DataSource createDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(getDriverClass());
        dataSource.setUrl(getUrl());
        dataSource.setUsername(getUser());
        dataSource.setPassword(getPassword());
        return dataSource;
    }

    protected abstract String getDriverClass();

    protected abstract String getUrl();

    protected abstract String getUser();

    protected abstract String getPassword();
}
