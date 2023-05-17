package com.anze.domain.entity;

import java.util.Date;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * ?˵?Ȩ?ޱ(Menu)表实体类
 *
 * @author makejava
 * @since 2023-05-16 16:07:17
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_menu")
public class Menu  {
    //?˵?ID@TableId
    private Long id;

    //?˵??
    private String menuName;
    //???˵?ID
    private Long parentId;
    //??ʾ˳?
    private Integer orderNum;
    //·?ɵ?ַ
    private String path;
    //????·??
    private String component;
    //?Ƿ?Ϊ??????0?? 1?
    private Integer isFrame;
    //?˵????ͣ?MĿ¼ C?˵? F??ť??
    private String menuType;
    //?˵?״̬??0??ʾ 1???أ?
    private String visible;
    //?˵?״̬??0???? 1ͣ?ã?
    private String status;
    //Ȩ?ޱ?ʶ
    private String perms;
    //?˵?ͼ?
    private String icon;
    //?????
    private Long createBy;
    //????ʱ?
    private Date createTime;
    //?????
    private Long updateBy;
    //????ʱ?
    private Date updateTime;
    //??ע
    private String remark;
    
    private String delFlag;

}

