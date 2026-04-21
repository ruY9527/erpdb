package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.EmpService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Emp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "员工管理")
@RestController
@RequestMapping("/emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    @ApiOperation("获取所有员工")
    @GetMapping("/list")
    public Result<List<Emp>> list() {
        return Result.success(empService.list());
    }

    @ApiOperation("分页查询员工")
    @GetMapping("/page")
    public Result<PageResult<Emp>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long deptId) {
        Page<Emp> result = empService.page(page, rows, username, name, deptId);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加员工")
    @SystemLog(title = "添加员工")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Emp emp) {
        try {
            empService.add(emp);
            return Result.success();
        } catch (RuntimeException e) {
            return Result.fail(e.getMessage());
        }
    }

    @ApiOperation("更新员工")
    @SystemLog(title = "更新员工")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }

    @ApiOperation("删除员工")
    @SystemLog(title = "删除员工")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        empService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据ID获取员工")
    @GetMapping("/get/{id}")
    public Result<Emp> getById(@PathVariable Long id) {
        return Result.success(empService.getById(id));
    }

    @ApiOperation("重置密码")
    @SystemLog(title = "重置员工密码")
    @PostMapping("/resetPwd/{id}")
    public Result<String> resetPwd(@PathVariable Long id) {
        empService.resetPwd(id);
        return Result.success("密码已重置为123456");
    }
}