package com.jin.yin.security.common.utils;


import com.jin.yin.security.common.bmould.entity.PageQuery;
import com.jin.yin.security.common.bmould.entity.Pageable;

import javax.validation.constraints.NotNull;

/**
 * @author: liangjinyin
 * @date:  2018/8/13 10:00
 * @description: 
 */
public class SqlUtils {

    public static String getPageSql(Pageable pageable){
        return String.format(" Order By %s %s LIMIT %d %d", pageable.getSortParam(),
                pageable.getSort(), (pageable.getIndex()-1)*pageable.getPageSize(), pageable.getPageSize());
    }


    public static String getPageQuerySql(@NotNull PageQuery query){
        StringBuffer sb = new StringBuffer();
        switch (query.getType()) {
            case "001":
                sb.append(" and a.name = " + query.getName());
                break;
            case "002":
                sb.append(" or a.name = " + query.getName());
                break;
            case "003":
                sb.append(" and a.name like '%"+query.getName()+"%' " );
                break;
            default:
                sb.append(" and a.name = " + query.getName());
                break;
        }
        return sb.toString();
    }
}
