package org.gpc.proteins.adapters.out.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

@Testcontainers
@SpringBootTest
class MysqlPetRepositoryImplTest {

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0.30"));

    @DynamicPropertySource
    static void dataBaseProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
        registry.add("spring.datasource.driverClassName", () -> mySQLContainer.getDriverClassName());
        registry.add("spring.datasource.username", () -> mySQLContainer.getUsername());
        registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());
        registry.add("spring.flyway.enabled", () -> "true");
    }

    @Autowired
    private MysqlProteinRepositoryImpl mySQLPetRepository;

    @Test
    void savePet() {

    }

    @Test
    void getGet() { //TODO: improve this test

    }
}