package com.anze.mapper;

import com.anze.domain.entity.ArticleTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


/**
 * ???±?ǩ?????(ArticleTag)表数据库访问层
 *
 * @author makejava
 * @since 2023-05-18 17:16:26
 */
public interface ArticleTagMapper extends BaseMapper<ArticleTag> {

    void deleteByArticleId(Long id);
}
