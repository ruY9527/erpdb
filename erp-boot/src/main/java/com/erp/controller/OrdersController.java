package com.erp.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.mapper.OrdersMapper;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Orders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "订单管理")
@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersMapper ordersMapper;

    @ApiOperation("根据类型和状态查询订单")
    @GetMapping("/list")
    public Result<List<Orders>> list(
            @RequestParam String type,
            @RequestParam(required = false) String state) {
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Orders::getType, type);
        if (state != null && !state.isEmpty()) {
            wrapper.eq(Orders::getState, state);
        }
        wrapper.orderByDesc(Orders::getCreatetime);
        List<Orders> list = ordersMapper.selectList(wrapper);
        return Result.success(list);
    }

    @ApiOperation("分页查询订单")
    @GetMapping("/page")
    public Result<PageResult<Orders>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String state) {
        Page<Orders> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Orders> wrapper = new LambdaQueryWrapper<>();

        if (type != null && !type.isEmpty()) {
            wrapper.eq(Orders::getType, type);
        }
        if (state != null && !state.isEmpty()) {
            wrapper.eq(Orders::getState, state);
        }
        wrapper.orderByDesc(Orders::getCreatetime);

        Page<Orders> result = ordersMapper.selectPage(pageObj, wrapper);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加订单")
    @SystemLog(title = "添加订单")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Orders orders) {
        orders.setState(Orders.STATE_CREATE);
        ordersMapper.insert(orders);
        return Result.success();
    }

    @ApiOperation("审核订单")
    @SystemLog(title = "审核订单")
    @PostMapping("/check/{id}")
    public Result<Void> check(@PathVariable Long id) {
        Orders orders = new Orders();
        orders.setOid(id);
        orders.setState(Orders.STATE_CHECK);
        ordersMapper.updateById(orders);
        return Result.success();
    }

    @ApiOperation("确认订单")
    @SystemLog(title = "确认订单")
    @PostMapping("/confirm/{id}")
    public Result<Void> confirm(@PathVariable Long id) {
        Orders orders = new Orders();
        orders.setOid(id);
        orders.setState(Orders.STATE_START);
        ordersMapper.updateById(orders);
        return Result.success();
    }

    @ApiOperation("完成订单")
    @SystemLog(title = "完成订单入库")
    @PostMapping("/complete/{id}")
    public Result<Void> complete(@PathVariable Long id) {
        Orders orders = new Orders();
        orders.setOid(id);
        orders.setState(Orders.STATE_END);
        ordersMapper.updateById(orders);
        return Result.success();
    }

    @ApiOperation("根据ID获取订单")
    @GetMapping("/get/{id}")
    public Result<Orders> getById(@PathVariable Long id) {
        Orders orders = ordersMapper.selectById(id);
        return Result.success(orders);
    }
}