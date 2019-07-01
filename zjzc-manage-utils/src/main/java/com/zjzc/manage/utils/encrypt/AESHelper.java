package com.zjzc.manage.utils.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.map.LRUMap;
//import org.slf4j.LoggerFactory;

/**
 * This class is used for ...
 * AES Coder<br/> 
 * secret key length:   128bit, default:    128 bit<br/> 
 * Generated through md5.
 * mode:    ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128<br/> 
 * padding: Nopadding/PKCS5Padding/ISO10126Padding/ 
 *
 *Improvement:If want to improve performence,you could cache Cipher to reduce the time of producing the Cipher.
 * @version
 *       1.0
 *       
 *       1.1 Implements improvement by apache collections LRUMap.
 * @author  zhangfeng@chinaduo.com
 * @time 2011-11-16 下午05:16:01
 */
public class AESHelper {
        // FIXME
//        private static org.slf4j.Logger logger = LoggerFactory.getLogger(AESHelper.class);
       
        /**
         * use password to encrypt content by AES(128bit). 
         * @param content       you want to encrypt message.
         * @param password      the encrypt key you want to use.
         * @return the content after encrypted.
         */
        public static String encrypt(String content, String password){
                try {
                        byte[] ptext = content.getBytes();
                        byte[] ctext = AESHelper.getEncryptCipher(password).doFinal(ptext);
                        return byte2hex(ctext);
                } catch(Exception e) {
                        throw new CipherException(e);
                }
        }
        /**
         * use password to decrypt content by AES(128bit).
         * @param content       you want to decrypt content.
         * @param password  the you want to use.
         * @return      the content after decrypted.
         */
        public static String decrypt(String content, String password) {
                try {
                        byte[] ptext = AESHelper.getDecryptCipher(password).doFinal(hex2byte(content)); 
                        return new String(ptext);
                } catch(Exception e) {
                        throw new CipherException(e);
                }
        }
        
        private static final int MAX_SIZE = 100;
        private static LRUMap cacheEncryptCipher = new LRUMap(MAX_SIZE);
        private static LRUMap cacheDecryptCipher = new LRUMap(MAX_SIZE);
        private static synchronized Cipher getEncryptCipher(String password) {
                try {
                        // FIXME
                        // log.info("before cacheEncryptCipher.get, password=" + password);
                        // StringBuilder passwordBud = new StringBuilder(password);
                        // password = password + "";
                        Cipher cp = (Cipher) cacheEncryptCipher.get(password);
                        // FIXME
                        // log.info("after cacheEncryptCipher.get, cp=" + cp);
                        if(cp == null) {
                                Key key = AESHelper.getKey(password);
                                // NO
                                cp = Cipher.getInstance("AES");
                                cp.init(Cipher.ENCRYPT_MODE, key);
                                cacheEncryptCipher.put(password, cp);
                        }
                        return cp;
                } catch (Exception e) {
                        throw new CipherException(e);
                }
        }
        private static synchronized Cipher getDecryptCipher(String password) {
                try {
                        Cipher cp = (Cipher) cacheDecryptCipher.get(password);
                        if(cp == null) {
                                Key key = AESHelper.getKey(password);
                                cp = Cipher.getInstance("AES");
                                cp.init(Cipher.DECRYPT_MODE, key);
                                
                                cacheDecryptCipher.put(password, cp);
                        }
                        return cp;
                } catch (Exception e) {
                        throw new CipherException(e);
                }
        }
        public static class CipherException extends RuntimeException {
                private static final long serialVersionUID = -7938919648349659765L;

                public CipherException(Exception e) {
                super(e);
            }
        }
        
        private static Key getKey(String publickey) {
                // NO
                byte[] bytes = hex2byte(DigestUtils.md5Hex(publickey));
                return new SecretKeySpec(bytes, "AES");
        }
        public static byte[] hex2byte(String strhex) {
                if (strhex == null) {
                        return null;
                }
                int l = strhex.length();
                if (l % 2 != 0) {
                        return null;
                }
                byte[] b = new byte[l / 2];
                for (int i = 0; i != l / 2; i++) {
                        b[i] = (byte) Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
                }
                return b;
        }

        public static String byte2hex(byte b[]) {
                StringBuilder sb = new StringBuilder();
                for (int n = 0; n < b.length; n++) {
                        String stmp = Integer.toHexString(b[n] & 0xff);
                        if (stmp.length() == 1) {
                                sb.append("0");
                        }
                        sb.append(stmp);
                }
                return sb.toString().toUpperCase();
        }

        public static void main(String[] args) {
                
                String key="yyDFFKiMf34285DM";
                String content = "I'am testing...";
                String encode = AESHelper.encrypt(content, key);
                
                
                System.out.println(AESHelper.decrypt("2FEB959CD7FC80D39418595D7934E7F4", key));
                
                String decode = AESHelper.decrypt(encode, key);
                System.out.println(content+"\n"+encode+"\n"+decode);
                
                
        }
}
