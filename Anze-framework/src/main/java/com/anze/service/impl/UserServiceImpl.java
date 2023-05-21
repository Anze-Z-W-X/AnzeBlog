package com.anze.service.impl;

import com.anze.domain.ResponseResult;
import com.anze.domain.dto.UserDto;
import com.anze.domain.entity.Role;
import com.anze.domain.entity.User;
import com.anze.domain.entity.UserRole;
import com.anze.domain.vo.PageVo;
import com.anze.domain.vo.UserInfoVo;
import com.anze.domain.vo.UserVo;
import com.anze.enums.AppHttpCodeEnum;
import com.anze.exception.SystemException;
import com.anze.mapper.UserMapper;
import com.anze.service.RoleService;
import com.anze.service.UserRoleService;
import com.anze.utils.BeanCopyUtils;
import com.anze.utils.SecurityUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.anze.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * ?û??(User)表服务实现类
 *
 * @author makejava
 * @since 2023-05-13 15:24:12
 */
@Service("userService")
@Transactional
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

    @Override
    public ResponseResult getUserList(Integer pageNum, Integer pageSize, String userName, String phonenumber, String status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(userName),User::getUserName,userName).eq(StringUtils.hasText(phonenumber),User::getPhonenumber,phonenumber).eq(StringUtils.hasText(status),User::getStatus,status);

        Page<User> page = new Page<>(pageNum,pageSize);
        page(page,wrapper);
        List<User> users = page.getRecords();

        return ResponseResult.okResult(new PageVo(users,page.getTotal()));
    }

    @Autowired
    private UserRoleService userRoleService;
    @Override
    public ResponseResult addUser(UserDto userDto) {
        if(Objects.isNull(userDto.getUserName()))throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        List<User> list = list();
        list.forEach(user1 -> {if(user.getUserName().equals(user1.getUserName()))throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);});
        list.forEach(user1 -> {if(user.getPhonenumber().equals(user1.getPhonenumber()))throw new SystemException(AppHttpCodeEnum.PHONENUMBER_EXIST);});
        list.forEach(user1 -> {if(user.getEmail().equals(user1.getEmail()))throw new SystemException(AppHttpCodeEnum.EMAIL_EXIST);});
        String encode = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encode);
        save(user);

        List<Long> roleIds = userDto.getRoleIds();
        roleIds.forEach(roleId->userRoleService.save(new UserRole(user.getId(),roleId)));

        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult delUser(Long id) {
        getBaseMapper().deleteById(id);
        return ResponseResult.okResult();
    }

    @Autowired
    private RoleService roleService;
    @Override
    public ResponseResult getUserById(Long id) {
        User user = getById(id);
        if(Objects.isNull(user))throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        List<UserRole> userRoles = userRoleService.list(wrapper);
        List<Long> roleIds = userRoles.stream()
                .map(UserRole::getRoleId).toList();

        List<Role> roles = roleService.list();
        return ResponseResult.okResult(new UserVo(roleIds,roles,user));
    }

    @Override
    public ResponseResult updateUser(UserDto userDto) {
        if(Objects.isNull(userDto))throw new SystemException(AppHttpCodeEnum.SYSTEM_ERROR);
        User user = BeanCopyUtils.copyBean(userDto, User.class);
        updateById(user);

        List<Long> roleIds = userDto.getRoleIds();
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,user.getId());
        userRoleService.getBaseMapper().delete(wrapper);
        if(!Objects.isNull(roleIds)) {
            roleIds.forEach(roleId -> userRoleService.save(new UserRole(user.getId(), roleId)));
        }
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
