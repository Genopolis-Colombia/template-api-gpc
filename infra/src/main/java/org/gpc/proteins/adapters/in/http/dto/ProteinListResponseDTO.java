package org.gpc.proteins.adapters.in.http.dto;

import java.util.List;

public class ProteinListResponseDTO implements DTO{

    private List<ProteinResponseDTO> proteins;

    public ProteinListResponseDTO(List<ProteinResponseDTO> proteins) {
        this.proteins = proteins;
    }

    public List<ProteinResponseDTO> getProteins() {
        return proteins;
    }
}
