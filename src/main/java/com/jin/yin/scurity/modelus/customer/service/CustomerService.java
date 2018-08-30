package com.jin.yin.scurity.modelus.customer.service;

import com.jin.yin.scurity.modelus.customer.entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public interface CustomerService {

    /**获取客户list
     * @return
     */
    List<Customer> findList();

    /**根据id获取客户信息
     * @param id
     * @return
     */
    Customer findById(Integer id);
}
