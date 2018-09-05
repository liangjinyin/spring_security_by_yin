package com.jin.yin.security.models.system.user.service;

import com.jin.yin.security.common.utils.AccountValidatorUtil;
import com.jin.yin.security.models.system.role.dao.RoleDao;
import com.jin.yin.security.models.system.role.entity.Role;
import com.jin.yin.security.models.system.user.dao.UserDao;
import com.jin.yin.security.models.system.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
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
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService, SocialUserDetailsService {


    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("表单登陆，登陆名称：{}", username);
        return checkUser(username);
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


    @Override
    public SocialUserDetails loadUserByUserId(String username) throws UsernameNotFoundException {
        log.info("qq登陆，登陆用户名：{}",username);
        return checkUser(username);
    }

    /**
     * 校验用户是否存在并配置用户的权限
     * @param param
     * @return
     */
    private SocialUserDetails checkUser(String param){
        User user = null;
        if (AccountValidatorUtil.isMobile(param)) {
            user = userDao.findUserByMobile(param);
        }else {
            user = userDao.findUserByUserName(param);
        }
        if (user != null) {
            return new SocialUser(
                    user.getUsername(), passwordEncoder.encode(user.getPassword()),
                    AuthorityUtils.commaSeparatedStringToAuthorityList("admin,super"));
        } else {
            throw new UsernameNotFoundException(user.getUsername());
        }
    }
}
