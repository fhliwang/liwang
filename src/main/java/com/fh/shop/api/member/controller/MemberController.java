package com.fh.shop.api.member.controller;

import com.fh.shop.api.common.ServerResponse;
import com.fh.shop.api.member.biz.IMemberService;
import com.fh.shop.api.member.po.Member;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 19:58
 */
@RestController
@RequestMapping("member")
public class MemberController {
    @Resource
    private IMemberService iMemberService;
    /**
     * 会员注册接口
     */
    @RequestMapping(value = "addMember",method = RequestMethod.POST)
    @ApiOperation(value = "会员注册", notes = "会员注册接口", tags = "Article",httpMethod = "post")
    @CrossOrigin()
    public ServerResponse addMember( Member member){
        return  iMemberService.addMember(member);
    }


    /**
     * 判断用户名是否存在
     */
    @GetMapping("/isexist")
    public Object isexist(String memberName,String callback){
        ServerResponse isexists= iMemberService.isexist(memberName);
        MappingJacksonValue mappingJacksonValue  = new MappingJacksonValue(isexists);
        mappingJacksonValue.setJsonpFunction(callback);
        return  mappingJacksonValue;
    }

    /**
     * 判断用户名是否存在
     */
    @PostMapping("/isexistse")
    public ServerResponse isexistse(String userName)
    {
        System.err.println(userName);
        String memberName = userName;
        System.out.println(memberName);
        return  iMemberService.isexist(memberName);
    }
}
