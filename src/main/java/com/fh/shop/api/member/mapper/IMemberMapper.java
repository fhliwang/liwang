package com.fh.shop.api.member.mapper;

import com.fh.shop.api.member.po.Member;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 20:54
 */
public interface IMemberMapper {

    void addMember(Member member);

    Member isexist(String memberName);
}
