package com.jin.yin.scurity.modelus.system.office.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Data
public class Office implements Serializable {
    /** 权限id*/
    private Integer id;
    /** 权限名称*/
    private String name;
    /** 权限类型*/
    private String type;
    /** 权限等级*/
    private String grade;
}
