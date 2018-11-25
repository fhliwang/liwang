package com.fh.shop.api.list.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.list.mapper.IGoodsMapper;
import com.fh.shop.api.list.po.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 16:50
 */
@Service
public class IGoodsServiceImpl implements IGoodsService {
    @Autowired
    private IGoodsMapper iGoodsMapper;
    public ServerResponse queryGoods() {
        List<Goods> goodslist =  iGoodsMapper.queryGoods();
        return  ServerResponse.success(goodslist);
    }

    @Override
    public ServerResponse deleteGoods(Integer id) {
       iGoodsMapper.deleteGoods(id);
        return ServerResponse.success();
    }
}
