package com.anze.service.impl;

import com.anze.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anze.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import com.anze.service.RoleService;

/**
 * ??ɫ??Ϣ?(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-05-16 16:11:45
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
