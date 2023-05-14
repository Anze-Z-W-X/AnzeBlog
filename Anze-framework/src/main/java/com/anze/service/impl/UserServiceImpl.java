package com.anze.service.impl;

import com.anze.domain.ResponseResult;
import com.anze.domain.entity.User;
import com.anze.domain.vo.UserInfoVo;
import com.anze.enums.AppHttpCodeEnum;
import com.anze.exception.SystemException;
import com.anze.mapper.UserMapper;
import com.anze.utils.BeanCopyUtils;
import com.anze.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.anze.service.UserService;
import org.springframework.util.StringUtils;

/**
 * ?û??(User)表服务实现类
 *
 * @author makejava
 * @since 2023-05-13 15:24:12
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public ResponseResult userInfo() {
        //获取用户当前id
        Long userId = SecurityUtils.getUserId();
        //根据用户id查询用户信息
        User user = getById(userId);
        //封装成userInfoVo
        UserInfoVo vo = BeanCopyUtils.copyBean(user,UserInfoVo.class);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult updateUserInfo(User user) {
        updateById(user);
        return ResponseResult.okResult();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseResult register(User user) {
        //对数据进行非空判断
        if(!StringUtils.hasText(user.getUserName()))throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        if(!StringUtils.hasText(user.getPassword()))throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        if(!StringUtils.hasText(user.getNickName()))throw new SystemException(AppHttpCodeEnum.NICKNAME_NOT_NULL);
        if(!StringUtils.hasText(user.getEmail()))throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        //对数据进行是否存在的判断
        if(userNameExist(user.getUserName()))throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        if(userNickNameExist(user.getNickName()))throw new SystemException(AppHttpCodeEnum.NICKNAME_EXIST);
        //加密密码
        String encode = passwordEncoder.encode(user.getPassword());
        user.setPassword(encode);
        //存入数据库
        save(user);
        return ResponseResult.okResult();
    }

    private boolean userNickNameExist(String nickName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getNickName,nickName);
        long count = count(queryWrapper);
        return count>0;
    }

    private boolean userNameExist(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,userName);
        long count = count(queryWrapper);
        return count>0;
    }
}
