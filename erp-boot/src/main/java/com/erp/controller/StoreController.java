package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.StoreService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Store;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 仓库管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "仓库管理")
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @ApiOperation("获取所有仓库")
    @GetMapping("/list")
    public Result<List<Store>> list() {
        return Result.success(storeService.list());
    }

    @ApiOperation("分页查询仓库")
    @GetMapping("/page")
    public Result<PageResult<Store>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String name) {
        Page<Store> result = storeService.page(page, rows, name);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加仓库")
    @SystemLog(title = "添加仓库")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Store store) {
        storeService.add(store);
        return Result.success();
    }

    @ApiOperation("更新仓库")
    @SystemLog(title = "更新仓库")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Store store) {
        storeService.update(store);
        return Result.success();
    }

    @ApiOperation("删除仓库")
    @SystemLog(title = "删除仓库")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        storeService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据ID获取仓库")
    @GetMapping("/get/{id}")
    public Result<Store> getById(@PathVariable Long id) {
        return Result.success(storeService.getById(id));
    }
}