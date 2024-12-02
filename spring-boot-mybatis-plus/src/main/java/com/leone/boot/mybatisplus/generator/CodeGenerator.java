package com.leone.boot.mybatisplus.generator;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

/**
 * <p> 执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
 *
 * @author leone
 * @since 2019-05-13
 **/
public class CodeGenerator {

    public static void main(String[] args) {
        String outputPath = Paths.get(System.getProperty("user.home")) + "/Downloads/java";

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/boot?useSSL=false", "root", "123456")
          .globalConfig(builder -> builder
            .author("leone")
            .commentDate("yyyy-MM-dd")
            .outputDir(outputPath)
          )
          .packageConfig(builder -> builder
              .parent("com.leone.boot.mybatisplus")
              .entity("entity")
              .mapper("mapper")
              .xml("mapper.xml")
            //.service("service")
            //.serviceImpl("service.impl")
          )
          .strategyConfig(builder -> builder
            // 表名
            .addInclude(
              "goods"
            )
          )
          .templateEngine(new FreemarkerTemplateEngine())
          .execute();
        System.out.println("GenerateCode: " + outputPath);
    }

}