package com.zjzc.manage.core.model.po;

import com.zjzc.manage.utils.common.enums.DownloadType;
import com.zjzc.manage.utils.common.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TblZcsysDownloadPo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String downloadPath;
    private String savePath;
    private LocalDateTime downloadTime;
    private LocalDateTime createTime;
    private String fileSize;
    private String code;
    private String status;
    private String version;
    private String arbtrCode;
    private String type;
    private String filename;

    public String getDownloadTypeDesc(){
        return DownloadType.getReportDesc(this.status).getDesc();
    }

    public TblZcsysDownloadPo(String downloadPath, String savePath, String code, String version,String arbtrCode,String type,String filename) {
        this.downloadPath = downloadPath;
        this.savePath = savePath;
        this.arbtrCode = arbtrCode;
        this.code = code;
        this.version = version;
        this.type = type;
        this.filename = filename;
    }
}
