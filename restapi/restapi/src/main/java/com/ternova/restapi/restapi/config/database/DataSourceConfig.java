package com.ternova.restapi.restapi.config.database;

import javax.sql.DataSource;

import com.ternova.restapi.restapi.context.DataContext;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Objects;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "ternova1Factory",
        transactionManagerRef = "ternova1TransactionManagerRef",
        basePackages = "com.ternova.restapi.restapi.service.database.repository"
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

    @Bean
    public DataSource getDataSourceTernova3() {
        return super.ternova3();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ternova3Factory(
            @Qualifier("getDataSourceTernova3") DataSource ds, EntityManagerFactoryBuilder builder){

        return builder.dataSource(ds)
                .packages("com.ternova.restapi.restapi.service.database.entity")
                .persistenceUnit("ternova3")
                .build();
    }

    @Bean
    public PlatformTransactionManager ternova3TransactionManagerRef(
            @Qualifier("ternova3Factory") EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }

    @Bean
    public DataSource getDataSourceTernova4() {
        return super.ternova4();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ternova4Factory(
            @Qualifier("getDataSourceTernova4") DataSource ds, EntityManagerFactoryBuilder builder){

        return builder.dataSource(ds)
                .packages("com.ternova.restapi.restapi.service.database.entity")
                .persistenceUnit("ternova4")
                .build();
    }

    @Bean
    public PlatformTransactionManager ternova4TransactionManagerRef(
            @Qualifier("ternova4Factory") EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }

    @Bean
    public DataSource getDataSourceTernova5() {
        return super.ternova5();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean ternova5Factory(
            @Qualifier("getDataSourceTernova5") DataSource ds, EntityManagerFactoryBuilder builder){

        return builder.dataSource(ds)
                .packages("com.ternova.restapi.restapi.service.database.entity")
                .persistenceUnit("ternova5")
                .build();
    }

    @Bean
    public PlatformTransactionManager ternova5TransactionManagerRef(
            @Qualifier("ternova5Factory") EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }

}
