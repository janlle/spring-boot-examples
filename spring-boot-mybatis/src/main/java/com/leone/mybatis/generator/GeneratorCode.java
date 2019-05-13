package com.leone.mybatis.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-13
 **/
public class GeneratorCode {

    public void generator() throws Exception {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        // 指定配置文件
        File configFile = new File("/mybatis/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    // 执行main方法以生成代码
    public static void main(String[] args) {
        System.out.println(GeneratorCode.class.getResource("/mybatis/generatorConfig.xml"));
        try {
            GeneratorCode generatorCode = new GeneratorCode();
            generatorCode.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}