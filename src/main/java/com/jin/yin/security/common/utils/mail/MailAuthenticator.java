package com.jin.yin.security.common.utils.mail;

import javax.mail.*;   
/**
 * @author: liangjinyin
 * @date:  2018/8/31 10:53
 * @description:
 */
public class MailAuthenticator extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public MailAuthenticator(){   
    }   
    public MailAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(userName, password);   
    }   
}   
