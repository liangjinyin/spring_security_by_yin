package com.jin.yin.scurity.modelus.system.user.service;

import com.jin.yin.scurity.modelus.system.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Service
public interface UserService {
    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    User findUserByUserName(String name);

    /**
     * 根据用户id查找用户
     * @param id
     * @return
     */
    Object finUserById(Integer id);

    /**
     * 注册user
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    Object insertUser(User user);
}
