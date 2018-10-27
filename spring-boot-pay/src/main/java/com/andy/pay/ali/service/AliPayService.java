package com.andy.pay.ali.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.andy.pay.common.exception.ExceptionMessage;
import com.andy.pay.common.property.AppProperties;
import com.andy.pay.common.utils.ImageCodeUtil;
import com.andy.pay.common.utils.RandomUtil;
import com.andy.pay.pojos.entity.Order;
import com.andy.pay.service.OrderService;
import com.andy.pay.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.other.common.constants.Constants;
import com.other.common.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p> 支付宝支付
 *
 * @author Leone
 * @since 2018-05-27
 **/
@Slf4j
@Service
public class AliPayService {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    @Resource
    private static AppProperties appProperties;

    @Resource
    private ObjectMapper objectMapper;

    private AlipayClient alipayClient;

    @PostConstruct
    public void initMethod() {
        alipayClient = new DefaultAlipayClient(
                appProperties.getAli().getRefund_url(),
                appProperties.getAli().getApp_id(),
                appProperties.getAli().getAlipay_private_key(),
                appProperties.getAli().getFormat(),
                appProperties.getAli().getCharset(),
                appProperties.getAli().getAlipay_public_key(),
                appProperties.getAli().getSign_type());
    }


    /**
     * 支付宝扫码支付生成二维码响应到浏览器
     *
     * @param orderId
     * @param response
     * @return
     */
    public void qrPay(Long orderId, HttpServletResponse response) throws Exception {
        Order order = orderService.findOne(orderId);
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        Map<String, String> params = new TreeMap<>();
        params.put("out_trade_no", order.getOutTradeNo());
        params.put("total_amount", order.getTotalFee().toString());
        params.put("subject", "备注");
        params.put("body", "详情");
        params.put("store_id", "NJ_2031");
        params.put("timeout_express", "90m");
        request.setBizContent(objectMapper.writeValueAsString(params));
        request.setNotifyUrl(appProperties.getAli().getNotify_url());

        AlipayTradePrecreateResponse responseData = alipayClient.execute(request);
        log.info("response:{}", responseData.getBody());
        String qrCode = responseData.getQrCode();
        ImageCodeUtil.createQRCode(qrCode, response);
    }

    /**
     * 支付宝退款
     *
     * @param orderId
     * @param servletRequest
     * @return
     */
    public Boolean aliRefund(Long orderId, HttpServletRequest servletRequest) throws Exception {
        Order order = orderService.findOne(orderId);
        // 创建退款请求builder，设置请求参数
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        Map<String, String> params = new TreeMap<>();
        //必须 商户订单号
        params.put("out_trade_no", order.getOutTradeNo());
        //必须 支付宝交易号
        params.put("trade_no", order.getTotalFee().toString());
        //必须 退款金额
        params.put("refund_amount", order.getTotalFee().toString());
        //可选 代表 退款的原因说明
        params.put("refund_reason", "退款的原因说明");
        //可选 标识一次退款请求，同一笔交易多次退款需要保证唯一（就是out_request_no在2次退款一笔交易时，要不一样），如需部分退款，则此参数必传
        params.put("out_request_no", 1 + RandomUtil.getNum(11));
        //可选 代表 商户的门店编号
        params.put("store_id", "90m");
        request.setBizContent(objectMapper.writeValueAsString(params));
        AlipayTradeRefundResponse responseData = alipayClient.execute(request);
        if (responseData.isSuccess()) {
            log.info("ali refund success tradeNo:{}", order.getOutTradeNo());
            return true;
        }
        log.info("ali refund failed tradeNo:{}", order.getOutTradeNo());
        return false;
    }


    /**
     * 阿里pc支付
     *
     * @param orderId
     * @param servletRequest
     * @return
     */
    public String pcPay(Long orderId, HttpServletRequest servletRequest) throws Exception {
        Order order = orderService.findOne(orderId);
        AlipayTradePagePayRequest payRequest = new AlipayTradePagePayRequest();
        //前台通知
        payRequest.setReturnUrl(appProperties.getAli().getReturn_url());
        //后台回调
        payRequest.setNotifyUrl(appProperties.getAli().getNotify_url());
        Map<String, String> params = new TreeMap<>();
        params.put("out_trade_no", order.getOutTradeNo());
        //订单金额:元
        params.put("total_amount", order.getTotalFee().toString());
        params.put("subject", "订单标题");
        //实际收款账号，一般填写商户PID即可
        params.put("seller_id", appProperties.getAli().getMch_id());
        //电脑网站支付
        params.put("product_code", "FAST_INSTANT_TRADE_PAY");
        params.put("body", "两个橘子");
        payRequest.setBizContent(objectMapper.writeValueAsString(params));
        log.info("业务参数:" + payRequest.getBizContent());
        String result = ExceptionMessage.ERROR.getMessage();
        try {
            result = AliPayConfig.getAlipayClient().pageExecute(payRequest).getBody();
        } catch (AlipayApiException e) {
            log.error("ali pay error message:{}", e.getMessage());
        }
        return result;
    }

