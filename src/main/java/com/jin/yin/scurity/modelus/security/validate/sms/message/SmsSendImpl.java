package com.jin.yin.scurity.modelus.security.validate.sms.message;

import org.springframework.stereotype.Component;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description:
 */
@Component(value = "SmsSendService")
public class SmsSendImpl implements SmsSend {

    @Override
    public void send(String mobile, String message) {
        try {
            System.out.println(message);
           // TecentSMSService.sendSMSSingle(mobile, message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
