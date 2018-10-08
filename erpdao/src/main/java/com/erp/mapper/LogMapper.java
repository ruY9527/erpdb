package com.erp.mapper;

import com.yang.erp.pojo.Log;

import java.util.List;

public interface LogMapper
{
    public void addLog(Log log);

    public List<Log> findAll(Log log);

    public Integer selectCount();
}
