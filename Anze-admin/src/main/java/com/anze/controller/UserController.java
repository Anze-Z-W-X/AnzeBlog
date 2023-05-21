package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.UserDto;
import com.anze.service.UserService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult getUserList(Integer pageNum,Integer pageSize,String userName,String phonenumber,String status){
        return userService.getUserList(pageNum,pageSize,userName,phonenumber,status);
    }

    @PostMapping
    public ResponseResult addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delUser(@PathVariable("id")Long id){
        return userService.delUser(id);
    }

    @GetMapping("/{id}")
    public ResponseResult getUserById(@PathVariable("id")Long id){
        return userService.getUserById(id);
    }

    @PutMapping
    public ResponseResult updateUser(@RequestBody UserDto userDto){
        return userService.updateUser(userDto);
    }
}
