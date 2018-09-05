package com.jin.yin.security.models.customer.dao;

import com.jin.yin.security.common.bmould.dao.BaseDao;
import com.jin.yin.security.models.customer.entity.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@Mapper
public interface CustomerDao extends BaseDao<Customer>{

/*    @Select("Select id,name,address,phone from sys_customer where flag = 0")
    List<Customer> findList();

    @Select("Select id,name,address,phone from sys_customer where flag = 0 and id = ${id}")
    Customer findById(@Param("id") Integer id);*/

    @Override
    @Select("Select id,name,address,phone from sys_customer where flag = 0 and id = ${id}")
    Customer findById(@Param("id") Integer id);

    @Override
    @Select("Select id,name,address,phone from sys_customer where flag = 0")
    List<Customer> findList();

    @Override
    Boolean update(Customer entity);

    @Override
    Boolean delete(Integer id);

    @Override
    Boolean insert(Customer entity);
}
