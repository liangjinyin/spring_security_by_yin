package com.jin.yin.security.common.bmould.controller;

import com.jin.yin.security.common.bmould.service.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@Controller
public abstract class CrudController<T extends CrudService> extends BaseController{

    @Autowired
    private T service;

    @GetMapping("/findList")
    public String findCustomerList(){
        data = service.findList();
        return result();
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable("id") Integer id){
        data = service.findById(id);
        return result();
    }
}
