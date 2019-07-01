package com.zjzc.manage.core.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@MapperScan(sqlSessionTemplateRef="engineSqlSessionTemplate",basePackages={"com.zjzc.manage.core.mapper.zjzcsys"})
public class EgineMapperScannerConfigurer {

}
