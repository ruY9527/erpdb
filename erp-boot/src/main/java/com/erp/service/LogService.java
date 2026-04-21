package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Log;

import java.util.List;
import java.util.Map;

/**
 * 日志服务接口
 *
 * @author baoyang
 */
public interface LogService {

    Page<Log> page(Integer page, Integer rows, String type, String title, String userId, String startDate, String endDate);

    Log getById(String id);

    void delete(String id);

    void deleteBatch(List<String> ids);

    void clear();

    List<Map<String, Object>> statsByType();
}