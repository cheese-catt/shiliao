package com.shiliao.domain;

import java.util.List;

public class PageResult<T> {
    private String Msg;
    private Long total;
    private Integer totalPage;
    private List<T> items;
    private Boolean flag;
    public static PageResult ok(){
        PageResult pageResult = new PageResult();
        pageResult.setFlag(true);
        pageResult.setMsg("返回成功");
        return pageResult;
    }

    public PageResult(String msg, Long total, Integer totalPage, List<T> items, Boolean flag) {
        Msg = msg;
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
        this.flag = flag;
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items, Boolean flag) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
        this.flag = flag;
    }

    public PageResult() {
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
