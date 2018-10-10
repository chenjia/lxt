package com.lxt.ms.manage.config;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.pagehelper.PageInterceptor;

@Configuration
@EnableTransactionManagement
public class DataSourceConfig {

	private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Autowired
	private Environment env;

	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
		fb.setDataSource(dataSource);
		fb.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
		fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
		return fb.getObject();
	}
}