package com.anze.service;

import com.anze.domain.entity.Menu;
import com.anze.domain.vo.MenuVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * ?˵?Ȩ?ޱ(Menu)表服务接口
 *
 * @author makejava
 * @since 2023-05-16 16:07:18
 */
public interface MenuService extends IService<Menu> {

    List<String> selectPermsKeyByUserId(Long id);

    List<MenuVo> selectRouterMenuTreeByUserId(Long userId);
}

