package com.jin.yin.security.models.system.user.dao;

import com.jin.yin.security.models.system.role.entity.RoleUser;
import com.jin.yin.security.models.system.user.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Mapper
public interface UserDao {

    @Select("select id,username,password,role_ids roles,mobile from sys_user where flag = 0 and username = #{name}")
    User findUserByUserName(@Param("name")String username);

    @Select("select id,username,password,role_ids roles,mobile from sys_user where flag = 0 and id = ${id}")
    User findUserById(@Param("id") Integer id);

    @Insert("insert into sys_user(`username`,`password`,role_ids,mobile) " +
            "values(#{username},#{password},#{roles},#{mobile})")
    @SelectKey(before = false, keyProperty = "id", resultType = Integer.class, statement = { "select last_insert_id()" })
    boolean insertUser(User user);

    @Select("select id,username,password,role_ids roles,mobile from sys_user where flag = 0 and mobile = #{mobile}")
    User findUserByMobile(@Param("mobile")String mobile);

    @Update("update sys_user username = #{username},password = #{password},mobile = #{mobile} where id = #{id}")
    boolean updateUser(User user);

    @Select("select id,username,mobile from sys_user where flag = 0 ${sql}")
    List<Map<String,Object>> findUserList(@Param("sql") String sql);

    @Select("select count(id) from sys_user where flag=0 ${sql}")
    int findUserTotal(@Param("sql") String sql);

    @Delete("delete from sys_role_user where user_id in(${userIds})")
    boolean deleteRoleUser(@Param("userIds") String userIds);

    @Insert("insert into sys_role_user (role_id,user_id) values(#{roleId},#{userId})")
    boolean insertRoleUser(RoleUser roleUser);

    @Select("select id, role_id roleId,user_id userId from sys_role_user where user_id = ${id}")
    Set<RoleUser> findRoleUserByUserId(@Param("id") Integer id);

    @Select("select resource_id from sys_role_resource where role_id in(${roleIds})")
    Set<Integer> findUserResourcesByRoleId(@Param("roleIds") String roleIds);

}
