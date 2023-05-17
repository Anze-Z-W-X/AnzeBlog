package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.LoginUser;
import com.anze.domain.entity.User;
import com.anze.domain.vo.AdminUserInfoVo;
import com.anze.domain.vo.UserInfoVo;
import com.anze.service.LoginService;
import com.anze.service.MenuService;
import com.anze.service.RoleService;
import com.anze.utils.BeanCopyUtils;
import com.anze.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms =  menuService.selectPermsKeyByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKetByUserId(loginUser.getUser().getId());
        //封装数据返回
        UserInfoVo vo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,vo);
        return ResponseResult.okResult(adminUserInfoVo);
    }
}
