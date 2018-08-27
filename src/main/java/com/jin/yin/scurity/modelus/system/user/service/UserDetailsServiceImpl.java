package com.jin.yin.scurity.modelus.system.user.service;

import com.jin.yin.scurity.modelus.system.role.dao.RoleDao;
import com.jin.yin.scurity.modelus.system.role.entity.Role;
import com.jin.yin.scurity.modelus.system.user.dao.UserDao;
import com.jin.yin.scurity.modelus.system.user.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUserName(username);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    username, passwordEncoder.encode(user.getPassword()),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin,super"));
        } else {
            throw new UsernameNotFoundException(username);
        }

    }

    /**
     * 获取用户对应的权限
     *
     * @param roles
     * @return
     */
    private List<GrantedAuthority> getGrantedAuthority(String roles) {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<String> authKeys = null;
        if (StringUtils.isNotEmpty(roles)) {
            List<Role> roleList = roleDao.findRoleListByIds(roles);
            for (Role role : roleList) {
                String[] os = role.getOffices().split(",");
                authKeys = Arrays.asList(os);
                for (String keys : authKeys) {
                    auths.add(new SimpleGrantedAuthority(keys));
                }
            }
        }
        return auths;
    }
}
