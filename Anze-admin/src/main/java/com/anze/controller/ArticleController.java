package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.ArticleDto;
import com.anze.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseResult writeBlog(@RequestBody ArticleDto articleDto){
        return articleService.addBlogArticle(articleDto);
    }

    @GetMapping("/list")
    public ResponseResult getAllList(Integer pageNum,Integer pageSize,String title,String summary){
        return articleService.getAllList(pageNum,pageSize,title,summary);
    }
}
