package com.shiliao.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "notes_details")
public class NotesDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer nid;
    private String ndimages;
    private String nddetails;
    private Integer ndorder;
    private Boolean ndvalid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
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
