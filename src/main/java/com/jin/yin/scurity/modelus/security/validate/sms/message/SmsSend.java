package com.jin.yin.scurity.modelus.security.validate.sms.message;

import org.springframework.stereotype.Component;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description:
 */
@Component
public interface SmsSend {

   /** 发送短信
    * @param mobile
    * @param message
    */
   void send(String mobile, String message);
}
