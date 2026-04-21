package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.LogService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Log;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 操作日志管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "操作日志管理")
@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    @ApiOperation("获取所有日志")
    @GetMapping("/list")
    public Result<List<Log>> list() {
        Page<Log> result = logService.page(1, 1000, null, null, null, null, null);
        return Result.success(result.getRecords());
    }

    @ApiOperation("分页查询日志")
    @GetMapping("/page")
    public Result<PageResult<Log>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        Page<Log> result = logService.page(page, rows, type, title, userId, startDate, endDate);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("根据ID获取日志详情")
    @GetMapping("/get/{id}")
    public Result<Log> getById(@PathVariable String id) {
        return Result.success(logService.getById(id));
    }

    @ApiOperation("删除日志")
    @SystemLog(title = "删除日志")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable String id) {
        logService.delete(id);
        return Result.success();
    }

    @ApiOperation("批量删除日志")
    @SystemLog(title = "批量删除日志")
    @PostMapping("/deleteBatch")
    public Result<Void> deleteBatch(@RequestBody List<String> ids) {
        logService.deleteBatch(ids);
        return Result.success();
    }

    @ApiOperation("清空日志")
    @SystemLog(title = "清空日志")
    @PostMapping("/clear")
    public Result<Void> clear() {
        logService.clear();
        return Result.success();
    }

    @ApiOperation("获取日志类型统计")
    @GetMapping("/stats/type")
    public Result<List<Map<String, Object>>> statsByType() {
        return Result.success(logService.statsByType());
    }
}