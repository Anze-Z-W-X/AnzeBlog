package com.anze.service.impl;

import com.anze.constants.SystemConstants;
import com.anze.domain.entity.Menu;
import com.anze.mapper.MenuMapper;
import com.anze.service.MenuService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ?˵?Ȩ?ޱ(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-05-16 16:07:18
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Override
    public List<String> selectPermsKeyByUserId(Long id) {
        //如果是管理员返回所有的权限
        if(id==1L){
            LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
            wrapper.in(Menu::getMenuType, SystemConstants.MENU,SystemConstants.BUTTON);
            wrapper.eq(Menu::getStatus,SystemConstants.STATUS_NORMAL);
            List<Menu> menus = list(wrapper);
            List<String> perms = menus.stream()
                    .map(Menu::getPerms).collect(Collectors.toList());
        }
        //否则返回所具有的权限
        else {
            
        }
        return null;
    }
}
