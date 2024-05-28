package org.gpc.proteins.handler;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.DTO;
import org.gpc.proteins.adapters.in.http.dto.ErrorResponse;
import org.gpc.proteins.adapters.in.http.dto.UpdateProteinRequestDTO;
import org.gpc.proteins.handler.commands.UpdateProteinCommand;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.usecase.GetProteinUseCaseImpl;
import org.gpc.proteins.usecase.PutProteinUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

@AllArgsConstructor
public class UpdateProteinHandler implements Handler<UpdateProteinCommand, ResponseEntity<DTO>> {
    private final PutProteinUseCaseImpl putProteinUseCase;
    private final GetProteinUseCaseImpl getProteinUseCase;

    @Override
    public ResponseEntity<DTO> handle(UpdateProteinCommand command) {
        UpdateProteinRequestDTO updateProteinRequestDTO = command.updateProteinRequestDTO();

        UUID proteinID = command.proteinID();
        Optional<String> maybeFastaNameToBeUpdated = updateProteinRequestDTO.fastaName().filter(filterNonEmptyString());
        Optional<String> maybeSourceToBeUpdated = updateProteinRequestDTO.source().filter(filterNonEmptyString());
        Optional<String> maybeOrganismToBeUpdated = updateProteinRequestDTO.organism().filter(filterNonEmptyString());
        Optional<String> maybeClasificationToBeUpdated = updateProteinRequestDTO.clasification().filter(filterNonEmptyString());
        Optional<String> maybeClasificationECToBeUpdated = updateProteinRequestDTO.clasificationEC().filter(filterNonEmptyString());
        Optional<String> maybeAuthorsToBeUpdated = updateProteinRequestDTO.authors().filter(filterNonEmptyString());
        Optional<String> maybeFastaSequenceToBeUpdated = updateProteinRequestDTO.fastaSequence().filter(filterNonEmptyString());


        Optional<DTO> maybeValidationError = validateFieldsToBeUpdated(
                maybeFastaNameToBeUpdated,
                maybeSourceToBeUpdated,
                maybeOrganismToBeUpdated,
                maybeClasificationToBeUpdated,
                maybeClasificationECToBeUpdated,
                maybeAuthorsToBeUpdated,
                maybeFastaSequenceToBeUpdated
        );
        return maybeValidationError.map(dto -> new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST)).
                orElseGet(() -> getProteinUseCase.execute(proteinID)
                        .map(protein ->
                                new UpdateProtein(
                                        maybeFastaNameToBeUpdated.orElse(protein.fastaName()),
                                        maybeSourceToBeUpdated.orElse(protein.source()),
                                        maybeOrganismToBeUpdated.orElse(protein.organism()),
                                        maybeClasificationToBeUpdated.orElse(protein.clasification()),
                                        maybeClasificationECToBeUpdated.orElse(protein.clasificationEC()),
                                        maybeAuthorsToBeUpdated.orElse(protein.authors()),
                                        maybeFastaSequenceToBeUpdated.orElse(protein.fastaSequence()),
                                        proteinID
                                )
                        ).flatMap(putProteinUseCase::execute)
                        .map(updatedProtein -> new ResponseEntity<DTO>(HttpStatus.NO_CONTENT))
                        .orElse(new ResponseEntity<>(new ErrorResponse("Invalid protein id", "The provided proteins was not found"), HttpStatus.NOT_FOUND))
                );
    }

    private static Predicate<String> filterNonEmptyString() {
        return value -> !value.trim().isEmpty();
    }


    private Optional<DTO> validateFieldsToBeUpdated(Optional<String> fastaName,
                                                    Optional<String> source,
                                                    Optional<String> organism,
                                                    Optional<String> clasification,
                                                    Optional<String> clasificationEC,
                                                    Optional<String> authors,
                                                    Optional<String> fastaSequence) {

        if (fastaName.isEmpty() && source.isEmpty() && organism.isEmpty() && clasification.isEmpty() && clasificationEC.isEmpty() && authors.isEmpty() && fastaSequence.isEmpty()) {
            return Optional
                    .of(new ErrorResponse(
                            "Invalid fields",
                            "One or more fields are not valid")
                    );
        }

        return Optional.empty();
    }

}

