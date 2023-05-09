package com.anze.domain.entity;

import java.util.Date;

import java.io.Serializable;
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

    //?????
    private String name;
    //??????id??????û?и?????Ϊ-1
    private Long pid;
    //???
    private String description;
    //״̬0:????,1?
    private String status;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //ɾ????־??0????δɾ????1??????ɾ?
    private Integer delFlag;

}

