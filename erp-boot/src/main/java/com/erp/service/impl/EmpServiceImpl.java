package com.erp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.mapper.EmpMapper;
import com.erp.pojo.Emp;
import com.erp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 员工服务实现类
 *
 * @author baoyang
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Emp> list() {
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getState, Emp.USE);
        return empMapper.selectList(wrapper);
    }

    @Override
    public Page<Emp> page(Integer page, Integer rows, String username, String name, Long deptId) {
        Page<Emp> pageObj = new Page<>(page, rows);
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getState, Emp.USE);

        if (username != null && !username.isEmpty()) {
            wrapper.like(Emp::getUsername, username);
        }
        if (name != null && !name.isEmpty()) {
            wrapper.like(Emp::getName, name);
        }
        if (deptId != null) {
            wrapper.eq(Emp::getDeptId, deptId);
        }

        return empMapper.selectPage(pageObj, wrapper);
    }

    @Override
    public void add(Emp emp) {
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getUsername, emp.getUsername());
        if (empMapper.selectCount(wrapper) > 0) {
            throw new RuntimeException("用户名已存在");
        }

        emp.setState(Emp.USE);
        if (emp.getPwd() == null || emp.getPwd().isEmpty()) {
            emp.setPwd(passwordEncoder.encode("123456"));
        } else {
            emp.setPwd(passwordEncoder.encode(emp.getPwd()));
        }
        empMapper.insert(emp);
    }

    @Override
    public void update(Emp emp) {
        if (emp.getPwd() != null && !emp.getPwd().isEmpty()) {
            if (!emp.getPwd().startsWith("$2a$")) {
                emp.setPwd(passwordEncoder.encode(emp.getPwd()));
            }
        }
        empMapper.updateById(emp);
    }

    @Override
    public void delete(Long id) {
        LambdaUpdateWrapper<Emp> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Emp::getEid, id).set(Emp::getState, Emp.NO_USE);
        empMapper.update(null, wrapper);
    }

    @Override
    public Emp getById(Long id) {
        return empMapper.selectById(id);
    }

    @Override
    public void resetPwd(Long id) {
        String encodedPwd = passwordEncoder.encode("123456");
        LambdaUpdateWrapper<Emp> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Emp::getEid, id).set(Emp::getPwd, encodedPwd);
        empMapper.update(null, wrapper);
    }

    @Override
    public Emp getByUsername(String username) {
        LambdaQueryWrapper<Emp> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Emp::getUsername, username);
        return empMapper.selectOne(wrapper);
    }

    @Override
    public Emp findEmpByUsernameAndPwd(String username, String pwd) {
        Emp emp = getByUsername(username);
        if (emp != null && passwordEncoder.matches(pwd, emp.getPwd())) {
            return emp;
        }
        return null;
    }

    @Override
    public Emp checkEmpByEidAndPwd(Long eid, String pwd) {
        Emp emp = getById(eid);
        if (emp != null && passwordEncoder.matches(pwd, emp.getPwd())) {
            return emp;
        }
        return null;
    }

    @Override
    public void updatePwdByEid(Long eid, String pwd) {
        String encodedPwd = passwordEncoder.encode(pwd);
        LambdaUpdateWrapper<Emp> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Emp::getEid, eid).set(Emp::getPwd, encodedPwd);
        empMapper.update(null, wrapper);
    }
}