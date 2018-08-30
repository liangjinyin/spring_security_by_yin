package com.jin.yin.scurity.modelus.customer.dao;

import com.jin.yin.scurity.modelus.customer.entity.Customer;
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
public interface CustomerDao {

    @Select("Select id,name,address,phone from sys_customer where flag = 0")
    List<Customer> findList();

    @Select("Select id,name,address,phone from sys_customer where flag = 0 and id = ${id}")
    Customer findById(@Param("id") Integer id);
}
