package com.jin.yin.security.models.security.session;

import com.jin.yin.security.common.bmould.controller.BaseController;
import com.jin.yin.security.common.enums.ResultCode;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author: liangjinyin
 * @Date: 2018-09-05
 * @Description:
 */
@Component
public class MySessionStrategy extends BaseController implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String message = String.format("登陆并发，由%s用户挤下线", event.getRequest().getHeader("user-agent"));
        writeResponse(event.getResponse(),ResultCode.createCustomResultCode(message));
    }
}
