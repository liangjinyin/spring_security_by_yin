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
     * 权限类型
     */
    private String type;
    /**
     * 权限等级
     */
    private String grade;
    /**
     * 父级资源
     */
    private Resource parent;
    /**
     * 子资源
     */
    private List<Resource> childes = new ArrayList();


    public Resource toTree(User user) {
        Set<Integer> resourceIds = user.getResourceIds();
        List<Resource> childes = new ArrayList();
        for (Resource child : getChildes()) {
            if (StringUtils.equalsIgnoreCase(user.getUsername(), "admin") || resourceIds.contains(child.getId())) {
                childes.add(child.toTree(user));
            }
        }
        this.setChildes(childes);
        return this;
    }

    public void addChild(Resource child){
        childes.add(child);
        child.setParent(this);
    }
}
