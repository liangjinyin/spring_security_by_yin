package com.jin.yin.scurity.common.bmould.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author: liangjinyin
 * @Date: 2018-08-20
 * @Description:
 */

public class Pageable implements Serializable{

    private Integer pageSize;
    private Integer index;
    private String sortValue;
    private String sort;

    public Integer getPageSize() {
        if (pageSize == null) {
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getIndex() {
        if (index == null) {
            index = 2;
        }
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getSortValue() {
        if (StringUtils.isEmpty(sortValue)) {
            sortValue = "id";
        }
        return sortValue;
    }

    public void setSortValue(String sortValue) {
        this.sortValue = sortValue;
    }

    public String getSort() {
        if (StringUtils.isEmpty(sort)) {
            sort = "ASC";
        }
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


    @Override
    public String toString() {
        return "Pageable{" +
                "pageSize=" + pageSize +
                ", index=" + index +
                ", sortValue='" + sortValue + '\'' +
                ", sort='" + sort + '\'' +
                '}';
    }
}
