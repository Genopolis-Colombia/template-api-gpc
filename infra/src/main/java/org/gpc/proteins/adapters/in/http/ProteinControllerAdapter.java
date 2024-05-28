package org.gpc.proteins.adapters.in.http;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.CreateProteinRequestDTO;
import org.gpc.proteins.adapters.in.http.dto.CreateProteinResponseDTO;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.adapters.in.http.dto.UpdateProteinRequestDTO;
import org.gpc.proteins.handler.*;
import org.gpc.proteins.handler.commands.UpdateProteinCommand;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.usecase.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class ProteinControllerAdapter {
    private final CreateProteinHandler createProteinHandler;
    private final GetProteinHandler getProteinHandler;
    private final DeleteProteinHandler deleteProteinHandler;
    private final UpdateProteinHandler updateProteinHandler;
    private final ListProteinHandler listProteinHandler;

    @PostMapping("/proteins")
    public ResponseEntity<DTO> createProtein(@RequestBody CreateProteinRequestDTO proteinRequestDto) {
        return createProteinHandler.handle(proteinRequestDto);
    }
    @GetMapping("/proteins/{protein_id}")
    public ResponseEntity<DTO> getProtein(@PathVariable UUID protein_id) {
        return getProteinHandler.handle(protein_id);
    }
    @GetMapping("/v1/proteins")
    public List<Protein> listProteins() {
        return (List<Protein>) listProteinHandler.handle(null);
    }
    @DeleteMapping("/proteins/{protein_id}")
    public ResponseEntity<DTO> deleteProtein(@PathVariable UUID protein_id) {
        return deleteProteinHandler.handle(protein_id);
    }

    @PutMapping("/proteins/{protein_id}")
    public ResponseEntity<DTO> putProtein(@PathVariable UUID pet_id, @RequestBody UpdateProteinRequestDTO petRequestDto) {
        return updateProteinHandler.handle(new UpdateProteinCommand(petRequestDto, pet_id));
    }

}
