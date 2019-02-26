package com.hand.hjmall.component;

import com.hand.hjmall.dto.CommonResult;
import com.hand.hjmall.utils.JsonUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Description:    当访问接口没有权限时，自定义的返回结果
* @CreateDate:     2019/2/21 20:28
* @CreateUser:     SilenceTian
* @Version:        1.0
*/
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler{
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JsonUtil.objectToJson(new CommonResult().forbidden(e.getMessage())));
        response.getWriter().flush();
    }
}
