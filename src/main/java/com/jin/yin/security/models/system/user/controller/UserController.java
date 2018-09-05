package com.jin.yin.security.models.system.user.controller;

import com.jin.yin.security.common.bmould.controller.BaseController;
import com.jin.yin.security.common.bmould.entity.PageQuery;
import com.jin.yin.security.models.security.social.qq.entity.SocialUserInfo;
import com.jin.yin.security.models.system.user.entity.User;
import com.jin.yin.security.models.system.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping(value = "/user",produces = "application/json;charset=utf-8")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    @GetMapping("/find/{username}")
    public String findUserByUserName(@PathVariable(value = "username") String username) {
        data = userService.findUserByUserName(username);
        return result();
    }

    @GetMapping("/get/{id}")
    public String finUserById(@PathVariable("id") Integer id){
        data = userService.finUserById(id);
        return result();
    }

    @PostMapping("/addUser")
    public String registUser(@Validated User user,BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            setErrorResultCode(bindingResult);
        }else{
            data = userService.insertUser(user);
        }
        return result();
    }

    /**
     * 根据request从session中取得connection
     * @param request
     * @return
     */
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        SocialUserInfo socialUserInfo = new SocialUserInfo();
        socialUserInfo.setHeadimg(connection.getImageUrl());
        socialUserInfo.setNickname(connection.getDisplayName());
        socialUserInfo.setProviderId(connection.getKey().getProviderId());
        socialUserInfo.setProviderUserId(connection.getKey().getProviderUserId());
        return socialUserInfo;
    }

    /**
     * 返回当前的用户信息
     * @param user
     * @return
     */
    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
        return user;
    }

    @GetMapping("get/user")
    public String getUserByParam(PageQuery query){
        query.get("");
        return result();
    }
}
