package org.gpc.template.handlers;

import lombok.AllArgsConstructor;
import org.gpc.template.adapters.in.http.dto.DTO;
import org.gpc.template.adapters.in.http.dto.ErrorResponse;
import org.gpc.template.adapters.in.http.dto.UpdatePetRequestDTO;
import org.gpc.template.kernel.Specie;
import org.gpc.template.kernel.UpdatePet;
import org.gpc.template.usecase.GetPetUseCaseImpl;
import org.gpc.template.usecase.PutPetUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

@AllArgsConstructor
public class UpdatePetHandler {
  private final PutPetUseCaseImpl putPetUseCase;
  private final GetPetUseCaseImpl getPetUseCase;

  public ResponseEntity<Optional<DTO>> handle(UpdatePetRequestDTO updatePetRequestDTO, Integer petID) {

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
    if (maybeValidationError.isPresent()) {
      return new ResponseEntity<Optional<DTO>>(maybeValidationError, HttpStatus.BAD_REQUEST);
    } else {
      return getPetUseCase.execute(petID)
          .map(pet ->
              new UpdatePet(
                  maybeNameToBeUpdated.orElse(pet.name()),
                  maybeAgeToBeUpdated.orElse(pet.age()),
                  maybeSpecieToBeUpdated.map(value -> Specie.valueOf(value.toUpperCase())).orElse(pet.specie()),
                  maybeBreedToBeUpdated.orElse(pet.breed()),
                  petID
              )
          ).flatMap(putPetUseCase::execute)
          .map(updatedPet -> new ResponseEntity<Optional<DTO>>(Optional.empty(), HttpStatus.OK))
          .orElse(new ResponseEntity<Optional<DTO>>(Optional.of(new ErrorResponse("Invalid pet id", "The provided pet was not found")), HttpStatus.NOT_FOUND));
    }
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
