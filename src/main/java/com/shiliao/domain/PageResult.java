package com.shiliao.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageResult<T> {
    private String Msg;//成功/错误信息
    private Long total; //总的数目
    private Integer totalPage;//总页码
    private List<T> items; //具体列表信息
    private Boolean flag; //用于判断返回给前端的信息是否成功
    private Map<String,Object> map = new HashMap<String,Object>();



    public static PageResult ok(){
        PageResult pageResult = new PageResult();
        pageResult.setFlag(true);
        pageResult.setMsg("返回成功");
        return pageResult;
    }

    public static PageResult error(){
        PageResult pageResult = new PageResult();
        pageResult.setFlag(false);
        pageResult.setMsg("请稍等");
        return pageResult;
    }
    public PageResult<T> add(String key,Object value){
        this.getMap().put(key,value);
        return this;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
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
