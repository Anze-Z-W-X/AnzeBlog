package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * ???۱(Comment)表服务接口
 *
 * @author makejava
 * @since 2023-05-13 14:47:29
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

