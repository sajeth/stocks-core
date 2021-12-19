package com.saji.stocks.core.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
//@ComponentScan({"com.saji.stocks.core.services"})
@EnableJpaRepositories(basePackages = {"com.saji.stocks.core.repository.transactions", "com.saji.stocks.core.repository.configurations", "com.saji.stocks.core.repository.data", "com.saji.stocks.core.repository.kite", "com.saji.stocks.core.repository"})
@PropertySource("classpath:database.properties")
public class CoreConfig {

    @Autowired
    private Environment env;

    public Properties hibernateProperties() {
        final Properties properties = new Properties();
        // properties.put("hibernate.hbm2ddl.auto",
        // env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
        // properties.put("hibernate.ejb.naming_strategy",env.getProperty("spring.jpa.hibernate.naming-strategy"));
        properties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
        // properties.setProperty("hibernate.connection.isolation",
        // String.valueOf(Connection.TRANSACTION_READ_COMMITTED));
        return properties;
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig dataSourceConfig = new HikariConfig();
        dataSourceConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSourceConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
        dataSourceConfig.setUsername(env.getProperty("spring.datasource.username"));
        dataSourceConfig.setPassword(env.getProperty("spring.datasource.password"));
        dataSourceConfig
                .setMaximumPoolSize(Integer.parseInt(env.getProperty("spring.datasource.hikari.maximum-pool-size")));
        dataSourceConfig.setMinimumIdle(Integer.parseInt(env.getProperty("spring.datasource.hikari.minimum-idle")));
        dataSourceConfig.setIdleTimeout(Long.parseLong(env.getProperty("spring.datasource.hikari.idle-timeout")));
        dataSourceConfig.setMaxLifetime(Long.parseLong(env.getProperty("spring.datasource.hikari.max-lifetime")));
        dataSourceConfig.setAutoCommit(true);

        return new HikariDataSource(dataSourceConfig);
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(Boolean.getBoolean(env.getProperty("spring.jpa.show-sql")));
        vendorAdapter.setDatabasePlatform(env.getProperty("spring.jpa.properties.hibernate.dialect"));
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan(env.getProperty("spring.jpa.packages"));
        factory.setDataSource(dataSource());
        factory.setJpaProperties(hibernateProperties());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setDataSource(dataSource());
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return txManager;
    }
}
