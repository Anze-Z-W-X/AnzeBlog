package com.anze.service.impl;

import com.anze.domain.entity.Article;
import com.anze.mapper.ArticleMapper;
import com.anze.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {
}
