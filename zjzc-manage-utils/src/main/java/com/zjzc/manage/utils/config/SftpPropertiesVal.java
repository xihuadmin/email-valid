package com.zjzc.manage.utils.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Component("sftpPropertiesVal")
public class SftpPropertiesVal {

    /**报警通知手机号码组*/
    @Value("${sys.alarmNoticeMobiles}")
    private List<String> alarmNoticeMobiles;
    /**报警通知接口url*/
    @Value("${sys.alarmNoticeUrl}")
    private String alarmNoticeUrl;
    /**钉钉预警开关*/
    @Value("${sys.dingAlarmSwitch}")
    private boolean dingAlarmSwitch;

    @Value("${download.sftp.ip}")
    private String sftpIp;

    @Value("${download.sftp.port}")
    private int sftpPort;

    @Value("${download.sftp.username}")
    private String sftpUserName;

    @Value("${download.sftp.password}")
    private String sftpPassWord;

    @Value("${download.sftp.file.path}")
    private String sftpFilePath;

    @Value("${download.sftp.zip.path}")
    private String sftpZipPath;

    @Value("${download.save.file.path}")
    private String saveFilePath;

    @Value("${download.save.zip.path}")
    private String saveZipPath;
}
