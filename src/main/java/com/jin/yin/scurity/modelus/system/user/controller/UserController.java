package com.jin.yin.scurity.modelus.system.user.controller;

import com.jin.yin.scurity.common.bmould.controller.BaseController;
import com.jin.yin.scurity.modelus.system.user.entity.User;
import com.jin.yin.scurity.modelus.system.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
