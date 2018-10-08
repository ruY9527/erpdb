package com.erp.mapper;

import com.yang.erp.pojo.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SupplierMapper {

    //根据type来查询获取信息
    public List<Supplier> findSupplierByType(String type);

    public void add(Supplier supplier);

    public Supplier getPojoById(Long suid);

    public void updateSupplier(Supplier supplier);

    public void deleteInUpdate(Long suid);

    public List<Supplier> findBySupplier(Supplier supplier);

    public Integer selectCountByType(String type);

    public List<Supplier> selectByNameAndType(@Param("name") String name,@Param("type") String type);
}
