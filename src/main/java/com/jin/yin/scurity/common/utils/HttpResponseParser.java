package com.jin.yin.scurity.common.utils;

import org.apache.http.HttpResponse;

/**
 * @author: liangjinyin
 * @date: 2018/9/3 9:49
 * @description:
 */
public interface HttpResponseParser {

    /**
     * parseHttpResponse
     *
     * @param response
     * @return
     * @throws Exception
     */
    CommonHttpResponse parseHttpResponse(HttpResponse response) throws Exception;
}
