package com.jin.yin.security.models.system.resource.service;

import com.jin.yin.security.common.bmould.service.BaseService;
import com.jin.yin.security.common.enums.ResultCode;
import com.jin.yin.security.common.exception.ServiceException;
import com.jin.yin.security.models.system.resource.dao.ResourceDao;
import com.jin.yin.security.models.system.resource.entity.Resource;
import com.jin.yin.security.models.system.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-09-10
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl extends BaseService<Resource> implements ResourceService {

    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Object saveResource(Resource resource) {
        try {
            Resource parent = resourceDao.findResourceById(resource.getParentId());
            if(parent==null){
                parent = resourceDao.findResourceById(1);
                resource.setParentId(parent.getId());
            }
            resourceDao.saveResource(resource);
            return ResultCode.OPERATION_SUCCESSED;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }

    }

    @Override
    public Object findResourceById(Integer id) {
        try {
            data.put("resource", validate(id));
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public Object findResourceTree() {
        try {
            List<Resource> parentList = new ArrayList<>();
            list = resourceDao.findResourceAllList();
            //获取第一级父级资源
            for (Resource resource : list) {
                if(resource.getParentId().equals(0)){
                    parentList.add(resource);
                }
            }
            //递归获取子资源
            for (Resource resource : parentList) {
                resource.setChildes(resourceTree(resource.getId(),list));
            }
            data.put("tree",parentList);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultCode.OPERATION_FAILED;
        }
    }

    /**
     * 获取子资源
     * @param id 父资源id
     * @param list 所有资源列表
     * @return
     */
    private List<Resource> resourceTree(Integer id, List<Resource> list) {
        List<Resource> childList = new ArrayList<>();
        for (Resource resource : list) {
            if(resource.getId()!=null){
                if (resource.getParentId().equals(id)) {
                    childList.add(resource);
                }
            }
        }
        for (Resource resource : childList) {
            resource.setChildes(resourceTree(resource.getId(),list));
        }
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    /**
     * 校验
     * @param id
     * @return
     */
    private Object validate(Integer id) {
        entity = resourceDao.findResourceById(id);
        if (entity == null) {
            throw new ServiceException(ResultCode.createResultCodeByMeg("资源不存在！"));
        }
        return entity;
    }
}
