package com.jin.yin.scurity.modelus.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jin.yin.scurity.common.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected ResultCode resCode = null;
    protected Object data = null;


  /*  public String Result() {
        Map<String, Object> result = new HashMap(16);
        Map<String, Object> status = new HashMap(16);
        if (resCode == null) {
            resCode = ResultCode.NONE;// 没有意义的数据
        }
        result.put("data", data);
        status.put("code", resCode.getResultCode());
        status.put("msg", resCode.getResultMsg());
        result.put("status", status);

        return JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
    }*/

    public String result() {
        Map<String, Object> result = new HashMap(8);
        Map<String, Object> status = new HashMap(8);
        if (resCode == null) {
            /**
             * 没有意义的数据
             */
            resCode = ResultCode.NONE;
        }

        result.put("data", data);
        status.put("code", resCode.getResultCode());
        status.put("msg", resCode.getResultMsg());
        result.put("status", status);

        return JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
    }

    public static void writeResponse(HttpServletResponse response, ResultCode result) throws IOException {
        JSONObject json = new JSONObject();
        json.put("retCode", result.getResultCode());
        json.put("retMsg", result.getResultMsg());
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(json.toJSONString());
        response.getWriter().flush();
        response.getWriter().close();
    }

    public void writeResponse(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(result());
        response.getWriter().flush();
        response.getWriter().close();
    }

    public void setErrorResultCode(BindingResult result) {
        List<ObjectError> list = result.getAllErrors();
        resCode = ResultCode.createResultCode(list.get(0).getDefaultMessage());
        data = null;
    }

}
