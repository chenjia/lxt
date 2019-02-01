package com.lxt.ms.common.bean.web;

import java.util.List;

public class PageData {
    private int pageNumber;

    private int pageSize;

    private long total;

    private List data;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return "pageNum:" + this.pageNumber+" pageSize:" + this.pageSize + " totoal:" + this.total;
    }
}
