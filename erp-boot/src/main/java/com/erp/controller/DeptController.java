package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.DeptService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Dept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "部门管理")
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    @ApiOperation("获取所有部门")
    @GetMapping("/list")
    public Result<List<Dept>> list() {
        return Result.success(deptService.list());
    }

    @ApiOperation("分页查询部门")
    @GetMapping("/page")
    public Result<PageResult<Dept>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String name) {
        Page<Dept> result = deptService.page(page, rows, name);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加部门")
    @SystemLog(title = "添加部门")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }

    @ApiOperation("更新部门")
    @SystemLog(title = "更新部门")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Dept dept) {
        deptService.update(dept);
        return Result.success();
    }

    @ApiOperation("删除部门")
    @SystemLog(title = "删除部门")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        deptService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据ID获取部门")
    @GetMapping("/get/{id}")
    public Result<Dept> getById(@PathVariable Long id) {
        return Result.success(deptService.getById(id));
    }
}