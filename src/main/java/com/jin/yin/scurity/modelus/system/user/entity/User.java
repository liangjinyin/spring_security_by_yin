package com.jin.yin.scurity.modelus.system.user.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
    /** 用户角色*/
    private String roles;
    @NotBlank
    /** 手机号*/
    private String mobile;
}
