package com.anze.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private Long roleId;

    private String status;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String remark;

    private List<Long> menuIds;
}
