package com.lxt.ms.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    public static void main(String[] args) throws IOException {
        MapUtils utils = new MapUtils();

        Map<String,Object> map = utils.getLngAndLat("杭州市华立科技园1号楼");
        if("OK".equals(map.get("status"))){
            Map<String, Object> temp = (Map)((Map)map.get("result")).get("location");
            System.out.println("经度："+temp.get("lng")+"---纬度："+temp.get("lat"));
        }
    }

    public  Map<String,Object> getLngAndLat(String address){
        Map<String,Object> map=new HashMap<String, Object>();
        String url = "http://api.map.baidu.com/geocoder?address="+address+"&output=json&ak=V4lA9lI4pyViBK1BQIXN2uNau9QnK4yL";
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