package com.fh.shop.api.util;

import com.fh.shop.api.common.SystemConst;
import com.fh.shop.api.common.SystemEnum;
import org.apache.commons.lang.RandomStringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 21:29
 */
public class SmsUtil {
    /**
     * sms二次封装
     * @return
     */
    public static  String getSms(String mobile){
        Map bodys = new HashMap();
        bodys.put("mobile",mobile);
        bodys.put("templateid",SystemConst.TEMPLATEID);
        bodys.put("codeLen",SystemConst.CODELEN);
        Map hends = new HashMap();
        String  appKey=SystemConst.APPKEY;
        String nonce = RandomStringUtils.randomAlphanumeric(Integer.parseInt("8"));
        String curTime=new Date().getTime()+"";
        String  checkSum =  CheckSumBuilder.getCheckSum(SystemConst.APPSECRET,nonce,curTime);
        hends.put("AppKey",appKey);
        hends.put("nonce",nonce);
        hends.put("CurTime",curTime);
        hends.put("CheckSum",checkSum);
        String result = SendPostUtil.getSendPost(SystemConst.URL, bodys, hends);
        return result;
    }
}
