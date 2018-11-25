package com.fh.shop.api.list.mapper;

import com.fh.shop.api.list.po.Goods;

import java.util.List;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 16:51
 */
public interface IGoodsMapper {

    List<Goods> queryGoods();

    void deleteGoods(Integer id);
}
