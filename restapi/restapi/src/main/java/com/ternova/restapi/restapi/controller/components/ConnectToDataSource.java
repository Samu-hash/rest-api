package com.ternova.restapi.restapi.controller.components;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class ConnectToDataSource {

    private final Logger logger = LoggerFactory.getLogger(ConnectToDataSource.class);

    @PersistenceContext(unitName = "ternova1")
    private EntityManager ternova1EntityManager;

    @PersistenceContext(unitName = "ternova2")
    private EntityManager ternova2EntityManager;

    @PersistenceContext(unitName = "ternova3")
    private EntityManager ternova3EntityManager;

    @PersistenceContext(unitName = "ternova4")
    private EntityManager ternova4EntityManager;

    @PersistenceContext(unitName = "ternova5")
    private EntityManager ternova5EntityManager;

    public EntityManager connectToDataSource(String databaseName){
        logger.info("base de datos a conectar {}", databaseName);
        switch (databaseName) {
            case "ternova1":
                return this.ternova1EntityManager;
            case "ternova2":
                return this.ternova2EntityManager;
            case "ternova3":
                return this.ternova3EntityManager;
            case "ternova4":
                return this.ternova4EntityManager;
            case "ternova5":
                return this.ternova5EntityManager;
            default:
                return this.ternova1EntityManager;
        }
    }
}
