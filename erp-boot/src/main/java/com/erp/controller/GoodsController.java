package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.annotation.SystemLog;
import com.erp.service.GoodsService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "商品管理")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation("获取所有商品")
    @GetMapping("/list")
    public Result<List<Goods>> list() {
        return Result.success(goodsService.list());
    }

    @ApiOperation("分页查询商品")
    @GetMapping("/page")
    public Result<PageResult<Goods>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) String name) {
        Page<Goods> result = goodsService.page(page, rows, name);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("添加商品")
    @SystemLog(title = "添加商品")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody Goods goods) {
        goodsService.add(goods);
        return Result.success();
    }

    @ApiOperation("更新商品")
    @SystemLog(title = "更新商品")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody Goods goods) {
        goodsService.update(goods);
        return Result.success();
    }

    @ApiOperation("删除商品")
    @SystemLog(title = "删除商品")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        goodsService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据ID获取商品")
    @GetMapping("/get/{id}")
    public Result<Goods> getById(@PathVariable Long id) {
        return Result.success(goodsService.getById(id));
    }
}