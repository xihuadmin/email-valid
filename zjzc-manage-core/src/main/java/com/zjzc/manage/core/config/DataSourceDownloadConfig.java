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
@MapperScan(basePackages = "com.zjzc.manage.core.mapper.download", sqlSessionTemplateRef  = "downloadSqlSessionTemplate")
public class DataSourceDownloadConfig {

    @Bean(name = "downloadDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.download")
    @Primary
    public DataSource downloadDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "downloadSqlSessionFactory")
    @Primary
    public SqlSessionFactory downloadSqlSessionFactory(@Qualifier("downloadDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/download/*.xml"));
        bean.setTypeAliasesPackage("com.zjzc.manage.core.model");
        return bean.getObject();
    }

    @Bean(name = "downloadTransactionManager")
    @Primary
    public DataSourceTransactionManager downloadTransactionManager(@Qualifier("downloadDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "downloadSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate downloadSqlSessionTemplate(@Qualifier("downloadSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}