package com.jin.yin.security.models.system.role.entity;

import com.jin.yin.security.models.system.user.entity.User;
import lombok.Data;

/**
 * @author: liangjinyin
 * @Date: 2018-09-07
 * @Description: 角色用户关系表
 */
@Data
public class RoleUser {
    /** 唯一标识*/
    private Integer id;
    /** userID*/
    private Integer userId;
    /** roleid*/
    private Integer roleId;
    /** 备注*/
    private String remark;

}
