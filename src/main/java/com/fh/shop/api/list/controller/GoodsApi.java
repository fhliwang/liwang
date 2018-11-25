package com.fh.shop.api.list.controller;

import com.fh.shop.api.aspect.Limit;
import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemEnum;
import com.fh.shop.api.list.biz.IGoodsService;
import com.fh.shop.api.list.biz.IGoodsServiceImpl;
import io.swagger.annotations.*;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 16:47
 */
@RestController
@RequestMapping("list")
public class GoodsApi {
    @Resource
    private IGoodsService iGoodsService;
    /**
     * 接口商品查询
     */
    @GetMapping("/queryGoods")
    @ApiOperation(value = "查询商品列表", notes = "查询新商品列表", tags = "Article",httpMethod = "get")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品编号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsPrice", value = "商品价格", required = true, dataType = "BigDecimal"),
            @ApiImplicitParam(name = "goodsImg", value = "商品图片", required = true, dataType = "String")
    })
    @Limit(sends = 30,maxCount = 3)
    public Object queryGoods(String callback){
        ServerResponse serverResponse = iGoodsService.queryGoods();
        MappingJacksonValue mappingJacksonValue   = new MappingJacksonValue(serverResponse);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }


    /**
     *
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("goods/{id}")
    public ServerResponse deleteGoods(@PathVariable  Integer id){
        return  iGoodsService.deleteGoods(id);
    }
}
