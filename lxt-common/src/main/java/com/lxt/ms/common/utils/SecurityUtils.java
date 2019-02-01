package com.lxt.ms.common.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.springframework.util.Base64Utils;

import com.thoughtworks.xstream.core.util.Base64Encoder;

public class SecurityUtils {
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
        String strs = new Base64Encoder().encode(bt);
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
        return new String(bt);
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
        return new String(bt);
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
	
//	public static void main(String[] args) throws Exception {
//		String data = "oI0fC7Up00hNB0k/Uh8B91GkoNUCcAnsqwbs95n+vZlxdp6wgxQMRkm6Y4GMRX6kZFHHzGjHJxlRpKDVAnAJ7PBiraYCDaX3FiuQrOwpB7Vwm3OMqBtaD5U2DO5c1OUhQq3TafM79BxTQ2fxU/JhZCR3Siu189mXzefNRe8pVB/N0h1TTUCg9UceHxHg7b13B8g2tWvk2yENpPelNv+whO4Qy1GaiWWWsvsGa9l/JYqlFyhROp9oftPWcS2bMEI+kFfF29dmi5EqushmBGmNb2LnWix+c4Ul1CwEj4Mh/1qq3SIah4/RqUKd50xulkR7AmZBCpHkovYRvgBIFtbGde06W57QPUELIY2+7zC2J2b4URxvaIM1qEAnAm/YYgErFqQwM496/JJ0h8ZT2tLl8OO53F66ZGxByPBhPRjNtnJCnX7gA/oKovBiraYCDaX3RGvN4niISPV7WeWZR8jZZPmvwelnYJbfH4v+vFs9nsdRpKDVAnAJ7G1wYBjkXutQvx8Og7O/V5drICHa6+StVPzyiCZ0S5I/Y0Ysi0JgYa3y3igWP2Qj9NZsIfdffNBeiavtRdx/F66JgBgIWSSy7b8fDoOzv1eXW2ygmQnvhNt3HFqiAYGYi2NGLItCYGGt8t4oFj9kI/TWbCH3X3zQXomr7UXcfxeuS6nCmLq7euOavZoxCqjulahc00pQHx1G";
//        String key = "ed26d4cd99aa11e5b8a4c89cdc776729";
//        System.out.println(decrypt(data, key));
//	}
}
