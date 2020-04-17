package com.shiliao.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid; //
    private String uname; //用户名
    private String upwd; //密码
    private String umail; //邮箱
    private Date udate; //创建日期
    private  Boolean utype;//用户类型 1代表管理员 0代表普通
    private Boolean ustate;//用户状态 1代表已激活 0代表未激活

    @Transient
    private UserDetails userDetails; //用户个性信息

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public User() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUmail() {
        return umail;
    }

    public void setUmail(String umail) {
        this.umail = umail;
    }

    public Date getUdate() {
        return udate;
    }

    public void setUdate(Date udate) {
        this.udate = udate;
    }

    public Boolean getUtype() {
        return utype;
    }

    public void setUtype(Boolean utype) {
        this.utype = utype;
    }

    public Boolean getUstate() {
        return ustate;
    }

    public void setUstate(Boolean ustate) {
        this.ustate = ustate;
    }
}
