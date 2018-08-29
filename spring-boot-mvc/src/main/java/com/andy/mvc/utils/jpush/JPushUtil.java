package com.andy.mvc.utils.jpush;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: lyon
 * @since: 2018-07-31
 **/
@Slf4j
public class JPushUtil {

    private static final String MASTER_SECRET = "18f7c13d5d082d31bb49bc5f";

    private static final String APP_KEY = "dbc2e9c8367279232f473da4";

    private static JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);

    public static void main(String[] args) {
        sendToRegistrationId("1232112212", "hello", "hello jiGuang", "hello content", "hello world");
    }

    /**
     * 推送给设备标识参数的用户
     *
     * @param registrationId     设备标识
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     * @param extrasparam        扩展字段
     * @return 0推送失败，1推送成功
     */
    public static int sendToRegistrationId(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam) {
        int result = 0;
        try {
            PushPayload pushPayload = JPushUtil.buildPushObject_all_registrationId_alertWithTitle(registrationId,
                    notification_title, msg_title, msg_content, extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static PushPayload buildPushObject_all_registrationId_alertWithTitle(String registrationId,
                                                                                 String notification_title, String msg_title, String msg_content, String extrasparam) {

        // 创建一个IosAlert对象，可指定APNs的alert、title等字段
        // IosAlert iosAlert = IosAlert.newBuilder().setTitleAndBody("title",
        // "alert body").build();
        return PushPayload.newBuilder()
                // 指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                // 指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration
                // id
                .setAudience(Audience.registrationId(registrationId))
                // jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        // 指定当前推送的android通知
                        .addPlatformNotification(AndroidNotification.newBuilder().setAlert(msg_title)
                                .setTitle(notification_title)
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("sss", extrasparam).build())
                        // 指定当前推送的iOS通知
                        .addPlatformNotification(IosNotification.newBuilder()
                                // 传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(msg_title)
                                // 直接传alert
                                // 此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                // 此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                // 此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("schema", extrasparam)
                                .setContentAvailable(true)
                                .build())
                        // 此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
                        // 取消此注释，消息推送时ios将无法在锁屏情况接收
                        // .setContentAvailable(true)
                        .build())
                // Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()

                        .setMsgContent(msg_content)

                        .setTitle(msg_title)

                        .addExtra("message extras key", extrasparam)

                        .build())
                .setOptions(Options.newBuilder()
                        // 此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        // 此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        // 此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天；
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    public void toJpushToUser(Integer type, String msg, JsonObject jsonObject, Integer... userIds) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        //设置接收对象为别名类型
        if (null != userIds) {
            String[] aliases = new String[userIds.length];
            for (int i = 0; i < userIds.length; i++) {
                aliases[i] = "jp" + userIds[i];
            }
            builder.setAudience(Audience.alias(aliases));
            //设置平台类型为所有
            builder.setPlatform(Platform.all());
            //设置APNs的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发），导致生产环境IOS收不到推送
            builder.setOptions(Options.newBuilder()
                    .setApnsProduction(true)
                    .build());

            builder.setNotification(Notification.newBuilder()
                    //通知内容
                    .setAlert(msg)
                    //通知平台为Android
                    .addPlatformNotification(AndroidNotification.newBuilder()
                            //附加字段data
                            .addExtra("data", jsonObject)
                            .addExtra("type", String.valueOf(type))
                            .build())
                    //通知平台为ios
                    .addPlatformNotification(IosNotification.newBuilder()
                            .autoBadge()
                            .addExtra("data", jsonObject)
                            .addExtra("type", String.valueOf(type))
                            .build())
                    .build());
            //new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, 3);
            PushPayload payload = builder.build();
            try {
                PushResult result = jpushClient.sendPush(payload);
                log.info(msg + "=========" + (result.isResultOK() ? "推送成功" : "推送失败"));
            } catch (Exception e) {
                e.printStackTrace();
            }
            //生产环境的iOS
//			iosProduction(type,msg,jsonObject, userIds);
        } else {
            log.info("接收用户为空");
        }
    }

    /**
     * 生产环境的iOS
     *
     * @param userIds
     */
    private void iosProduction(Integer type, String msg, JsonObject jsonObject,
                               Integer... userIds) {
        PushPayload.Builder builder = PushPayload.newBuilder();
        //设置接收对象为别名类型
        if (null != userIds) {
            String[] aliases = new String[userIds.length];
            for (int i = 0; i < userIds.length; i++) {
                aliases[i] = "jp" + userIds[i];
            }
            builder.setAudience(Audience.alias(aliases));
            //设置平台类型为所有
            builder.setPlatform(Platform.ios());
            //设置APNs的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发），导致生产环境IOS收不到推送
            builder.setOptions(Options.newBuilder()
                    .setApnsProduction(false)
                    .build());

            builder.setNotification(Notification.newBuilder()
                    //通知内容
                    .setAlert(msg)
                    //通知平台为ios
                    .addPlatformNotification(IosNotification.newBuilder()
                            .autoBadge()
                            .addExtra("data", jsonObject)
                            .addExtra("type", String.valueOf(type))
                            .build())
                    .build());
            //new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
            JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, 3);
            PushPayload payload = builder.build();
            try {
                PushResult result = jpushClient.sendPush(payload);
                log.info(msg + "=========" + (result.isResultOK() ? "推送成功" : "推送失败"));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("推生成环境的iOS失败");
            }
        } else {
            log.info("接收用户为空");
        }
    }

}