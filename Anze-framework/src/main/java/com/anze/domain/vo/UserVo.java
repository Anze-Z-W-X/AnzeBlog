package com.anze.domain.vo;

import com.anze.domain.entity.Role;
import com.anze.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    private List<Long> roleIds;
    private List<Role> roles;
    private User user;
}
