package com.anze.service.impl;

import com.anze.constants.SystemConstants;
import com.anze.domain.ResponseResult;
import com.anze.domain.entity.Comment;
import com.anze.domain.vo.CommentVo;
import com.anze.domain.vo.PageVo;
import com.anze.enums.AppHttpCodeEnum;
import com.anze.exception.SystemException;
import com.anze.mapper.CommentMapper;
import com.anze.service.CommentService;
import com.anze.service.UserService;
import com.anze.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * ???۱(Comment)表服务实现类
 *
 * @author makejava
 * @since 2023-05-13 14:47:30
 */
@Service("commentService")
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private UserService userService;

    @Override
    public ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize) {
        //查询对应文章的根评论
        LambdaQueryWrapper<Comment> lambdaQueryWrapper =
                new LambdaQueryWrapper<>();
        //对articleId进行判断
        lambdaQueryWrapper.eq(SystemConstants.ARTICLE_COMMENT.equals(commentType),Comment::getArticleId,articleId);
        //根评论rootId为-1
        lambdaQueryWrapper.eq(Comment::getRootId,-1);

        //评论类型
        lambdaQueryWrapper.eq(Comment::getType,commentType);

        //分页查询
        Page<Comment> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<CommentVo> commentVos = toCommentVoList(page.getRecords());
        //查询所有根评论对应的子评论集合，并且赋值对应的属性
//方法一:     for (CommentVo commentVo : commentVos) {
//            //查询对应子评论
//            List<CommentVo> children = getChildren(commentVo.getId());
//            //赋值
//            commentVo.setChildren(children);
//        }
        commentVos.forEach(commentVo -> commentVo.setChildren(getChildren(commentVo.getId())));
        return ResponseResult.okResult(new PageVo(commentVos,page.getTotal()));
    }

    @Override
    public ResponseResult addComment(Comment comment) {
        //评论内容不能为空
        if(!StringUtils.hasText(comment.getContent()))throw new SystemException(AppHttpCodeEnum.CONTENT_NOT_NULL);
        save(comment);
        return ResponseResult.okResult();
    }

    /**
     * 根据根评论的id查询所对应的子评论的集合
     * @param id
     * @return
     */
    private List<CommentVo> getChildren(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper();
        queryWrapper.eq(Comment::getRootId,id);
        queryWrapper.orderByAsc(Comment::getCreateTime);
        List<Comment> list = list(queryWrapper);
        List<CommentVo> commentVos = toCommentVoList(list);
        return commentVos;
    }

    private List<CommentVo> toCommentVoList(List<Comment> list){
        List<CommentVo> commentVos = BeanCopyUtils.copyBeanList(list, CommentVo.class);
        //遍历vo集合
        for (CommentVo commentVo : commentVos) {
            //通过creatyBy查询用户的昵称并赋值
            String nickName = userService.getById(commentVo.getCreateBy()).getNickName();
            commentVo.setUsername(nickName);
            //通过toCommentUserId查询用户的昵称并赋值
            //如果toCommentUserId不为-1才进行查询
            if(commentVo.getToCommentUserId()!=-1){
                String toCommentUserName = userService.getById(commentVo.getToCommentUserId()).getNickName();
                commentVo.setToCommentUserName(toCommentUserName);
            }
        }
        return commentVos;
    }
}
