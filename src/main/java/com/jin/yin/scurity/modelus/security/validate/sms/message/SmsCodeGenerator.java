package com.jin.yin.scurity.modelus.security.validate.sms.message;

import com.jin.yin.scurity.common.constants.properties.SystemProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description: 短信验证码生成类
 */
@Component(value = "SmsCodeGenerator")
public class SmsCodeGenerator {

    @Autowired
    private SystemProperties sysProperties;

    public ValidateCode getValidateCode(){
        String code = RandomStringUtils.randomNumeric(sysProperties.getValidate().getSms().getSlength());
        return new ValidateCode(code, sysProperties.getValidate().getSms().getExpireTime());
    }
}
