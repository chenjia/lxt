package com.lxt.ms;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ScriptResult;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import com.lxt.ms.common.utils.BaiduUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootApplication
public class PdfApplication {

    public static void main( String[] args ) {
        SpringApplication.run(PdfApplication.class, args);
        test();
    }


    public static void test(){
        try {
            String url = "https://www.baidu.com?a=1";
            WebClient client = new WebClient(BrowserVersion.CHROME);
            client.getOptions().setJavaScriptEnabled(true);
            client.getOptions().setCssEnabled(false);
            client.getOptions().setRedirectEnabled(true);
//            client.waitForBackgroundJavaScript(3000L);
            client.getCookieManager().setCookiesEnabled(true);
            client.getOptions().setThrowExceptionOnScriptError(false);
            client.getOptions().setThrowExceptionOnFailingStatusCode(false);
            client.getOptions().setUseInsecureSSL(true);
            client.getOptions().setTimeout(30000);
            HtmlPage page = client.getPage(url);
//            page.executeJavaScript("document.getElementById('kw').value = 'java';");
//            page.executeJavaScript("document.forms[0].submit();");

            final HtmlForm form = page.getFormByName("f");
            final HtmlSubmitInput button = form.getInputByValue("百度一下");
            final HtmlTextInput textField = form.getInputByName("wd");
            textField.setValueAttribute("java");
            final HtmlPage nextPage = button.click();
            HtmlForm loginForm = (HtmlForm) page.getElementById("loginForm");
            DomNodeList<HtmlElement> list = loginForm.getElementsByTagName("a");
            for(HtmlElement a : list){
                System.out.println(a.getTextContent());
            }
            client.waitForBackgroundJavaScript(10000);

            System.out.println(nextPage.getUrl());
            System.out.println(nextPage.getBaseURI());
        } catch (Exception e) {
            e.printStackTrace();
        }


//        ScriptResult scriptResult = page.executeJavaScript("function getBase64Image(img) {\r\n" +
//                "console.log(img); \r\n" +
//                "var canvas = document.createElement(\"canvas\");\r\n" +
//                "canvas.width = img.width;\r\n" +
//                "canvas.height = img.height;\r\n" +
//                "var ctx = canvas.getContext(\"2d\");\r\n" +
//                "ctx.drawImage(img, 0, 0, img.width, img.height);\r\n" +
//                "var dataURL = canvas.toDataURL(\"image/png\");\r\n" +
//                "return dataURL.replace(\"data:image/png;base64,\", \"\")" +
//                "}" +
//                "getBase64Image(document.getElementById('logo'))");
//        System.out.println(scriptResult);
//        Object result = scriptResult.getJavaScriptResult();
//        BaiduUtils.ocrText(null, (String) result);
    }
}
