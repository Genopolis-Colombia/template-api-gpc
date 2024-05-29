package org.gpc.proteins.adapters.in.http.dto;

import java.util.UUID;

public record ProteinResponseDTO (String fastaName, String source, String organism, String clasification, String clasificationEC, String authors, String fastaSequence, UUID proteinID) implements DTO{

}
