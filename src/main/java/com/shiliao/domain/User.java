package com.shiliao.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Uid; //
    private String Uname; //用户名
    private String Upwd; //密码
    private String Umail; //邮箱
    private Date Udate; //创建日期
    private  Boolean Utype;//用户类型 1代表管理员 0代表普通
    private Boolean Ustate;//用户状态 1代表已激活 0代表未激活

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

    public String getUmail() {
        return Umail;
    }

    public void setUmail(String umail) {
        Umail = umail;
    }

    public Long getUid() {

        return Uid;
    }

    public void setUid(Long uid) {
        Uid = uid;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getUpwd() {
        return Upwd;
    }

    public void setUpwd(String upwd) {
        Upwd = upwd;
    }

    public Date getUdate() {
        return Udate;
    }

    public void setUdate(Date udate) {
        Udate = udate;
    }

    public Boolean getUtype() {
        return Utype;
    }

    public void setUtype(Boolean utype) {
        Utype = utype;
    }

    public Boolean getUstate() {
        return Ustate;
    }

    public void setUstate(Boolean ustate) {
        Ustate = ustate;
    }
}
