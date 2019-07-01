package com.zjzc.manage.core.dao;

import com.zjzc.manage.core.model.CaseBatchInfo;
import com.zjzc.manage.core.model.CaseParty;

import java.util.List;

/**
 * @author JLICC
 * @Package com.zjzc.manage.core.dao
 * @Description:
 * @date 2019/06/04 11:33:32
 */
public interface SmallBaseDao {
    List<CaseBatchInfo> selectCaseIdBybatch();

    void updateCaseBatchByAmount(CaseBatchInfo caseBatchInfo);

    List<CaseBatchInfo> selectCaseIdByJfuBatch();

    List<CaseParty> selectCasePartyListByNotCheckTop1000();

    void insertCaseEmailVaild(CaseParty caseParty);

    Integer selectCaseVaildByEmail(CaseParty caseParty);

    void updateCaseEmailVaild(CaseParty caseParty);
}
