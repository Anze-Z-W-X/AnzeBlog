package com.anze.service.impl;

import com.anze.domain.entity.UserRole;
import com.anze.mapper.UserRoleMapper;
import com.anze.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * ?û??ͽ?ɫ?????(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2023-05-21 13:47:55
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
