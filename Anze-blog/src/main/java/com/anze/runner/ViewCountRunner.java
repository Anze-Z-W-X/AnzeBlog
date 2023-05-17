package com.anze.runner;

import com.anze.domain.entity.Article;
import com.anze.mapper.ArticleMapper;
import com.anze.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ViewCountRunner implements CommandLineRunner {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public void run(String... args) throws Exception {
        //查询博客信息  id    viewCount
        List<Article> articles = articleMapper.selectList(null);
        //存储到redis中
        Map<String, Integer> viewCountMap = articles.stream()
                /**
                 *                  new Function<Article, Long>() {
                 *                     @Override
                 *                     public Long apply(Article article) {
                 *                         return article.getId();
                 *                     }
                 *                 }, new Function<Article, Integer>() {
                 *                     @Override
                 *                     public Integer apply(Article article) {
                 *                         return article.getViewCount().intValue();
                 *                     }
                 *                 }
                 *                 换为lambda表达式
                 */
                .collect(Collectors.toMap(article -> article.getId().toString(), article -> article.getViewCount().intValue()));
        redisCache.setCacheMap("article::viewCount",viewCountMap);
    }

}
