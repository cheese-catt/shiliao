package com.shiliao.domain;

import javax.persistence.*;
import java.util.Date;
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
    private Integer ncategory;//功效/标签的分类
    private String ntitle;//文章标题
    private Date ndate;//文章创建时间
    private Long nuid;//外键

    @Transient
    private NotesDetails notesDetails;

    @Transient
    private String udnames;

    public NotesDetails getNotesDetails() {
        return notesDetails;
    }

    public void setNotesDetails(NotesDetails notesDetails) {
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

    public Integer getNcategory() {
        return ncategory;
    }

    public void setNcategory(Integer ncategory) {
        this.ncategory = ncategory;
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
