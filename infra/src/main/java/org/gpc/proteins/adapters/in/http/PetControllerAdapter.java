package org.gpc.proteins.adapters.in.http;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.CreatePetRequestDTO;
import org.gpc.proteins.adapters.in.http.dto.CreatePetResponseDTO;
import org.gpc.proteins.kernel.Pet;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.usecase.CreateProteinUseCaseImpl;
import org.gpc.proteins.usecase.DeleteProteinUseCaseImpl;
import org.gpc.proteins.usecase.GetProteinUseCaseImpl;
import org.gpc.proteins.usecase.PutProteinUseCaseImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class PetControllerAdapter {
    private final CreateProteinUseCaseImpl createPetUseCase;
    private final GetProteinUseCaseImpl getPetUseCase;
    private final DeleteProteinUseCaseImpl deletePetUseCase;
    private final PutProteinUseCaseImpl putPetUseCase;

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
            new UpdateProtein(
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
