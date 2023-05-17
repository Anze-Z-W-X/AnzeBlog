package com.anze.service.impl;

import com.anze.constants.SystemConstants;
import com.anze.domain.entity.Menu;
import com.anze.domain.vo.MenuVo;
import com.anze.mapper.MenuMapper;
import com.anze.service.MenuService;
import com.anze.utils.BeanCopyUtils;
import com.anze.utils.SecurityUtils;
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
            return perms;
        }
        //否则返回所具有的权限
        return getBaseMapper().selectPermsByUserId(id);
    }

    @Override
    public List<MenuVo> selectRouterMenuTreeByUserId(Long userId) {
        MenuMapper menuMapper = getBaseMapper();
        List<Menu> menus = null;
        //判断是否为管理员
        if(SecurityUtils.isAdmin()){
            //如果是 返回所有符合要求的MenuVo
            menus = menuMapper.selectAllRouterMenu();
        }
        //否则返回当前用户的MenuVo
        else {
            menus = menuMapper.selectRouterMenuTreeByUserId(userId);
        }
        //转为Vo
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        //构建tree(set入children的值)
        //找出第一层的菜单，然后找它们的子菜单设置到children属性中
        menuVos = buildMenuVoTree(menuVos,0L);
        return menuVos;
    }

    private List<MenuVo> buildMenuVoTree(List<MenuVo> menuVos,Long parentId) {
        List<MenuVo> menuVosTree = menuVos.stream()
                .filter(menuVo -> menuVo.getParentId().equals(parentId))
                .map(menuVo -> menuVo.setChildren(getChildren(menuVo, menuVos)))
                .collect(Collectors.toList());
        return menuVosTree;
    }

    /**
     * 获取传入菜单的子menu
     *
     * @param menuVo:父节点
     * @param menuVos:从这其中查出父节点的子List
     * @return
     */
    private List<MenuVo> getChildren(MenuVo menuVo, List<MenuVo> menuVos) {
        List<MenuVo> collect = menuVos.stream()
                .filter(m -> m.getParentId().equals(menuVo.getId()))
                .map(m->m.setChildren(getChildren(m,menuVos)))
                .collect(Collectors.toList());
        return collect;
    }
}
