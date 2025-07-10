package com.leone.boot.mybatis.runner;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;

public class DataFactory {

    public static void main(String[] args) throws SQLException {
        for (int i = 0; i < 10000; i++) {
            Db.use().insert(Entity.create("user2")
              .set("username", IdUtil.fastSimpleUUID())
              .set("password", IdUtil.nanoId())
              .set("gender", RandomUtil.randomInt(2))
              .set("phone", "159" + RandomUtil.randomNumbers(8))
              .set("create_time", RandomUtil.randomDay(1, 10))
              .set("deleted", 1)
            );
        }
    }

}
