package com.shiliao.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "n_users")
public class Nusers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //主键

    private Long uid;  //用户id
    private String nids; //用户收藏的帖子ID，多个以逗号隔开
    private String nlike; //用户点赞的帖子ID，多个以逗号分隔

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getNids() {
        return nids;
    }

    public void setNids(String nids) {
        this.nids = nids;
    }

    public String getNlike() {
        return nlike;
    }

    public void setNlike(String nlike) {
        this.nlike = nlike;
    }
}
