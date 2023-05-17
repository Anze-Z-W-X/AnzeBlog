package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.User;
import com.anze.domain.vo.AdminUserInfoVo;
import com.anze.domain.vo.RoutersVo;

public interface LoginService {
    ResponseResult login(User user);

    ResponseResult<AdminUserInfoVo> getInfo();

    ResponseResult<RoutersVo> getRouters();

    ResponseResult logout();
}
