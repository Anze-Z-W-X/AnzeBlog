package com.anze.domain.entity;


import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * ???±?ǩ?????(ArticleTag)表实体类
 *
 * @author makejava
 * @since 2023-05-18 17:16:27
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("az_article_tag")
public class ArticleTag  {
    private Long articleId;
    private Long tagId;

}

