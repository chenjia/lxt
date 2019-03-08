package com.lxt.ms.common.utils;

public class IpUtils {

//    public static void main(String[] args) {
//        String[] array = new String[]{"127.0.0.1", "192.168.1.102", "255.255.255.0"};
//        for(String item : array){
//            int intIp = ip2int(item);
//            String strIp = int2ip(intIp);
//            System.out.println(intIp+":"+strIp);
//        }
//
//    }

    public static int ip2int(String ip){
        int result = 0;

        String[] array = ip.split("\\.");
        for(int i=0;i<array.length;i++){
            result = result | Integer.parseInt(array[i]) << 8 * i;
        }

        return result;
    }

    public static String int2ip(int intIp){
        String[] result = new String[4];

        for(int i=0;i<result.length;i++){
            int pos = i * 8;
            int and = intIp & (255 << pos);
            result[i] = String.valueOf(and >>> pos);
        }

        return String.join(".", result);
    }
}
