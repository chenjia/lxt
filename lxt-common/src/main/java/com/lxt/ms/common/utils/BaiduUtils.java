package com.lxt.ms.common.utils;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class BaiduUtils {
    /**
     * 获取权限token
     * @return 返回示例：
     * {
     * "access_token": "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567",
     * "expires_in": 2592000
     * }
     */
    public static String getAuth() {
        // 官网获取的 API Key 更新为你注册的
        String clientId = "GY8L4ytmX0GMWY4V5M2nThkr";
        // 官网获取的 Secret Key 更新为你注册的
        String clientSecret = "7teu7mhpEx7P423EDnwl0tkvW6vHZnr0";
        return getAuth(clientId, clientSecret);
    }

    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAuth(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            System.err.println("result:" + result);
            Map<String, Object> jsonMap = JSONUtils.json2Map(result);
            String access_token = (String)jsonMap.get("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }


    public static String ocrTextJson(String filePath, String base64) {
        String result = "";
        String apiHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic";

        try {
            String imgStr = "";
            if(StringUtils.isEmpty(filePath)){
                imgStr = base64;
            }else{
                byte[] imgData = FileUtil.readFileByBytes(filePath);
                imgStr = Base64Util.encode(imgData);
            }

            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");

            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = getAuth();
            result = HttpUtil.post(apiHost, accessToken, params);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String ocrText(String filePath, String base64) {
        String result = "";

        String json = ocrTextJson(filePath, base64);
        Map<String, Object> map = JSONUtils.json2Map(json);
        List<Map<String, Object>> results = (List<Map<String, Object>>) map.get("words_result");
        for(Map<String, Object> item : results){
            result += item.get("words");
        }
        System.out.println(result);

        return result;
    }

    public static void main(String[] args) {
        ocrText("/Users/farben/Downloads/aaaa.png", null);
    }
}
