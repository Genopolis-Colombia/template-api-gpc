package org.gpc.proteins.adapters.in.http.dto;

import java.util.List;

public class ProteinsListResponseDTO implements DTO{

    private List<ProteinResponseDTO> proteins;

    public ProteinsListResponseDTO(List<ProteinResponseDTO> proteins) {
        this.proteins = proteins;
    }
}
