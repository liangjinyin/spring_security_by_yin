package com.jin.yin.scurity.modelus.system.user.service;

import com.jin.yin.scurity.common.bmould.service.BaseService;
import com.jin.yin.scurity.common.enums.ResultCode;
import com.jin.yin.scurity.modelus.system.user.dao.UserDao;
import com.jin.yin.scurity.modelus.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Service
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UserDao userDao;

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
        try {
            User user = userDao.findUserById(id);
            if (user == null) {
                return ResultCode.USER_NOTEXIST;
            }
            data.put("user", user);
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
            if(!userDao.insertUser(user)){
                return ResultCode.OPERATION_FAILED;
            }
            return  ResultCode.OPERATION_SUCCESSED;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }
}
