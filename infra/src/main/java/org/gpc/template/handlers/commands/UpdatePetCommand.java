package org.gpc.template.handlers.commands;

import org.gpc.template.adapters.in.http.dto.UpdatePetRequestDTO;

public record UpdatePetCommand(UpdatePetRequestDTO updatePetRequestDTO, Integer petID) {
}
