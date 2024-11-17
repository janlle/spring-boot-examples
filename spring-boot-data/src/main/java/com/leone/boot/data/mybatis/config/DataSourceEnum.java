package com.leone.boot.data.mybatis.config;

/**
 * <p>
 *
 * @author leone
 * @since 2021-01-20
 **/
public enum DataSourceEnum {

    MASTER, SLAVE;

    String dataSourceName;

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }
}