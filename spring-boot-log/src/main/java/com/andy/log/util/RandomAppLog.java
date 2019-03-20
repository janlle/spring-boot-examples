package com.andy.log.util;

import com.andy.log.app.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *
 * @author leone
 * @since 2019-03-20
 **/
public class RandomAppLog {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String url = "http://localhost:8080/coll/index";

    private static Random random = new Random();

    private static String appId = "sdk34734";

    private static String[] tenantIds = {"cake"};

    private static String[] deviceIds = initDeviceId();

    private static String[] appVersions = {"3.2.1", "3.2.2"};

    private static String[] appChannels = {"youmeng1", "youmeng2"};

    private static String[] appPlatforms = {"android", "ios", "winPhone"};

    private static Long[] createdAtMsS = initCreatedAtMs();

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

    // 事件持续时长
    private static Long[] eventDurationSecsS = {12L, 23L, 5L, 59L, 129L, 290L, 600L, 10L};

    static Map<String, String> map1 = new HashMap<String, String>() {
        {
            put("testparam1key", "testparam1value");
            put("testparam2key", "testparam2value");
        }
    };
    static Map<String, String> map2 = new HashMap<String, String>() {
        {
            put("testparam3key", "testparam3value");
            put("testparam4key", "testparam4value");
        }
    };
    private static Map[] paramKeyValueMapsS = {map1, map2};

    //单次使用时长(秒数),指一次启动内应用在前台的持续时长
    private static Long[] singleUseDurationSecsS = initSingleUseDurationSecs();

    private static String[] errorBriefs = {"at cn.lift.dfdf.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)", "at cn.lift.appIn.control.CommandUtil.getInfo(CommandUtil.java:67)"};        //错误摘要

    private static String[] errorDetails = {"java.lang.NullPointerException\\n    " + "at cn.lift.appIn.web.AbstractBaseController.validInbound(AbstractBaseController.java:72)\\n " + "at cn.lift.dfdf.web.AbstractBaseController.validInbound", "at cn.lift.dfdfdf.control.CommandUtil.getInfo(CommandUtil.java:67)\\n " + "at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\\n" + " at java.lang.reflect.Method.invoke(Method.java:606)\\n"};        //错误详情

    // 页面id
    private static String[] pageIds = {"list.html", "main.html", "test.html"};

    // 访问顺序号，0为第一个页面
    private static int[] visitIndexArr = {0, 1, 2, 3, 4};

    // 下一个访问页面，如为空则表示为退出应用的页面
    private static String[] nextPages = {"list.html", "main.html", "test.html"};

    // 当前页面停留时长
    private static Long[] stayDurationSecsS = {45L, 23L, 59L, 129L, 290L};

    //启动相关信息的数组
    private static AppStartupLog[] appStartupLogs = initAppStartupLogs();

    // 页面跳转相关信息的数组
    private static AppPageLog[] appPageLogs = initAppPageLogs();

    // 事件相关信息的数组
    private static AppEventLog[] appEventLogs = initAppEventLogs();

    // app使用情况相关信息的数组
    private static AppUsageLog[] appUsageLogs = initAppUsageLogs();

