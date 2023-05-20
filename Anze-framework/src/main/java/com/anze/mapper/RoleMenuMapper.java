package com.anze.mapper;

import com.anze.domain.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * ??ɫ?Ͳ˵??????(RoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-20 17:38:56
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    void deleteMenu(Long id);
}
