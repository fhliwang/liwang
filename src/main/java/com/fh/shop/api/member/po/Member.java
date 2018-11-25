package com.fh.shop.api.member.po;

import jdk.internal.util.xml.impl.Input;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @类描述：
 * @作者        ：李旺
 * @邮箱        : 888888888@qq.com
 * @创建日期    ：2018年11月04日 19:48
 */
public class Member implements Serializable {
    private static final long serialVersionUID = -2160290091380648855L;
    private Integer id;
    private String userName;
    private String password;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Date regTime;
    private Date lastLoginTime;
    private Integer  shengId;
    private Integer shiId;
    private Integer xianId;
    private String areaInfo;

    private String pnoneCode;

    public String getPnoneCode() {
        return pnoneCode;
    }

    public void setPnoneCode(String pnoneCode) {
        this.pnoneCode = pnoneCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getShengId() {
        return shengId;
    }

    public void setShengId(Integer shengId) {
        this.shengId = shengId;
    }

    public Integer getShiId() {
        return shiId;
    }

    public void setShiId(Integer shiId) {
        this.shiId = shiId;
    }

    public Integer getXianId() {
        return xianId;
    }

    public void setXianId(Integer xianId) {
        this.xianId = xianId;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }
}
