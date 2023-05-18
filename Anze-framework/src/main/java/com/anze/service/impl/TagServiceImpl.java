package com.anze.service.impl;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.TagListDto;
import com.anze.domain.entity.Tag;
import com.anze.domain.vo.PageVo;
import com.anze.domain.vo.TagVo;
import com.anze.enums.AppHttpCodeEnum;
import com.anze.exception.SystemException;
import com.anze.mapper.TagMapper;
import com.anze.service.TagService;
import com.anze.utils.BeanCopyUtils;
import com.anze.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public ResponseResult<PageVo> pageTagList(Integer pageNum, Integer pageSize, TagListDto tagListDto) {
        //分页查询
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(tagListDto.getName()),Tag::getName,tagListDto.getName());
        wrapper.eq(StringUtils.hasText(tagListDto.getRemark()),Tag::getRemark,tagListDto.getRemark());
        Page<Tag> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,wrapper);
        //封装数据返回
        PageVo pageVo= new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addTag(Tag tag) {
        if(!StringUtils.hasText(tag.getName()))throw new SystemException(AppHttpCodeEnum.TAG_NAME_NOT_NULL);
        if(!StringUtils.hasText(tag.getRemark()))throw new SystemException(AppHttpCodeEnum.TAG_REMARK_NOT_NULL);
        tag.setCreateBy(SecurityUtils.getUserId());
        tag.setCreateTime(new Date());
        tag.setDelFlag(0);
        tag.setUpdateBy(SecurityUtils.getUserId());
        tag.setUpdateTime(new Date());
        save(tag);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delTag(Long id) {
        LambdaUpdateWrapper<Tag> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Tag::getId,id).set(Tag::getDelFlag,1).set(Tag::getUpdateBy,SecurityUtils.getUserId()).set(Tag::getUpdateTime,new Date());
        boolean update = update(null,wrapper);
        System.out.println(update);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult<TagVo> getTagInfoById(Long id) {
        Tag tag = tagMapper.selectById(id);
        TagVo tagVo = BeanCopyUtils.copyBean(tag, TagVo.class);
        return ResponseResult.okResult(tagVo);
    }

    @Override
    public ResponseResult updateTagInfo(TagVo tagVo) {
        LambdaUpdateWrapper<Tag> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Tag::getId,tagVo.getId()).set(Tag::getName,tagVo.getName()).set(Tag::getRemark,tagVo.getRemark());

        int update = tagMapper.update(null, wrapper);
        if(update!=1)throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllTag() {
        LambdaQueryWrapper<Tag> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Tag::getDelFlag,0);
        List<Tag> tags = list(wrapper);
        List<TagVo> tagVos = BeanCopyUtils.copyBeanList(tags, TagVo.class);
        return ResponseResult.okResult(tagVos);
    }
}
