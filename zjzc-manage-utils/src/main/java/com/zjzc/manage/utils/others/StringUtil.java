package com.zjzc.manage.utils.others;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 字符串操作常用方法类.
 * <p>
 * 
 * 建议不要使用此类，请使用acpache StringUtils 
 */
public class StringUtil {

    public static final long INVALID_LONG_VALUE = Long.MIN_VALUE;

    /**
     * 删除字符
     * 
     * @param content
     * @param str
     * @return NONE
     */
    public static String deleteTail(String content, String str) {
        if (content == null || str == null) {
            return content;
        }
        int minSize = str.length();
        if (content.length() >= minSize) {
            content = content.substring(0, content.length() - minSize);
        }
        return content;
    }

    
 
    /**昵称规则
     * @param nikeName
     * @return
     */
    public static boolean patternNikeName(String nikeName) {
        String nikeNametrim=nikeName .replaceAll("\\s*", "");     
    	String pattern = "[\u4e00-\u9fa5\\w]+";   	
  	  Pattern p = Pattern.compile(pattern);
  	  Matcher m = p.matcher(nikeNametrim);
  	  return m.matches();
    }

    
    /**
     * 转换正则表达式特殊字符
     * 
     * @param content
     *            String
     * @return String
     */
    public static String escapePattern(String content) {
        // 这个不要使用正则表达式,或者要测试一下正则表达式的性能
        if (content == null) {
            return null;
        }
        content = content.replaceAll("\\\\", "\\\\\\\\");
        content = content.replaceAll("\\?", "\\\\?");
        content = content.replaceAll("\\+", "\\\\+");
        content = content.replaceAll("\\*", "\\\\*");
        content = content.replaceAll("\\[", "\\\\[");
        content = content.replaceAll("\\]", "\\\\]");
        content = content.replaceAll("\\{", "\\\\{");
        content = content.replaceAll("\\}", "\\\\}");
        content = content.replaceAll("\\(", "\\\\(");
        content = content.replaceAll("\\)", "\\\\)");
        content = content.replaceAll("\\-", "\\\\-");
        content = content.replaceAll("\\$", "\\\\\\$");
        return content;
    }

    /**
     * 固定位数
     * 
     * @param number
     *            int
     * @param minlen
     *            int
     * @return String
     */
    public static String fixed(int number, int minlen) {
        String result = String.valueOf(number);
        while (result.length() < minlen) {
            result = "0" + result;
        }
        return result;
    }

    /**
     * 在 str 中查找 s1 出现的次数
     * 
     * @param str
     * @param s1
     * @return
     */
    public static final int find(String str, String s1) {
        int count = 0;
        int fromindex = -1;
        while ((fromindex = str.indexOf(s1, fromindex + 1)) > -1) {
            count++;
        }
        return count;
    }

    /**
     * 字符串为空时返回默认值，否则返回字符串本身
     * 
     * @param str
     *            - 字符串
     * @param defaultValue
     *            - 默认值
     * @return
     */
    public static String getDefault(String str, String defaultValue) {
        if (StringUtils.isNotEmpty(str)) {
            return str;
        }
        return defaultValue;
    }

    /**
     * 过滤特殊字符，返回<code>String</code>类型.
     * <p>
     * 遇到特殊字符就中断截取,值只能是[a-zA-Z0-9].
     * 
     * @param string
     * @return 没有该参数则返回空字符串，不返回null.
     */
    public String getSimpleString(String string) {
        if (string == null) {
            return "";
        }
        String regex = "[a-zA-Z0-9]+";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        if (m.find()) {
            return m.group();
        }
        return "";
    }


