package com.anze.service.impl;

import com.anze.constants.SystemConstants;
import com.anze.domain.ResponseResult;
import com.anze.domain.dto.RoleDto;
import com.anze.domain.dto.RoleDto2;
import com.anze.domain.entity.Role;
import com.anze.domain.entity.RoleMenu;
import com.anze.domain.vo.PageVo;
import com.anze.domain.vo.RoleVo;
import com.anze.enums.AppHttpCodeEnum;
import com.anze.exception.SystemException;
import com.anze.mapper.RoleMenuMapper;
import com.anze.service.RoleMenuService;
import com.anze.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anze.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.anze.service.RoleService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ??ɫ??Ϣ?(Role)表服务实现类
 *
 * @author makejava
 * @since 2023-05-16 16:11:45
 */
@Service("roleService")
@Transactional
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

    @Override
    public ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(roleName),Role::getRoleName,roleName).like(StringUtils.hasText(status),Role::getStatus,status).orderByAsc(Role::getRoleSort);

        Page<Role> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);

        List<Role> records = page.getRecords();
        List<RoleVo> roleVos = BeanCopyUtils.copyBeanList(records, RoleVo.class);

        return ResponseResult.okResult(new PageVo(roleVos,page.getTotal()));
    }

    @Override
    public ResponseResult changeStatus(RoleDto roleDto) {
        RoleMapper roleMapper = getBaseMapper();
        LambdaUpdateWrapper<Role> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Role::getId,roleDto.getRoleId()).set(Role::getStatus,roleDto.getStatus());
        roleMapper.update(null,wrapper);
        return ResponseResult.okResult();
    }

    @Autowired
    private RoleMenuService roleMenuService;
    @Override
    public ResponseResult addRole(RoleDto roleDto) {
        if(Objects.isNull(roleDto))throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        save(role);

        List<Long> menuIds = roleDto.getMenuIds();
        menuIds.forEach(menuId->{
                  roleMenuService.save(new RoleMenu(role.getId(),menuId));
                        });
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getRole(Long id) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        Role role = getBaseMapper().selectById(id);

        RoleVo roleVo = BeanCopyUtils.copyBean(role, RoleVo.class);

        return ResponseResult.okResult(roleVo);
    }

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public ResponseResult updateRole(RoleDto2 roleDto) {
        Role role = BeanCopyUtils.copyBean(roleDto, Role.class);
        updateById(role);

        List<Long> menuIds = roleDto.getMenuIds();
        roleMenuMapper.deleteMenu(role.getId());
        menuIds.forEach(menuId->{
            roleMenuService.save(new RoleMenu(role.getId(),menuId));
        });
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteRoleById(Long id) {
        getBaseMapper().deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllRole() {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getStatus, SystemConstants.STATUS_NORMAL);
        List<Role> roles = list(wrapper);

        return ResponseResult.okResult(roles);
    }
}
