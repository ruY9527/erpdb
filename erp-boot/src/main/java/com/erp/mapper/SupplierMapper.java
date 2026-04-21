package com.erp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.pojo.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 供应商/客户 Mapper 接口
 *
 * @author baoyang
 */
public interface SupplierMapper extends BaseMapper<Supplier> {

    List<Supplier> findSupplierByType(String type);
    
    void add(Supplier supplier);
    
    Supplier getPojoById(Long suid);
    
    void updateSupplier(Supplier supplier);
    
    void deleteInUpdate(Long suid);
    
    List<Supplier> findBySupplier(Supplier supplier);
    
    Integer selectCountByType(String type);
    
    List<Supplier> selectByNameAndType(@Param("name") String name, @Param("type") String type);
}