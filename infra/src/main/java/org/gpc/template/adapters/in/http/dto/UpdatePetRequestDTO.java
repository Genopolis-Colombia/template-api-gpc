package org.gpc.template.adapters.in.http.dto;

import java.util.Optional;

public record UpdatePetRequestDTO(
        Optional<String> name,
        Optional<Integer> age,
        Optional<String> specie,
        Optional<String> breed
) { }
