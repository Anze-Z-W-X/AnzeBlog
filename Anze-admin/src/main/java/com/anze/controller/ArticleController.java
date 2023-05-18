package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.ArticleDto;
import com.anze.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult writeBlog(@RequestBody ArticleDto articleDto){
        return articleService.addBlogArticle(articleDto);
    }
}
