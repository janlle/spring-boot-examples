package com.leone.boot.mvc.lock.mysql;

import com.leone.boot.mvc.lock.redis.DistributeLock;
import com.leone.boot.mvc.lock.zk.ZkLock;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * mysql 实现分布式锁的切面
 * 有三种方式
 * 1.基于唯一索引，加锁插入数据，释放锁删除数据
 * CREATE TABLE `t_lock` (
 * `lock_key` varchar(64) NOT NULL COMMENT '锁的标识',
 * PRIMARY KEY (`lock_key`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分布式锁'
 * 根据插入sql返回受影响的行数，大于0表示成功占有锁
 * insert ignore into t_lock(lock_key) values(:lockKey)
 * 释放锁的时候就删除记录
 * delete from t_lock where lock_key = lockKey
 * <p>
 * 2.悲观锁实现 select for update
 * 3.乐观锁实现 version 字段
 *
 * @author leone
 */
@Aspect
@Component
//@ConditionalOnBean(HikariDataSource.class)
public class DatabaseLockAspect {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLockAspect.class);

    private HikariDataSource dataSource;

    public DatabaseLockAspect(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Around("@annotation(com.leone.boot.mvc.lock.mysql.DatabaseLock)")
    public Object process(ProceedingJoinPoint jointPoint) throws Exception {
        log.info("mysql lock.");
        Object response = null;
        Method method = ((MethodSignature) jointPoint.getSignature()).getMethod();
        DatabaseLock databaseLock = method.getAnnotation(DatabaseLock.class);

        // 加锁 如果锁不存在则创建
        try (ResultSet query = query(String.format("select * from distribute_lock where lock_key = '%s' for update", databaseLock.key()))) {
            if (query == null || !query.next()) {
                exec(String.format("insert into distribute_lock(lock_key, expire_time) values('%s', %s)", databaseLock.key(), databaseLock.expireTime()));
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        // 锁存在
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            ResultSet query = connection.createStatement().executeQuery(String.format("select * from distribute_lock where lock_key = '%s' for update", databaseLock.key()));
            // 增加enter count
            if (query.next() && query.getString(1).equals(databaseLock.keyPrefix() + databaseLock.key())) {
                exec("update distribute_lock set enter_count = enter_count + 1 where id = " + query.getInt(0));
            }
            // 执行业务操作
            response = jointPoint.proceed();
        } catch (Exception ex) {
            log.error(ex.toString());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.commit();
                    connection.close();
                } catch (SQLException ex) {
                    log.error(ex.toString());
                }
            }
        }
        return response;
    }

    public ResultSet query(String sql) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            log.info("query: {}", sql);
            return conn.createStatement().executeQuery(sql);
        } catch (Exception e) {
            if (conn != null) {
                try {
                    conn.commit();
                } catch (SQLException ex) {
                    log.error(e.getMessage());
                }
            }
            log.error(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.commit();
                } catch (SQLException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return null;
    }

    public void exec(String sql) {
        Connection c = null;
        try {
            c = dataSource.getConnection();
            c.createStatement().execute(sql);
            log.info("exec: {}", sql);
        } catch (Exception e) {
            if (c != null) {
                try {
                    c.rollback();
                } catch (SQLException ex) {
                    log.error(e.getMessage());
                }
            }
            log.error(e.getMessage());
        } finally {
            try {
                if (c != null) {
                    c.commit();
                }
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
        }
    }

}
