package com.lxt.ms.pdf.controller;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.ResponseWrapper;
import com.lxt.ms.common.utils.ImageUtils;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

@Controller
public class UploadController {
    @Value("${file.uploadPath}")
    private String uploadPath;

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws Exception {
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

        response.setContentType("text/html;chatset=utf-8");
        response.getWriter().write("ok");
        response.getWriter().flush();
        response.getWriter().close();

//        return new ResponseEntity<Packages>(result, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadImg", method = RequestMethod.POST)
    public Packages uploadImg(HttpServletRequest request) throws Exception {
        String decryptedText = request.getParameter("request");
        Packages result = JSONUtils.json2Obj(decryptedText, Packages.class);

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
}