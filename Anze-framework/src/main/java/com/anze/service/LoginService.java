package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.User;

public interface LoginService {
    ResponseResult login(User user);
}
