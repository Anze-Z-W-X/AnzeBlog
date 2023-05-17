package com.anze.service;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.TagListDto;
import com.anze.domain.entity.Tag;
import com.anze.domain.vo.PageVo;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * ??ǩ(Tag)表服务接口
 *
 * @author makejava
 * @since 2023-05-16 12:12:16
 */
public interface TagService extends IService<Tag> {

    ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto);
}

