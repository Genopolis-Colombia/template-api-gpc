package org.gpc.proteins.adapters.in.http.dto;

public record CreateProteinRequestDTO(String fastaName, String source, String organism, String clasification, String clasificationEC, String authors, String fastaSequence) {
}
