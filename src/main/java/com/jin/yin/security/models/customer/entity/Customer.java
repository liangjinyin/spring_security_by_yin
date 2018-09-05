package com.jin.yin.security.models.customer.entity;

import com.jin.yin.security.common.bmould.entity.BaseEntity;
import com.jin.yin.security.common.utils.excel.ExcelField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: liangjinyin
 * @Date: 2018-08-30
 * @Description:
 */
@Data
public class Customer extends BaseEntity implements Serializable{

    @ExcelField(title = "id",sort = 1)
    private Integer id;
    @ExcelField(title = "名称",sort = 2)
    private String name;
    @ExcelField(title = "地址",sort = 3)
    private String address;
    @ExcelField(title = "电话",sort = 4)
    private String phone;
}
