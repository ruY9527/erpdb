package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.SupplierService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Supplier;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商/客户管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "供应商/客户管理")
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @ApiOperation("根据类型获取列表")
    @GetMapping("/list/{type}")
    public Result<List<Supplier>> listByType(@PathVariable String type) {
        return Result.success(supplierService.listByType(type));
    }

    @ApiOperation("获取所有供应商")
    @GetMapping("/list")
    public Result<List<Supplier>> list() {
        return Result.success(supplierService.list());
    }

    @ApiOperation("分页查询")
    @GetMapping("/page")
    public Result<PageResult<Supplier>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String name) {
        Page<Supplier> result = supplierService.page(page, rows, type, name);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加")
    @SystemLog(title = "添加供应商")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Supplier supplier) {
        supplierService.add(supplier);
        return Result.success();
    }

    @ApiOperation("更新")
    @SystemLog(title = "更新供应商")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Supplier supplier) {
        supplierService.update(supplier);
        return Result.success();
    }

    @ApiOperation("删除")
    @SystemLog(title = "删除供应商")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        supplierService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据ID获取")
    @GetMapping("/get/{id}")
    public Result<Supplier> getById(@PathVariable Long id) {
        return Result.success(supplierService.getById(id));
    }
}