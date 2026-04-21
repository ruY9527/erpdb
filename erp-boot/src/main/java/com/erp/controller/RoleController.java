package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.RoleService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "角色管理")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/list")
    public Result<List<Role>> list() {
        return Result.success(roleService.list());
    }

    @ApiOperation("分页查询角色")
    @GetMapping("/page")
    public Result<PageResult<Role>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows) {
        Page<Role> result = roleService.page(page, rows);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加角色")
    @SystemLog(title = "添加角色")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Role role) {
        roleService.add(role);
        return Result.success();
    }

    @ApiOperation("更新角色")
    @SystemLog(title = "更新角色")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Role role) {
        roleService.update(role);
        return Result.success();
    }

    @ApiOperation("删除角色")
    @SystemLog(title = "删除角色")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        roleService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据ID获取角色")
    @GetMapping("/get/{id}")
    public Result<Role> getById(@PathVariable Long id) {
        return Result.success(roleService.getById(id));
    }

    @ApiOperation("获取角色的菜单权限")
    @GetMapping("/menu/{rid}")
    public Result<List<Long>> getRoleMenus(@PathVariable Long rid) {
        return Result.success(roleService.getRoleMenus(rid));
    }

    @ApiOperation("更新角色的菜单权限")
    @SystemLog(title = "更新角色权限")
    @PostMapping("/menu/{rid}")
    public Result<Void> updateRoleMenus(@PathVariable Long rid, @RequestBody List<Long> menuIds) {
        roleService.updateRoleMenus(rid, menuIds);
        return Result.success();
    }
}