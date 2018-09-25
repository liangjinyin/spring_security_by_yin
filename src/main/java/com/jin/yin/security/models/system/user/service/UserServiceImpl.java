package com.jin.yin.security.models.system.user.service;

import com.jin.yin.security.common.bmould.entity.PageQuery;
import com.jin.yin.security.common.bmould.entity.Pageable;
import com.jin.yin.security.common.bmould.service.BaseService;
import com.jin.yin.security.common.enums.ResultCode;
import com.jin.yin.security.common.utils.MyCollectionsUtils;
import com.jin.yin.security.common.utils.MyBeanUtils;
import com.jin.yin.security.common.utils.SqlUtils;
import com.jin.yin.security.models.system.role.entity.RoleUser;
import com.jin.yin.security.models.system.user.dao.UserDao;
import com.jin.yin.security.models.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<User> implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUserName(String name) {
        User user = null;
        try {
             user = userDao.findUserByUserName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findUserByMobile(String name) {
        User user = null;
        try {
            user = userDao.findUserByUserName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Object finUserById(Integer id) {
        Set<RoleUser> roleUserSet = null;
        Set<Integer> resourceIds = null;
        try {
            entity = userDao.findUserById(id);
            if (entity == null) {
                return ResultCode.USER_NOTEXIST;
            }
            roleUserSet = userDao.findRoleUserByUserId(id);
            String roleIds = MyCollectionsUtils.extractToString(roleUserSet, "roleId", ",");
            //roleUserSet.stream().map((RoleUser e, String s) -> String.format("%d,", e.getRoleId()));
            resourceIds = userDao.findUserResourcesByRoleId(roleIds);
            entity.setResourceIds(resourceIds);
            entity.setRoleUsers(roleUserSet);
            data.put("user", entity);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public Object insertUser(User user) {
        try {
            if (userDao.findUserByUserName(user.getUsername()) != null) {
                return ResultCode.USER_HAS_EXIST;
            }
            //密码加密在存到数据库
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userDao.insertUser(user);
            //创建用户和角色的关系
            createRoleUser(user);
            return  ResultCode.OPERATION_SUCCESSED;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    /**
     * 创建用户和角色的关系
     * @param user
     */
    private void createRoleUser(User user) {
        if (!MyCollectionsUtils.isEmpty(user.getRoleUsers())) {
            userDao.deleteRoleUser(MyCollectionsUtils.extractToString(user.getRoleUsers(),"userId",","));
        }
        for (RoleUser roleUser : user.getRoleUsers()) {
            userDao.insertRoleUser(roleUser);
        }
    }

    @Override
    public Object updateUser(User user) {
        try {
            User user1 = userDao.findUserById(user.getId());
            MyBeanUtils.copyProperties(user,user1);
            createRoleUser(user1);
            userDao.updateUser(user1);
            return  ResultCode.OPERATION_SUCCESSED;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public Object getUserList(Pageable pageable, PageQuery pageQuery) {
        try {
            sql = SqlUtils.getPageQuerySql(pageQuery)+SqlUtils.getPageSql(pageable);
            temp = userDao.findUserList(sql);
            total = userDao.findUserTotal(SqlUtils.getPageQuerySql(pageQuery));
            data.put("userList", temp);
            data.put("total", total);
            return data;
       } catch (Exception e) {
           e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
       }
    }
}
