package ru.basanov.notification.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("ru.basanov")
@EnableJpaRepositories("ru.basanov.notification.repository")
@PropertySource("db-conf.properties")
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource(
            @Value("${datasource.driver}") final String dataSourceDriver,
            @Value("${datasource.url}") final String dataSourceUrl,
            @Value("${datasource.user}") final String dataSourceUser,
            @Value("${datasource.password}") final String dataSourcePassword
    ) {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(dataSourceDriver);
        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUser);
        dataSource.setPassword(dataSourcePassword);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            final DataSource dataSource,
            @Value("${hibernate.show_sql}") final boolean showSql,
            @Value("${hibernate.hbm2ddl.auto}") final String tableStrategy,
            @Value("${hibernate.dialect}") final String dialect,
            @Value("${hibernate.max_fetch_depth}") final String fetchDepth,
            @Value("${hibernate.jdbc.fetch_size}") final String fetchSize,
            @Value("${hibernate.jdbc.batch_size}") final String batchSize
    ) {
        final LocalContainerEntityManagerFactoryBean factoryBean;
        factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("ru.basanov");
        final Properties properties = new Properties();
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.hbm2ddl.auto", tableStrategy);
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.max.fetch_depth", fetchDepth);
        properties.put("hibernate.jdbc.fetch_size", fetchSize);
        properties.put("hibernate.jdbc.batch_size", batchSize);
        factoryBean.setJpaProperties(properties);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            final LocalContainerEntityManagerFactoryBean entityManagerFactory
    ) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

}
