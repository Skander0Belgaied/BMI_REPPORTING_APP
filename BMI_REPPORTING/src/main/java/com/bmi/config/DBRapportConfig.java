package com.bmi.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(
		basePackages = "com.bmi.rapport.repository",
        entityManagerFactoryRef = "rapportEntityManagerFactory",
        transactionManagerRef = "rapportTransactionManager"
)
public class DBRapportConfig {
	@Autowired
	private Environment env;
	
	@Bean
    @ConfigurationProperties(prefix="datasource.rapport")
    public DataSourceProperties rapportDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Bean
    public DataSource rapportDataSource() {
        DataSourceProperties primaryDataSourceProperties = rapportDataSourceProperties();
		return DataSourceBuilder.create()
        			.driverClassName(primaryDataSourceProperties.getDriverClassName())
        			.url(primaryDataSourceProperties.getUrl())
        			.username(primaryDataSourceProperties.getUsername())
        			.password(primaryDataSourceProperties.getPassword())
        			.build();
    }
    @Bean
    public PlatformTransactionManager rapportTransactionManager()
    {
        EntityManagerFactory factory = rapportEntityManagerFactory().getObject();
        return new JpaTransactionManager(factory);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean rapportEntityManagerFactory()
    {
    	LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(rapportDataSource());
        factory.setPackagesToScan(new String[]{"com.bmi.rapport.entity"});
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
        jpaProperties.put("hibernate.show-sql", env.getProperty("spring.jpa.show-sql"));
        factory.setJpaProperties(jpaProperties);
        
        return factory;
        
    }
    @Bean
  	public DataSourceInitializer rapportDataSourceInitializer() 
  	{
  		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
  		dataSourceInitializer.setDataSource(rapportDataSource());
  		ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
  	//	databasePopulator.addScript(new ClassPathResource("rapport-data.sql"));
  		dataSourceInitializer.setDatabasePopulator(databasePopulator);
  		dataSourceInitializer.setEnabled(env.getProperty("datasource.rapport.initialize", Boolean.class, false));
  		return dataSourceInitializer;
  	}
      

}
