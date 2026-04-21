package com.erp.mapper;

import com.erp.pojo.WayBillDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 运单详情Mapper
 *
 * @author baoyang
 */
@Mapper
public interface WaybilldetailMapper {

    List<WayBillDetail> findByWaybillsn(String waybillsn);

    WayBillDetail findBySn(Long sn);
}