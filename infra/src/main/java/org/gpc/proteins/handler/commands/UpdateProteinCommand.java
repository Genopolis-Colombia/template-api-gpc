package org.gpc.proteins.handler.commands;

import org.gpc.proteins.adapters.in.http.dto.UpdateProteinRequestDTO;

import java.util.UUID;

public record UpdateProteinCommand (UpdateProteinRequestDTO updateProteinRequestDTO, UUID proteinID) {

}
