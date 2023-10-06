package com.ternova.restapi.restapi.config.database;

import com.ternova.restapi.restapi.utils.EncryptClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatasSourceConfigParam {

    @Autowired
    private Environment env;

    @Autowired
    private EncryptClass encryptClass;

    protected DriverManagerDataSource ternova1(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(
                encryptClass.decrypt(env.getProperty("datasource.ternova1.driver-class-name"))
        );
        driverManagerDataSource.setUrl(
                encryptClass.decrypt(env.getProperty("datasource.ternova1.url"))
        );
        driverManagerDataSource.setUsername(
                encryptClass.decrypt(env.getProperty("datasource.ternova1.username"))
        );
        driverManagerDataSource.setPassword(
                encryptClass.decrypt(env.getProperty("datasource.ternova1.password"))
        );
        return driverManagerDataSource;
    }

    protected DriverManagerDataSource ternova2(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(
                encryptClass.decrypt(env.getProperty("datasource.ternova2.driver-class-name"))
        );
        driverManagerDataSource.setUrl(
                encryptClass.decrypt(env.getProperty("datasource.ternova2.url"))
        );
        driverManagerDataSource.setUsername(
                encryptClass.decrypt(env.getProperty("datasource.ternova2.username"))
        );
        driverManagerDataSource.setPassword(
                encryptClass.decrypt(env.getProperty("datasource.ternova2.password"))
        );
        return driverManagerDataSource;
    }

    protected DriverManagerDataSource ternova3(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(
                encryptClass.decrypt(env.getProperty("datasource.ternova3.driver-class-name"))
        );
        driverManagerDataSource.setUrl(
                encryptClass.decrypt(env.getProperty("datasource.ternova3.url"))
        );
        driverManagerDataSource.setUsername(
                encryptClass.decrypt(env.getProperty("datasource.ternova3.username"))
        );
        driverManagerDataSource.setPassword(
                encryptClass.decrypt(env.getProperty("datasource.ternova3.password"))
        );
        return driverManagerDataSource;
    }

    protected DriverManagerDataSource ternova4(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(
                encryptClass.decrypt(env.getProperty("datasource.ternova4.driver-class-name"))
        );
        driverManagerDataSource.setUrl(
                encryptClass.decrypt(env.getProperty("datasource.ternova4.url"))
        );
        driverManagerDataSource.setUsername(
                encryptClass.decrypt(env.getProperty("datasource.ternova4.username"))
        );
        driverManagerDataSource.setPassword(
                encryptClass.decrypt(env.getProperty("datasource.ternova4.password"))
        );
        return driverManagerDataSource;
    }

    protected DriverManagerDataSource ternova5(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

        driverManagerDataSource.setDriverClassName(
                encryptClass.decrypt(env.getProperty("datasource.ternova5.driver-class-name"))
        );
        driverManagerDataSource.setUrl(
                encryptClass.decrypt(env.getProperty("datasource.ternova5.url"))
        );
        driverManagerDataSource.setUsername(
                encryptClass.decrypt(env.getProperty("datasource.ternova5.username"))
        );
        driverManagerDataSource.setPassword(
                encryptClass.decrypt(env.getProperty("datasource.ternova5.password"))
        );
        return driverManagerDataSource;
    }
}
