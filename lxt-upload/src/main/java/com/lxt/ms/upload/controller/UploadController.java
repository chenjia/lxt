package com.lxt.ms.upload.controller;

import com.lxt.ms.common.bean.web.Packages;
import com.lxt.ms.common.bean.web.ResponseWrapper;
import com.lxt.ms.common.utils.JSONUtils;
import com.lxt.ms.common.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Controller
public class UploadController {
    @Value("${file.uploadPath}")
    private String uploadPath;

    @ResponseBody
    @RequestMapping(value = "/api/uploadFile", method = RequestMethod.POST)
    public ResponseWrapper upload(@RequestParam("file") MultipartFile file) throws Exception {
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

        return new ResponseWrapper(result);
    }

    @ResponseBody
    @RequestMapping(value = "/api/uploadImg", method = RequestMethod.POST)
    public ResponseWrapper uploadImg(HttpServletRequest request) throws Exception {
        String encryptedText = request.getParameter("request");
        System.out.println(encryptedText + "\n");
        Packages pkg = JSONUtils.json2Obj(encryptedText, Packages.class);

        Map<String, String> params = (Map<String, String>) pkg.getBody().getData();
        String base64url = params.get("base64url");

        Packages result = new Packages();
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(base64url);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    b[i]+=256;
                }
            }
            File dest = new File(uploadPath + "/" + UUIDUtils.UUID() + ".jpeg");
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            OutputStream out = new FileOutputStream(dest);
            out.write(b);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
            result.getHead().setStatus(500);
            int msgLength = e.getMessage().length() > 100 ? 100 : e.getMessage().length();
            result.getHead().setMsg("图片上传失败:" + e.getMessage().substring(0, msgLength));
        }

        return new ResponseWrapper(result);
    }
}
