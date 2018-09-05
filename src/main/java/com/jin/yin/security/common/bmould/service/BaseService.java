package com.jin.yin.security.common.bmould.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangjinyin
 * @date 20180813
 * @description 权限控制service层
 */
@Transactional(readOnly = true, rollbackFor = Exception.class)
public abstract class BaseService {

    protected int total = 0;
    protected Map<String, Object> data = new HashMap();
    protected List<Map<String, Object>> temp = null;


}
