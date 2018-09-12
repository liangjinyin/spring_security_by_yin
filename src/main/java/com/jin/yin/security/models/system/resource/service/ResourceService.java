package com.jin.yin.security.models.system.resource.service;

import com.jin.yin.security.models.system.resource.entity.Resource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author: liangjinyin
 * @Date: 2018-09-10
 * @Description:
 */
public interface ResourceService {

    Object saveResource(Resource resource);

    Object findResourceById(Integer id);

    Object findResourceTree();
}
