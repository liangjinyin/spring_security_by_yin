package com.jin.yin.security.models.system.role.service;

import com.jin.yin.security.common.bmould.service.BaseService;
import com.jin.yin.security.models.system.role.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService {


    @Override
    public List<Role> findRoleListByIds(String ids) {
        List<Role> roleList = null;
        try {

       } catch (Exception e) {
           e.printStackTrace();
       }
       return roleList;
    }
}
