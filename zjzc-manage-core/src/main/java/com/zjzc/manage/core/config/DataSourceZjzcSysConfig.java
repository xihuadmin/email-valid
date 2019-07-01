package com.zjzc.manage.core.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.zjzc.manage.core.mapper.zjzcsys", sqlSessionTemplateRef  = "zjzcsysSqlSessionTemplate")
public class DataSourceZjzcSysConfig {

    @Bean(name = "zjzcsysDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.zjzcsys")
    public DataSource zjzcsysDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "zjzcsysSqlSessionFactory")
    public SqlSessionFactory zjzcsysSqlSessionFactory(@Qualifier("zjzcsysDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/zjzcsys/*.xml"));
        bean.setTypeAliasesPackage("com.zjzc.manage.core.model");
        return bean.getObject();
    }

    @Bean(name = "zjzcsysTransactionManager")
    public DataSourceTransactionManager zjzcsysTransactionManager(@Qualifier("zjzcsysDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "zjzcsysSqlSessionTemplate")
    public SqlSessionTemplate zjzcsysSqlSessionTemplate(@Qualifier("zjzcsysSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}