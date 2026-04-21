package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.EmpMapper;
import com.erp.mapper.ReturnOrdersMapper;
import com.erp.mapper.SupplierMapper;
import com.erp.pojo.Emp;
import com.erp.pojo.ReturnOrders;
import com.erp.pojo.Supplier;
import com.erp.service.ReturnOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 退货订单服务实现类
 *
 * @author baoyang
 */
@Service
public class ReturnOrdersServiceImpl implements ReturnOrdersService {

    @Autowired
    private ReturnOrdersMapper returnOrdersMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<ReturnOrders> list(String type, String state) {
        LambdaQueryWrapper<ReturnOrders> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ReturnOrders::getType, type);
        if (state != null && !state.isEmpty()) {
            wrapper.eq(ReturnOrders::getState, state);
        }
        wrapper.orderByDesc(ReturnOrders::getCreatetime);
        List<ReturnOrders> list = returnOrdersMapper.selectList(wrapper);
        fillAssociationData(list);
        return list;
    }

    @Override
    public Page<ReturnOrders> page(Integer page, Integer rows, String type, String state, Long supplierId) {
        Page<ReturnOrders> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<ReturnOrders> wrapper = new LambdaQueryWrapper<>();

        if (type != null && !type.isEmpty()) {
            wrapper.eq(ReturnOrders::getType, type);
        }
        if (state != null && !state.isEmpty()) {
            wrapper.eq(ReturnOrders::getState, state);
        }
        if (supplierId != null) {
            wrapper.eq(ReturnOrders::getSupplierId, supplierId);
        }
        wrapper.orderByDesc(ReturnOrders::getCreatetime);

        Page<ReturnOrders> result = returnOrdersMapper.selectPage(pageObj, wrapper);
        fillAssociationData(result.getRecords());
        return result;
    }

    @Override
    public void add(ReturnOrders returnOrders) {
        returnOrders.setState(ReturnOrders.ON_CHECK);
        returnOrders.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        returnOrdersMapper.insert(returnOrders);
    }

    @Override
    public void check(Long id, Long checker) {
        LambdaUpdateWrapper<ReturnOrders> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ReturnOrders::getRoid, id)
               .set(ReturnOrders::getState, ReturnOrders.HAVE_CHECK)
               .set(ReturnOrders::getChecker, checker)
               .set(ReturnOrders::getChecktime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        returnOrdersMapper.update(null, wrapper);
    }

    @Override
    public void out(Long id, Long ender) {
        LambdaUpdateWrapper<ReturnOrders> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(ReturnOrders::getRoid, id)
               .set(ReturnOrders::getState, ReturnOrders.SALES_RETURN)
               .set(ReturnOrders::getEnder, ender)
               .set(ReturnOrders::getEndtime, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        returnOrdersMapper.update(null, wrapper);
    }

    @Override
    public ReturnOrders getById(Long id) {
        ReturnOrders returnOrders = returnOrdersMapper.selectById(id);
        if (returnOrders != null) {
            fillAssociationData(Collections.singletonList(returnOrders));
        }
        return returnOrders;
    }

    @Override
    public void delete(Long id) {
        returnOrdersMapper.deleteById(id);
    }

    private void fillAssociationData(List<ReturnOrders> list) {
        for (ReturnOrders ro : list) {
            if (ro.getCreateer() != null) {
                Emp creatorEmp = empMapper.selectById(ro.getCreateer());
                ro.setCreateEmp(creatorEmp);
            }
            if (ro.getChecker() != null) {
                Emp checkerEmp = empMapper.selectById(ro.getChecker());
                ro.setCheckEmp(checkerEmp);
            }
            if (ro.getEnder() != null) {
                Emp enderEmp = empMapper.selectById(ro.getEnder());
                ro.setEndEmp(enderEmp);
            }
            if (ro.getSupplierId() != null) {
                Supplier supplier = supplierMapper.selectById(ro.getSupplierId());
                ro.setSupplier(supplier);
            }
        }
    }
}