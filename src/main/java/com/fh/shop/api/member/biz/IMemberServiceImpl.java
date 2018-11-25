package com.fh.shop.api.member.biz;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.common.SystemEnum;
import com.fh.shop.api.member.mapper.IMemberMapper;
import com.fh.shop.api.member.po.Member;
import com.fh.shop.api.util.JedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 20:54
 */
@Service
public class IMemberServiceImpl implements IMemberService {
    @Autowired
    private IMemberMapper iMemberMapper;


    public ServerResponse addMember(Member member) {
        String phone = member.getPhone();
        String result = JedisUtil.get(phone);
        String pnoneCode = member.getPnoneCode();
        /*判断用户名是否重复*/
        Member isexist = iMemberMapper.isexist(member.getUserName());
            if (isexist !=null){
            return  ServerResponse.error(SystemEnum.USER_ISEXIS);
        }
        /*判断手机号不可为空*/
        if (StringUtils.isEmpty(phone)){
            return  ServerResponse.error(SystemEnum.SMS_ERROR_PHONE);
        }
        /*从redis获取手机号*/
        if (StringUtils.isEmpty(result)){
            return ServerResponse.error(SystemEnum.SMS_MEMBER_ERROR);
        }
        /*判断验证吗是否一致*/
        if (!pnoneCode.equals(result)){
            return  ServerResponse.error(SystemEnum.SMS_CODE_ERROR);
        }

        System.out.println("eeee");
        member.setRegTime(new Date());
        /*添加数据库*/
        iMemberMapper.addMember(member);
        return ServerResponse.success();
    }

    @Override
    public ServerResponse isexist(String memberName) {
        System.err.println(memberName);
        /*判断是否为空*/
        if(StringUtils.isEmpty(memberName)){
            return  ServerResponse.error(SystemEnum.USER_ERROR);
        }
        /*判断是否存在*/
          Member member =   iMemberMapper.isexist(memberName);
          if (member!=null){
              return  ServerResponse.error(SystemEnum.USER_ISEXIS);
          }
        return  ServerResponse.success();
    }
}
