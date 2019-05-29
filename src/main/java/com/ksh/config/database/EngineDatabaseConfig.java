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

import com.ksh.mapper.engine.SigPSRuleMapper;

/**
 * MapperScan은 스프링버전 3.1이상에서 지원
 * 하위 버전에서는 mybatis:scan 또는 context:component-scan등 XML설정을 활용해야 한다.
 * 
 * sqlSessionFactoryBean과 sqlSessionTemplate는 마이바티스 스프링 연동모듈 1.0.2 이상에서만 사용 가능
 * */

@Configuration
@MapperScan(value="com.ksh.mapper.engine", sqlSessionFactoryRef="engineSqlSessionFactory")
public class EngineDatabaseConfig {
	@Bean(name="engineDataSource")
	@ConfigurationProperties(prefix="spring.engine.datasource")
	public DataSource engineDataSource(){
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="engineSqlSessionFactory")
	public SqlSessionFactory engineSqlSessionFactory(@Qualifier("engineDataSource") DataSource engineDataSource, ApplicationContext applicationContext) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(engineDataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/engine/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean(name="engineSqlSessionTemplate")
	public SqlSessionTemplate engineSqlSessionTemplate(@Qualifier("engineSqlSessionFactory") SqlSessionFactory engineSqlSessionFactory) throws Exception{
		return new SqlSessionTemplate(engineSqlSessionFactory);
	}
}
