package com.anze.mapper;

import com.anze.domain.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * ?˵?Ȩ?ޱ(Menu)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-16 16:07:17
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(@Param("userId") Long id);

    List<Menu> selectAllRouterMenu();

    List<Menu> selectRouterMenuTreeByUserId(Long userId);
}
