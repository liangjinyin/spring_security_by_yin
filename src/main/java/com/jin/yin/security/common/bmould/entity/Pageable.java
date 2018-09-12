package com.jin.yin.security.common.bmould.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: liangjinyin
 * @date:  2018/8/27 20:37
 * @description: 自定义page类
 */
@Data
public class Pageable implements Serializable{

    private Integer pageSize = 10;
    private Integer index = 1;
    private String sortParam = "id";
    private String sort = "DESC";
}
