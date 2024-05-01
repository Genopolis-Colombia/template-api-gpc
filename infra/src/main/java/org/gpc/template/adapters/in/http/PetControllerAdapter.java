package org.gpc.template.adapters.in.http;

import lombok.AllArgsConstructor;
import org.gpc.template.adapters.in.http.dto.CreatePetRequestDTO;
import org.gpc.template.adapters.in.http.dto.CreatePetResponseDTO;
import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.Specie;
import org.gpc.template.kernel.UpdatePet;
import org.gpc.template.usecase.CreatePetUseCaseImpl;
import org.gpc.template.usecase.DeletePetUseCaseImpl;
import org.gpc.template.usecase.GetPetUseCaseImpl;
import org.gpc.template.usecase.PutPetUseCaseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class PetControllerAdapter {
    private final CreatePetUseCaseImpl createPetUseCase;
    private final GetPetUseCaseImpl getPetUseCase;
    private final DeletePetUseCaseImpl deletePetUseCase;
    private final PutPetUseCaseImpl putPetUseCase;
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
    public CreatePetResponseDTO putPet (@PathVariable Integer pet_id,@RequestBody CreatePetRequestDTO petRequestDto ){
        Optional<Pet> maybePet = getPetUseCase.execute(pet_id);
        maybePet.map(pet ->
            new UpdatePet(
                    getNameToUpdate(pet,petRequestDto.name()),
                    getAgeToUpdate(pet,petRequestDto.age()),
                    getSpecieToUpdate(pet,petRequestDto.specie()),
                    getBreedToUpdate(pet,petRequestDto.breed()),
                    pet_id
            )
        ).flatMap(putPetUseCase::execute);
        return new CreatePetResponseDTO(pet_id);
    }

    private String getNameToUpdate (Pet pet, String name){
        if (name != null && name != "") {
           return name;
        } else {
            return pet.name();
        }
    }
    private Integer getAgeToUpdate (Pet pet, Integer age){
        if (age != null && age > 0) {
            return age;
        } else {
            return pet.age();
        }
    }
    private Specie getSpecieToUpdate (Pet pet, String specie){
        if (specie != null && specie != "") {
            return Specie.valueOf(specie.toUpperCase());
        } else {
            return pet.specie();
        }
    }
    private String getBreedToUpdate (Pet pet, String breed){
        if (breed != null && breed != "") {
            return breed;
        } else {
            return pet.breed();
        }
    }
}
