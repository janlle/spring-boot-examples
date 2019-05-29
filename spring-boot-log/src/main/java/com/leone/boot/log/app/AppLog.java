package com.leone.boot.log.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leone.boot.log.app.entity.*;

import java.util.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-20
 **/
public abstract class AppLog {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String url = "http://localhost:8080/coll/index";

    private static Random random = new Random();

    private static String appId = "sdk34734";

    private static String[] tenantIds = {"cake"};

    private static String[] deviceIds = initDeviceId();

    private static String[] appVersions = {"3.2.1", "3.2.2"};

    private static String[] appChannels = {"youmeng1", "youmeng2"};

    private static String[] appPlatforms = {"android", "ios", "winPhone"};

    // 国家，终端不用上报，服务器自动填充该属性
    private static String[] country = {"America", "china", "England", "Australian"};

    // 省份，终端不用上报，服务器自动填充该属性
    private static String[] provinces = {"Washington", "guangzhou", "beijing", "hangzhou", "shanghai", "wuhan", "chongqing"};

    // 网络
    private static String[] networks = {"WiFi", "CellNetwork"};

    // 运营商
    private static String[] carriers = {"中国移动", "中国电信", "EE", "中国联通"};

    // 机型
    private static String[] deviceStyles = {"iPhoneX", "iPhone 6 Plus", "红米手机1s", "华为P20Pro"};

    // 分辨率
    private static String[] screenSizes = {"1136*640", "960*640", "480*320"};

    // 操作系统
    private static String[] osTypes = {"8.3", "7.1.1", "4.0.5", "5.2.0"};

    // 品牌
    private static String[] brands = {"三星", "华为", "Apple", "魅族", "小米", "锤子"};

    // 事件唯一标识
    private static String[] eventIds = {"popMenu", "autoImport", "BookStore"};

    static Map<String, String> map1 = new HashMap<String, String>() {
        {
            put("key1", "value1");
            put("key2", "value2");
        }
    };

    static Map<String, String> map2 = new HashMap<String, String>() {
        {
            put("key3", "value3");
            put("key4", "value4");
        }
    };

    private static Map[] params = {map1, map2};

    // 异常原因
    private static String[] errorBriefs = {"java.lang.ArrayIndexOutOfBoundsException", "java.lang.NullPointerException", "java.lang.Error: Unresolved compilation problem", "java.lang.IndexOutOfBoundsException", "java.lang.UnsupportedClassVersionError", "java.lang.NoClassDefFoundError", "java.lang.NoSuchFieldError", "java.lang.OutOfMemoryError", "java.lang.StackOverflowError", "java.lang.ClassNotFoundException"};        //错误摘要

    // 异常介绍
    private static String[] errorDetails = {"违法访问错误。当一个应用试图访问、修改某个类的域（Field）或者调用其方法", "类循环依赖错误。在初始化一个类时，若检测到类之间循环依赖", "断言错。用来指示一个断言失败的情况。", "抽象方法错误。当应用试图调用抽象方法时抛出。", "方法未找到异常", "类格式错误。当Java虚拟机试图从一个文件中读取Java类", "初始化程序错误。当执行一个类的静态初始化程序的过程中，发生了异常时抛出"};

    // 页面id
    private static String[] pageIds = {"list.html", "main.html", "test.html", "info.html", "goods.html", "search.html", "goodsInfo.html", "shoppingCar.html"};

    // 访问顺序号，0为第一个页面
    private static int[] visitIndexArr = {0, 1, 2, 3, 4, 5, 6, 7, 8};

    // 下一个访问页面，如为空则表示为退出应用的页面
    private static String[] nextPages = {"list.html", "main.html", "test.html", "info.html", "goods.html", "search.html", "goodsInfo.html", "shoppingCar.html"};

    /**
     * 获取随机数组中的元素
     *
     * @param array
     * @return
     */
    public static Object randomArray(Object[] array) {
        if (array == null || array.length < 1) {
            return null;
        }
        return array[random.nextInt(array.length)];
    }

    private static String[] initDeviceId() {
        String base = "device_";
        String[] result = new String[100];
        for (int i = 0; i < 100; i++) {
            result[i] = base + String.format("%06d", i);
        }
        return result;
    }

