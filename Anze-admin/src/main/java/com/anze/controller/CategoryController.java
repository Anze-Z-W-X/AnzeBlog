package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.Category;
import com.anze.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listAllCategory")
    public ResponseResult getAllCategoryList(){
        return categoryService.getAllCategoryList();
    }

    @GetMapping("/export")
    @PreAuthorize("@ps.hasPermission('content:category:export')")
    public void export(HttpServletResponse response){
        categoryService.export(response);
    }

    @GetMapping("/list")
    public ResponseResult getCategoryListByPage(Integer pageNum,Integer pageSize,String name,String status){
        return categoryService.getCategoryListByPage(pageNum,pageSize,name,status);
    }

    @PostMapping
    public ResponseResult addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/{id}")
    public ResponseResult getCategoryById(@PathVariable("id")Long id){
        return categoryService.getCategoryById(id);
    }

    @PutMapping
    public ResponseResult updateCategory(@RequestBody Category category){
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delCategoryById(@PathVariable("id")Long id){
        return categoryService.delCategoryById(id);
    }
}
