package com.fjw.util;

import java.util.List;
//分页通用工具类
public class PageUtil {
    private int currentPage;//当前页
    private long totalPage;//总页数
    private List<?> items;//每页显示条数

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
