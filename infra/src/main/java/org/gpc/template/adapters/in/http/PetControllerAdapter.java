package org.gpc.template.adapters.in.http;

import lombok.AllArgsConstructor;
import org.gpc.template.adapters.in.http.dto.CreatePetRequestDTO;
import org.gpc.template.adapters.in.http.dto.CreatePetResponseDTO;
import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.Specie;
import org.gpc.template.usecase.CreatePetUseCaseImpl;
import org.gpc.template.usecase.GetPetUseCaseImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class PetControllerAdapter {
    private final CreatePetUseCaseImpl createPetUseCase;
    private final GetPetUseCaseImpl getPetUseCase;

    @PostMapping("/pets")
    public CreatePetResponseDTO createPet(@RequestBody CreatePetRequestDTO petRequestDto) {
        Integer id = createPetUseCase.execute(new Pet(
                petRequestDto.name(),
                petRequestDto.age(),
                Specie.valueOf(petRequestDto.specie().toUpperCase()),
                petRequestDto.breed()
        ));
        return new CreatePetResponseDTO(id);
    }

    @GetMapping("/pets/{pet_id}")
    public Optional<Pet> getPet(@PathVariable Integer pet_id) {
        return getPetUseCase.execute(pet_id);
    }
}
