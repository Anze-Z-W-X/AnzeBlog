package com.anze.mapper;

import com.anze.domain.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-16 16:11:43
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<String> selectRoleKeyByUserId(@Param("userId") Long id);
}
