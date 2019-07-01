package com.zjzc.manage.core.mapper.zjzcsys;

import com.zjzc.manage.core.model.CaseBatchInfo;
import com.zjzc.manage.core.model.CaseParty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ZjzcsysAribMapper {

    List<CaseBatchInfo> selectCaseIdBybatch();

    void updateCaseBatchByAmount(CaseBatchInfo caseBatchInfo);

    List<CaseParty> selectCasePartyListByNotCheckTop1000();

    void insertCaseEmailVaild(CaseParty caseParty);

    Integer selectCaseVaildByEmail(CaseParty caseParty);

    void updateCaseEmailVaild(CaseParty caseParty);
}
