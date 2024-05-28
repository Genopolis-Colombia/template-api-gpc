package org.gpc.proteins.handler;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.CreateProteinRequestDTO;
import org.gpc.proteins.adapters.in.http.dto.CreateProteinResponseDTO;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.usecase.CreateProteinUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.UUID;

@AllArgsConstructor
public class CreateProteinHandler implements Handler<CreateProteinRequestDTO, ResponseEntity<DTO>>{
    private final CreateProteinUseCaseImpl createProteinUseCase;

    @Override
    public ResponseEntity<DTO> handle(CreateProteinRequestDTO proteinRequestDto){
        UUID id = createProteinUseCase.execute(new Protein(
                proteinRequestDto.fastaName(),
                proteinRequestDto.source(),
                proteinRequestDto.organism(),
                proteinRequestDto.clasification(),
                proteinRequestDto.clasificationEC(),
                proteinRequestDto.authors(),
                proteinRequestDto.fastaSequence()
        ));
        return new ResponseEntity<>(new CreateProteinResponseDTO(id), HttpStatus.CREATED);
    }
}
