package com.want.demospringboot.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.want.demospringboot.mapper.CheckInMapper"}, sqlSessionFactoryRef = "checkInSqlSessionTemplate")
public class CheckInDataSourceConfig {
	
	@Bean(name = "checkInDataSource")
	@ConfigurationProperties("spring.datasource.check-in")
	public DataSource checkInDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "checkInTransactionManager")
	public DataSourceTransactionManager checkInTransactionManager() {
		return new DataSourceTransactionManager(checkInDataSource());		
	}
	
	@Bean(name = "checkInSqlSessionFactory")
	public SqlSessionFactory checkInSqlSessionFactory(@Qualifier("checkInDataSource") DataSource checkInDataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(checkInDataSource);
		return sqlSessionFactoryBean.getObject();
	}
	
    @Bean(name = "checkInSqlSessionTemplate")
    public SqlSessionTemplate checkInSqlSessionTemplate(@Qualifier("checkInSqlSessionFactory") SqlSessionFactory checkInSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(checkInSqlSessionFactory);
    }
	
}
