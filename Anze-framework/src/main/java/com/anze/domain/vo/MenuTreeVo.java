package com.anze.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class MenuTreeVo {
    private Long id;
    @TableField()
    private String label;
    private Long parentId;
    private List<MenuTreeVo> children;
}
