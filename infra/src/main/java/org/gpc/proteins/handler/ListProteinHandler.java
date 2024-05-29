package org.gpc.proteins.handler;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.adapters.in.http.dto.ProteinResponseDTO;
import org.gpc.proteins.adapters.in.http.dto.ProteinListResponseDTO;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.usecase.ListProteinUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ListProteinHandler implements Handler <Optional<Integer>, ResponseEntity<DTO>> {

    private final ListProteinUseCaseImpl listProteinsUseCase;

    @Override
    public ResponseEntity<DTO> handle(Optional<Integer> input) {
        List<Protein> proteins = listProteinsUseCase.execute(input);
        List<ProteinResponseDTO> proteinResponseDtoList = proteins.stream()
                .map(protein -> new ProteinResponseDTO(
                        protein.fastaName(),
                        protein.source(),
                        protein.organism(),
                        protein.clasification(),
                        protein.clasificationEC(),
                        protein.authors(),
                        protein.fastaSequence(),
                        UUID.randomUUID() // Generamos un UUID como identificador único para cada proteína
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ProteinListResponseDTO(proteinResponseDtoList), HttpStatus.OK);
    }
}
