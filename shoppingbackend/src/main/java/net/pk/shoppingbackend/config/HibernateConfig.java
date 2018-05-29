package net.pk.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration // Annotating a class with the @Configuration indicates that the
				// class can be used by the Spring IoC container as a source of
				// bean definitions.
@ComponentScan(basePackages = {
		"net.pk.shoppingbackend.dto" }) /*
										 * The @ComponentScan annotation is used
										 * with the @Configuration annotation to
										 * tell Spring the packages to scan for
										 * annotated components. @ComponentScan
										 * also used to specify base packages
										 * and base package classes using
										 * thebasePackageClasses or basePackages
										 * attributes of @ComponentScan.
										 */
@EnableTransactionManagement
public class HibernateConfig {

	// change the below based on the dbms we choose
	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/onlineshopping";
	private final static String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQLDialect";
	private final static String DATABASE_USERNAME = "root";
	private final static String DATABASE_PASSWORD = "root";

	// dataSource bean will be available
	@Bean("dataSource")
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		// providing the database connection information
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);

		return dataSource;

	}

	// sessionFactory bean will be available

	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("net.pk.shoppingbackend.dto");
		return builder.buildSessionFactory();
	}

	// All the hibernate properties will be returned in this method
	private Properties getHibernateProperties() {
		// TODO Auto-generated method stub

		Properties properties = new Properties();

		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	// transaction manager bean
	@Bean
	public HibernateTransactionManager getTrasactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
