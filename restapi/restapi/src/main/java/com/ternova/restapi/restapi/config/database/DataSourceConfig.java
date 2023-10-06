package com.ternova.restapi.restapi.config.database;

import javax.sql.DataSource;

import com.ternova.restapi.restapi.context.DataContext;
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


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "ternovaFactory",
        transactionManagerRef = "ternovaTransactionManagerRef",
        basePackages = "com.ternova.restapi.restapi.service.database.repository"
)
public class DataSourceConfig extends DatasSourceConfigParam{

    @Primary
    @Bean
    public DataSource getDataSourceTernova() {
        String activeDatabase = DataContext.getDataStringContext().getConnectToDb();
        switch (activeDatabase) {
            case "ternova2":
                return super.ternova2();
            case "ternova3":
                return super.ternova3();
            case "ternova4":
                return super.ternova4();
            case "ternova5":
                return super.ternova5();
            default:
                return super.ternova1();
        }
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean ternovaFactory(
            @Qualifier("getDataSourceTernova") DataSource ds, EntityManagerFactoryBuilder builder){

        return builder.dataSource(ds)
                .packages("com.ternova.restapi.restapi.service.database.entity")
                .build();
    }

    @Bean
    public PlatformTransactionManager ternovaTransactionManagerRef(
            @Qualifier("ternovaFactory") EntityManagerFactory factory){
        return new JpaTransactionManager(factory);
    }


}
