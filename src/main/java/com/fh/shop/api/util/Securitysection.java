package com.fh.shop.api.util;

import com.fh.shop.api.app.biz.IAppService;
import com.fh.shop.api.aspect.Limit;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemEnum;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Enumeration;
import java.util.UUID;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月19日 23:09
 */
public class Securitysection {
    /**
     * 注入service
     *
     */
    @Resource(name = "appServiceImpl")
    private IAppService appServiceImpl;

    /**
     * 过期时间
     */
        private static final long SURVIVALTIME = 60*1000;
    /**
     * 切面类
     */
    public  Object doSecurityApi(ProceedingJoinPoint joinPoint){
        /*获取头信息*/
        HttpServletRequest request = WebContext.getRequest();
        /*appkey*/
        String appKey = request.getHeader("appKey");
        /*time*/
        String time = request.getHeader("time");
        /*sign*/
        String sign = request.getHeader("sign");
        /*nonce*/
        String nonce = request.getHeader("nonce");
        /**
         * 判断头信息是否完整
         */
        if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(time) ||  StringUtils.isEmpty(sign) || StringUtils.isEmpty(nonce)){
            return ServerResponse.error(SystemEnum.HERDER_ISEXIS);
        }
        /**
         * 判断是否过期
         */
        if(Long.parseLong(time)+SURVIVALTIME < System.currentTimeMillis()){
            return ServerResponse.error(SystemEnum.TIME_OUT_ISEXIS);
        }
        /**
         *判断nonce
         * nonce只有第一次请求有效
         */
        /*第一次放到redis中*/
        boolean success = JedisUtil.setNxExpire(nonce, "1", 60);
        /*判断是否存在*/
         if (!success){
             return ServerResponse.error(SystemEnum.NONCE__ERROR);
         }
        /**
         * 判断签名是否存在
         */
            /**
             * 判断appkey是否有效
             */
        String appSecret = appServiceImpl.isexistAppSecret(appKey);
        if (StringUtils.isEmpty(appSecret)){
            return  ServerResponse.error(SystemEnum.APPKEY_OUT_ISEXIS);
        }
        /**
         * 有效则获取appSecret创建签名
         */
        /*生成签名*/
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, time);
        if (!checkSum.equals(sign)){
            return  ServerResponse.error(SystemEnum.CHECK_SUM_ERROR);
        }
        /**
         * 证明是合法者设置限流
         */
        /*拿到签名对象*/
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        /*获取签名*/
        Method method = signature.getMethod();
        /*判断方法上是否有注解*/
        if (method.isAnnotationPresent(Limit.class)){
            Limit annotation = method.getAnnotation(Limit.class);
            int sends = annotation.sends();
            int maxCount = annotation.maxCount();
            /*放到redis中*/
            /*生成key*/
            String key =appKey+":"+request.getMethod()+":"+request.getRequestURI();
            Long aLong = JedisUtil.incrKey(key, sends);
            if (aLong>maxCount){
                return  ServerResponse.error(SystemEnum.IMPOSE__ERROR);
            }
        }
        /*验证通过*/
        Object proceed = null;
        try {
            proceed  = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;
    }


    /**
     * 测试
     */
    public static void main(String[] args) {
        long Outime = new Date().getTime();
        String nonce;
        nonce=UUID.randomUUID().toString().replace("-","").toUpperCase()+":"+RandomStringUtils.randomAlphanumeric(10)+":"+System.currentTimeMillis();
        System.out.println(Outime);
        System.out.println(nonce);
        System.out.println(CheckSumBuilder.getCheckSum("f8c40435-c4ea-49cc-94df-dd5c5ddef0bb",nonce,Outime+""));
    }
}
