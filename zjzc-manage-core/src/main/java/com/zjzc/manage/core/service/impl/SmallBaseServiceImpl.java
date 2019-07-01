package com.zjzc.manage.core.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zjzc.manage.core.dao.SmallBaseDao;
import com.zjzc.manage.core.model.CaseBatchInfo;
import com.zjzc.manage.core.model.CaseParty;
import com.zjzc.manage.core.model.FinanceSupplement;
import com.zjzc.manage.core.service.SmallBaseService;
import com.zjzc.manage.utils.common.JsonEntity;
import com.zjzc.manage.utils.emailUtils.CheckEmailAddress;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JLICC
 * @Package com.zjzc.manage.core.service.impl
 * @Description:
 * @date 2019/06/04 11:32:07
 */
@Service("smallBaseService")
public class SmallBaseServiceImpl implements SmallBaseService {

    @Autowired
    private SmallBaseDao smallBaseDao;

    @Override
    public JsonEntity initUpdateBatch() {

        JsonEntity jsonEntity = new JsonEntity();
        List<CaseBatchInfo> caseBatchInfoList = smallBaseDao.selectCaseIdBybatch();

        if(CollectionUtils.isNotEmpty(caseBatchInfoList)){
            for(CaseBatchInfo info: caseBatchInfoList){
                FinanceSupplement financeSupplement = JSONObject.parseObject(info.getMsg(),FinanceSupplement.class);
                info.setPrice(zjacJs(financeSupplement.getBorrowAmount()));
                smallBaseDao.updateCaseBatchByAmount(info);
            }
        }

        return jsonEntity;

    }

    @Override
    public JsonEntity initUpdateBatchByJfu() {
        JsonEntity jsonEntity = new JsonEntity();
        List<CaseBatchInfo> caseBatchInfoList = smallBaseDao.selectCaseIdByJfuBatch();
        return null;
    }

    public JsonEntity checkEmailByParty(){

        List<CaseParty> casePartyList = smallBaseDao.selectCasePartyListByNotCheckTop1000();
        for(CaseParty caseParty : casePartyList){
            if(StringUtils.isNotEmpty(caseParty.getEmail())){
                caseParty.setState(CheckEmailAddress.valid(caseParty.getEmail()) ? 1 : 0);
            }
            if(smallBaseDao.selectCaseVaildByEmail(caseParty) == 0){
                smallBaseDao.insertCaseEmailVaild(caseParty);
            }else {
                smallBaseDao.updateCaseEmailVaild(caseParty);
            }
        }

        return new JsonEntity("success","200");
    }











    //zjac官网计算规则
    public static BigDecimal zjacJs(BigDecimal amount) {
        double e = amount.doubleValue();
        double s = 0;
        double c = 0;
        double sc = 0;
        if (e <= 1000) {
            sc = 100;
        }
        //ri : 案件处理费收费比例 r2：
        if (1001 < e && e <= 50000) {
            s = js(e, 0.045, 55) * 1;
        }
        if (50000 < e && e <= 100000) {
            s = js(e, 0.035, 555) * 1;
        }
        if (100000 < e && e <= 200000) {
            s = js(e, 0.025, 1555) * 1;
        }
        if (200000 < e && e <= 500000) {
            s = js(e, 0.015, 3555) * 1;
        }
        if (500000 < e && e <= 1000000) {
            s = js(e, 0.008, 7055) * 1;
        }
        if (1000000 < e) {
            s = js(e, 0.004, 11055) * 1;
        }
        c = js(s, 0.55, 0) * 1;
        sc = s + c + sc;
        System.out.println("案件受理费：" + s);
        System.out.println("案件处理费：" + c);
        System.out.println("总计：" + sc);
        return new BigDecimal(sc).setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    //案件受理费计算 ： e:争议金额，r1:受理费取费比例，r2:服务费
    public static double js(double e, double r1, double r2) {
        return e * r1 + r2;
    }
}
