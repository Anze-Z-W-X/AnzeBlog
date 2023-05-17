package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.LoginUser;
import com.anze.domain.entity.User;
import com.anze.domain.vo.AdminUserInfoVo;
import com.anze.domain.vo.MenuVo;
import com.anze.domain.vo.RoutersVo;
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
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){

        return loginService.getInfo();
    }

    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        return loginService.getRouters();
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }
}
