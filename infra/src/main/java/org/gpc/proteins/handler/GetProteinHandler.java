package org.gpc.proteins.handler;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.adapters.in.http.dto.ErrorResponse;
import org.gpc.proteins.adapters.in.http.dto.ProteinResponseDTO;
import org.gpc.proteins.usecase.GetProteinUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@AllArgsConstructor
public class GetProteinHandler implements Handler <UUID, ResponseEntity<DTO>> {

    private final GetProteinUseCaseImpl getProteinUseCase;

    @Override
    public ResponseEntity<DTO> handle (UUID proteinID){
        return getProteinUseCase.execute(proteinID)
                .map(protein -> new ResponseEntity<DTO>(
                        new ProteinResponseDTO(protein.fastaName(), protein.source(), protein.organism(), protein.clasification(), protein.clasificationEC(),
                                protein.authors(),protein.fastaSequence(), proteinID), HttpStatus.OK
                        )
                ).orElse(
                        new ResponseEntity<>(
                                new ErrorResponse ("Protein not found", "The protein with id:" + proteinID + "Wasn´t found"),
                                HttpStatus.NOT_FOUND)
                );

    }
}
