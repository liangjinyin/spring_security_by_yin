package com.jin.yin.security.models.system.role.dao;

import com.jin.yin.security.models.system.role.entity.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Mapper
public interface RoleDao {

    @Select("select * from sys_role where flag = 0 ${sql}")
    List<Role> findRoleList(@Param("sql") String sql);

    @Select("select * from sys_role where flag = 0 and id = ${id}")
    Role findRoleById(@Param("id") String id);

    @Update("update sys_role set flag = 1 where id = ${id }")
    boolean deleteRoleById(@Param("id") String id);

    @Delete("delete from sys_role_resource where role_id = ${id}")
    boolean deleteRoleResource(@Param("id") String id);

    @Insert("insert into sys_role_resource (role_id,resource_id )values(${roleId},${resourceId})")
    boolean saveRoleResource(@Param("roleId") String roleId, @Param("resourceId")String resourceId);
}
