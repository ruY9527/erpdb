package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.MenuService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Menu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取所有菜单")
    @GetMapping("/list")
    public Result<List<Menu>> list() {
        return Result.success(menuService.list());
    }

    @ApiOperation("分页查询菜单")
    @GetMapping("/page")
    public Result<PageResult<Menu>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows) {
        Page<Menu> result = menuService.page(page, rows);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("获取菜单树")
    @GetMapping("/tree")
    public Result<List<Menu>> tree() {
        return Result.success(menuService.tree());
    }

    @ApiOperation("根据ID获取菜单")
    @GetMapping("/get/{id}")
    public Result<Menu> getById(@PathVariable Long id) {
        return Result.success(menuService.getById(id));
    }

    @ApiOperation("添加菜单")
    @SystemLog(title = "添加菜单")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Menu menu) {
        menuService.add(menu);
        return Result.success();
    }

    @ApiOperation("更新菜单")
    @SystemLog(title = "更新菜单")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Menu menu) {
        menuService.update(menu);
        return Result.success();
    }

    @ApiOperation("删除菜单")
    @SystemLog(title = "删除菜单")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        menuService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据用户ID获取菜单权限")
    @GetMapping("/user/{userId}")
    public Result<List<Menu>> getByUserId(@PathVariable Long userId) {
        return Result.success(menuService.getByUserId(userId));
    }
}