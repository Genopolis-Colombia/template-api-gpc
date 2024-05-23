package org.gpc.proteins.handler;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.adapters.in.http.dto.UpdateProteinRequestDTO;
import org.gpc.proteins.handler.commands.UpdateProteinCommand;
import org.gpc.proteins.usecase.GetProteinUseCaseImpl;
import org.gpc.proteins.usecase.UpdateProteinUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@AllArgsConstructor
public class UpdateProteinHandler implements Handler<UpdateProteinCommand, ResponseEntity<DTO>> {
    private final UpdateProteinUseCaseImpl updateProteinUseCase;
    private final GetProteinUseCaseImpl getProteinUseCase;

    @Override
    public ResponseEntity<DTO> handle(UpdateProteinCommand command) {
        UpdateProteinRequestDTO updateProteinRequestDTO = command.updateProteinRequestDTO();
        UUID proteinID = command.proteinID();

        Optional<String> maybeNameToBeUpdated = updatePetRequestDTO.name().filter(filterNonEmptyString());
        Optional<Integer> maybeAgeToBeUpdated = updatePetRequestDTO.age().filter(filterNonNegativeNumbers());
        Optional<String> maybeSpecieToBeUpdated = updatePetRequestDTO.specie().filter(filterNonEmptyString());
        Optional<String> maybeBreedToBeUpdated = updatePetRequestDTO.breed().filter(filterNonEmptyString());

        Optional<DTO> maybeValidationError = validateFieldsToBeUpdated(
                maybeNameToBeUpdated,
                maybeAgeToBeUpdated,
                maybeSpecieToBeUpdated,
                maybeBreedToBeUpdated
        );
        return maybeValidationError.map(dto -> new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST)).
                orElseGet(() -> getPetUseCase.execute(petID)
                        .map(pet ->
                                new UpdatePet(
                                        maybeNameToBeUpdated.orElse(pet.name()),
                                        maybeAgeToBeUpdated.orElse(pet.age()),
                                        maybeSpecieToBeUpdated.map(value -> Specie.valueOf(value.toUpperCase())).orElse(pet.specie()),
                                        maybeBreedToBeUpdated.orElse(pet.breed()),
                                        petID
                                )
                        ).flatMap(putPetUseCase::execute)
                        .map(updatedPet -> new ResponseEntity<DTO>(HttpStatus.NO_CONTENT))
                        .orElse(new ResponseEntity<>(new ErrorResponse("Invalid pet id", "The provided pet was not found"), HttpStatus.NOT_FOUND))
                );
    }

    private static Predicate<String> filterNonEmptyString() {
        return value -> !value.trim().isEmpty();
    }

    private static Predicate<Integer> filterNonNegativeNumbers() {
        return number -> number > 0;
    }

    private Optional<DTO> validateFieldsToBeUpdated(Optional<String> name,
                                                    Optional<Integer> age,
                                                    Optional<String> specie,
                                                    Optional<String> breed) {

        boolean isValidSpecie = specie.map(value -> Arrays.stream(Specie.values())
                .map(Specie::toString)
                .toList().contains(value.toUpperCase())).orElse(true);
        if (name.isEmpty() && age.isEmpty() && specie.isEmpty() && breed.isEmpty() || !isValidSpecie) {
            return Optional
                    .of(new ErrorResponse(
                            "Invalid fields",
                            "One or more fields are not valid")
                    );
        }
        return Optional.empty();
    }

}

