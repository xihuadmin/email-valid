package com.zjzc.manage.utils.encrypt;

/**
 * 针对properties文件的加密、解密接口
 *
 */
public interface PropertiesEncoder {

    /**
     * 加密
     * @param str
     * @return 加密后的字符串
     */
    String encode(String str);
    
    /**
     * 解密
     * @param str
     * @return 解密后的字符串
     */
    String decode(String str);
    
    /**
     * 加密
     * @param str
     * @param key
     * @return 加密后的字符串
     */
    String encode(String str, String key);
    
    /**
     * 解密
     * @param str
     * @param key
     * @return 解密后的字符串
     */
    String decode(String str, String key);
    
}
