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
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.want.demospringboot.mapper.AccountMapper"}, sqlSessionFactoryRef = "accountSqlSessionTemplate")
public class AccountDataSourceConfig {
	
	@Bean(name = "accountDataSource")
	@Primary
	@ConfigurationProperties("spring.datasource.account")
	public DataSource accountDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "accountTransactionManager")
	@Primary
	public DataSourceTransactionManager accountTransactionManager() {
		return new DataSourceTransactionManager(accountDataSource());		
	}
	
	@Bean(name = "accountSqlSessionFactory")
	@Primary
	public SqlSessionFactory accountSqlSessionFactory(@Qualifier("accountDataSource") DataSource accountDataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(accountDataSource);
		return sqlSessionFactoryBean.getObject();
	}
	
    @Bean(name = "accountSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate accountSqlSessionTemplate(@Qualifier("accountSqlSessionFactory") SqlSessionFactory accountSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(accountSqlSessionFactory);
    }
    
    //---------------------------------- for JPA Begin ----------------------------------
    @Bean(name = "entityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(){
    	LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    	bean.setDataSource(accountDataSource());
    	bean.setPackagesToScan("com.want.demospringboot.dto");
    	bean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    	return bean;
    }
    
    @Bean(name = "transactionManager")
    @Primary
    public PlatformTransactionManager transactionManager(){
    	JpaTransactionManager manager = new JpaTransactionManager();
    	manager.setEntityManagerFactory(entityManagerFactoryBean().getObject());
    	return manager;
    }
    
    //----------------------------------- for JPA end -----------------------------------
	
}
