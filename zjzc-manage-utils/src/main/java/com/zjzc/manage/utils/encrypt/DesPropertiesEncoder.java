package com.zjzc.manage.utils.encrypt;


import java.util.Scanner;

/**
 * DES实现
 * @author qinzihao
 */
public class DesPropertiesEncoder implements PropertiesEncoder {
    
    /**
     * 默认的key (请不要随意更改)
     */
    private static final String KEY = "jT#L505/pTb0%&d3&$u1R(#!9Fy3C";

    @Override
    public String encode(String str) {
        String s = DESEncrypt.encoder(str, KEY);
        return s;
    }

    @Override
    public String decode(String str) {
        String s = DESEncrypt.decoder(str, KEY);
        return s;
    }

	@Override
	public String encode(String str, String key) {
		String s = DESEncrypt.encoder(str, key);
        return s;
	}

	@Override
	public String decode(String str, String key) {
		String s = DESEncrypt.decoder(str, key);
        return s;
	}
	
	public static void main(String[] args) {
    	DesPropertiesEncoder des = new DesPropertiesEncoder();
    	/*System.out.println(des.encode("root"));
    	System.out.println(des.encode("DK1fq2K82MzTcYU6wHp7"));
    	System.out.println(des.encode("123456"));*/

        Scanner out = new Scanner(System.in);
        while (true) {
            System.out.println("输入一个String型数据(Y结束)：");
            String a= out.next();
            if("Y".equals(a.toUpperCase())){
                System.out.println("结束");
                break;
            }
            String test = a;
            System.out.println("加密前的字符：" + test);
            System.out.println("加密后的字符：" + des.encode(test));
        }
    }
	
}
