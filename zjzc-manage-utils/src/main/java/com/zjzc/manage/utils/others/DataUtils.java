package com.zjzc.manage.utils.others;

import com.zjzc.manage.utils.config.ValueStatic;
import com.zjzc.manage.utils.dingTalk.TextMessage;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by lijun on 2017/9/7 22:16.
 */
@Slf4j
public class DataUtils {

    /**
     *
     * @param one 平台金额
     * @param two 富友金额
     * @return
     */
    public static boolean isEqueal(String one,String two){
        try {
            if(StringUtil.IsEmpty(one) && StringUtil.IsEmpty(two)){
                return false;
            }else {
                BigDecimal bone = new BigDecimal(one).multiply(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_DOWN);
                BigDecimal btwo = new BigDecimal(two).multiply(new BigDecimal( 10)).setScale(2, BigDecimal.ROUND_DOWN);
                return bone.equals(btwo);
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 格式化文件名称
     * @param str1
     * @return
     */
    public static String toFileNum(int str1){
        DecimalFormat df=new DecimalFormat("0000");
        String str2=df.format(str1);
        return str2;
    }

    /**
     * 格式化文件名称
     * @param str1
     * @return
     */
    public static String toFileNumTwo(int str1){
        DecimalFormat df=new DecimalFormat("00");
        String str2=df.format(str1);
        return str2;
    }
    /**
     * 添加预警通知
     * @param content 消息体
     * @param flag 是否@全部
     * @return 返回结果
     * 钉钉频率限制在一分钟20条，怎么控制呢
     */
    public static String addDingTalk(String content,boolean flag) {
        if(!ValueStatic.getDingAlarmSwitch()){
            log.info("【钉钉预警】状态为关闭");
            return "";
        }
        try {
            String json = HttpUtil.postJson(ValueStatic.getAlarmNoticeUrl(),
                    new TextMessage(content,ValueStatic.getAlarmNoticeMobiles(),flag).toJsonString());
            log.info(json);
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
