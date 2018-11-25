package com.fh.shop.api.area.controller;

import com.fh.shop.api.area.biz.AreaService;
import com.fh.shop.api.common.ServerResponse;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月09日 00:00
 */
@RestController
public class AreaController {
    @Resource
    private AreaService areaService;
    /*
    查询地区
     */
    @GetMapping("area/areaList")
    public Object areaList(Integer id,String callback){
        ServerResponse serverResponse = areaService.areaList(id);
        MappingJacksonValue mappingJacksonValue   = new MappingJacksonValue(serverResponse);
        mappingJacksonValue.setJsonpFunction(callback);
        return  mappingJacksonValue;
    }
}
