package org.gpc.template.adapters.in.http;

import lombok.AllArgsConstructor;
import org.gpc.template.adapters.in.http.dto.CreatePetRequestDTO;
import org.gpc.template.adapters.in.http.dto.DTO;
import org.gpc.template.adapters.in.http.dto.UpdatePetRequestDTO;
import org.gpc.template.handlers.CreatePetHandler;
import org.gpc.template.handlers.DeletePetHandler;
import org.gpc.template.handlers.GetPetHandler;
import org.gpc.template.handlers.UpdatePetHandler;
import org.gpc.template.handlers.commands.UpdatePetCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PetControllerAdapter {
  private final CreatePetHandler createPetHandler;
  private final GetPetHandler getPetHandler;
  private final DeletePetHandler deletePetHandler;
  private final UpdatePetHandler updatePetHandler;
  private static final Logger logger = LoggerFactory.getLogger(PetControllerAdapter.class);

  @PostMapping("/pets")
  public ResponseEntity<DTO> createPet(@RequestBody CreatePetRequestDTO petRequestDto) {
    return createPetHandler.handle(petRequestDto);
  }

  @GetMapping("/pets/{pet_id}")
  public ResponseEntity<DTO> getPet(@PathVariable Integer pet_id) {
    return getPetHandler.handle(pet_id);
  }

  @DeleteMapping("/pets/{pet_id}")
  public ResponseEntity<DTO> deletePet(@PathVariable Integer pet_id) {
    return deletePetHandler.handle(pet_id);
  }

  @PutMapping("/pets/{pet_id}")
  public ResponseEntity<DTO> putPet(@PathVariable Integer pet_id, @RequestBody UpdatePetRequestDTO petRequestDto) {
    return updatePetHandler.handle(new UpdatePetCommand(petRequestDto, pet_id));
  }

}
