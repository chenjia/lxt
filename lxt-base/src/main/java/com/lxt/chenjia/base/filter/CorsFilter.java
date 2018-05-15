package com.lxt.chenjia.base.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/callService/*",filterName = "corsFilter")
public class CorsFilter extends OncePerRequestFilter {
	@Value("${security.allowDomain}")
	private String allowDomain;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		
		Set<String> allowedOrigins= new HashSet<String>(Arrays.asList(allowDomain.split(","))); 
		String originHeader = request.getHeader("Origin");
		if (allowedOrigins.contains(originHeader)){
			response.setHeader("Access-Control-Allow-Origin", originHeader);
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS");
			response.setHeader("Access-Control-Allow-Headers", "Origin,X-Requested-With,Content-Type,Accept,token");
			response.setHeader("Access-Control-Allow-Credentials", "true");
		}
		
		response.setCharacterEncoding("UTF-8");
		filterChain.doFilter(request, response);
	}
}