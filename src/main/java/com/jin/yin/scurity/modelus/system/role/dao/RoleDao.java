package com.jin.yin.scurity.modelus.system.role.dao;

import com.jin.yin.scurity.modelus.system.role.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Mapper
public interface RoleDao {
    @Select("select id,name from sys_role where flag = 0 and id in (#{ids})")
    List<Role> findRoleListByIds(@Param("ids") String ids);
}
