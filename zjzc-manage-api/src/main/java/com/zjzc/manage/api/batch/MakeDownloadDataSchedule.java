package com.zjzc.manage.api.batch;

import com.zjzc.manage.core.job.ScheduledJob;
import com.zjzc.manage.core.service.SmallBaseService;
import com.zjzc.manage.utils.common.JsonEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@ScheduledJob(orderNum = 1,name = "修改仲裁费任务", cron = "0 10 0 * * ?", method = "execute",autoStartup = false)
public class MakeDownloadDataSchedule {

    @Autowired
    SmallBaseService smallBaseService;

    public void execute() {
        log.info("【修改仲裁费任务开始............................】");
        try {
            JsonEntity entity = this.smallBaseService.initUpdateBatch();
            log.info("【生成下载数据】{}",entity.getMsg());
        } catch (Exception e) {
            log.error("【生成下载数据】异常{}",e);
        }
        log.info("【修改仲裁费任务结束............................】");
    }
}
