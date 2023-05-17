package com.anze.service.impl;

import com.anze.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anze.mapper.RoleMapper;
import org.springframework.stereotype.Service;
import com.anze.service.RoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * ??ɫ??Ϣ?(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-05-16 16:11:45
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<String> selectRoleKetByUserId(Long id) {
        //判断是否时管理员，如果是返回集合中只需要有admin
        if(id==1){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        //否则查询用户所具有的角色信息

        return getBaseMapper().selectRoleKeyByUserId(id);
    }
}
