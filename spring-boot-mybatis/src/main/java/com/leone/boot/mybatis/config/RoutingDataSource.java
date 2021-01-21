package com.leone.boot.mybatis.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * <p>
 *
 * @author leone
 * @since 2021-01-20
 **/
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DatasourceContextHolder.get();
    }

}