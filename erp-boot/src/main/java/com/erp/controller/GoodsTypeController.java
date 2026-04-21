package com.erp.controller;

import com.erp.annotation.SystemLog;
import com.erp.service.GoodsTypeService;
import com.erp.utils.Result;
import com.erp.pojo.GoodsType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商品类型管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "商品类型管理")
@RestController
@RequestMapping("/goodsType")
public class GoodsTypeController {

    @Autowired
    private GoodsTypeService goodsTypeService;

    @ApiOperation("获取所有商品类型")
    @GetMapping("/list")
    public Result<List<GoodsType>> list() {
        return Result.success(goodsTypeService.list());
    }

    @ApiOperation("添加商品类型")
    @SystemLog(title = "添加商品类型")
    @PostMapping("/add")
    public Result<Void> add(@RequestBody GoodsType goodsType) {
        goodsTypeService.add(goodsType);
        return Result.success();
    }

    @ApiOperation("更新商品类型")
    @SystemLog(title = "更新商品类型")
    @PostMapping("/update")
    public Result<Void> update(@RequestBody GoodsType goodsType) {
        goodsTypeService.update(goodsType);
        return Result.success();
    }

    @ApiOperation("删除商品类型")
    @SystemLog(title = "删除商品类型")
    @PostMapping("/delete/{id}")
    public Result<Void> delete(@PathVariable Long id) {
        goodsTypeService.delete(id);
        return Result.success();
    }

    @ApiOperation("根据ID获取商品类型")
    @GetMapping("/get/{id}")
    public Result<GoodsType> getById(@PathVariable Long id) {
        return Result.success(goodsTypeService.getById(id));
    }
}