package com.erp.controller;

import com.erp.annotation.SystemLog;
import com.erp.service.EmpService;
import com.erp.security.JwtUtils;
import com.erp.utils.Result;
import com.erp.pojo.Emp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 *
 * @author baoyang
 */
@Slf4j
@Api(tags = "登录管理")
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @Autowired
    private JwtUtils jwtUtils;

    @ApiOperation("用户登录")
    @SystemLog(title = "用户登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String pwd = params.get("pwd");

        if (username == null || pwd == null) {
            return Result.fail("用户名或密码不能为空");
        }

        // 使用BCrypt验证密码
        Emp emp = empService.findEmpByUsernameAndPwd(username, pwd);
        if (emp == null) {
            return Result.fail("用户名或密码错误");
        }

        if (!Emp.USE.equals(emp.getState())) {
            return Result.fail("用户已被禁用");
        }

        String token = jwtUtils.generateToken(emp.getEid(), emp.getUsername());

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("emp", emp);

        log.info("用户登录成功: {}", username);
        return Result.success("登录成功", data);
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping("/user/info")
    public Result<Emp> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtils.getUserId(token);

        if (userId == null) {
            return Result.fail("获取用户信息失败");
        }

        Emp emp = empService.getById(userId);
        return Result.success(emp);
    }

    @ApiOperation("修改密码")
    @SystemLog(title = "修改密码")
    @PostMapping("/password/update")
    public Result<String> updatePassword(@RequestBody Map<String, String> params,
                                        @RequestHeader("Authorization") String authHeader) {
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");

        if (oldPwd == null || newPwd == null) {
            return Result.fail("参数不完整");
        }

        String token = authHeader.replace("Bearer ", "");
        Long userId = jwtUtils.getUserId(token);

        if (userId == null) {
            return Result.fail("用户信息获取失败");
        }

        // 验证旧密码
        Emp emp = empService.checkEmpByEidAndPwd(userId, oldPwd);
        if (emp == null) {
            return Result.fail("原密码错误");
        }

        // 更新密码
        empService.updatePwdByEid(userId, newPwd);
        log.info("用户修改密码成功: {}", emp.getUsername());

        return Result.success("密码修改成功");
    }
}