    /**
     * 支付宝App支付
     *
     * @param orderId
     * @param servletRequest
     * @return
     */
    public String appPay(Long orderId, HttpServletRequest servletRequest) {
        Order order = orderService.findOne(orderId);
        String orderString = Constants.FAIL;
        AlipayClient alipayClient = AliPayConfig.getAlipayClient();
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        // SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody("描述");
        model.setSubject("商品名称");
        model.setOutTradeNo(order.getOutTradeNo());
        model.setTimeoutExpress("30m");
        model.setTotalAmount(order.getTotalFee().toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(appProperties.getAli().getNotify_url());
        try {
            // 这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
            //就是orderString 可以直接给客户端请求，无需再做处理。
            orderString = response.getBody();
            log.info("orderString:{}", orderString);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return orderString;
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> bizContent = new TreeMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        bizContent.put("out_trade_no", "aaa");
        //订单金额:元
        bizContent.put("total_amount", "b bbb");
        bizContent.put("subject", "订单标题");
        //实际收款账号，一般填写商户PID即可
        bizContent.put("seller_id", "eeee");
        //电脑网站支付
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        bizContent.put("body", "两个橘子");
        System.out.println(objectMapper.writeValueAsString(bizContent));
    }

    /**
     * 如果你调用的是当面付预下单接口(alipay.trade.precreate)，调用成功后订单实际上是没有生成，因为创建一笔订单要买家、卖家、金额三要素。
     * 预下单并没有创建订单，所以根据商户订单号操作订单，比如查询或者关闭，会报错订单不存在。
     * 当用户扫码后订单才会创建，用户扫码之前二维码有效期2小时，扫码之后有效期根据timeout_express时间指定。
     * =====只有支付成功后 调用此订单才可以=====
     */
    public String aliCloseOrder(Product product) {
        log.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单");
        String message = Constants.SUCCESS;
        try {
            String imgPath = Constants.QRCODE_PATH + Constants.SF_FILE_SEPARATOR + "alipay_" + product.getOutTradeNo() + ".png";
            File file = new File(imgPath);
            if (file.exists()) {
                AlipayClient alipayClient = AliPayConfig.getAlipayClient();
                AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
                request.setBizContent("{" +
                        "    \"out_trade_no\":\"" + product.getOutTradeNo() + "\"" +
                        "  }");
                AlipayTradeCloseResponse response = alipayClient.execute(request);
                if (response.isSuccess()) {//扫码未支付的情况
                    log.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单成功并删除支付二维码");
                    file.delete();
                } else {
                    if ("ACQ.TRADE_NOT_EXIST".equals(response.getSubCode())) {
                        log.info("订单号：" + product.getOutTradeNo() + response.getSubMsg() + "(预下单 未扫码的情况)");
                    } else if ("ACQ.TRADE_STATUS_ERROR".equals(response.getSubCode())) {
                        log.info("订单号：" + product.getOutTradeNo() + response.getSubMsg());
                    } else {
                        log.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单失败" + response.getSubCode() + response.getSubMsg());
                        message = Constants.FAIL;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = Constants.FAIL;
            log.info("订单号：" + product.getOutTradeNo() + "支付宝关闭订单异常");
        }
        return message;
    }

    public String downloadBillUrl(String billDate, String billType) {
        log.info("获取支付宝订单地址:" + billDate);
        String downloadBillUrl = "";
        try {
            AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
            request.setBizContent("{" + "    \"bill_type\":\"trade\","
                    + "    \"bill_date\":\"2016-12-26\"" + "  }");

            AlipayDataDataserviceBillDownloadurlQueryResponse response
                    = AliPayConfig.getAlipayClient().execute(request);
            if (response.isSuccess()) {
                log.info("获取支付宝订单地址成功:" + billDate);
                downloadBillUrl = response.getBillDownloadUrl();//获取下载地
            } else {
                log.info("获取支付宝订单地址失败" + response.getSubMsg() + ":" + billDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取支付宝订单地址异常:" + billDate);
        }
        return downloadBillUrl;
    }


    // 简单打印应答
    private void dumpResponse(AlipayResponse response) {
        if (response != null) {
            log.info(String.format("code:%s, msg:%s", response.getCode(), response.getMsg()));
            if (StringUtils.isEmpty(response.getSubCode())) {
                log.info(String.format("subCode:%s, subMsg:%s", response.getSubCode(), response.getSubMsg()));
            }
            log.info("body:" + response.getBody());
        }
    }


}
