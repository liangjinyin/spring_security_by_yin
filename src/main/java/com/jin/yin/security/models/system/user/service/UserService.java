package com.jin.yin.security.models.system.user.service;

import com.jin.yin.security.common.bmould.entity.PageQuery;
import com.jin.yin.security.common.bmould.entity.Pageable;
import com.jin.yin.security.models.system.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
public interface UserService {
    /**
     * 根据用户名查找用户
     * @param name
     * @return
     */
    User findUserByUserName(String name);

    /**
     * 根据手机号查找用户
     * @param name
     * @return
     */
    User findUserByMobile(String name);
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


    /**
     * 修改user
     * @param user
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    Object updateUser(User user);

    Object getUserList(Pageable pageable, PageQuery pageQuery);
}
