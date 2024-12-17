package com.leone.boot.mybatisplus.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;
import java.util.Map;

/**
 * <p> 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 *
 * @author leone
 * @since 2019-05-13
 **/
public class CodeGenerator {

    public static void main(String[] args) {
        String outputPath = Paths.get(System.getProperty("user.home")) + "/Downloads";
        System.out.println("Generate code path: " + outputPath);

        FastAutoGenerator.create("jdbc:mysql://" + System.getenv("mysql_host") + "/boot?useSSL=false",
            System.getenv("mysql_username"),
            System.getenv("mysql_password"))
          .globalConfig(builder -> builder
            .author(System.getenv("USER"))
            .commentDate("yyyy-MM-dd")
            .outputDir(outputPath)
          )
          .packageConfig(builder -> builder
            .parent("com.leone.xxx")
            .entity("entity")
            .mapper("mapper")
            .xml("mapper.xml")
            .service("service")
            .serviceImpl("service.impl")
          )
          .strategyConfig(builder -> builder
              // 表名
              .addInclude(
                "sys_user",
                "sys_role",
                "sys_permission"
              )
            //.addTablePrefix("t_")
          )
          .templateEngine(new FreemarkerTemplateEngine())
          .execute();
        System.out.println("Code generate finished...");
    }

}