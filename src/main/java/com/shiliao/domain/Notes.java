package com.shiliao.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name="notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Nid; //主键
    private String Ndetails;//主帖内容
    private boolean Nvaild;//是否显示/删除就把值改为0
    private Integer NlikeTimes;//点赞数
    private String Nimages ;//图片，多个图片以‘,’分割
    private Integer Ncategory;//帖子所在的分区
    private String Ntitle;//文章标题
    private Date Ndate;//文章创建时间
    private Integer Nuid;//外键
    private Boolean Nflag ;//0为普通 1为精品

    public Integer getNid() {
        return Nid;
    }

    public void setNid(Integer nid) {
        Nid = nid;
    }

    public String getNdetails() {
        return Ndetails;
    }

    public void setNdetails(String ndetails) {
        Ndetails = ndetails;
    }

    public boolean isNvaild() {
        return Nvaild;
    }

    public void setNvaild(boolean nvaild) {
        Nvaild = nvaild;
    }

    public Integer getNlikeTimes() {
        return NlikeTimes;
    }

    public void setNlikeTimes(Integer nlikeTimes) {
        NlikeTimes = nlikeTimes;
    }

    public String getNimages() {
        return Nimages;
    }

    public void setNimages(String nimages) {
        Nimages = nimages;
    }

    public Integer getNcategory() {
        return Ncategory;
    }

    public void setNcategory(Integer ncategory) {
        Ncategory = ncategory;
    }

    public String getNtitle() {
        return Ntitle;
    }

    public void setNtitle(String ntitle) {
        Ntitle = ntitle;
    }

    public Date getNdate() {
        return Ndate;
    }

    public void setNdate(Date ndate) {
        Ndate = ndate;
    }

    public Integer getNuid() {
        return Nuid;
    }

    public void setNuid(Integer nuid) {
        Nuid = nuid;
    }

    public Boolean getNflag() {
        return Nflag;
    }

    public void setNflag(Boolean nflag) {
        Nflag = nflag;
    }
}
