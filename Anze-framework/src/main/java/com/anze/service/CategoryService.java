package com.anze.service;

import com.anze.domain.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.anze.domain.entity.Category;


/**
 * ?????(Category)表服务接口
 *
 * @author makejava
 * @since 2023-05-09 14:52:09
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}

