package com.jin.yin.scurity.common.constants.constant;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description: 系统常量
 */
public interface SystemConstant {

    /** 验证码放入session时的名称*/
    String SESSION_CODE_KEY = "SESSION_KEY_FOR_CODE";
    /** 短信发送的信息模板*/
    String SYS_SMS_MESSAGE = "尊敬的%s用户，您的验证码是%s,验证码有效时间是%s秒";
    /** 短信验证拦截的url*/
    String SMS_FILTER_URL = "http://localhost/sms/mobile";
    /** */
    String SMS_URL_LOGIN = "/authentication/mobile";

    String HTTP_METHOD_POST = "POST";

}
