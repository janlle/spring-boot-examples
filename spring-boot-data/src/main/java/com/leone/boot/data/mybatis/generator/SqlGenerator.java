package com.leone.boot.data.mybatis.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;

/**
 * <p>
 *
 * @author leone
 * @since 2024-11-20
 **/
public class SqlGenerator {

    private static final Logger logger = LoggerFactory.getLogger(SqlGenerator.class);
    public static Map<String, String> map = new HashMap<>();

    static {
        map.put("class java.lang.String", "varchar(255)");
        map.put("class java.lang.Integer", "int");
        map.put("class java.lang.Long", "bigint");
        map.put("class java.lang.byte[]", "blob");
        map.put("class java.lang.Boolean", "bit");
        map.put("class java.math.BigInteger", "bigint");
        map.put("class java.lang.Float", "float");
        map.put("class java.lang.Double", "double");
        map.put("class java.sql.Date", "datetime");
        map.put("class java.sql.Time", "time");
        map.put("class java.sql.Timestamp", "datetime");
        map.put("class java.util.Date", "datetime");
        map.put("class java.time.LocalDateTime", "datetime");
        map.put("class java.time.LocalDate", "date");
        map.put("class java.time.LocalTime", "time");
        map.put("class java.lang.Byte", "tinyint");
        map.put("class java.math.BigDecimal", "decimal(18, 6)");
    }

    public static void main(String[] args) {
        //表命名前缀
        String tablePrefix = "";
        //实体类所在的package在磁盘上的绝对路径
        String packageName = "/Users/leone/Documents/Workspaces/Java/spring-boot-examples/spring-boot-data/src/main/java/com/leone/boot/data/mybatis/entity";
        String prefix = packageName.substring(packageName.indexOf("java") + 5).replace("/", ".") + ".";
        sqlConstruction(packageName, prefix, tablePrefix);
    }

    public static Field[] getAllFields(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        return fieldList.toArray(fields);
    }

    /**
     * 生成sql建库语句
     *
     * @param tablePrefix 表命名前缀
     * @param packageName 实体类所在的package在磁盘上的绝对路径
     * @param prefix      项目中实体类的路径
     */
    private static void sqlConstruction(String packageName, String prefix, String tablePrefix) {
        String className;
        StringBuffer allSql = new StringBuffer();
        List<String> list = getAllClasses(packageName);
        for (String str : Objects.requireNonNull(list)) {
            if (str.equals("BaseEntity.java")) {
                continue;
            }
            className = prefix + str.substring(0, str.lastIndexOf("."));
            String sql = generateSql(className, tablePrefix);
            allSql.append(sql);
        }
        System.out.println(allSql);
    }


    public static String generateSql(String className, String tablePrefix) {
        try {
            Class<?> clz = Class.forName(className);
            className = clz.getSimpleName();
            // 表表名adminUser → tb_admin_user
            className = tablePrefix + getStandardFields(className);
            Field[] fields = getAllFields(clz);
            StringBuilder column = new StringBuilder();
            String varchar = " default null,";
            for (Field f : fields) {
                if (f.getName().equalsIgnoreCase("serialVersionUID")) {
                    continue;
                }
                if ("class java.lang.String".equals(f.getType().toString())) {
                    column.append("\n\t").append(getStandardFields(f.getName())).append(" ").append(map.get(f.getType().toString())).append(varchar);
                } else if (f.getName().equalsIgnoreCase("id")) {
                    column.append("\n\t").append(getStandardFields(f.getName())).append(" ").append("bigint not null auto_increment,");
                } else {
                    column.append("\n\t").append(getStandardFields(f.getName())).append(" ").append(map.get(f.getType().toString())).append(",");
                }
            }
            //已单独指定id列的生成语句，去掉多余id的拼接
            String columnSql = column.substring(column.indexOf(",") + 1);
            StringBuilder sql = new StringBuilder();
            sql.append("\nDROP TABLE IF EXISTS ").append(className).append(";")
              .append("\nCREATE TABLE ").append(className).append(" (")
              .append(columnSql)
              .append("\n\tPRIMARY KEY (id)")
              .append("\n) ENGINE=InnoDB DEFAULT CHARSET=UTF8;");
            return sql.toString();
        } catch (ClassNotFoundException e) {
            logger.error("该类未找到！");
            return null;
        }

    }

    /**
     * 转换为标准等sql字段 例如 adminUser → admin_user
     *
     * @param str 转换为字符串的字段名
     */
    public static String getStandardFields(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            //非首字母，在大写字母A到Z之间
            if (i != 0 && (c >= 'A' && c <= 'Z')) {
                sb.append("_");
            }
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 获取包下面等所有实体类名称，类似于获取 XXX.java
     *
     * @param packageName 全类名
     */
    public static List<String> getAllClasses(String packageName) {
        List<String> classList = new ArrayList<>();
        String className = "";
        File f = new File(packageName);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            // 遍历实体类下面等所有.java文件 获取其类名
            for (File file : Objects.requireNonNull(files)) {
                className = file.getName();
                classList.add(className);
            }
            return classList;
        } else {
            logger.debug("包路径未找到！");
            return null;
        }
    }


}