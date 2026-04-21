package com.erp.mapper;

import com.erp.pojo.WayBill;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 运单Mapper
 *
 * @author baoyang
 */
@Mapper
public interface WaybillMapper {

    WayBill findByOrdersId(Long ordersid);

    List<WayBill> findAll(WayBill wayBill);

    void add(WayBill wayBill);

    WayBill getPojoById(Long sn);

    void update(WayBill wayBill);

    void delete(Long sn);

    Integer selectCount();
}