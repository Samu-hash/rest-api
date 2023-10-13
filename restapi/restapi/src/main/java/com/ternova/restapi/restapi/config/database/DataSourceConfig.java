package com.ternova.restapi.restapi.config.database;

import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("ternova1")
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "ternova1Factory",
        transactionManagerRef = "ternova1TransactionManagerRef",
        basePackages = "com.ternova.restapi.restapi.service.database.repository.db1"
)
public class DataSourceConfig extends DatasSourceConfigParam{

    @Primary
    @Bean
    public DataSource getDataSourceTernova1() {
        return super.ternova1();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean ternova1Factory(
            @Qualifier("getDataSourceTernova1") DataSource ds, EntityManagerFactoryBuilder builder){

        return builder.dataSource(ds)
                .packages("com.ternova.restapi.restapi.service.database.entity")
                .persistenceUnit("ternova1")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager ternova1TransactionManagerRef(
            @Qualifier("ternova1Factory") EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }
}
