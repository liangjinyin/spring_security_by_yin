package com.jin.yin.scurity.common.interceptor;

import com.jin.yin.scurity.common.bmould.controller.BaseController;
import com.jin.yin.scurity.common.constants.properties.SystemProperties;
import com.jin.yin.scurity.common.enums.ResultCode;
import com.jin.yin.scurity.common.utils.CorsHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: liangjinyin
 * @Date: 2018-08-27
 * @Description:
 */
@Component
@Slf4j
public class LoginHandler extends BaseController implements AuthenticationFailureHandler, AuthenticationSuccessHandler {

    @Autowired
    protected SystemProperties properties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = request.getParameter("username");
        System.out.println(username);
        log.info("登陆成功！login_name = {}", username);
       /* data = userService.getUserTokenByUserName(username,check(request,response));
        if(data instanceof ResultCode){
            resCode = ResultCode.OPERATION_FAILED;
            data = null;
        }*/
        Boolean flag = CorsHandler.addCorsMapping(response, request, properties.getCors().getCurls());
        if (flag) {
            writeResponse(response);
        }else{
            resCode = ResultCode.OPERATION_FAILED;
        }
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("登陆失败！原因：{}", exception.getMessage());
        System.out.println("authenticationFailure:");
        exception.printStackTrace();
        if (exception instanceof UsernameNotFoundException) {
            resCode = ResultCode.USER_NOTEXIST;
        } else if (exception instanceof DisabledException) {
            resCode = ResultCode.USER_DENIED;
        } else if (exception instanceof BadCredentialsException) {
            resCode = ResultCode.USER_NAME_OR_PASSWORD_ERROR;
        } else {
            resCode = ResultCode.OPERATION_FAILED;
        }
        data = null;
        CorsHandler.addCorsMapping(response, request, properties.getCors().getCurls());
        writeResponse(response);
    }
}
