package com.erp.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.pojo.Emp;

import java.util.List;

/**
 * 员工服务接口
 *
 * @author baoyang
 */
public interface EmpService {

    /**
 * 获取所有在职员工
 */
    List<Emp> list();

    /**
 * 分页查询员工
 */
    Page<Emp> page(Integer page, Integer rows, String username, String name, Long deptId);

    /**
 * 添加员工
 */
    void add(Emp emp);

    /**
 * 更新员工
 */
    void update(Emp emp);

    /**
 * 删除员工（逻辑删除）
 */
    void delete(Long id);

    /**
 * 根据ID获取员工
 */
    Emp getById(Long id);

    /**
 * 重置密码
 */
    void resetPwd(Long id);

    /**
 * 根据用户名查询员工
 */
    Emp getByUsername(String username);

    /**
 * 根据用户名和密码验证登录
 */
    Emp findEmpByUsernameAndPwd(String username, String pwd);

    /**
 * 根据ID验证密码
 */
    Emp checkEmpByEidAndPwd(Long eid, String pwd);

    /**
 * 更新密码
 */
    void updatePwdByEid(Long eid, String pwd);
}