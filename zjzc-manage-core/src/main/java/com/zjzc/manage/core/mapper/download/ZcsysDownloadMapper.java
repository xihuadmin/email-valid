package com.zjzc.manage.core.mapper.download;

import com.zjzc.manage.core.model.po.TblZcsysDownloadPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by lijun on 2017/12/27 17:19.
 */
@Mapper
public interface ZcsysDownloadMapper {
    void insertDownloadByList(@Param("list")List<TblZcsysDownloadPo> list);

    List<TblZcsysDownloadPo> findDownloadByList(Map<String,Object> map);

    void updateDownloadById(TblZcsysDownloadPo po);

    int insertDownloadIsDel(TblZcsysDownloadPo po);

    void delDownloadById(TblZcsysDownloadPo po);

    Integer findTotalDownloadOk(@Param("version") String version);
}
