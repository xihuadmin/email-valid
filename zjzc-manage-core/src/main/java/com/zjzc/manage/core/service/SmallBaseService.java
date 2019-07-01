package com.zjzc.manage.core.service;

import com.zjzc.manage.utils.common.JsonEntity;

/**
 * @author JLICC
 * @Package com.zjzc.manage.core.service
 * @Description:
 * @date 2019/06/04 11:31:54
 */
public interface SmallBaseService {

    JsonEntity initUpdateBatch();

    JsonEntity initUpdateBatchByJfu();

    JsonEntity checkEmailByParty();
}
