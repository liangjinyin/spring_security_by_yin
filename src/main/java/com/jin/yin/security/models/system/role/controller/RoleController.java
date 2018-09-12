package com.jin.yin.security.models.system.role.controller;

import com.jin.yin.security.common.bmould.controller.BaseController;
import com.jin.yin.security.common.bmould.entity.PageQuery;
import com.jin.yin.security.common.bmould.entity.Pageable;
import com.jin.yin.security.models.system.role.service.RoleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: liangjinyin
 * @Date: 2018-08-23
 * @Description:
 */
@RestController
@RequestMapping(value = "/role",produces = "application/json;charset=utf-8")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/findList")
    public String findRoleList(Pageable pageable, PageQuery pageQuery){
        data = roleService.findRoleList(pageable,pageQuery);
        return result();
    }

    @GetMapping("/find/{id}")
    public String findRoleById(@PathVariable("id") String id){
        data = roleService.findRoleById(id);
        return result();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRoleById(@PathVariable("id")String id){
        data = roleService.deleteRoleById(id);
        return result();
    }

    @GetMapping("/{id}/resource")
    public String findRoleResource(@PathVariable("id")String id) {
        data = roleService.findRoleResource(id);
        return result();
    }

    /**
     * 创建角色资源关系
     * @param id 角色id
     * @param ids 资源id
     * @return
     */
    @PostMapping("/{id}/resource")
    public String createRoleResource(@PathVariable("id")String id,@RequestBody String ids){
        data = roleService.createRoleResource(id,ids);
        return result();
    }
}
