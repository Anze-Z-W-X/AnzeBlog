package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.RoleDto;
import com.anze.domain.dto.RoleDto2;
import com.anze.domain.entity.Role;
import com.anze.service.RoleService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/list")
    public ResponseResult getRoleList(Integer pageNum,Integer pageSize,String roleName,String status){
        return roleService.getRoleList(pageNum,pageSize,roleName,status);
    }

    @PutMapping("/changeStatus")
    public ResponseResult changeStatus(@RequestBody RoleDto role){
        return roleService.changeStatus(role);
    }

    @PostMapping
    public ResponseResult addRole(@RequestBody RoleDto roleDto){
        return roleService.addRole(roleDto);
    }

    @GetMapping("/{id}")
    public ResponseResult getRole(@PathVariable("id")Long id){
        return roleService.getRole(id);
    }

    @PutMapping
    public ResponseResult updateRole(@RequestBody RoleDto2 roleDto){
        return roleService.updateRole(roleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseResult deleteRoleById(@PathVariable("id")Long id){
        return roleService.deleteRoleById(id);
    }
}
