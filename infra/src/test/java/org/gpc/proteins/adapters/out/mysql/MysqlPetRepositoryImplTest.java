package org.gpc.proteins.adapters.out.mysql;

import org.gpc.proteins.kernel.Pet;
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
    static MySQLContainer mySQLContainer = new MySQLContainer<>(DockerImageName.parse("mysql:8.0-debian"));

    @DynamicPropertySource
    static void dataBaseProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", () -> mySQLContainer.getJdbcUrl());
        registry.add("spring.datasource.driverClassName", () -> mySQLContainer.getDriverClassName());
        registry.add("spring.datasource.username", () -> mySQLContainer.getUsername());
        registry.add("spring.datasource.password", () -> mySQLContainer.getPassword());
        registry.add("spring.flyway.enabled", () -> "true");
    }

    @Autowired
    private MysqlPetRepositoryImpl mySQLPetRepository;

    @Test
    void savePet() {
        Integer id = 1;
        String name = "Makarras";
        Integer age = 1;
        Specie specie = Specie.CAT;
        String breed = "Criollo";
        mySQLPetRepository.savePet(new Pet(name, age, specie, breed));
        Optional<Pet> maybePet = mySQLPetRepository.getPet(id);
        assert(maybePet.isPresent());
        assertEquals(name, maybePet.get().name());
        assertEquals(age, maybePet.get().age());
        assertEquals(specie, maybePet.get().specie());
        assertEquals(breed, maybePet.get().breed());
    }

    @Test
    void getGet() { //TODO: improve this test
        Integer id = 2;
        Optional<Pet> maybePet = mySQLPetRepository.getPet(id);
        maybePet.ifPresent(pet -> System.out.println("The pet is " + pet));
        assert(maybePet.isEmpty());
    }
}