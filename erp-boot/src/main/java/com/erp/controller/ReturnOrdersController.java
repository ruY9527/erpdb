package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.ReturnOrdersService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.ReturnOrders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退货订单管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "退货订单管理")
@RestController
@RequestMapping("/returnOrders")
public class ReturnOrdersController {

    @Autowired
    private ReturnOrdersService returnOrdersService;

    @ApiOperation("根据类型和状态查询退货订单")
    @GetMapping("/list")
    public Result<List<ReturnOrders>> list(
            @RequestParam String type,
            @RequestParam(required = false) String state) {
        return Result.success(returnOrdersService.list(type, state));
    }

    @ApiOperation("分页查询退货订单")
    @GetMapping("/page")
    public Result<PageResult<ReturnOrders>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) Long supplierId) {
        Page<ReturnOrders> result = returnOrdersService.page(page, rows, type, state, supplierId);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加退货订单")
    @SystemLog(title = "添加退货订单")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody ReturnOrders returnOrders) {
        returnOrdersService.add(returnOrders);
        return Result.success();
    }

    @ApiOperation("审核退货订单")
    @SystemLog(title = "审核退货订单")
    @PostMapping("/check/{id}")
    public Result<Void> check(@PathVariable Long id, @RequestParam Long checker) {
        returnOrdersService.check(id, checker);
        return Result.success();
    }

    @ApiOperation("退货出库")
    @SystemLog(title = "退货出库")
    @PostMapping("/out/{id}")
    public Result<Void> out(@PathVariable Long id, @RequestParam Long ender) {
        returnOrdersService.out(id, ender);
        return Result.success();
    }

    @ApiOperation("根据ID获取退货订单")
    @GetMapping("/get/{id}")
    public Result<ReturnOrders> getById(@PathVariable Long id) {
        return Result.success(returnOrdersService.getById(id));
    }

    @ApiOperation("删除退货订单")
    @SystemLog(title = "删除退货订单")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        returnOrdersService.delete(id);
        return Result.success();
    }
}