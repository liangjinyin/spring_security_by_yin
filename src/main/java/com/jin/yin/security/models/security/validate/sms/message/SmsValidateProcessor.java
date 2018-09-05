package com.jin.yin.security.models.security.validate.sms.message;

import com.jin.yin.security.common.constants.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description: 短信验证码处理器
 */
@Component(value = "SmsValidateProcessor")
public class SmsValidateProcessor extends AbstractValidateCodeProcessor {

    @Autowired
    private SmsSend smsSend;

    @Override
    protected void send(ValidateCode validateCode, ServletWebRequest request) throws Exception {
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), "mobile");
        String message = String.format(SystemConstant.SYS_SMS_MESSAGE,mobile,validateCode.getCode(),validateCode.getExpireTime());
        smsSend.send(mobile,message);
    }
}
