package com.jin.yin.security.models.system.role.service;

import com.jin.yin.security.common.bmould.entity.PageQuery;
import com.jin.yin.security.common.bmould.entity.Pageable;
import com.jin.yin.security.common.bmould.service.BaseService;
import com.jin.yin.security.common.enums.ResultCode;
import com.jin.yin.security.common.exception.ServiceException;
import com.jin.yin.security.common.utils.MyCollectionsUtils;
import com.jin.yin.security.common.utils.SqlUtils;
import com.jin.yin.security.models.system.role.dao.RoleDao;
import com.jin.yin.security.models.system.role.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Object findRoleList(Pageable pageable, PageQuery pageQuery) {
        try {
            sql = SqlUtils.getPageQuerySql(pageQuery) + SqlUtils.getPageSql(pageable);
            list = roleDao.findRoleList(sql);
            data.put("roleList", list);
            data.put("total", total);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public Object findRoleById(String id) {
        try {
            data.put("role", validate(id));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public Object deleteRoleById(String id) {
        try {
            Role role = (Role) validate(id);
            if (!MyCollectionsUtils.isEmpty(role.getUsers())) {
                return ResultCode.createResultCodeByMeg("不能删除有下挂用户的角色!");
            }
            roleDao.deleteRoleById(id);
            return ResultCode.OPERATION_SUCCESSED;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public Object findRoleResource(String id) {
        try {
            Role role = (Role) validate(id);
           // Set resourceIds = MyCollectionsUtils.extractToSet(role.getResources(), "resourceId");
            Set resourceIds = role.getResources().stream().map(e -> e.getResourceId()).collect(Collectors.toSet());
            data.put("resourceIds", resourceIds);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public Object createRoleResource(String id, String resourceIds) {
        try {
            String[] ids = StringUtils.split(resourceIds, ",");
            boolean flag = roleDao.deleteRoleResource(id);
            if (flag) {
                for (String rid : ids) {
                    roleDao.saveRoleResource(id, rid);
                }
            }
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }


    /**
     * 校验role是否存在
     *
     * @param id
     * @return
     */
    private Object validate(String id) {
        entity = roleDao.findRoleById(id);
        if (entity == null) {
            throw new ServiceException("角色不存在！");
        }
        return entity;
    }
}
