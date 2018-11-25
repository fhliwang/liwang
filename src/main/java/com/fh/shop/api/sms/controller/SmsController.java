package com.fh.shop.api.sms.controller;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.sms.biz.ISmsService;
import com.fh.shop.api.sms.biz.ISmsServiceImpl;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 21:22
 */

@RestController
public class SmsController {

    @Resource
    private ISmsService iSmsService;


    /**
     * sms短信接口
     */
    @GetMapping("sms/smsSend")
    public Object smsSend(String mobile,String callback){
        ServerResponse serverResponse = iSmsService.smsSend(mobile);
        MappingJacksonValue mappingJacksonValue  = new MappingJacksonValue(serverResponse);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue ;
    }

}
