package com.anze.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MenuVo {
    private Long id;

    private String menuName;

    private Long parentId;

    private Integer orderNum;

    private String path;

    private String component;

    private Integer isFrame;

    private String menuType;

    private String visible;

    private String status;

    private String icon;

    private Date createTime;

    private List<MenuVo> children;
}
