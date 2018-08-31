package com.jin.yin.scurity.common.bmould.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@Mapper
public interface BaseDao<T> {

    /**获取指定的实体数据
     * @param id
     * @return
     */
    T findById(@Param("id") Integer id);

    /**获取实体list
     * @return
     */
    List<T> findList();

    /**
     * 更新实体类
     * @param entity
     * @return
     */
    Boolean update(T entity);

    /**
     * 删除实体类
     * @param id
     * @return
     */
    Boolean delete(@Param("id") Integer id);

    /**
     * 添加实体
     * @param entity
     * @return
     */
    Boolean insert(T entity);
}
