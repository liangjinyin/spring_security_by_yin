package com.jin.yin.scurity.common.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description:
 */
@Slf4j
public class HttpHelper {


    public HttpHelper() {
    }

    public HttpHelper(HttpResponseParser resParser) {
        this.resParser = resParser;
    }

    private HttpResponseParser resParser;

    public static String httpPostWithStringStream(String url, JSONObject params, JSONObject headers) throws Exception {
        HttpEntity entity = null;
        if (params != null && !params.isEmpty()) {
            entity = new StringEntity(params.toString(), Charset.forName("utf-8"));
        }
        return httpPost(url, entity, headers);
    }

    private static String httpPost(String url, HttpEntity entity, JSONObject headers) throws Exception {
        log.info("post request url:" + url);
        HttpClient client = HttpClientBuilder.create()
                .build();
        HttpPost post = new HttpPost(url);
        if (entity != null) {
            post.setEntity(entity);
        }
        if (headers != null) {
            for (String header : headers.keySet()) {
                post.addHeader(header, headers.getString(header));
            }
        }
        HttpResponse response = client.execute(post);
        return parseResponse(response);
    }

    private static String parseResponse(HttpResponse response) throws Exception {
        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            return "error";
        }
        return EntityUtils.toString(response.getEntity());
    }

    public CommonHttpResponse httpPostWithStringStream(String url, JSONObject params) throws Exception {
        HttpEntity entity = null;
        if(params != null && !params.isEmpty()) {
            entity = new StringEntity(params.toString(), Charset.forName("utf-8"));
        }
        return httpPost(url, entity);
    }

    private CommonHttpResponse httpPost(String url, HttpEntity entity) throws Exception {
        log.info("post request url:" + url);
        HttpClient client = HttpClientBuilder.create()
                .build();
        HttpPost post = new HttpPost(url);
        if(entity != null) {
            post.setEntity(entity);
        }
        HttpResponse response = client.execute(post);
        return resParser.parseHttpResponse(response);
    }
}
