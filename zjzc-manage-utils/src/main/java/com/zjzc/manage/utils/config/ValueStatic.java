package com.zjzc.manage.utils.config;

import com.zjzc.manage.utils.SpringContextUtil;

import java.util.List;

public class ValueStatic {

    private static SftpPropertiesVal sftpPropertiesVal;

    public static int getSftpPort() {
        return getSftpPropertiesVal().getSftpPort();
    }

    public static String getSftpUserName() {
        return getSftpPropertiesVal().getSftpUserName();
    }

    public static String getSftpPassWord() {
        return getSftpPropertiesVal().getSftpPassWord();
    }

    public static String getSftpFilePath() {
        return getSftpPropertiesVal().getSftpFilePath();
    }

    public static String getSftpZipPath() {
        return getSftpPropertiesVal().getSftpZipPath();
    }

    public static String getSaveFilePath() {
        return getSftpPropertiesVal().getSaveFilePath();
    }

    public static String getSaveZipPath() {
        return getSftpPropertiesVal().getSaveZipPath();
    }

    public static String getSftpIp(){
        return getSftpPropertiesVal().getSftpIp();
    }

    public static String getFilePath(){
        return "";
    }

    public static List<String> getAlarmNoticeMobiles(){
        return getSftpPropertiesVal().getAlarmNoticeMobiles();
    }

    public static String getAlarmNoticeUrl(){
        return getSftpPropertiesVal().getAlarmNoticeUrl();
    }

    public static boolean getDingAlarmSwitch(){
        return getSftpPropertiesVal().isDingAlarmSwitch();
    }

    public static SftpPropertiesVal getSftpPropertiesVal(){
        if(sftpPropertiesVal == null){
            sftpPropertiesVal = (SftpPropertiesVal) SpringContextUtil.getBean("sftpPropertiesVal");
        }
        return sftpPropertiesVal;
    }
}
