package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.RoleDto;
import com.anze.domain.dto.RoleDto2;
import com.anze.domain.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * ??ɫ??Ϣ?(Role)表服务接口
 *
 * @author makejava
 * @since 2023-05-16 16:11:44
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKetByUserId(Long id);

    ResponseResult getRoleList(Integer pageNum, Integer pageSize, String roleName, String status);

    ResponseResult changeStatus(RoleDto roleDto);

    ResponseResult addRole(RoleDto roleDto);

    ResponseResult getRole(Long id);

    ResponseResult updateRole(RoleDto2 roleDto);

    ResponseResult deleteRoleById(Long id);
}

