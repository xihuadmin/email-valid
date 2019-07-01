package com.zjzc.manage.core.dao.impl;

import com.zjzc.manage.core.dao.SmallBaseDao;
import com.zjzc.manage.core.mapper.zjzcsys.ZjzcsysAribMapper;
import com.zjzc.manage.core.model.CaseBatchInfo;
import com.zjzc.manage.core.model.CaseParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JLICC
 * @Package com.zjzc.manage.core.dao.impl
 * @Description:
 * @date 2019/06/04 11:33:43
 */
@Service("smallBaseDao")
public class SmallBaseDaoImpl implements SmallBaseDao {

    @Autowired
    private ZjzcsysAribMapper mapper;

    @Override
    public List<CaseBatchInfo> selectCaseIdBybatch() {
        return mapper.selectCaseIdBybatch();
    }

    @Override
    public void updateCaseBatchByAmount(CaseBatchInfo caseBatchInfo) {
        mapper.updateCaseBatchByAmount(caseBatchInfo);
    }

    @Override
    public List<CaseBatchInfo> selectCaseIdByJfuBatch() {
        return null;
    }

    @Override
    public List<CaseParty> selectCasePartyListByNotCheckTop1000() {
        return mapper.selectCasePartyListByNotCheckTop1000();
    }

    @Override
    public void insertCaseEmailVaild(CaseParty caseParty) {
        mapper.insertCaseEmailVaild(caseParty);
    }

    @Override
    public Integer selectCaseVaildByEmail(CaseParty caseParty) {
        return mapper.selectCaseVaildByEmail(caseParty);
    }

    @Override
    public void updateCaseEmailVaild(CaseParty caseParty) {
        mapper.updateCaseEmailVaild(caseParty);
    }
}
