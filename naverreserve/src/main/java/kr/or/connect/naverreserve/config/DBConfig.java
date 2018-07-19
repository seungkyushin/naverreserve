package kr.or.connect.naverreserve.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement

public class DBConfig implements TransactionManagementConfigurer{

	private String driverClassName = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/culture_db?useUnicode=true&characterEncoding=utf8";
	private String username = "root";
	private String password = "dkagh1234.";
	
	@Bean
	public DataSource jdbcConnection() {
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		
		return ds;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}
	
	@Bean
	public PlatformTransactionManager transactionManger() {
		System.out.println("DBConfig : transactionManger");
		return new DataSourceTransactionManager(jdbcConnection());
	}

}
