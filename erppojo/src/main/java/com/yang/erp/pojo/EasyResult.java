package com.yang.erp.pojo;

import java.util.List;

public class EasyResult<T> {
    private Integer total;
    private List<T> rows;

    public EasyResult(Integer total, List<T> rows)
    {
        this.total = total;
        this.rows = rows;
    }

    public EasyResult(){}

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "EasyResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
