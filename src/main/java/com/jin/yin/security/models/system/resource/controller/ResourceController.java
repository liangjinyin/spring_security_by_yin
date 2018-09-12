package com.jin.yin.security.models.system.resource.controller;

import com.jin.yin.security.common.bmould.controller.BaseController;
import com.jin.yin.security.models.system.resource.entity.Resource;
import com.jin.yin.security.models.system.resource.service.ResourceService;
import com.jin.yin.security.models.system.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liangjinyin
 * @Date: 2018-09-10
 * @Description:
 */
@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController{

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/create")
    public String createResource(@Validated Resource resource,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            setErrorResultCode(bindingResult);
        }else {
            data = resourceService.saveResource(resource);
        }
        return result();
    }

    @GetMapping("/find/{id}")
    public String findResourceById(@PathVariable("id")Integer id) {
        data = resourceService.findResourceById(id);
        return result();
    }

    @GetMapping("find/tree")
    public String findResourceTree(){
        data = resourceService.findResourceTree();
        return result();
    }
}
