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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
@MapperScan(basePackages = "com.zjzc.manage.core.mapper.proddata", sqlSessionTemplateRef  = "proddataSqlSessionTemplate")
public class DataSourceProddataConfig {

    @Bean(name = "proddataDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.proddata")
    public DataSource proddataDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "proddataSqlSessionFactory")
    public SqlSessionFactory proddataSqlSessionFactory(@Qualifier("proddataDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapping/proddata/*.xml"));
        bean.setTypeAliasesPackage("com.zjzc.manage.core.model");
        return bean.getObject();
    }

    @Bean(name = "proddataTransactionManager")
    public DataSourceTransactionManager proddataTransactionManager(@Qualifier("proddataDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "proddataSqlSessionTemplate")
    public SqlSessionTemplate proddataSqlSessionTemplate(@Qualifier("proddataSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}