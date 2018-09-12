package com.jin.yin.security.common.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.NON_EXISTENT;

/**
 * @author: liangjinyin
 * @Date: 2018-09-08
 * @Description:
 */
@Getter
public enum QueryEnum {


    /**
     * sql 枚举类
     */
    OR("001", " or"),
    EQ("002", " ="),
    LIKE("003", " like"),
    NON_EXISTENT("009", "");

    QueryEnum(String type, String sql) {
        this.type = type;
        this.sql = sql;
    }

    private String type;
    private String sql;

    public static QueryEnum getQuery(String type) {
        for (QueryEnum queryEnum : QueryEnum.values()) {
            if (StringUtils.equals(type, queryEnum.getType())) {
                return queryEnum;
            }
        }
        return NON_EXISTENT;
    }
}
