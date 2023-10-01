package com.orbitalyards.landwar.jpa;

//@Configuration
//@EnableJpaRepositories(basePackages = "com.orbitalyards.landwar.jpa.dao")
//@EnableTransactionManagement
public class PersistenceJPAConfig {
//
//	@Autowired
//	private Environment env;
//	
//	/**
//	 * TODO: debug option only! eventually a standalone database will exist.
//	 * 
//	 * @return {@linkplain DataSource}
//	 */
//	@Bean
//	public DataSource dataSource() {
//		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUrl("jdbc:h2:mem:testdb");
//		
//		dataSource.setUsername("sa");
//		dataSource.setPassword("sa");
//		return dataSource;
//	}
//
//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//		em.setDataSource(dataSource());
//		em.setPackagesToScan("com.orbitalyards.landwar.jpa");
//
//		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		em.setJpaVendorAdapter(vendorAdapter);
//		em.setJpaProperties(additionalProperties());
//
//		return em;
//	}
//	@Bean
//	public PlatformTransactionManager transactionManager() {
//	    JpaTransactionManager transactionManager = new JpaTransactionManager();
//	    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//
//	    return transactionManager;
//	}
//
//	@Bean
//	public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
//	    return new PersistenceExceptionTranslationPostProcessor();
//	}
//
//	private Properties additionalProperties() {
//	    Properties properties = new Properties();
////	    properties.setProperty("hibernate.dialect", "org.hibernate.community.dialect.SQLiteDialect");
//	    properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//	    properties.setProperty("spring.session.jdbc.initialize-schema", "always");
//	    properties.setProperty("spring.jpa.generate-ddl", "true");
//	    properties.setProperty("spring.jpa.hibernate.ddl-auto", "update");
//	    properties.setProperty("hibernate.ddl-auto", "update");
//	    return properties;
//	}
}