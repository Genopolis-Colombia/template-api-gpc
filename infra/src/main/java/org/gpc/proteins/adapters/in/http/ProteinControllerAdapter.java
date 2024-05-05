package org.gpc.proteins.adapters.in.http;

import lombok.AllArgsConstructor;
import org.gpc.proteins.adapters.in.http.dto.CreateProteinRequestDTO;
import org.gpc.proteins.adapters.in.http.dto.CreateProteinResponseDTO;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.usecase.CreateProteinUseCaseImpl;
import org.gpc.proteins.usecase.DeleteProteinUseCaseImpl;
import org.gpc.proteins.usecase.GetProteinUseCaseImpl;
import org.gpc.proteins.usecase.PutProteinUseCaseImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class ProteinControllerAdapter {
    private final CreateProteinUseCaseImpl createProteinUseCase;
    private final GetProteinUseCaseImpl getProteinUseCase;
    private final DeleteProteinUseCaseImpl deleteProteinUseCase;
    private final PutProteinUseCaseImpl putProteinUseCase;

    @PostMapping("/proteins")
    public CreateProteinResponseDTO createProtein(@RequestBody CreateProteinRequestDTO proteinRequestDto) {
        Integer id = createProteinUseCase.execute(new Protein(
                proteinRequestDto.fastaName(),
                proteinRequestDto.source(),
                proteinRequestDto.organism(),
                proteinRequestDto.clasification(),
                proteinRequestDto.clasificationEC(),
                proteinRequestDto.authors(),
                proteinRequestDto.fastaSequence()
        ));

        return new CreateProteinResponseDTO(id);
    }

    @GetMapping("/proteins/{protein_id}")
    public Optional<Protein> getProtein(@PathVariable Integer protein_id) {
        return getProteinUseCase.execute(protein_id);
    }

    @DeleteMapping("/pets/{pet_id}")
    public Integer deleteProtein (@PathVariable Integer protein_id){
        return deleteProteinUseCase.execute(protein_id);
    }
    @PutMapping("/pets/{pet_id}")
    public CreateProteinResponseDTO putProtein (@PathVariable Integer protein_id, @RequestBody CreateProteinRequestDTO proteinRequestDto ){

        Optional<Protein> maybePet = getProteinUseCase.execute(protein_id);
        maybePet.map(protein ->
            new UpdateProtein(

                    getFastaNameToUpdate(protein,proteinRequestDto.fastaName()),
                    getSourceToUpdate(protein,proteinRequestDto.source()),
                    getOrganismToUpdate(protein, proteinRequestDto.organism()),
                    getClasificationToUpdate(protein,proteinRequestDto.clasification()),
                    getClasificationECToUpdate(protein,proteinRequestDto.clasificationEC()),
                    getAuthorsToUpdate(protein, proteinRequestDto.authors()),
                    getFastaSequenceToUpdate(protein,proteinRequestDto.fastaSequence()),
                    protein_id
            )
        ).flatMap(putProteinUseCase::execute);
        return new CreateProteinResponseDTO(protein_id);
    }

    private String getFastaNameToUpdate (Protein protein, String fastaName){
        if (fastaName != null && fastaName != "") {
           return fastaName;
        } else {
            return protein.fastaName();
        }
    }
    private String getSourceToUpdate (Protein protein, String source){
        if (source!= null && source != "") {
            return source;
        } else {
            return protein.source();
        }
    }

    private String getOrganismToUpdate (Protein protein, String organism){
        if (organism != null && organism != "") {
            return organism;
        } else {
            return protein.organism();
        }
    }
    private String getClasificationToUpdate (Protein protein, String clasification){
        if (clasification!= null && clasification != "") {
            return clasification;
        } else {
            return protein.clasification();
        }
    }

    private String getClasificationECToUpdate (Protein protein, String clasificationEC){
        if (clasificationEC!= null && clasificationEC != "") {
            return clasificationEC;
        } else {
            return protein.clasificationEC();
        }
    }

    private String getAuthorsToUpdate (Protein protein, String authors){
        if (authors != null && authors != "") {
            return authors;
        } else {
            return protein.authors();
        }
    }
    private String getFastaSequenceToUpdate (Protein protein, String fastaSequence){
        if (fastaSequence!= null && fastaSequence != "") {
            return fastaSequence;
        } else {
            return protein.fastaSequence();
        }
    }






}
