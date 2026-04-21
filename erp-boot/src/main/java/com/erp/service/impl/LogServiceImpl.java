package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.EmpMapper;
import com.erp.mapper.LogMapper;
import com.erp.pojo.Emp;
import com.erp.pojo.Log;
import com.erp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 日志服务实现类
 *
 * @author baoyang
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public Page<Log> page(Integer page, Integer rows, String type, String title, String userId, String startDate, String endDate) {
        Page<Log> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Log> wrapper = new LambdaQueryWrapper<>();

        if (type != null && !type.isEmpty()) {
            wrapper.eq(Log::getType, type);
        }
        if (title != null && !title.isEmpty()) {
            wrapper.like(Log::getTitle, title);
        }
        if (userId != null && !userId.isEmpty()) {
            wrapper.eq(Log::getUserId, userId);
        }
        if (startDate != null && !startDate.isEmpty()) {
            wrapper.ge(Log::getOperateDate, startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            wrapper.le(Log::getOperateDate, endDate + " 23:59:59");
        }

        wrapper.orderByDesc(Log::getOperateDate);

        Page<Log> result = logMapper.selectPage(pageObj, wrapper);
        fillEmpData(result.getRecords());
        return result;
    }

    @Override
    public Log getById(String id) {
        Log log = logMapper.selectById(id);
        if (log != null) {
            fillEmpData(Collections.singletonList(log));
        }
        return log;
    }

    @Override
    public void delete(String id) {
        logMapper.deleteById(id);
    }

    @Override
    public void deleteBatch(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            logMapper.deleteBatchIds(ids);
        }
    }

    @Override
    public void clear() {
        logMapper.delete(null);
    }

    @Override
    public List<Map<String, Object>> statsByType() {
        return logMapper.selectCountByType();
    }

    /**
 * 填充操作人员信息
 */
    private void fillEmpData(List<Log> logList) {
        for (Log log : logList) {
            if (log.getUserId() != null && !log.getUserId().equals("anonymous")) {
                try {
                    Long empId = Long.parseLong(log.getUserId());
                    Emp emp = empMapper.selectById(empId);
                    log.setEmp(emp);
                } catch (NumberFormatException e) {
                    // userId 不是数字格式，忽略
                }
            }
        }
    }
}