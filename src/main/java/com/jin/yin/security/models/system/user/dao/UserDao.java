package com.jin.yin.security.models.system.user.dao;

import com.jin.yin.security.models.system.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    boolean insertUser(User user);

    @Select("select id,username,password,role_ids roles,mobile from sys_user where flag = 0 and mobile = #{mobile}")
    User findUserByMobile(@Param("mobile")String mobile);
}
