package com.jin.yin.security.models.customer.service;

import com.jin.yin.security.models.customer.dao.CustomerDao;
import com.jin.yin.security.models.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public List<Customer> findList() {
        return customerDao.findList();
    }

    @Override
    public Customer findById(Integer id) {
        return customerDao.findById(id);
    }
}
