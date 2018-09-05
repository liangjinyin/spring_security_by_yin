package com.jin.yin.security.models.system.role.controller;

import com.jin.yin.security.common.bmould.controller.BaseController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@RestController
@RequestMapping(value = "/role",produces = "application/json;charset=utf-8")
public class RoleController extends BaseController {

    @GetMapping("/findList/{ids}")
    public String findRoleList(@PathVariable("ids") String ids){

        return result();
    }


}
