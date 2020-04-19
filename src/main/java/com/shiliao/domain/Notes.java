package com.shiliao.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Table(name="notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nid; //主键

    private String ndetails;//主帖内容
    private Boolean nvalid;//是否显示/删除就把值改为0
    private Integer nlikeTimes;//点赞数
    private String nimages ;//图片，多个图片以‘,’分割
    private Integer narea;//帖子所在的分区 0为浏览区 1为精品区  默认为0
    private String ncategory;//功效/标签的分类
    private String ntitle;//文章标题
    private Date ndate;//文章创建时间
    private Long nuid;//外键

    @Transient
    private List<NotesDetails> notesDetails; //具体的评论数

    @Transient
    private String udnames; //用户名
    @Transient
    private String udsex;//帖子用户的性别
    @Transient
    private String udimages;//帖子用户的头像

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

    @Transient
    private List<Map<String,String>> ncategorys; //标签的集合

    public List<NotesDetails> getNotesDetails() {
        return notesDetails;
    }

    public void setNotesDetails(List<NotesDetails> notesDetails) {
        this.notesDetails = notesDetails;
    }

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getNdetails() {
        return ndetails;
    }

    public void setNdetails(String ndetails) {
        this.ndetails = ndetails;
    }

    public Boolean getNvalid() {
        return nvalid;
    }

    public void setNvalid(Boolean nvalid) {
        this.nvalid = nvalid;
    }

    public Integer getNlikeTimes() {
        return nlikeTimes;
    }

    public void setNlikeTimes(Integer nlikeTimes) {
        this.nlikeTimes = nlikeTimes;
    }

    public String getNimages() {
        return nimages;
    }

    public void setNimages(String nimages) {
        this.nimages = nimages;
    }

    public Integer getNarea() {
        return narea;
    }

    public void setNarea(Integer narea) {
        this.narea = narea;
    }

    public String getNcategory() {
        return ncategory;
    }

    public void setNcategory(String ncategory) {
        this.ncategory = ncategory;
    }

    public List<Map<String, String>> getNcategorys() {
        return ncategorys;
    }

    public void setNcategorys(List<Map<String, String>> ncategorys) {
        this.ncategorys = ncategorys;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public Date getNdate() {
        return ndate;
    }

    public void setNdate(Date ndate) {
        this.ndate = ndate;
    }

    public Long getNuid() {
        return nuid;
    }

    public void setNuid(Long nuid) {
        this.nuid = nuid;
    }

    public String getUdnames() {
        return udnames;
    }

    public void setUdnames(String udnames) {
        this.udnames = udnames;
    }
}
