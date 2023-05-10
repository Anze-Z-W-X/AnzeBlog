package com.anze.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkVo {
    private Long id;


    private String name;

    private String logo;

    private String description;

    private String address;

}
