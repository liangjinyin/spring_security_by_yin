package com.jin.yin.security.models.system.resource.dao;

import com.jin.yin.security.models.system.resource.entity.Resource;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @author: liangjinyin
 * @Date: 2018-09-10
 * @Description:
 */
@Mapper
public interface ResourceDao {

    @Select("select id,name,parent_id parentId from sys_resource where id = ${id}")
    Resource findResourceById(@Param("id") Integer id);

    @Insert("insert into sys_resource (name,parent_id )values(#{name},#{parentId})")
    boolean saveResource(Resource resource);

    @Select("select name from sys_resource where id in(${resourceIds})")
    Set<String> findResourceNameById(@Param("resourceIds")String resourceIds);

    @Select("select id,name,parent_id parentId from sys_resource where parent_id = ${id}")
    List<Resource> findParentResource(@Param("id") Integer id);

    @Select("select id,name,parent_id parentId from sys_resource")
    List<Resource> findResourceAllList();

}
