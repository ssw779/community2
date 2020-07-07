package com.zb.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 *
 * @param <T>
 */
public class PageUtil<T> implements Serializable {
    private Integer index =1;
    private Integer size =6;
    private Integer total;
    private Integer count;

    public PageUtil() {
    }

    public PageUtil(Integer index, Integer size, Integer total, Integer count, List<T> data) {
        this.index = index;
        this.size = size;
        this.total = total;
        this.count = count;
        this.data = data;
    }

    private List<T> data = new ArrayList<T>();

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        this.total = this.count % this.size == 0 ? this.count / this.size : this.count / this.size + 1;
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
