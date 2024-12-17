package com.leone.boot.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p> 根据实体生成sql建表语句，打印到控制台
 *
 * @author leone
 * @since 2024-11-20
 **/
public class SqlGeneratorUtil {

    private static final Logger logger = LoggerFactory.getLogger(SqlGeneratorUtil.class);
    public static Map<String, String> map = new HashMap<>();
    private static final String NEWLINE_AND_TAB = "\n\t";

    static {
        map.put("java.lang.String", "varchar(255)");
        map.put("java.lang.Integer", "int");
        map.put("java.lang.Long", "bigint");
        map.put("java.lang.Boolean", "bit");
        map.put("java.math.BigInteger", "bigint");
        map.put("java.lang.Float", "float");
        map.put("java.lang.Double", "double");
        map.put("java.util.Date", "datetime");
        map.put("java.time.LocalDate", "date");
        map.put("java.time.LocalTime", "time");
        map.put("java.time.LocalDateTime", "datetime");
        map.put("java.math.BigDecimal", "decimal(18, 6)");
    }

    public static void main(String[] args) {
        // 实体类所在的package在磁盘上的绝对路径
        String packageName = "/Users/leone/Documents/Workspaces/Java/spring-boot-examples/spring-boot-mybatis-plus/src/main/java/com/leone/boot/mybatisplus/entity";
        String prefix = packageName.substring(packageName.indexOf("java") + 5).replace("/", ".") + ".";
        logger.info("packageName: {}", prefix);
        sqlConstruction(packageName, prefix, "");
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
                if ("java.lang.String".equals(f.getType().getName())) {
                    column.append(NEWLINE_AND_TAB).append(getStandardFields(f.getName())).append(" ").append(map.get(f.getType().getName())).append(varchar);
                } else if (f.getName().equalsIgnoreCase("id")) {
                    column.append(NEWLINE_AND_TAB).append(getStandardFields(f.getName())).append(" ").append("bigint primary key auto_increment,");
                } else {
                    column.append(NEWLINE_AND_TAB).append(getStandardFields(f.getName())).append(" ").append(map.get(f.getType().getName())).append(",");
                }
            }
            StringBuilder sql = new StringBuilder();
            sql.append("\n")
              .append("DROP TABLE IF EXISTS ")
              .append(className)
              .append(";")
              .append("\n")
              .append("CREATE TABLE ")
              .append(className)
              .append(" (")
              .append(column.deleteCharAt(column.toString().lastIndexOf(",")))
              .append("\n")
              .append(") ENGINE=InnoDB DEFAULT CHARSET=UTF8;");
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
        File f = new File(packageName);
        if (f.exists() && f.isDirectory()) {
            return Arrays.stream(Objects.requireNonNull(f.listFiles())).toList().stream().map(File::getName).collect(Collectors.toList());
        } else {
            logger.error("包路径未找到！");
            return null;
        }
    }


}