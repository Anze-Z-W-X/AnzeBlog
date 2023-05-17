package com.anze.service.impl;

import com.anze.domain.entity.Tag;
import com.anze.mapper.TagMapper;
import com.anze.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("tagService")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
