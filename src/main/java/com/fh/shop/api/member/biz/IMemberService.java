package com.fh.shop.api.member.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.po.Member;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 20:53
 */
public interface IMemberService {
    ServerResponse addMember(Member member);

    ServerResponse isexist(String memberName);
}
