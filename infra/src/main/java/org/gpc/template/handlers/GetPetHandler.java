package org.gpc.template.handlers;

import lombok.AllArgsConstructor;
import org.gpc.template.adapters.in.http.dto.DTO;
import org.gpc.template.adapters.in.http.dto.ErrorResponse;
import org.gpc.template.adapters.in.http.dto.PetResponseDTO;
import org.gpc.template.usecase.GetPetUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
public class GetPetHandler implements Handler<Integer, ResponseEntity<DTO>> {
  private final GetPetUseCaseImpl getPetUseCase;

  @Override
  public ResponseEntity<DTO> handle(Integer petID) {
    return getPetUseCase.execute(petID)
        .map(pet -> new ResponseEntity<DTO>(
                new PetResponseDTO(petID, pet.name(), pet.age(), pet.specie().name(), pet.breed()),
                HttpStatus.OK
            )
        ).orElse(
            new ResponseEntity<>(
                new ErrorResponse("Pet not found", "the pet with id: " + petID + " was not found"),
                HttpStatus.NOT_FOUND)
        );
  }
}