    // 错误相关信息的数组
    private static AppErrorLog[] appErrorLogs = initAppErrorLogs();


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
        String base = "device22";
        String[] result = new String[100];
        for (int i = 0; i < 100; i++) {
            result[i] = base + i + "";
        }
        return result;
    }

    private static Long[] initCreatedAtMs() {
        Long createdAtMs = System.currentTimeMillis();
        Long[] result = new Long[11];
        for (int i = 0; i < 10; i++) {
            result[i] = createdAtMs - (long) (i * 24 * 3600 * 1000);
        }
        result[10] = createdAtMs;
        return result;
    }

    private static Long[] initSingleUseDurationSecs() {
        Random random = new Random();
        Long[] result = new Long[200];
        for (int i = 1; i < 200; i++) {
            result[i] = (long) random.nextInt(200);
        }
        return result;
    }

    // 启动相关信息的数组
    private static AppStartupLog[] initAppStartupLogs() {
        AppStartupLog[] result = new AppStartupLog[10];
        for (int i = 0; i < 10; i++) {
            AppStartupLog appStartupLog = new AppStartupLog();
            appStartupLog.setCountry(randomArray(country).toString());
            appStartupLog.setProvince(randomArray(provinces).toString());
            appStartupLog.setNetwork(randomArray(networks).toString());
            appStartupLog.setCarrier(randomArray(carriers).toString());
            appStartupLog.setDeviceStyle(randomArray(deviceStyles).toString());
            appStartupLog.setScreenSize(randomArray(screenSizes).toString());
            appStartupLog.setOsType(randomArray(osTypes).toString());
            appStartupLog.setBrand(randomArray(brands).toString());
//			appStartupLog.setCreatedAtMs(createdAtMsS[random.nextInt(createdAtMsS.length)]);
            appStartupLog.setCreatedAtMs(System.currentTimeMillis());
            result[i] = appStartupLog;
        }
        return result;
    }

    // 页面跳转相关信息的数组
    private static AppPageLog[] initAppPageLogs() {
        AppPageLog[] result = new AppPageLog[10];
        for (int i = 0; i < 10; i++) {
            AppPageLog appPageLog = new AppPageLog();
            String pageId = randomArray(pageIds).toString();
            int visitIndex = visitIndexArr[random.nextInt(visitIndexArr.length)];
            String nextPage = randomArray(nextPages).toString();
            while (pageId.equals(nextPage)) {
                nextPage = randomArray(nextPages).toString();
            }
            Long stayDurationSecs = stayDurationSecsS[random.nextInt(stayDurationSecsS.length)];

            appPageLog.setPageId(pageId);
            appPageLog.setStayDurationSecs(stayDurationSecs);
            appPageLog.setVisitIndex(visitIndex);
            appPageLog.setNextPage(nextPage);
//			appPageLog.setCreatedAtMs(createdAtMsS[random.nextInt(createdAtMsS.length)]);
            appPageLog.setCreatedAtMs(System.currentTimeMillis());
            result[i] = appPageLog;
        }
        return result;
    }

    ;

    // 事件相关信息的数组
    private static AppEventLog[] initAppEventLogs() {
        AppEventLog[] result = new AppEventLog[10];
        for (int i = 0; i < 10; i++) {
            AppEventLog appEventLog = new AppEventLog();
            appEventLog.setEventId(eventIds[random.nextInt(eventIds.length)]);
            appEventLog.setParamKeyValueMap(paramKeyValueMapsS[random.nextInt(paramKeyValueMapsS.length)]);
            appEventLog.setEventDurationSecs(eventDurationSecsS[random.nextInt(eventDurationSecsS.length)]);
//			appEventLog.setCreatedAtMs(createdAtMsS[random.nextInt(createdAtMsS.length)]);
            appEventLog.setCreatedAtMs(System.currentTimeMillis());
            result[i] = appEventLog;
        }
        return result;
    }

    ;

    // app使用情况相关信息的数组
    private static AppUsageLog[] initAppUsageLogs() {
        AppUsageLog[] result = new AppUsageLog[10];
        for (int i = 0; i < 10; i++) {
            AppUsageLog appUsageLog = new AppUsageLog();
            appUsageLog.setSingleUseDurationSecs(singleUseDurationSecsS[random.nextInt(singleUseDurationSecsS.length)]);
//			appUsageLog.setCreatedAtMs(createdAtMsS[random.nextInt(createdAtMsS.length)]);
            appUsageLog.setCreatedAtMs(System.currentTimeMillis());
            result[i] = appUsageLog;
        }
        return result;
    }

    ;

    // 错误相关信息的数组
    private static AppErrorLog[] initAppErrorLogs() {
        AppErrorLog[] result = new AppErrorLog[10];
        for (int i = 0; i < 10; i++) {
            AppErrorLog appErrorLog = new AppErrorLog();
            appErrorLog.setErrorBrief(errorBriefs[random.nextInt(errorBriefs.length)]);
            appErrorLog.setErrorDetail(errorDetails[random.nextInt(errorDetails.length)]);
//			appErrorLog.setCreatedAtMs(createdAtMsS[random.nextInt(createdAtMsS.length)]);
            appErrorLog.setCreatedAtMs(System.currentTimeMillis());
            appErrorLog.setOsType(osTypes[random.nextInt(osTypes.length)]);
            appErrorLog.setDeviceStyle(deviceStyles[random.nextInt(deviceStyles.length)]);
            result[i] = appErrorLog;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = randomAppLog();
        System.out.println(s);
    }

    public static String randomAppLog() {

        AppLogEntity logEntity = new AppLogEntity();

        // 渠道
        logEntity.setAppChannel(appChannels[random.nextInt(appChannels.length)]);

        // appId
        logEntity.setAppId(appId);

        //platform
        logEntity.setAppPlatform(appPlatforms[random.nextInt(appPlatforms.length)]);
        logEntity.setAppVersion(appVersions[random.nextInt(appVersions.length)]);
        String tenantId = tenantIds[random.nextInt(tenantIds.length)];
        logEntity.setTenantId(tenantId);
        logEntity.setTenantId(tenantIds[random.nextInt(tenantIds.length)]);
        logEntity.setDeviceId(deviceIds[random.nextInt(deviceIds.length)]);

        //startup log集合
        logEntity.setAppStartupLogs(new AppStartupLog[]{appStartupLogs[random.nextInt(appStartupLogs.length)]});
        logEntity.setAppEventLogs(new AppEventLog[]{appEventLogs[random.nextInt(appEventLogs.length)]});
        logEntity.setAppErrorLogs(new AppErrorLog[]{appErrorLogs[random.nextInt(appErrorLogs.length)]});
        logEntity.setAppPageLogs(new AppPageLog[]{appPageLogs[random.nextInt(appPageLogs.length)]});
        logEntity.setAppUsageLogs(new AppUsageLog[]{appUsageLogs[random.nextInt(appUsageLogs.length)]});
        try {
            for (AppBaseLog log : logEntity.getAppStartupLogs()) {
                log.setCreatedAtMs(System.currentTimeMillis());
            }
            for (AppBaseLog log : logEntity.getAppErrorLogs()) {
                log.setCreatedAtMs(System.currentTimeMillis());
            }
            for (AppBaseLog log : logEntity.getAppEventLogs()) {
                log.setCreatedAtMs(System.currentTimeMillis());
            }
            for (AppBaseLog log : logEntity.getAppPageLogs()) {
                log.setCreatedAtMs(System.currentTimeMillis());
            }
            for (AppBaseLog log : logEntity.getAppUsageLogs()) {
                log.setCreatedAtMs(System.currentTimeMillis());
            }
            return objectMapper.writeValueAsString(logEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
