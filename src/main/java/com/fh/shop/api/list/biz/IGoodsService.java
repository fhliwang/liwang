package com.fh.shop.api.list.biz;

import com.fh.shop.api.common.ServerResponse;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 16:50
 */
public interface IGoodsService {

    ServerResponse queryGoods();

    ServerResponse deleteGoods(Integer id);
}
