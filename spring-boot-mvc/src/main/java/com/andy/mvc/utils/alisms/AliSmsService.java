package com.andy.mvc.utils.alisms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * @author Leone
 **/
@Component
public class AliSmsService {

    private static final Logger logger = LoggerFactory.getLogger(AliSmsService.class);

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AliSmsProperties aliSmsProperties;

    public AliSmsService() {
    }

    /**
     * 发送手机验证码
     *
     * @param phone
     * @return
     * @throws ClientException
     */
    public boolean send(String phone) throws ClientException {
        deleteCaptcha(phone);
        String captcha = randomNum(aliSmsProperties.getCaptchaLength());
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsProperties.getAccessKeyId(), aliSmsProperties.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(phone);
        request.setSignName(aliSmsProperties.getSignName());
        request.setTemplateCode(aliSmsProperties.getTemplateCode());
        request.setTemplateParam("{'" + aliSmsProperties.getTemplateCodeName() + "':'" + captcha + "'}");
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (!"OK".equals(sendSmsResponse.getMessage()) && !"OK".equals(sendSmsResponse.getCode())) {
            logger.error("Ali sms send error phone:{}-----code:{}", phone, captcha);
            return false;
        } else {
            this.stringRedisTemplate.opsForValue().set(aliSmsProperties.getRedisPrefix() + ":" + phone, captcha, aliSmsProperties.getExpireTime(), TimeUnit.MINUTES);
            logger.info("Ali sms send success phone:{}-----code:{}", phone, captcha);
            return true;
        }
    }

    /**
     * 匹配
     *
     * @param phone
     * @param captcha
     * @return
     */
    public boolean matchingCaptcha(String phone, String captcha) {
        String rightCaptcha = this.stringRedisTemplate.opsForValue().get(aliSmsProperties.getRedisPrefix() + ":" + phone);
        if (!StringUtils.isEmpty(rightCaptcha) && rightCaptcha.equals(captcha)) {
            this.deleteCaptcha(phone);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 删除
     *
     * @param phone
     */
    public void deleteCaptcha(String phone) {
        String captcha = this.stringRedisTemplate.opsForValue().get(aliSmsProperties.getRedisPrefix() + ":" + phone);
        if (!StringUtils.isEmpty(captcha)) {
            this.stringRedisTemplate.delete(Arrays.asList(aliSmsProperties.getRedisPrefix() + ":" + phone));
        }
    }

    /**
     * 生成数字验证码
     *
     * @param length
     * @return
     */
    public static String randomNum(Integer length) {
        Random rand = new Random();
        StringBuffer result = new StringBuffer();
        final String sources = "0123456789";
        for (int i = 0; i < length; i++) {
            result.append(sources.charAt(rand.nextInt(9)) + "");
        }
        return result.toString();
    }

}