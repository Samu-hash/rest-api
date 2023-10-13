package com.ternova.restapi.restapi.config.database;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Profile("ternova2")
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "ternova2Factory",
        transactionManagerRef = "ternova2TransactionManagerRef",
        basePackages = "com.ternova.restapi.restapi.service.database.repository.db2"
)
public class DataSourceConfig2 extends DatasSourceConfigParam{

    @Bean
    public DataSource getDataSourceTernova2() {
        return super.ternova2();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ternova2Factory(
            @Qualifier("getDataSourceTernova2") DataSource ds, EntityManagerFactoryBuilder builder){

        return builder.dataSource(ds)
                .packages("com.ternova.restapi.restapi.service.database.entity")
                .persistenceUnit("ternova2")
                .build();
    }

    @Bean
    public PlatformTransactionManager ternova2TransactionManagerRef(
            @Qualifier("ternova2Factory") EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }
}
