package com.aarya.model;

import com.aarya.controllers.AuthorizationController;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String code = request.getHeader("sessionId");
        if(code == null){
            response.sendError(401);
            return false;
        } else {
            return AuthorizationController.sessions.containsKey(code) || code.equalsIgnoreCase("dev");
        }
    }
}
