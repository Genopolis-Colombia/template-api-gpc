package org.gpc.proteins.adapters.in.http.dto;

import java.util.Optional;

public record  UpdateProteinRequestDTO (

    Optional<String> fastaName,
    Optional<String> source,
    Optional<String> organism,
    Optional<String> clasification,
    Optional<String>clasificationEC,
    Optional<String> authors,
    Optional<String> fastaSequence
){
}
