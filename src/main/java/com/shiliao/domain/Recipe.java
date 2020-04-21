package com.shiliao.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rdetails; //内容
    private String rimages; //图片
    private Boolean rvalid;//是否显示 true为显示
    private String rtitles;//标题
    private Date rdate;//日期
    private Long uid;//用户id
    private Long ncategory;//标签

    public Long getNcategory() {
        return ncategory;
    }

    public void setNcategory(Long ncategory) {
        this.ncategory = ncategory;
    }

    @Transient
    private String udnames; //用户名
    @Transient
    private String udsex;//帖子用户的性别
    @Transient
    private String udimages;//帖子用户的头像
    @Transient
    private String category;//帖子的标签名;

    public String getUdnames() {
        return udnames;
    }

    public void setUdnames(String udnames) {
        this.udnames = udnames;
    }

    public String getUdsex() {
        return udsex;
    }

    public void setUdsex(String udsex) {
        this.udsex = udsex;
    }

    public String getUdimages() {
        return udimages;
    }

    public void setUdimages(String udimages) {
        this.udimages = udimages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRdetails() {
        return rdetails;
    }

    public void setRdetails(String rdetails) {
        this.rdetails = rdetails;
    }

    public String getRimages() {
        return rimages;
    }

    public void setRimages(String rimages) {
        this.rimages = rimages;
    }

    public Boolean getRvalid() {
        return rvalid;
    }

    public void setRvalid(Boolean rvalid) {
        this.rvalid = rvalid;
    }

    public String getRtitles() {
        return rtitles;
    }

    public void setRtitles(String rtitles) {
        this.rtitles = rtitles;
    }

    public Date getRdate() {
        return rdate;
    }

    public void setRdate(Date rdate) {
        this.rdate = rdate;
    }
}
