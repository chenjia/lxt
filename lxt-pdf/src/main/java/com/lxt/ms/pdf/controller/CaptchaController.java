package com.lxt.ms.pdf.controller;

import com.lxt.ms.CreateImageCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class CaptchaController {

    @RequestMapping("captcha")
    public void captcha(HttpServletRequest req, HttpServletResponse response, HttpSession session) throws Exception {
        response.setContentType("image/jpeg");
//禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);


        CreateImageCode vCode = new CreateImageCode(100,30,5,10);
        session.setAttribute("code", vCode.getCode());
        vCode.write(response.getOutputStream());
    }
}
