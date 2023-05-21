package com.anze.domain.entity;

import java.util.Date;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * ?????(Category)表实体类
 *
 * @author makejava
 * @since 2023-05-09 14:52:08
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("az_category")
public class Category  {
    @TableId
    private Long id;

    private String name;

    private Long pid;

    private String description;

    private String status;

    @TableField(fill = FieldFill.INSERT)    //自动填充,具体实现靠com.anze.handler.mybatisplus.MyMetaObjectHandler
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    private Integer delFlag;

}

