package org.gpc.proteins.handler;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.adapters.in.http.dto.ProteinResponseDTO;
import org.gpc.proteins.adapters.in.http.dto.ProteinsListResponseDTO;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.usecase.DeleteProteinUseCaseImpl;
import org.gpc.proteins.usecase.ListProteinUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ListProteinHandler implements Handler <Void, ResponseEntity<DTO>> {

    private final ListProteinUseCaseImpl listProteinsUseCase;

    @Override
    public ResponseEntity<DTO> handle(Void input) {
        List<Protein> proteins = listProteinsUseCase.execute(input);
        List<ProteinResponseDTO> proteinResponseDTOs = proteins.stream()
                .map(protein -> new ProteinResponseDTO(
                        protein.id(), // Asumiendo que Protein tiene un campo id
                        protein.fastaName(),
                        protein.source(),
                        protein.organism(),
                        protein.clasification(),
                        protein.clasificationEC(),
                        protein.authors(),
                        protein.fastaSequence()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ProteinsListResponseDTO(proteinResponseDTOs), HttpStatus.OK);
    }
}
