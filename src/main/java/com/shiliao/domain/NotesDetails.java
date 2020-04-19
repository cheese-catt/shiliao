package com.shiliao.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "notes_details")
public class NotesDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //主键
    private Long nid;//帖子的ID
    private String ndimages; //图片
    private String nddetails;//内容
    private Integer ndorder;//每层楼顺序
    private Boolean ndvalid;//是否显示
    private Boolean ndread;//是否已读
    private Long uid;//用户ID
    private Date nddate;//评论的时间

    @Transient
    private UserDetails userDetails;

    @Transient
    private String udnames;//用户名

    @Transient
    private String ntitle;//对应的帖子标题

    public String getUdnames() {
        return udnames;
    }

    public void setUdnames(String udnames) {
        this.udnames = udnames;
    }

    public String getNtitle() {
        return ntitle;
    }

    public void setNtitle(String ntitle) {
        this.ntitle = ntitle;
    }

    public Date getNddate() {
        return nddate;
    }

    public void setNddate(Date nddate) {
        this.nddate = nddate;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Boolean getNdread() {
        return ndread;
    }

    public void setNdread(Boolean ndread) {
        this.ndread = ndread;
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

    public Long getNid() {
        return nid;
    }

    public void setNid(Long nid) {
        this.nid = nid;
    }

    public String getNdimages() {
        return ndimages;
    }

    public void setNdimages(String ndimages) {
        this.ndimages = ndimages;
    }

    public String getNddetails() {
        return nddetails;
    }

    public void setNddetails(String nddetails) {
        this.nddetails = nddetails;
    }

    public Integer getNdorder() {
        return ndorder;
    }

    public void setNdorder(Integer ndorder) {
        this.ndorder = ndorder;
    }

    public Boolean getNdvalid() {
        return ndvalid;
    }

    public void setNdvalid(Boolean ndvalid) {
        this.ndvalid = ndvalid;
    }
}
