package org.gpc.template.adapters.in.http;

import org.gpc.template.MySQLTestContainer;
import org.gpc.template.adapters.in.http.dto.CreatePetRequestDTO;
import org.gpc.template.adapters.in.http.dto.CreatePetResponseDTO;
import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.Specie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetControllerAdapterTest extends MySQLTestContainer {

    @Value("${local.server.port}")
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String host = "http://localhost:";

    @Test
    void shouldSaveAPetSuccessful() {
        String path = host + port + "/pets";
        CreatePetRequestDTO entity = new CreatePetRequestDTO("Moris", 3, "dog", "chihuahua ");
        HttpEntity<CreatePetRequestDTO> request = new HttpEntity<>(entity);
        ResponseEntity<CreatePetResponseDTO> response = restTemplate.exchange(path, HttpMethod.POST, request, CreatePetResponseDTO.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        Optional<Pet> maybePetSaved = mySQLPetRepository.getPet(Objects.requireNonNull(response.getBody()).id());
        System.out.println("Response: " + response);
        assertTrue(maybePetSaved.isPresent());
        assertEquals(entity.name(), maybePetSaved.get().name());
        assertEquals(entity.age(), maybePetSaved.get().age());
        assertEquals(entity.specie().toUpperCase(), maybePetSaved.get().specie().toString());
        assertEquals(entity.breed(), maybePetSaved.get().breed());
    }

    @Test
    void shouldGetAPetSuccessful() {
        Pet pet = new Pet("Mauricio", 2, Specie.CAT, "Criollito");
        Integer id = mySQLPetRepository.savePet(pet);
        String path = host + port + "/pets" + "/" + id;
        ResponseEntity<Pet> response = restTemplate.exchange(path, HttpMethod.GET, null, Pet.class);
        System.out.println("Response: " + response);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(pet.name(), Objects.requireNonNull(response.getBody()).name());
        assertEquals(pet.age(), Objects.requireNonNull(response.getBody()).age());
        assertEquals(pet.specie(), Objects.requireNonNull(response.getBody()).specie());
        assertEquals(pet.breed(), Objects.requireNonNull(response.getBody()).breed());
    }
}