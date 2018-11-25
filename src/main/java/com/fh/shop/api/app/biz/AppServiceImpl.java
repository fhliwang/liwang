package com.fh.shop.api.app.biz;

import com.fh.shop.api.app.mapper.IAppMapper;
import com.fh.shop.api.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月19日 22:56
 */
@Service("appServiceImpl")
public class AppServiceImpl implements IAppService {

    @Autowired
    private IAppMapper iAppMapper;
    /**
     * 查询该appkey是否存在
     */
    public String isexistAppSecret(String appKey){
        /**
         * 放到rerdis中
         */
        String jedisinfo  = JedisUtil.get(appKey);
        if (StringUtils.isNoneBlank(jedisinfo)){
            return  jedisinfo;
        }
        /**
         * 缓存不存在从数据库查并放在redis中
         */
        String result = iAppMapper.isexistAppSecret(appKey);
        if (StringUtils.isEmpty(result)){
            return "";
        }
        JedisUtil.set(appKey,result);
        return  result;
    }
}
