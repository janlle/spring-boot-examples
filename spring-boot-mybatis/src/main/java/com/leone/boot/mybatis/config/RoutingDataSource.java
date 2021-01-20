package com.leone.boot.mybatis.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>
 *
 * @author leone
 * @since 2021-01-20
 **/
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceContextHolder.get();
    }

}