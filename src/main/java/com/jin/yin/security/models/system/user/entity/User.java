package com.jin.yin.security.models.system.user.entity;

import com.jin.yin.security.models.system.resource.entity.Resource;
import com.jin.yin.security.models.system.role.entity.RoleResource;
import com.jin.yin.security.models.system.role.entity.RoleUser;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Data
public class User implements Serializable {

    /** 用户id*/
    private Integer id;
    /** 用户名*/
    @NotNull(message = "用户名不能为空")
    private String username;
    /** 密码*/
    @NotBlank(message = "密码不能为空")
    private String password;
    /** 用户角色关系*/
    private Set<RoleUser> roles = new HashSet();
    /** 用户的资源*/
    private Set<Integer> resourceIds = new HashSet<>();
    @NotBlank
    /** 手机号*/
    private String mobile;

    public Set<Integer> getAllResourceIds(){
        forEachResource(resource -> resourceIds.add(resource.getId()));
        return resourceIds;
    }

    private void forEachResource(Consumer<Resource> consumer) {
        for (RoleUser role : roles) {
            for (RoleResource resource : role.getRole().getResources()) {
                consumer.accept(resource.getResource());
            }
        }
    }
}
