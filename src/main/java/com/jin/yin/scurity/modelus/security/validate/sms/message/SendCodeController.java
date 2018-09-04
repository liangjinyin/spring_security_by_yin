package com.jin.yin.scurity.modelus.security.validate.sms.message;

import com.jin.yin.scurity.common.bmould.controller.BaseController;
import com.jin.yin.scurity.common.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author: liangjinyin
 * @Date: 2018-09-03
 * @Description: 发送验证码controller
 */
@RestController
@RequestMapping("/sms")
@Slf4j
public class SendCodeController extends BaseController {

    @Resource(name = "SmsValidateProcessor")
    private SmsValidateProcessor smsValidateProcessor;

    @GetMapping("/create")
    public String createSms(HttpServletRequest request) {
        try {
            smsValidateProcessor.create(new ServletWebRequest(request));
        } catch (Exception e) {
            log.info("发送短信验证码异常，异常信息：{}", e.getMessage());
            resCode = ResultCode.OPERATION_FAILED;
            e.printStackTrace();
        }
        return result();
    }
}
