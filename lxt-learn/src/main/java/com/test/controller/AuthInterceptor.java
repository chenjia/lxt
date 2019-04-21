package com.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@WebFilter(urlPatterns = "/*")
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 在controller之前执行此方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    //preHandle：在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {

        System.out.println(request.getMethod());
        if("OPTIONS".equalsIgnoreCase(request.getMethod())){
            return true;
        }

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));// 指定允许其他域名访问
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");// 响应类型
        response.setHeader("Access-Control-Allow-Headers", "content-type, x-requested-with");// 响应头设置
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("auth:"+request.getSession().getAttribute("userId"));

        return true;
    }

    /*postHandle：在业务处理器处理请求执行完成后，生成视图之前执行。后处理（调用了Service并返回ModelAndView，但未进行页面渲染），
    有机会修改ModelAndView */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) throws Exception {

    }

    //afterCompletion：在DispatcherServlet完全处理完请求后被调用，可用于清理资源等。返回处理（已经渲染了页面）
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {


    }
}
