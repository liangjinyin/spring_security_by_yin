package com.jin.yin.scurity.modelus.system.role.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Data
public class Role implements Serializable {
    /** 角色id*/
    private Integer id;
    /** 角色名称*/
    private String name;
    /** 角色的数据范围*/
    private String dataScope;
    /** 角色关联的权限*/
    private String offices;
}
