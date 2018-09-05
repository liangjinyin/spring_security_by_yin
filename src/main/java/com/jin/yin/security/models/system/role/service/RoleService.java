package com.jin.yin.security.models.system.role.service;

import com.jin.yin.security.models.system.role.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Service
public interface RoleService {
    /**
     * 根据id获取相应的role列表
     * @param ids
     * @return
     */
    List<Role> findRoleListByIds(String ids);
}
