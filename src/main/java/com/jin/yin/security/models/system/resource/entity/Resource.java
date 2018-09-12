package com.jin.yin.security.models.system.resource.entity;

import com.jin.yin.security.models.system.user.entity.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description: 树形结构。资源
 */
@Data
public class Resource implements Serializable {
    /**
     * 权限id
     */
    private Integer id;
    /**
     * 权限名称
     */
    private String name;
    /**
     * 权限url
     */
    private String url;
    /**
     * 权限等级
     */
    private String grade;
    /** 菜单顺序*/
    private Integer order;
    /**
     * 父级资源id
     */
    private Integer parentId;
    /**
     * 子资源
     */
    private List<Resource> childes = new ArrayList();


    public Resource toTree() {
        List<Resource> childes = new ArrayList();
        for (Resource child : getChildes()) {
                childes.add(child.toTree());
        }
        this.setChildes(childes);
        return this;
    }
}
