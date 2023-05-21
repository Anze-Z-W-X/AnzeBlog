package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.UserDto;
import com.anze.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * ?û??(User)表服务接口
 *
 * @author makejava
 * @since 2023-05-13 15:24:11
 */
public interface UserService extends IService<User> {

    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);

    ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status);

    ResponseResult addUser(UserDto userDto);

    ResponseResult delUser(Long id);

    ResponseResult getUserById(Long id);

    ResponseResult updateUser(UserDto userDto);
}

