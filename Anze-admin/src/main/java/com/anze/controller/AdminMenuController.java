package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.Menu;
import com.anze.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/menu")
public class AdminMenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/list")
    public ResponseResult getMenuList(String status,String menuName){
        return menuService.getMenuList(status,menuName);
    }

    @PostMapping
    public ResponseResult addMenu(@RequestBody Menu menu){
        return menuService.addMenu(menu);
    }

    @GetMapping("/{id}")
    public ResponseResult getMenuById(@PathVariable("id")Long id){
        return menuService.getMenuById(id);
    }

    @PutMapping
    public ResponseResult updateMenu(@RequestBody Menu menu){
        return menuService.updateMenu(menu);
    }

    @DeleteMapping("/{menuId}")
    public ResponseResult deleteMenuById(@PathVariable("menuId")Long id){
        return menuService.deleteMenuById(id);
    }

    @GetMapping("/treeselect")
    public ResponseResult getTreeSelect(){
        return menuService.getTreeSelect();
    }

    @GetMapping("/roleMenuTreeselect/{id}")
    public ResponseResult getRoleMenuTreeselect(@PathVariable("id") Long id){
        return menuService.getRoleMenuTreeselect(id);
    }
}
