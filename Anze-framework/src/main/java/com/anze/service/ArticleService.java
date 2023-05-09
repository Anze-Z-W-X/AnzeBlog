package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();
}
