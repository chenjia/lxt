package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @RequestMapping("/login")
    public Object login(HttpServletRequest request){
        System.out.println("login");
        request.getSession().setAttribute("userId", "chenjia");
        return "login";
    }

    @RequestMapping("/list")
    public Object list(HttpServletRequest request){
        System.out.println("list");
        System.out.println(request.getSession().getAttribute("userId"));
        return "list";
    }
}
