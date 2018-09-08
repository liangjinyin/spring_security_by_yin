package com.jin.yin.security.models.system.role.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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
    /** 角色关联的资源*/
    private Set<RoleResource> resources  = new HashSet();
    /** 角色下的用户*/
    private Set<RoleUser> users = new HashSet();
}
