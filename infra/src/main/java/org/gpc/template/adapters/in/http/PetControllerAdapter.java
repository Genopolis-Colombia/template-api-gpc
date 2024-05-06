package org.gpc.template.adapters.in.http;

import lombok.AllArgsConstructor;
import org.gpc.template.adapters.in.http.dto.CreatePetRequestDTO;
import org.gpc.template.adapters.in.http.dto.CreatePetResponseDTO;
import org.gpc.template.adapters.in.http.dto.DTO;
import org.gpc.template.adapters.in.http.dto.UpdatePetRequestDTO;
import org.gpc.template.handlers.UpdatePetHandler;
import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.Specie;
import org.gpc.template.usecase.CreatePetUseCaseImpl;
import org.gpc.template.usecase.DeletePetUseCaseImpl;
import org.gpc.template.usecase.GetPetUseCaseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class PetControllerAdapter {
    private final CreatePetUseCaseImpl createPetUseCase;
    private final GetPetUseCaseImpl getPetUseCase;
    private final DeletePetUseCaseImpl deletePetUseCase;
    private final UpdatePetHandler updatePetHandler;
    private static final Logger logger = LoggerFactory.getLogger(PetControllerAdapter.class);

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

    @DeleteMapping("/pets/{pet_id}")
    public Integer deletePet (@PathVariable Integer pet_id){
        return deletePetUseCase.execute(pet_id);
    }
    @PutMapping("/pets/{pet_id}")
    public ResponseEntity<Optional<DTO>> putPet (@PathVariable Integer pet_id, @RequestBody UpdatePetRequestDTO petRequestDto ){
        return updatePetHandler.handle(petRequestDto, pet_id);
    }

}
