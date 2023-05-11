package com.anze.service.impl;

import com.anze.domain.entity.User;
import com.anze.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.anze.service.UserService;

/**
 * ?û??(User)表服务实现类
 *
 * @author makejava
 * @since 2023-05-13 15:24:12
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
