package com.anze.service.impl;

import com.anze.constants.SystemConstants;
import com.anze.domain.ResponseResult;
import com.anze.domain.vo.LinkVo;
import com.anze.utils.BeanCopyUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.anze.domain.entity.Link;
import com.anze.mapper.LinkMapper;
import org.springframework.stereotype.Service;
import com.anze.service.LinkService;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.Link_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //封装成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}
