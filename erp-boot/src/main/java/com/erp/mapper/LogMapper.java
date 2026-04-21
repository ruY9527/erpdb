package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Log;

import java.util.List;
import java.util.Map;

/**
 * 日志 Mapper 接口
 *
 * @author baoyang
 */
public interface LogMapper extends BaseMapper<Log> {

    /**
 * 按类型统计日志数量
 */
    List<Map<String, Object>> selectCountByType();
}