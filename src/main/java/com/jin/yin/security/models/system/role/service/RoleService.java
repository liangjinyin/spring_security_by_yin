package com.jin.yin.security.models.system.role.service;

import com.jin.yin.security.common.bmould.entity.PageQuery;
import com.jin.yin.security.common.bmould.entity.Pageable;
import com.jin.yin.security.models.system.role.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
public interface RoleService {

    Object findRoleList(Pageable pageable, PageQuery pageQuery);

    Object findRoleById(String id);

    Object deleteRoleById(String id);

    Object findRoleResource(String id);

    Object createRoleResource(String id, String ids);
}
