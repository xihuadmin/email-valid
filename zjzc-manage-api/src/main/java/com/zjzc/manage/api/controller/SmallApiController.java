package com.zjzc.manage.api.controller;

import com.zjzc.manage.api.model.EmailEntity;
import com.zjzc.manage.utils.common.Result;
import com.zjzc.manage.utils.common.enums.ResponseCode;
import com.zjzc.manage.utils.emailUtils.CheckEmailAddress;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JLICC
 * @Package com.zjzc.manage.api.controller
 * @Description:
 * @date 2019/06/20 18:35:50
 */
@RestController
@Slf4j
@RequestMapping("/small/api/")
public class SmallApiController {


    /**
     * 上传记录页
     *
     * @return
     */
    @GetMapping("/checkEmail")
    public Result<EmailEntity> uploadPage(String email) {
        if(StringUtils.isEmpty(email)){
            log.error("【邮箱验证】邮箱为空:{}",email);
            return new Result(ResponseCode.INVALID,"邮箱为空");
        }
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setInEmail(email);
        emailEntity.setOutEmail(CheckEmailAddress.formatErrorEmail(email));
        if(CheckEmailAddress.valid(emailEntity.getOutEmail())){
            log.info("【邮箱验证】通过:{}",email);
            return new Result(ResponseCode.VALID,emailEntity);
        }else {
            log.info("【邮箱验证】无效:{}",email);
            return new Result(ResponseCode.INVALID,emailEntity);
        }
    }
}
