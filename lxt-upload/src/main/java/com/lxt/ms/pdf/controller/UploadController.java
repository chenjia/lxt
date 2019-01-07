package com.lxt.ms.pdf.controller;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.ResponseWrapper;
import com.lxt.ms.common.utils.ImageUtils;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.PdfUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

@Controller
public class UploadController {
    @Value("${file.uploadPath}")
    private String uploadPath;

    @ResponseBody
    @RequestMapping(value = "/api/uploadFile", method = RequestMethod.POST)
    public Packages upload(@RequestParam("file") MultipartFile file) throws Exception {
        Packages result = new Packages();
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        File dest = new File(uploadPath + "/" + UUIDUtils.UUID()+fileName.substring(fileName.lastIndexOf(".")));
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            result.getHead().setStatus(500);
            int msgLength = e.getMessage().length() > 100 ? 100 : e.getMessage().length();
            result.getHead().setMsg("文件上传失败:" + e.getMessage().substring(0, msgLength));
        }

        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/api/uploadImg", method = RequestMethod.POST)
    public Packages uploadImg(HttpServletRequest request) throws Exception {
        String encryptedText = request.getParameter("request");
        Packages result = JSONUtils.json2Obj(encryptedText, Packages.class);

        Map<String, String> params = (Map<String, String>) result.getBody().getData();
        String base64url = params.get("base64url");
        String type = params.get("type");

        try {
            String fileName = ImageUtils.base64ToImageMin(base64url, uploadPath, type);

            result.getBody().setData(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            result.getHead().setStatus(500);
            int msgLength = e.getMessage().length() > 100 ? 100 : e.getMessage().length();
            result.getHead().setMsg("图片上传失败:" + e.getMessage().substring(0, msgLength));
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/pdf")
    public Packages index(HttpServletRequest request) {
        System.out.println("\n[request service]" + request.getRequestURI() + "\n");

        try {
            PdfUtils.buildPdf("{name:'aa'}");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Packages pkg = new Packages();
        return pkg;
    }

    @RequestMapping("/baidu")
    public String test(){
        return "redirect:https://www.baidu.com/";
    }
}