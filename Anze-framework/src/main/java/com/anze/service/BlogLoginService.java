package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
