package com.ksh.config.database;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(value="com.ksh.mapper.work", sqlSessionFactoryRef="workSqlSessionFactory")
public class WorkDatabaseConfig {
	@Bean(name="workDataSource")
	@Primary // 동일한 prefix의 datasource를 사용하기 위해서 Primary 어노테이션을 지정, 기본값을 정한다. 
	@ConfigurationProperties(prefix="spring.work.datasource")
	public DataSource workDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="workSqlSessionFactory")
	@Primary
	public SqlSessionFactory workSqlSessionFactory(@Qualifier("workDataSource") DataSource workDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(workDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/work/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="workSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate workSqlSessionTemplate(@Qualifier("workSqlSessionFactory") SqlSessionFactory workSqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(workSqlSessionFactory);
	}
	
}
