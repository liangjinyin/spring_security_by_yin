package com.jin.yin.scurity.modelus.security.validate.sms.message;

import com.jin.yin.scurity.common.constants.constant.SystemConstant;
import com.jin.yin.scurity.modelus.security.validate.sms.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author: liangjinyin
 * @Date: 2018-08-31
 * @Description: 抽象验证码处理器
 */
@Slf4j
public abstract class AbstractValidateCodeProcessor implements ValidateCodeProcessor {

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Autowired
    private SmsCodeGenerator smsCodeGenerator;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        ValidateCode validateCode = smsCodeGenerator.getValidateCode();
        save(validateCode, request);
        send(validateCode, request);
    }

    /**
     * 发送验证码 由子类实现
     *
     * @param validateCode 验证码
     * @param request      request
     * @throws Exception dd
     */
    protected abstract void send(ValidateCode validateCode, ServletWebRequest request) throws Exception;

    /**
     * 保存验证码到session中
     *
     * @param validateCode
     * @param request
     */
    private void save(ValidateCode validateCode, ServletWebRequest request) {
        sessionStrategy.setAttribute(request, SystemConstant.SESSION_CODE_KEY, validateCode);
    }

    /**
     * 校验验证码
     * @param request
     */
    @Override
    public void validate(ServletWebRequest request) {
        /** 从session中拿到code*/
        ValidateCode sessionCode = (ValidateCode) sessionStrategy.getAttribute(request, SystemConstant.SESSION_CODE_KEY);
        /** 校验*/
        String requestCode = null;
        try {
            requestCode = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"code");
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
            log.info("获取请求中的code异常");
        }
        if(!StringUtils.isNotBlank(sessionCode.getCode())){
            log.info("验证码不存在！");
            throw new ValidateException("验证码不存在！");
        }
        if(!StringUtils.isNotBlank(requestCode)){
            log.info("验证码不能为空！");
            throw new ValidateException("验证码不能为空！");
        }
        if (sessionCode.isExpired()) {
            sessionStrategy.removeAttribute(request, SystemConstant.SESSION_CODE_KEY);
            log.info("验证码已过期！");
            throw new ValidateException("验证码已过期!");
        }
        if (!StringUtils.equals(sessionCode.getCode(), requestCode)) {
            log.info("验证码不匹配！");
            throw new ValidateException("验证码不匹配!");
        }
        /** 校验完验证码之后要清除session中的验证码*/
        sessionStrategy.removeAttribute(request,SystemConstant.SESSION_CODE_KEY);
    }
}
