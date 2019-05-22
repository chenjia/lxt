package com.lxt.ms.common.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.util.Base64Utils;

public class SecurityUtils {
    public final static String letters = "abcdefghijklmnopqrstuvwxyz0123456789";

	public final static String key = "ed26d4cd99aa11e5b8a4c89cdc776729";
	
	private final static String DES = "DES";
	
	private final static String encoding = "UTF-8";
     
	/**
     * Description 根据键值进行加密
     * @param data 
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
        return encrypt(data, key);
    }
    
    /**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(encoding), key.getBytes(encoding));
        String strs = Base64Utils.encodeToString(bt);
        return strs.replaceAll("\\r\\n", "");
    }
 
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws IOException,
            Exception {
        if (data == null)
            return null;
        byte[] buf = Base64Utils.decodeFromString(data);
        byte[] bt = decrypt(buf,key.getBytes(encoding));
        return new String(bt, encoding);
    }
    
    /**
     * Description 根据键值进行解密
     * @param data
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data) throws IOException,
            Exception {
        if (data == null)
            return null;
        byte[] buf = Base64Utils.decodeFromString(data);
        byte[] bt = decrypt(buf,key.getBytes(encoding));
        return new String(bt, encoding);
    }
 
    /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
     
     
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
	
	public static String md5Encrypt(String str){
		try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");  
	        md.update(str.getBytes());  
	        byte[]byteDigest = md.digest();  
	        int i;  
	        StringBuffer buf = new StringBuffer("");  
	        for (int offset = 0; offset < byteDigest.length; offset++) {  
	            i = byteDigest[offset];  
	            if (i < 0)  
	                i += 256;  
	            if (i < 16)  
	                buf.append("0");  
	            buf.append(Integer.toHexString(i));  
	        }  
	        //32位加密  
	        return buf.toString();  
	        // 16位的加密  
	        //return buf.toString().substring(8, 24);   
	    } catch (NoSuchAlgorithmException e) {  
	        e.printStackTrace();  
	        return null;  
	    }
	}

	public static String encryptKey(String key) throws Exception {
        String encryptedKey = "";

        String[] array = key.split("");

        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            encryptedKey += array[i];
            for (int j = 0; j < i % 2 + 1; j++) {
                int index = random.nextInt(letters.length());
                encryptedKey += letters.substring(index, index+1);
            }
        }
        return Base64Utils.encodeToString(new StringBuilder(encryptedKey).reverse().toString().getBytes(encoding)).replaceAll("\n", "");
    }

    public static String decryptKey(String encryptedKey) {
        encryptedKey = new String(Base64Utils.decodeFromString(encryptedKey));
        String key = "";

        char[] c = new StringBuilder(encryptedKey).reverse().toString().toCharArray();

        for (int i = 0, j = 0; i < encryptedKey.length(); i++) {
            key += c[i];
            i += (j++ % 2 + 1);
        }

        return key;
    }
	
	public static void main(String[] args) throws Exception {

		String data = "oI0fC7Up00hNB0k/Uh8B98B2+kAmnZ+51mns5W7+37Q6JDlW+cxawPZskqRpSg2YV216yyNthtADtS7Iemxv1s9qaHldoTdwFXmU+muDB2YllLvMRfP89INOYPv/A5e4t6C9QooYxOYZ8klFxTZ5V+mE/S94hruHgpbB52FZawfZ1OiwOYhj7/SisLreVHd2f4fEsnLNeA+fSXogLh6gfYDtYcvFEwimn8rmWYQ8V7LDMgdZTO6hhckr+2vYdXUKap/migSeH6etVQocdrKy85dsEi/KC9TGc00/ZCqmPqZS2sJXu31stJUFh57vG2Pt+wMbu5rAk+8TboBreBa6AFIz4QD9V2kvPuof/c80JsdI3HqoTZ5N2LYH/KC5aFrgxbVHQTEdN9aOpeDonX2nWyUnAMOVYjIOiRsEeAz8WP4/YbmvdziwruqU+f7ZIrS/9BoJe1AGIUl3+w2plwhksrWJPVDd13srUuFV3BNJMG4wcsSx2tI1buuhz71AoKCMAfYvBAKHt8Ei+M8VeiPDNv2LQOL2bwm9WEgmWUkWIyXfTz0XPovWGVUgU3R/GYEsVv8F7CWolRmglGFOzwQ/RZQ+GgCZNC/J+uNocuEAEsfSbZUK6n49ewK2HuWRRepltYk9UN3XeyuTpJ+esYOL1gP4gknGrYx1Nij/hhGmJtO1iT1Q3dd7K63Y5uqlJ9TqcETzxAalXaeUM8ah9XOyYGQ082dhIwG9HmfZZOT6KEhkNPNnYSMBvd4WOLeFb29ftYk9UN3Xeys5SIs07wgq81P5IQGo2qFzeyP8OOUgNFtgVTAsCNYH4NdTVtnIc+s0yhz1UB/o5Y1moBP3eIXVJSU2SNGeHOoL35VNhPk/l0i6ErsaTWZBsls3Nrl6o+h64ze+ATgUvhPGK8RtiymLi71sY1sfJKfq5OAZXE2rEAoscnK5EZanQCn54mDAUYQwKbb+UD/sm7BLV51vFQiNOM7WX91RZTd6I1Gv17rNps4FOHiRC1wleBJvoL4kkfry2sQCkjvD41FuLx4Rxq6QLsPpn0crldmCFJ7o0bFfzRh3C99Ee2SeSv4qjWV9TNVbl/ltnCcHW4zKm3UXyuaQPmqirakw2rWtYoJJcu1n/2rJmAhSLw1gw/qChOS3j9tWmk6Gyeil76TRDyjjb8TCfrVWXwoO4hm0uK5jzMi7ncm619GHHwJ2J7c0Fv+BiGOaAnM5SsTu3XfAm8515YknVOofHT08YjV+A+IDHF8Kp4lqMxK77HstwzPwirtFh3S9hml60FRJzBLKmUP7YuBe5xMURiwIyhRx/C/iWV0a7K+n4OUuTW3d4hi33O+M6SKkt5oTNrW+x1MjUa/Xus2mznVvMGYZitCM1sLFMsezrUfvVaeV1otVJK9ftReiAlfoKcFAl1xi4+u1iT1Q3dd7KyGZuyDrX7pijkWePdDIvWyucPleoqXFv9+C5pHUhSX8s9nf0pQfHU61iT1Q3dd7K0bmwQKtju/lxQjkC8RWRdPh+wfzgab9/McYS7eGyDokqgyMyLEJaHXX1jnaNkSYeoi1HiGsTNH4t5oTNrW+x1O1iT1Q3dd7K77fP8Z/xwQX1Be83L2y/cChoexoasBc8qEp1J1rgmTri3a7gRht/x3uyunwEvltxTo3/oAH/CJ3Ybuc8XWj131O12vk1qdEr4eEZUKaDl2zjNBqFiM1psabbpeeuxFRtiNstApCsjMfTtdr5NanRK+FSU3Jqo604vl1JNF+M5akcFEndo2+RxuiDwRJJ4R4fareblCE7CNZp9vszKLtR/XxzV/ynUHrSQPZmeJRO+g2ubrCPIWjRpIx6s0KPy51OsP+KdYNtDUTrAYOZCIpiomm2Xpf/LdvNcHwGvTICymayhz1UB/o5Y1ZPh7MVpNl678bdV35RC7p9H7Hkde6gaUAZuoqiHXhifqChOS3j9tWmk6Gyeil76TRDyjjb8TCfrVWXwoO4hm0uK5jzMi7nclnCo8oSdiEpZ2Pw9642H/+CCimoWwuXTnHiHc2ylu3+ZWoXv5RwZZJZC2B0vc0voRt/Q0WnbcSKQ4zSFZ6V5mVwMJyPBqMR6D1tWwoA2pdls7WX91RZTd6I1Gv17rNps4FOHiRC1wleBJvoL4kkfry2sQCkjvD41FuLx4Rxq6QLsPpn0crldmCmiUcxFu1SjzHiMCPGmRbATYhPn5t2U3HRwqX4VNPivEXRB9L8pI1f8LHiy4o01htLgqKq9Bj/qUC+zSlrgg7Hda+V0QTSoZhBVRj3UoUKiLm51xrKJ2DWar1SEbIGcfA6uHGG8Xk+9VFATP1XZ6Qo+AsSJ3WwXK/s9OvfOGqm+4YP1EF8bLwhkLcRJEAp4dAKPG48Lwbh4Z9JD2dDNj7AXjQHcBOHit8FuubL8rJP1obxo6+m42YCdOXxNBi5K4xbi8eEcaukC4nSV4cu35+vbMXIRt78LwRBTh4kQtcJXgSb6C+JJH68trEApI7w+NR8ClhGcuXMgPHsqGgWKaG3VDsa5fnpH18l+cq/eo3fW7lrA83NaIbGQYoZoqS96Rsmk6Gyeil76QdE5ov6neelub+zVjWRlM6X62m7kksemXlzIWKyE8+/aO7bBbaN6OtoUs7e/Ln76eAogW0zpZ+mYfe0HP4/POkXV2vTjGeiCRAEeVgkB5DNRlNDkFj90cvYFWC5zW6hC0bxo6+m42YCdOXxNBi5K4xbi8eEcaukC4nSV4cu35+vbMXIRt78LwRBTh4kQtcJXgSb6C+JJH68trEApI7w+NR8ClhGcuXMgPHsqGgWKaG3VDsa5fnpH18/30qJPSbxGHlrA83NaIbGQYoZoqS96Rsmk6Gyeil76QdE5ov6neelub+zVjWRlM6w905NwO1iUsyJumlYpAN7aO7bBbaN6OtoUs7e/Ln76eAogW0zpZ+mYfe0HP4/POkXV2vTjGeiCRAEeVgkB5DNRlNDkFj90cvA+8hI3+3rOqdcXHmzxC9sTHyWKOQGHWQpVLh+KpSXlT6q4xNK0CYTw==";
        String text = decrypt(data, key);
        System.out.println(text);

	}
}
