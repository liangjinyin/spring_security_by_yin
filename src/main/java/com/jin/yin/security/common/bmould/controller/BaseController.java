package com.jin.yin.security.common.bmould.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jin.yin.security.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liangjinyin
 * @date: 2018/8/23 10:24
 * @description:
 */
@Slf4j
public abstract class BaseController {

    protected ResultCode resCode = ResultCode.OPERATION_SUCCESSED;
    protected Object data = null;


//    public String Result() {
//        Map<String, Object> result = new HashMap(16);
//        Map<String, Object> status = new HashMap(16);
//        if (resCode == null) {
//            resCode = ResultCode.NONE;// 没有意义的数据
//        }
//        result.put("data", data);
//        status.put("code", resCode.getResultCode());
//        status.put("msg", resCode.getResultMsg());
//        result.put("status", status);
//
//        return JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue);
//    }

    public String result() {
        Map<String, Object> result = new HashMap(8);
        Map<String, Object> status = new HashMap(8);
        if (resCode == null) {
            /**
             * 没有意义的数据
             */
            resCode = ResultCode.NONE;
        }
        if (data instanceof ResultCode) {
            resCode = (ResultCode) data;
            data = null;
        }
        result.put("data", data);
        status.put("code", resCode.getResultCode());
        status.put("msg", resCode.getResultMsg());
        result.put("status", status);

        /*return JSON.toJSONString(result);*/

        return JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
       /*return result.toString();*/
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
        resCode = ResultCode.createCustomResultCode(list.get(0).getDefaultMessage());
        data = null;
    }

}
