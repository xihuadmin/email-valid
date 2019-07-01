package com.zjzc.manage.api.batch;

import com.zjzc.manage.core.job.ScheduledJob;
import com.zjzc.manage.core.service.SmallBaseService;
import com.zjzc.manage.utils.common.JsonEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@ScheduledJob(orderNum = 2,name = "验证邮箱地址", cron = "0 0/20 * * * ?", method = "execute",autoStartup = false)
public class CheckEmailSchedule {

    @Autowired
    SmallBaseService smallBaseService;

    public void execute() {
        log.info("【验证邮箱地址任务开始............................】");
        try {
            JsonEntity entity = this.smallBaseService.checkEmailByParty();
            log.info("【验证邮箱地址】{}",entity.getMsg());
        } catch (Exception e) {
            log.error("【验证邮箱地址】异常{}",e);
        }
        log.info("【验证邮箱地址任务结束............................】");
    }
}
