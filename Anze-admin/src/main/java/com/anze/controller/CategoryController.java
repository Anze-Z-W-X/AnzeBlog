package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public void export(HttpServletResponse response){
        categoryService.export(response);
    }
}
