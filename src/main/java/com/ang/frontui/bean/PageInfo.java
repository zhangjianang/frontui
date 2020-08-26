package com.ang.frontui.bean;

public class PageInfo {
    private Integer pageSize = 10;
    private Integer pageNum = 1;

    private Integer start;
    private Integer end;


    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getStart() {
        start = (pageNum - 1) * pageSize;
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        end = pageNum * pageSize;
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}
