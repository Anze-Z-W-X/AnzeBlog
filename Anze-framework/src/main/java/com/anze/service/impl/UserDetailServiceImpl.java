package com.anze.service.impl;

import com.anze.constants.SystemConstants;
import com.anze.domain.entity.LoginUser;
import com.anze.domain.entity.User;
import com.anze.mapper.MenuMapper;
import com.anze.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        //判断是否查到用户，如果没查到抛出异常
        if(Objects.isNull(user))throw new RuntimeException(("用户不存在!"));
        //返回用户信息
        //TODO 查询权限信息封装
        //只有后台用户才需要查询权限
        if(user.getType().equals(SystemConstants.ADMIN)){
            List<String> strings = menuMapper.selectPermsByUserId(user.getId());
            return new LoginUser(user,strings);
        }
        return new LoginUser(user);
    }
}
