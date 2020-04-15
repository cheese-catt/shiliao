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
    private Integer Nid;
    private String NDimages;
    private String NDdetails;
    private Integer NDorder;
    private Boolean NDvalid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNid() {
        return Nid;
    }

    public void setNid(Integer nid) {
        Nid = nid;
    }

    public String getNDimages() {
        return NDimages;
    }

    public void setNDimages(String NDimages) {
        this.NDimages = NDimages;
    }

    public String getNDdetails() {
        return NDdetails;
    }

    public void setNDdetails(String NDdetails) {
        this.NDdetails = NDdetails;
    }

    public Integer getNDorder() {
        return NDorder;
    }

    public void setNDorder(Integer NDorder) {
        this.NDorder = NDorder;
    }

    public Boolean getNDvalid() {
        return NDvalid;
    }

    public void setNDvalid(Boolean NDvalid) {
        this.NDvalid = NDvalid;
    }
}
