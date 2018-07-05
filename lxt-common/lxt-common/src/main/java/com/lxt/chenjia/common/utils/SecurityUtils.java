package com.lxt.chenjia.common.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtils {
	public final static String key = "ed26d4cd99aa11e5b8a4c89cdc776729";
	
	private final static String DES = "DES";
	
	private final static String encoding = "UTF-8";
     
	/**
     * Description 根据键值进行加密
     * @param data 
     * @param key  加密键byte数组
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
        String strs = new BASE64Encoder().encode(bt);
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
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,key.getBytes(encoding));
        return new String(bt);
    }
    
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt(String data) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
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
	
	public static void main(String[] args) throws Exception {
		String data = "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";
        String key = "ed26d4cd99aa11e5b8a4c89cdc776729";
        System.err.println(decrypt(data, key));
	}
}
