package com.mygradleproject.publictransportation.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mygradleproject.publictransportation.utils.JwtUtil;
import com.mygradleproject.publictransportation.utils.RequestMeta;

import io.jsonwebtoken.Claims;

@Component("jwtInterceptorBean")
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtils;
    
    private RequestMeta requestMeta;

    public JwtInterceptor(RequestMeta requestMeta){
        this.requestMeta = requestMeta;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String auth = request.getHeader("authorization");

        if( !(request.getRequestURI().contains("login"))){
        	Claims claims = jwtUtils.verify(auth);

        	requestMeta.setUserId(Integer.valueOf(claims.get("userId").toString()));
            requestMeta.setName(claims.get("name").toString());
            requestMeta.setSurname(claims.get("surname").toString());
            requestMeta.setEmail(claims.get("email").toString());
        }

        return super.preHandle(request, response, handler);
    }
}
