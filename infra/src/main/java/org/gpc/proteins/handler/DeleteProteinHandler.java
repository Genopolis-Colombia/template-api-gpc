package org.gpc.proteins.handler;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.usecase.DeleteProteinUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@AllArgsConstructor
public class DeleteProteinHandler implements Handler <UUID, ResponseEntity<DTO>> {

    private final DeleteProteinUseCaseImpl deleteProteinUseCase;

    @Override
    public ResponseEntity<DTO> handle (UUID proteinID){
        deleteProteinUseCase.execute(proteinID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
