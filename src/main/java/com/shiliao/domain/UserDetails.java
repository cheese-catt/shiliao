package com.shiliao.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "u_details")
public class UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long udid; //主键
    private String udnames;//用户名
    private Date udbirthday;//生日
    private String udsign;//个性签名
    private String udimage;//头像
    private String udsex;//性别
    private Long uid;//外键

    public Long getUdid() {
        return udid;
    }

    public void setUdid(Long udid) {
        this.udid = udid;
    }

    public String getUdnames() {
        return udnames;
    }

    public void setUdnames(String udnames) {
        this.udnames = udnames;
    }

    public Date getUdbirthday() {
        return udbirthday;
    }

    public void setUdbirthday(Date udbirthday) {
        this.udbirthday = udbirthday;
    }

    public String getUdsign() {
        return udsign;
    }

    public void setUdsign(String udsign) {
        this.udsign = udsign;
    }

    public String getUdimage() {
        return udimage;
    }

    public void setUdimage(String udimage) {
        this.udimage = udimage;
    }

    public String getUdsex() {
        return udsex;
    }

    public void setUdsex(String udsex) {
        this.udsex = udsex;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
