package com.andy.pay.ali.service;

/**
 * @author Leone
 * @since 2018-06-16
 **/
public final class AliPayConfig {

    private AliPayConfig() {
    }

//    /**
//     * 参数类型
//     */
//    public static final String PARAM_TYPE = "json";
//
//    /**
//     * 编码
//     */
//    public static final String CHARSET = StandardCharsets.UTF_8.displayName();
//
//    /**
//     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
//     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
//     */
//    private static class SingletonHolder {
//        /**
//         * 静态初始化器，由JVM来保证线程安全
//         */
//        private static AlipayClient alipayClient = new DefaultAlipayClient(
//                Configs.getOpenApiDomain(), Configs.getAppid(),
//                Configs.getPrivateKey(), PARAM_TYPE, CHARSET,
//                Configs.getAlipayPublicKey(), "RSA2");
//
//        private static AlipayTradeService alipayTradeService = new AlipayTradeServiceImpl.ClientBuilder().build();
//    }
//
//    public static AlipayClient getAlipayClient() {
//        return SingletonHolder.alipayClient;
//    }
//
//    public static AlipayTradeService getAlipayTradeService() {
//        return SingletonHolder.alipayTradeService;
//    }
}
