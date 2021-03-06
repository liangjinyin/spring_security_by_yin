package com.jin.yin.security.models.customer.controller;

import com.jin.yin.security.common.bmould.controller.BaseController;
import com.jin.yin.security.common.enums.ResultCode;
import com.jin.yin.security.common.utils.excel.ExportExcel;
import com.jin.yin.security.models.customer.entity.Customer;
import com.jin.yin.security.models.customer.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@RestController
@Slf4j
@RequestMapping("/customer")
public class CustomerController extends BaseController{

  /*  @Autowired
    private CustomerService customerService;
*/
    @Autowired
    private TestService customerService;

    @GetMapping("/findList")
    public String findCustomerList(){

        List<Customer> list = customerService.findList();
        int a = 1/0;
        data = list;
        return result();
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable("id") Integer id){
        data = customerService.findById(id);
        return result();
    }

    @GetMapping("/export")
    public String exportCustomer(HttpServletResponse response){

        try {
            String fileName = "客户"+ "2"+".xlsx";
            List<Customer> customerList = customerService.findList();
            new ExportExcel("客户", Customer.class).setDataList(customerList).write(response, fileName).dispose();
        } catch (Exception e) {
            log.info("导出数据失败！原因：{}",e.getMessage());
            resCode = ResultCode.OPERATION_FAILED;
            e.printStackTrace();
        }
        return result();
    }
}
