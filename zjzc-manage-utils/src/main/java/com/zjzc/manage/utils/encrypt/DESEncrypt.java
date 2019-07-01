package com.zjzc.manage.utils.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DESEncrypt {

	public static final String DES = "DES";

	
	public static byte[] encoder(byte[] src, byte[] key)
			throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}


	public static byte[] decoder(byte[] src, byte[] key)
			throws Exception {
		SecureRandom sr = new SecureRandom();
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(DES);
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 * @param data
	 * @param key
	 * @return
	 */
	public final static String decoder(String data, String key) {
		try {
			return new String(
					decoder(hex2byte(data.getBytes()), key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 加密
	 * @param data
	 * @param key
	 * @return
	 */
	public final static String encoder(String data, String key) {
		try {
			return byte2hex(encoder(data.getBytes(), key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}


	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();

	}


	private static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("HAHA");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String key = "aiF&Wr!IFUw#ifj%wUawf&#";
		String src = "{\"mobile\":\"18825182343\",\"memberNo\":\"101\",\"channel\":\"ZXWXP\",\"outTradeNo\":\"123456789012351\",\"body\":\"浪里个浪\",\"subOpenId\":\"my_fucking_open_id\",\"totalFee\":\"1\",\"callbackUrl\":\"http://www.hao123.com\",\"notifyUrl\":\"http://www.baidu.com\"}";
		
		String data = encoder(src, key);
		System.out.println(data);
		
		System.out.println(decoder(data, key));
		
	}
}
