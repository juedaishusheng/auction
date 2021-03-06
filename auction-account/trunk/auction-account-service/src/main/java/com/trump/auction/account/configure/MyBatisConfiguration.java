package com.trump.auction.account.configure;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@MapperScan("com.trump.auction.account.dao")
public class MyBatisConfiguration implements TransactionManagementConfigurer {

	private ApplicationContext applicationContext;

	MyBatisConfiguration(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	private DataSource getDataSource() {
		return applicationContext.getBean("dataSource", DataSource.class);
	}

	@Value("${mybatis.mapperLocations}")
	private String mapperLocations;

	@Value("${mybatis.typeAliasesPackage}")
	private String typeAliasesPackage;

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(getDataSource());
		bean.setTypeAliasesPackage(typeAliasesPackage);

		Properties props = new Properties();
		props.setProperty("reasonable", "true");
		props.setProperty("supportMethodsArguments", "true");
		props.setProperty("returnPageInfo", "check");
		props.setProperty("params", "count=countSql");

		PageHelper pageHelper = new PageHelper();
		pageHelper.setProperties(props);
		bean.setPlugins(new Interceptor[]{pageHelper});
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			bean.setMapperLocations(resolver.getResources(mapperLocations));
			bean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);//下划线-->驼峰
			return bean.getObject();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}

}
