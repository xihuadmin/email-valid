package com.zjzc.manage.core.mapper.proddata;

import com.zjzc.manage.core.model.po.TblZcsysArbitrationPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ZcsysArbitrationMapper {

    List<TblZcsysArbitrationPo> selectZcsysArbitrationByProd(@Param("version") String version);

}