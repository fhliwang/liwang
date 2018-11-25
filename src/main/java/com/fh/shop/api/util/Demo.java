package com.fh.shop.api.util;

import java.util.UUID;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月19日 22:52
 */
public class Demo {

    /**
     * 生成appkey,appSecret
     */
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString()+":"+UUID.randomUUID().toString());
    }
}
