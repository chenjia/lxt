package com.lxt.ms;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossDomainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) resp;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "POST,GET,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin,X-Requested-With,Content-Type,Accept,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {

    }
}
