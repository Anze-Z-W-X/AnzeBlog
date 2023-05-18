package com.anze.controller;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.TagListDto;
import com.anze.domain.entity.Tag;
import com.anze.domain.vo.PageVo;
import com.anze.domain.vo.TagVo;
import com.anze.service.TagService;
import com.anze.utils.BeanCopyUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/content/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping("/list")
    public ResponseResult<PageVo> List(Integer pageNum, Integer pageSize, TagListDto tagListDto){
        return tagService.pageTagList(pageNum,pageSize,tagListDto);
    }

    @PostMapping
    public ResponseResult addTag(@RequestBody TagListDto tagListDto){
        Tag tag = BeanCopyUtils.copyBean(tagListDto, Tag.class);
        return tagService.addTag(tag);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delTag(@PathVariable("id") Long id){
        return tagService.delTag(id);
    }

    @GetMapping("/{id}")
    public ResponseResult<TagVo> getTagInfoById(@PathVariable("id")Long id){
        return tagService.getTagInfoById(id);
    }

    @PutMapping
    public ResponseResult updateTagInfo(@RequestBody TagVo tagVo){
        return tagService.updateTagInfo(tagVo);
    }

    @GetMapping("/listAllTag")
    public ResponseResult listAllTag(){
        return tagService.listAllTag();
    }
}
