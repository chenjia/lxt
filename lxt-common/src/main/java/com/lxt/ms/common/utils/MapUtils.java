package com.lxt.ms.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
//    public static void main(String[] args) throws IOException {
//        MapUtils utils = new MapUtils();
//
//        Map<String,Object> map = utils.getLngAndLat("陈家镇安若路58弄");
//        if("OK".equals(map.get("status"))){
//            Map<String, Object> temp = (Map)((Map)map.get("result")).get("location");
//            System.out.println("经度："+temp.get("lng")+"---纬度："+temp.get("lat")+"---level："+((Map)map.get("result")).get("level"));
//        }
//    }

    //http://api.map.baidu.com/geocoder?address=%E5%98%89%E5%AE%9A%E5%8C%BA%E5%8D%97%E7%BF%94%E9%95%87%E5%8F%A4%E6%BC%AA%E5%9B%AD%E8%B7%AF658%E5%BC%84%E5%AE%BD%E5%BA%A6%E7%BD%91%E7%BB%9C-%E5%98%89%E5%AE%9D%E5%B0%8F%E5%8C%BA&output=json&ak=V4lA9lI4pyViBK1BQIXN2uNau9QnK4yL
    //http://api.map.baidu.com/geocoder/v2/?address=上海市&output=json&city=上海市&ak=qkLucZ2AnCHjuFci45pkGNXL

    public  Map<String,Object> getLngAndLat(String address){
        Map<String,Object> map=new HashMap<String, Object>();

        String url = "http://api.map.baidu.com/geocoder/v2/?address="+URLEncoder.encode(address)+"&output=json&city="+URLEncoder.encode("上海市")+"&ak=qkLucZ2AnCHjuFci45pkGNXL";
        String json = loadJSON(url);
        System.out.println(json);
        map = JSONUtils.json2Map(json);
        if("OK".equals(map.get("status"))){
            System.out.println("经纬度获取成功");
        }else{
            System.out.println("未找到相匹配的经纬度！");
        }
        return map;
    }

    public static String loadJSON (String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL oracle = new URL(url);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = null;
            while ( (inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {

        } catch (IOException e) {

        }
        return json.toString();
    }
}