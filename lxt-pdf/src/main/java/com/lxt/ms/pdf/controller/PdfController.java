package com.lxt.ms.pdf.controller;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.ResponseWrapper;
import com.lxt.ms.common.utils.ClassUtils;
import com.lxt.ms.common.utils.ImageUtils;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import com.lxt.ms.pdf.utils.PdfUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.xhtmlrenderer.util.XRRuntimeException;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

@Controller
public class PdfController {

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/pdf")
    public Packages index(HttpServletRequest request) {
        System.out.println("\n[build pdf]" + request.getRequestURI() + "\n");
        String template = request.getParameter("template");
        if(template == null){
            template = "template.html";
        }

        try {
            PdfUtils.buildPdf(template,"{name:'自定义标题'}");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Packages pkg = new Packages();
        return pkg;
    }

    @ResponseBody
    @RequestMapping("/findclass")
    public Packages findClass(HttpServletRequest request) {
        String path = request.getParameter("path");

        ClassUtils.getPath(path);

        Packages pkg = new Packages();
        return pkg;
    }
}