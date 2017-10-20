package com.atto.server.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class DataSourceConfig extends WebMvcConfigurerAdapter {
	
	public SqlSessionFactory sdqlSessionFactory;
	

}
