package com.anze.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * ?û??(User)表实体类
 *
 * @author makejava
 * @since 2023-05-11 09:17:19
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User  {
    private Long id;

    private String userName;

    private String nickName;

    private String password;

    private String type;

    private String status;

    private String email;

    private String phonenumber;

    private String sex;

    private String avatar;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer delFlag;

}