    /**
     * 把数组拼接成字符串
     * 
     * @param strs
     * @param split
     *            - 分隔符
     * @return
     */
    public static String join(String[] strs, String split) {
        if (strs == null || strs.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(strs[0]);
        for (int i = 1; i < strs.length; i++) {
            sb.append(split + strs[i]);
        }
        return sb.toString();
    }

    /**
     * 如果 str 为 null，返回空字符串（""），否则返回 str
     * 
     * @param str
     * @return
     */
    public static String getNotNull(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

    /**
     * 判断是否空
     * @param str
     * @return
     */
    public static boolean IsEmpty(String str) {

        return str == null || str == "";
    }

    public static double parseDouble(String str) {
        double v = 0;
        try {
            v = Double.parseDouble(str);
        } catch (Exception e) {
            v = INVALID_LONG_VALUE;
        }
        return v;
    }

    public static int parseInt(double num) {
        return new Double(num).intValue();
    }

    public static int parseInt(String str) {
        return parseInt(str, Integer.MIN_VALUE);
    }

    public static int parseInt(String str, int custom) {
        return parseInt(str, Integer.MIN_VALUE, custom);
    }

    public static int parseInt(String str, int min, int custom) {
        int result = custom;
        try {
            result = Integer.parseInt(str);
        } catch (Exception e) {
            // e.printStackTrace();
            result = custom;
        }
        // result = Math.abs(result);
        if (result < min) {
            result = min;
        }
        return result;
    }

    public static long parseLong(String str) {
        long v = 0;
        try {
            v = Long.parseLong(str);
        } catch (Exception e) {
            v = INVALID_LONG_VALUE;
        }
        return v;
    }

    /**
     * 获得字符串的子字符串，考虑中文为 2 个字符
     * 
     * @param str
     * @param len
     * @return
     */
    public static String subString(String str, int len) {
        return subString(str, 0, len);
    }

    /**
     * 获得字符串的子字符串，考虑中文为 2 个字符
     * 
     * @param str
     * @param start
     * @param len
     * @return
     */
    private static String subString(String str, int start, int len) {
        if (str == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (int i = start; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < 255) {
                counter++;
            } else {
                counter = counter + 2;
            }
            if (counter > len) {
                break;
            }
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * s中的s1替换成s2
     * 
     * @param s
     * @param s1
     * @param s2
     * @return
     */
    public static final String replace(String s, String s1, String s2) {
        if (s == null)
            return null;
        int i = 0;
        if ((i = s.indexOf(s1, i)) >= 0) {
            char ac[] = s.toCharArray();
            char ac1[] = s2.toCharArray();
            int j = s1.length();
            StringBuffer sb = new StringBuffer(ac.length);
            sb.append(ac, 0, i).append(ac1);
            i += j;
            int k;
            for (k = i; (i = s.indexOf(s1, i)) > 0; k = i) {
                sb.append(ac, k, i - k).append(ac1);
                i += j;
            }
            sb.append(ac, k, ac.length - k);
            return sb.toString();
        } else {
            return s;
        }
    }

    /**
     * 转换成 '1','2','3' 这样的形式
     */
    public static String joinString(String strs[]) {
        if (strs == null) {
            return null;
        }

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (i > 0) {
                buf.append(',');
            }
            buf.append('\'');
            buf.append(strs[i]);
            buf.append('\'');
        }
        return buf.toString();
    }

    /**
     * 分割为 int 数组，默认以 , 作分隔符
     * 
     * @param content
     * @return
     */
    public static int[] toInts(String content) {
        return toInts(content, ",");
    }

    public static int[] toInts(String content, String split) {
        if (StringUtils.isBlank(content) || split == null) {
            return null;
        }
        String[] strs = content.split(split);
        if (strs.length == 0) {
            return null;
        }
        int[] re = new int[strs.length];
        for (int i = 0; i < re.length; i++) {
            re[i] = parseInt(strs[i]);
        }

        return re;
    }
    
    public static Long[] toLongs(String content, String split) {
        if (StringUtils.isBlank(content) || split == null) {
            return null;
        }
        String[] strs = content.split(split);
        if (strs.length == 0) {
            return null;
        }
        Long[] re = new Long[strs.length];
        for (int i = 0; i < re.length; i++) {
            re[i] = NumberUtils.toLong(strs[i]);
        }

        return re;
    }

    /**
     * 将字符串分解成字符串数组.
     * <p>
     * 
     * @param str
     * @return NONE
     */
    public static String[] split(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        int SIZE = 125;
        int pages = (str.length() + SIZE - 1) / SIZE;
        String[] contents = new String[pages];
        int start = 0;
        for (int i = 0; i < pages; i++) {
            if (i == (pages - 1)) {
                contents[i] = str.substring(start);
            } else {
                contents[i] = str.substring(start, start + SIZE);
            }
            start += SIZE;
        }
        return contents;
    }

    /**
     * 分段
     * 
     * @param content
     * @param coptem
     * @return
     */
    public static String[] patchMa(String content, String coptem) {
        StringBuffer contentBatch = new StringBuffer();
        Pattern pa = Pattern.compile(coptem);
        Matcher ma = pa.matcher(content);
        int pos = 0;
        int ii = 0;

        while (ma.find(pos)) {
            contentBatch.append(ma.group(1));
            contentBatch.append("#!#");
            ii = ii + 1;
            pos = ma.end();
        }
        return contentBatch.toString().split("#!#");
    }

    /**
     * 在数字前补零
     * 
     * @param num
     *            数字
     * @param length
     *            输出位数
     */
    public static String addzero(int num, int length) {
        String str = "";
        if (num < Math.pow(10, length - 1)) {
            for (int i = 0; i < (length - (num + "").length()); i++) {
                str += "0";
            }
        }
        str = str + num;
        return str;
    }

    public static boolean isEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return false;
        }
        // String pattern = "^\\w+\\.*\\w+@\\w+\\.\\w+$";
        String pattern = "^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\\.[a-zA-Z0-9_-]{2,3}){1,2})+$";

        if (email.matches(pattern)) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否为数字
     * 
     * @param sNum
     * @return
     */
    public static boolean isNum(String sNum) {
        if (StringUtils.isBlank(sNum)) {
            return false;
        }
        if (sNum.matches("[0-9]*")) {
            return true;
        }
        return false;
    }

    /**
     * 检查是否为小数
     *
     * @param sNum
     * @return
     */
    public static boolean isDecimal(String sNum) {
        if (StringUtils.isBlank(sNum)) {
            return false;
        }
        if (sNum.matches("[0-9]*")) {
            return true;
        }
        if (sNum.matches("[0-9]+(\\.[0-9]{1,5})?")) {
            return true;
        }
        return false;
    }


    /**
     * 获得一个字符串有多少个字节
     * 
     * @param s
     *            - 字符串
     * @return 长度
     */
    public static int getByteLength(String s) {
        return (s == null ? 0 : s.getBytes().length);
    }

    // public static boolean isEmpty(String str){
    // return (null == str || str.equals("") || str.isEmpty());
    // }
    /**
     * 全角半角互转方法
     * 
     * @param str
     *            要转换的字符串
     * @param flag
     *            标记，为false时半转全，为true时全转半
     * @return
     */
    public static String dbc2sbc(String str, boolean flag) {
        // 如果传入的字符串为空串，则直接返回
        if (str.length() <= 0)
            return "";
        char[] inputStr = str.toCharArray();
        for (int i = 0; i < inputStr.length; i++) {
            int str1 = inputStr[i];
            // 全角空格12288，半角空格为32,全角中文句号12290,全角英文句号65294，半角句号46
            // 其他字符半角(33-126)与全角(65281-65347)的对应关系，均相差65248
            // 半角转全角
            if (!flag) {
                if (str1 == 32 || (str1 >= 33 && str1 <= 126))
                    str1 = str1 + 65248;
                if (str1 == 46)
                    str1 = 65294;
            }
            // 全角转半角
            else {
                if (str1 == 12288 || (str1 >= 65281 && str1 <= 65347))
                    str1 = str1 - 65248;
                if (str1 == 12290 || str1 == 65294)
                    str1 = 46;
            }
            inputStr[i] = (char) str1;
        }
        return new String(inputStr);
    }

    /**
     * 全角转半角
     * 
     * @param str
     * @return
     */
    public static String fullToHalf(String str) {
        return dbc2sbc(str, true);
    }

    /**
     * 半角转全角
     * 
     * @param str
     * @return
     */
    public static String halfToFull(String str) {
        return dbc2sbc(str, false);
    }

    /**
     * input如果长度不够length则未尾加空格补齐到length长度
     * 
     * @param input
     * @param length
     * @return
     */
    public static String addBlankWord(String input, int length) {
        if (input.length() >= length)
            return input;
        StringBuilder sb = new StringBuilder(input);
        for (int i = input.length(); i < length; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * 把字符串转换成ascii码
     * 
     * @param str
     * @return
     */
    public static byte[] strToAscii(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] c = str.toCharArray();
        byte[] b = new byte[c.length];
        for (int i = 0; i < c.length; i++)
            b[i] = (byte) (c[i] & 0x007F);
        return b;
    }

    public static String strToAscii(String str, String fix) {
        byte[] b = strToAscii(str);
        if (b == null) {
            return "";
        }
        String s = "";
        for (int i = 0; i < b.length; i++) {
            s = s + b[i] + fix;
        }
        return s.substring(0, s.length() - 1);
    }

    /**
     * ASCII码转字符串
     * 
     * @param ascii
     * @param fix
     * @return
     */
    public static String asciiToStr(String ascii, String fix) {
        String[] asciiArr = ascii.split(fix);
        int length = asciiArr.length;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append((char) Integer.parseInt(asciiArr[i]));
        }
        return sb.toString();
    }

    /**
     * 将中文进行ASCII编码 : "线双线" -> "\u897f\u5b89\u53cc\u7ebf"
     * 
     * @param str
     * @return
     * @author liuzifeng
     */
    public static String toAsciiString(String str) {
        char[] chars = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (char ch : chars) {
            String tmp = ch + "";
            if (tmp.length() == tmp.getBytes().length) {
                sb.append(tmp);
            } else {
                String hex = "\\u" + Integer.toHexString((int) ch);
                sb.append(hex);
            }
        }
        return sb.toString();
    }

    /**
     * 将ASCII码字符串转换成中文 : "\u897f\u5b89\u53cc\u7ebf" -> "西安双线" <br>
     * (能同时转换 "\u8888" 和 "\\u8888" )
     * 
     * @param str
     * @return
     * @author liuzifeng
     */
    public static String toNormalString(String str) {
        StringBuffer sb = new StringBuffer();
        boolean begin = true;
        String[] ss = str.split("\\\\\\\\u");
        if (ss.length == 1)
            ss = str.split("\\\\u");
        for (String s : ss) {
            if (begin) {
                begin = false;
                sb.append(s);
                continue;
            }
            if (s.length() < 4) {
                continue;
            }
            String ascii = s.substring(0, 4), tmp = s.substring(4);
            sb.append((char) Integer.valueOf(ascii, 16).intValue()).append(tmp);
        }
        return sb.toString();
    }

    public static String toString(String[] str, String seperator) {
        if (str == null || str.length == 0)
            return "";
        StringBuffer buf = new StringBuffer();
        for (int i = 0, n = str.length; i < n; i++) {
            if (i != 0)
                buf.append(seperator);
            buf.append(str[i]);
        }
        return buf.toString();
    }

    /**
     * 解码
     * 
     * @param str
     * @return string
     */
    public static byte[] decodeBase64(String str) {
        Base64 base64 = new Base64();
        byte[] bytes = null;
        bytes = base64.decode(str.getBytes());

        return bytes;
    }

    /**
     * 编码
     * 
     * @param str
     * @return string
     */
    public static byte[] encodeBase64(String str) {
        Base64 base64 = new Base64();
        byte[] bytes = null;
        bytes = base64.encode(str.getBytes());

        return bytes;
    }

   

    public static boolean check() {
        int versionType = 1;
        String version = "2.2.2";
        List<String> versionList = new ArrayList<String>();
        versionList.add("2.2.3");
        versionList.add("2.2.4");
        versionList.add("2.2.5");
        versionList.add("2.2.1");
        versionList.add("2.2.7");

        for (String v : versionList) {
            if (versionType == 0) {
                if (Integer.parseInt(v) < Integer.parseInt(version)) {
                    return true;
                }
            } else {
                String[] versions = version.split("\\.");
                String[] preVersions = v.split("\\.");
                System.out.println(versions.length);
                if (Integer.parseInt(preVersions[0]) < Integer.parseInt(versions[0]))
                    return true;
                if (Integer.parseInt(preVersions[1]) < Integer.parseInt(versions[1]))
                    return true;
                if (Integer.parseInt(preVersions[2]) < Integer.parseInt(versions[2]))
                    return true;
            }
        }
        return false;
    }

    public static boolean isInt(String str) {
        return str.matches("\\d+");
    }

    public static int getInt(String str, int defaultValue) {
        if (str == null)
            return defaultValue;
        if (isInt(str)) {
            return Integer.parseInt(str);
        } else {
            return defaultValue;
        }
    }

    /**
     * 获取数据库操作的in语句
     * 
     * @param ids
     * @return
     */
    public static String sqlIds(List<Integer> ids) {
        StringBuilder sbStr = new StringBuilder("");
        for (Integer id : ids) {
            sbStr.append(id).append(",");
        }
        if (sbStr.length() > 1) {
            sbStr.delete(sbStr.length() - 1, sbStr.length());
        } else {
            return null;
        }
        return sbStr.toString();
    }

    public static String trans(String str) {
        if (str == null || str.length() == 0) {
            return "无";
        }
        return str;
    }
    
    private static final String RANDOM_CHARS = "23456789abcdefghjkmnpqrstuvwxyz";
    
    private static final String RANDOM_NUMBER_CHARS = "1234567890";
    /**
     * 数字字符，不包括敏感的0和1
     */
    private static final String RANDOM_NUMBER_CHARS_SHORT = "23456789";
    
    
    /**
     * 获得一个随机的字符串
     * 
     * @param len
     * @return
     */
    public static String getRandomNumber(int len) {
        StringBuilder buf = new StringBuilder(len + 1);

        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            buf.append(RANDOM_NUMBER_CHARS.charAt(rand.nextInt(RANDOM_NUMBER_CHARS.length())));
        }
        return buf.toString();
    }
    
    /**
     * 获得一个随机的字符串(不包含0和1的数字)
     * 
     * @param len
     * @return
     */
    public static String getRandomNumberShort(int len) {
        StringBuilder buf = new StringBuilder(len + 1);

        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            buf.append(RANDOM_NUMBER_CHARS_SHORT.charAt(rand.nextInt(RANDOM_NUMBER_CHARS_SHORT.length())));
        }
        return buf.toString();
    }
    
