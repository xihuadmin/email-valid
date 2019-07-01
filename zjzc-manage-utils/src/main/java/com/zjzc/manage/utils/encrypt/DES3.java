package com.zjzc.manage.utils.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 * 
 */
public class DES3 {
    // 向量
    private final static String iv = "01234567";

    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密(加密后会做一次BASE64编码)
     * 
     * @param plainText
     *            普通文本
     * @return
     * @throws Exception
     */
    public static String encrypt(String plainText, String secretKey)
            throws Exception {
    	byte[] encryptData=encrypt(plainText.getBytes(encoding), secretKey);
    	return Base64.encode(encryptData);
    }
    
    /**
     * 3DES加密(不会再做一次base64编码)
     * @param plainText--加密字节数组
     * @param secretKey--加密key
     * @param offset--加密字符数组偏移量
     * @param length--截取的字节数据长度
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] plainText, String secretKey,int offset,int length)
            throws Exception {
    	Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);

        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText,offset,length);
        return encryptData;
    }
    
    /**
     * 3DES加密(不会再做一次base64编码)
     * @param plainText--加密字节数组
     * @param secretKey--加密key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] plainText, String secretKey)throws Exception{
    	return encrypt(plainText, secretKey, 0, plainText.length);
    }

    /**
     * 3DES解密
     * 
     * @param encryptText
     *            加密文本
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptText, String secretKey)
            throws Exception {
    	byte[] decryptData=decrypt(Base64.decode(encryptText),secretKey);
    	return new String(decryptData, encoding);
    }
    
    /**
     * 3DS解密(不会再做一次base64解码)
     * @param encryptText--字节数组
     * @param secretKey--解密key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] encryptText, String secretKey,int offset,int length)
            throws Exception {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] decryptData = cipher.doFinal(encryptText,offset,length);
        return decryptData;
    }
    
    /**
     * 3DS解密(不会再做一次base64解码)
     * @param encryptText--字节数组
     * @param secretKey--解密key
     * @param offset--加密字符数组偏移量
     * @param length--截取的字节数据长度
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] encryptText, String secretKey)
            throws Exception {
    	return decrypt(encryptText, secretKey, 0, encryptText.length);
    }

    public static void main(String[] args) throws Exception {
        String message = "今天天气好吗？yes.";
        String key = "faefd16@@93f1-54a849%844";
        // faefd16@@93f1-54a849%844!ed3a2#9
        String s = encrypt(message, key);
        System.out.println(s);
        String t = decrypt(s, key);
        System.out.println(t);
    }
}
