package com.jin.yin.security.common.bmould.service;

import com.jin.yin.security.common.bmould.dao.BaseDao;
import com.jin.yin.security.common.bmould.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description: service 的基类
 */
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class CrudService<D extends BaseDao, E extends BaseEntity> extends BaseService {

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    public E findById(@Param("id") Integer id) {
        return (E) dao.findById(id);
    }

    public List<E> findList() {
        return dao.findList();
    }
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Boolean update(E entity) {
        return dao.update(entity);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Boolean deleteById(@Param("id") Integer id) {
        return dao.delete(id);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Boolean insert(E entity){
        return dao.insert(entity);
    }
}