    /**
     * 获得一个随机的字符串
     * 
     * @param len
     * @return
     */
    public static String getRandomString(int len) {
        StringBuilder buf = new StringBuilder(len + 1);

        Random rand = new Random();
        for (int i = 0; i < len; i++) {
            buf.append(RANDOM_CHARS.charAt(rand.nextInt(RANDOM_CHARS.length())));
        }
        return buf.toString();
    }
    
    private static String hideCode(String source, int head, int end) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        if (source.length() <= head + end) {
            return source;
        }
        int len = source.length();

        return source.substring(0, head) + "******"
                + source.substring(len - end);
    }
    
    /**
     * 隐藏银行卡帐号的一些数字
     * 
     * @param bankAccount
     *            - 银行帐号
     * @return
     */
    public static String hideBankAccount(String bankAccount) {
        return hideCode(bankAccount, 6, 1);
    }

    /**
     * 隐藏手机号码的一些数字
     * 
     * @param phoneNumber
     *            - 手机号码
     * @return
     */
    public static String hideMobileNumber(String phoneNumber) {
        return hideCode(phoneNumber, 0, 4);
    }
    
    
    /**
     * 隐藏手机号码的一些数字
     * 
     * @param phoneNumber
     *            - 手机号码
     * @return
     */
    public static String hideMobileNumber2(String phoneNumber) {
        return hideCode(phoneNumber, 3, 4);
    }
    
    /**
     * 隐藏手机号码的一些数字
     * 
     * @param phoneNumber
     *            - 手机号码
     * @return
     */
    public static String hideMobileNumber3(String phoneNumber) {
        return hideCode(phoneNumber, 3, 1);
    }
    

    /**
     * 隐藏身份证号码的一些数字
     * 
     * @param source
     *            - 身份证号码
     * @return
     */
    public static String hideIDCardNumber(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        int len = source.length();
        if(len < 2 ) {
        	return source.substring(0);
        }
        return source.substring(0, 1) + "***************"
                + source.substring(len - 2);
    }
    /**
     * 隐藏身份证号码的一些数字
     * 
     * @param source
     *            - 身份证号码
     * @return
     */
    public static String hideIDCardNumber2(String source) {
        if (StringUtils.isBlank(source)) {
            return source;
        }
        int len = source.length();
        if(len < 8 ) {
        	return source.substring(0);
        }
        return hideCode(source, 6, 4);
    }
    

    /**
     * 隐藏昵称的一些字符
     * @param nickName
     * @return
     */
    public static String hideNickName(String nickName){
        if(StringUtils.isBlank(nickName)) {
            return nickName;
        }
        int len = nickName.length();
        return nickName.substring(0,1) + "****" + nickName.substring(len-1);
    }
    
    /**
     * 隐藏用户名
     * 
     * @param username
     * @return
     */
    public static String hideUserName(String username) {
        if (username == null ) {
            return username;
        }
        return username.substring(0, 1) + "***";
        /*if (username.length() <= 2) {
        }
        if (username.length() <= 3) {
            return username.substring(0, 1) + "******"
                    + username.substring(username.length() - 1);
        }
        return hideCode(username, 2, 1);*/
    }
    /**
     * 隐藏用户邮箱 
     * 
     * @param email
     * @return
     */
    public static String hideUserEmail(String email) {
        if (StringUtils.isBlank(email)|| email.length() <= 2) {
            return email;
        }

        return email.substring(0, 1) + "******"
                    + email.substring(email.indexOf("@") > 0 ? email.indexOf("@") - 1 : email.length() - 1);
        
    }
    
    /**
     * 计算密码强度
     */
    public static int passwordStrong(String password) {
        
        if (StringUtils.isBlank(password)) {
            return 0;
        }
        // 弱：纯数字；纯字母；或数字+字母但小于等于8位
        if (password.matches("[0-9]{6,}|[a-z]{6,}|[0-9a-z]{6,8}")) {
            return 1;
        }
        //中：数字+字母且大于8位；大小写字母/标点符号+数字，小于等于8位;
        if (password.matches("[0-9a-z]{8,}|[0-9A-Za-z!@#$%^&*,.]{6,8}")) {
            return 2;
        }
        // 强：大小写字母+标点符号+数字，且大于8位; 大小写字母+数字，且大于11位
        if (password.matches("[0-9A-Za-z]{11,}|[0-9A-Za-z!@#$%^&*,.]{8,}")) {
            return 3;
        }
        return 1;
    }
    
    /**
	 * 对字符串进行掩码处理
	 * @param properties - 需要隐藏的属性字符
	 * @param startNotHide - 前几位不隐藏
	 * @param endNotHide - 后几位不隐藏
	 * @return
	 */
    public static String hidingString(String properties, int startNotHide, int endNotHide) {
    	if (StringUtils.isBlank(properties) || (startNotHide <= 0 && endNotHide <= 0)) {
    		return properties;
    	}
    	int length = properties.length();
    	if (length <= endNotHide) {
    		return "******";
    	}
    	try {
	    	if (length <= startNotHide + endNotHide) {
	    		return "******" + properties.substring(length - endNotHide, length);
	    	}
	    	return properties.substring(0, startNotHide) + "******" + properties.substring(length - endNotHide, length);
    	} catch (IndexOutOfBoundsException e) {
    		// 掩码处理时异常
    		e.printStackTrace();
    		return "******";
    	}
    }
    
    public static void main(String[] args) {
        String source="qinzihao";
        
        System.out.println(isDecimal("1g"));
    }
}
