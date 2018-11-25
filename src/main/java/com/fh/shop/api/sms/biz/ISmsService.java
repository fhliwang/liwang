package com.fh.shop.api.sms.biz;

import com.fh.shop.api.common.ServerResponse;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月06日 21:33
 */
public interface ISmsService {
    ServerResponse smsSend(String mobile);
}