    /**
     * 启动相关信息的数组
     *
     * @param count
     * @return
     */
    private static List<AppStartupLog> appStartupLogs(int count) {
        List<AppStartupLog> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            AppStartupLog log = new AppStartupLog();
            log.setCountry(randomArray(country).toString());
            log.setProvince(randomArray(provinces).toString());
            log.setNetwork(randomArray(networks).toString());
            log.setCarrier(randomArray(carriers).toString());
            log.setDeviceStyle(randomArray(deviceStyles).toString());
            log.setScreenSize(randomArray(screenSizes).toString());
            log.setOsType(randomArray(osTypes).toString());
            log.setBrand(randomArray(brands).toString());
            log.setCreatedAtMs(System.currentTimeMillis());
            result.add(log);
        }
        return result;
    }

    /**
     * 页面跳转相关信息的数组
     *
     * @return
     */
    private static List<AppPageLog> appPageLogs(int count) {
        List<AppPageLog> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            AppPageLog log = new AppPageLog();
            String pageId = randomArray(pageIds).toString();
            int visitIndex = visitIndexArr[random.nextInt(visitIndexArr.length)];
            String nextPage = randomArray(nextPages).toString();
            while (pageId.equals(nextPage)) {
                nextPage = randomArray(nextPages).toString();
            }
            log.setPageId(pageId);
            log.setStandingTime(random.nextInt(1000));
            log.setVisitIndex(visitIndex);
            log.setNextPage(nextPage);
            log.setCreatedAtMs(System.currentTimeMillis());
            result.add(log);
        }
        return result;
    }

    /**
     * 事件相关信息的数组
     *
     * @return
     */
    private static List<AppEventLog> appEventLogs(int count) {
        List<AppEventLog> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            AppEventLog log = new AppEventLog();
            log.setEventId(eventIds[random.nextInt(eventIds.length)]);
            log.setParams(params[randomInt(params)]);
            log.setEventDurationTime(random.nextInt(10));
            log.setCreatedAtMs(System.currentTimeMillis());
            result.add(log);
        }
        return result;
    }

    /**
     * app使用情况相关信息的数组
     *
     * @return
     */
    private static List<AppUsageLog> appUsageLogs(int count) {
        List<AppUsageLog> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            AppUsageLog log = new AppUsageLog();
            log.setSingleUseDurationTime(random.nextInt(Integer.MAX_VALUE));
            log.setCreatedAtMs(System.currentTimeMillis());
            result.add(log);
        }
        return result;
    }

    ;

    // 错误相关信息的数组
    private static List<AppErrorLog> appErrorLogs(int count) {
        List<AppErrorLog> result = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            AppErrorLog log = new AppErrorLog();
            log.setErrorBrief(errorBriefs[randomInt(errorBriefs)]);
            log.setErrorDetail(errorDetails[randomInt(errorDetails)]);
            log.setCreatedAtMs(System.currentTimeMillis());
            log.setOsType(osTypes[random.nextInt(osTypes.length)]);
            log.setDeviceStyle(deviceStyles[random.nextInt(deviceStyles.length)]);
            result.add(log);
        }
        return result;
    }

    public static void main(String[] args) {
        String s = randomAppLog();
        System.out.println(s);
    }

    /**
     * 根据数据大小生成随机数
     *
     * @param arr
     * @return
     */
    private static int randomInt(Object[] arr) {
        return random.nextInt(arr.length);
    }

    /**
     * 生成jsonLog
     *
     * @return
     */
    public static String randomAppLog() {
        try {
            AppLogEntity logEntity = new AppLogEntity();
            logEntity.setAppChannel(appChannels[randomInt(appChannels)]);
            logEntity.setAppId(appId);
            logEntity.setAppPlatform(appPlatforms[randomInt(appPlatforms)]);
            logEntity.setAppVersion(appVersions[randomInt(appVersions)]);
            logEntity.setTenantId(tenantIds[randomInt(tenantIds)]);
            logEntity.setDeviceId(deviceIds[randomInt(deviceIds)]);
            // startup log集合
            logEntity.setAppStartupLogs(appStartupLogs(1));
            logEntity.setAppEventLogs(appEventLogs(1));
            logEntity.setAppErrorLogs(appErrorLogs(1));
            logEntity.setAppPageLogs(appPageLogs(1));
            logEntity.setAppUsageLogs(appUsageLogs(1));
            return objectMapper.writeValueAsString(logEntity);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

}
