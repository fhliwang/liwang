package com.fh.shop.api.sms.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemEnum;
import com.fh.shop.api.util.JedisUtil;
import com.fh.shop.api.util.SmsUtil;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 21:34
 */
@Service
public class ISmsServiceImpl implements ISmsService {
    /*sms*/
    public ServerResponse smsSend(String mobile) {
        if(StringUtils.isEmpty(mobile)){
            return  ServerResponse.error(SystemEnum.SMS_ERROR_PHONE);
        }
        if (mobile.length()!=11){
            return  ServerResponse.error(SystemEnum.SMS_ERROR_LACK);
        }
        /*调用工具类*/
        String result = SmsUtil.getSms(mobile);
        /*反转*/
        Gson gson = new Gson();
        Map resultMap = gson.fromJson(result, Map.class);
        int code =((Double)resultMap.get("code")).intValue();
        String  msg = (String) resultMap.get("msg");
        /*判断短信是否调用成功*/
        if (code!=200){
            return ServerResponse.error(-1,"网易云信接口调用失败"+msg);
        }
        String  obj = (String) resultMap.get("obj");
        JedisUtil.set(mobile,obj);
        JedisUtil.expire(mobile,60);
        return ServerResponse.success();
    }
}
