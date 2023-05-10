package com.anze.service;

import com.anze.domain.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.anze.domain.entity.Link;


/**
 * ????(Link)表服务接口
 *
 * @author makejava
 * @since 2023-05-10 19:07:57
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}

