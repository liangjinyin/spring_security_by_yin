package com.jin.yin.scurity.common.bmould.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liangjinyin
 * @date 20180813
 * @description
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
@Slf4j
public abstract class BaseService {

    protected int total = 0;
    protected Map<String, Object> data = new HashMap();
    protected List<Map<String, Object>> temp = null;
}
