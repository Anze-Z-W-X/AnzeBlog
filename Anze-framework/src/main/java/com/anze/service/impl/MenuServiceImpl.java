package com.anze.service.impl;

import com.anze.constants.SystemConstants;
import com.anze.domain.ResponseResult;
import com.anze.domain.entity.Menu;
import com.anze.domain.entity.RoleMenu;
import com.anze.domain.vo.MenuTreeVo;
import com.anze.domain.vo.MenuVo;
import com.anze.domain.vo.RoleMenuVo;
import com.anze.enums.AppHttpCodeEnum;
import com.anze.exception.SystemException;
import com.anze.mapper.MenuMapper;
import com.anze.service.MenuService;
import com.anze.service.RoleMenuService;
import com.anze.utils.BeanCopyUtils;
import com.anze.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ?˵?Ȩ?ޱ(Menu)表服务实现类
 *
 * @author makejava
 * @since 2023-05-16 16:07:18
 */
@Service("menuService")
@Transactional
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

    @Override
    public ResponseResult getMenuList(String status, String menuName) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(status),Menu::getStatus,status).like(StringUtils.hasText(menuName),Menu::getMenuName,menuName).orderByAsc(Menu::getOrderNum);
        List<Menu> menus = list(wrapper);
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        return ResponseResult.okResult(menuVos);
    }

    @Override
    public ResponseResult addMenu(Menu menu) {
        if(Objects.isNull(menu))throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        save(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMenuById(Long id) {
        MenuMapper menuMapper = getBaseMapper();
        Menu menu = menuMapper.selectById(id);
        MenuVo menuVo = BeanCopyUtils.copyBean(menu, MenuVo.class);
        return ResponseResult.okResult(menuVo);
    }

    @Override
    public ResponseResult updateMenu(Menu menu) {
        if (Objects.isNull(menu))throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        MenuMapper menuMapper = getBaseMapper();
        menuMapper.updateById(menu);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult deleteMenuById(Long id) {
        List<Menu> menus = list();
        List<MenuVo> menuVos = BeanCopyUtils.copyBeanList(menus, MenuVo.class);
        MenuMapper menuMapper = getBaseMapper();
        Menu menu = menuMapper.selectById(id);
        MenuVo menuVo = BeanCopyUtils.copyBean(menu, MenuVo.class);
        List<MenuVo> children = getChildren(menuVo, menuVos);
        if(children.size()!=0)throw new SystemException(AppHttpCodeEnum.HAVE_CHILDREN);
        menuMapper.deleteById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getTreeSelect() {
        List<MenuTreeVo> menuTreeVos = getMenuTreeVoTree();
        return ResponseResult.okResult(menuTreeVos);
    }

    @Autowired
    private RoleMenuService roleMenuService;
    @Override
    public ResponseResult getRoleMenuTreeselect(Long id) {
        List<MenuTreeVo> menuTreeVoTree = getMenuTreeVoTree();
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(RoleMenu::getMenuId).eq(RoleMenu::getRoleId,id);
        List<RoleMenu> roleMenus = roleMenuService.list(wrapper);
        List<Long> checkedKeys = roleMenus.stream()
                .map(RoleMenu::getMenuId).toList();

        return ResponseResult.okResult(new RoleMenuVo(menuTreeVoTree,checkedKeys));
    }

    private List<MenuTreeVo> getMenuTreeVoTree(){
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Menu::getOrderNum);
        List<Menu> menus = list(wrapper);
        List<MenuTreeVo> menuTreeVos = BeanCopyUtils.copyBeanList(menus, MenuTreeVo.class);
        for(int i=0;i<menuTreeVos.size();i++){
            menuTreeVos.get(i).setLabel(menus.get(i).getMenuName());
        }
        menuTreeVos = buildMenuTreeVo(menuTreeVos, 0L);
        return menuTreeVos;
    }

    private List<MenuTreeVo> buildMenuTreeVo(List<MenuTreeVo> menuTreeVos,Long parentId) {
        List<MenuTreeVo> menuVosTree = menuTreeVos.stream()
                .filter(menuTreeVo -> menuTreeVo.getParentId().equals(parentId))
                .map(menuTreeVo -> menuTreeVo.setChildren(getTreeChildren(menuTreeVo, menuTreeVos)))
                .collect(Collectors.toList());
        return menuVosTree;
    }

    private List<MenuTreeVo> getTreeChildren(MenuTreeVo menuTreeVo, List<MenuTreeVo> menuTreeVos) {
        List<MenuTreeVo> collect = menuTreeVos.stream()
                .filter(m -> m.getParentId().equals(menuTreeVo.getId()))
                .map(m -> m.setChildren(getTreeChildren(m, menuTreeVos)))
                .collect(Collectors.toList());
        return collect;
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
