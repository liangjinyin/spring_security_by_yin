package com.jin.yin.security.models.system.role.entity;

import com.jin.yin.security.models.system.resource.entity.Resource;
import lombok.Data;

/**
 * @author: liangjinyin
 * @Date: 2018-09-07
 * @Description: 角色资源关系表
 */
@Data
public class RoleResource {
    /** id 唯一标识*/
    private Integer id;
    /** 角色id*/
    private Role role;
    /** 资源id*/
    private Resource resource;
    /** 备注*/
    private String remark;
}
