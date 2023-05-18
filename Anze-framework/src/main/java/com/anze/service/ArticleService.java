package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.ArticleDto;
import com.anze.domain.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ArticleService extends IService<Article> {

    ResponseResult hotArticleList();

    ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId);

    ResponseResult getArticleDetail(Long id);

    ResponseResult updateViewCount(Long id);

    ResponseResult addBlogArticle(ArticleDto articleDto);
}
