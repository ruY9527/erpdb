package com.erp.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.service.StoredetailService;
import com.erp.utils.PageResult;
import com.erp.utils.Result;
import com.erp.pojo.Storedetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 库存详情管理控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "库存详情管理")
@RestController
@RequestMapping("/storeDetail")
public class StoredetailController {

    @Autowired
    private StoredetailService storedetailService;

    @ApiOperation("获取所有库存详情")
    @GetMapping("/list")
    public Result<List<Storedetail>> list() {
        return Result.success(storedetailService.list());
    }

    @ApiOperation("分页查询库存详情")
    @GetMapping("/page")
    public Result<PageResult<Storedetail>> page(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer rows,
            @RequestParam(required = false) Long storeId,
            @RequestParam(required = false) Long goodsId) {
        Page<Storedetail> result = storedetailService.page(page, rows, storeId, goodsId);
        return Result.success(PageResult.build(result.getTotal(), result.getRecords()));
    }

    @ApiOperation("根据ID获取库存详情")
    @GetMapping("/get/{id}")
    public Result<Storedetail> getById(@PathVariable Long id) {
        return Result.success(storedetailService.getById(id));
    }

    @ApiOperation("库存统计")
    @GetMapping("/stats")
    public Result<Map<String, Object>> stats() {
        return Result.success(storedetailService.stats());
    }
}