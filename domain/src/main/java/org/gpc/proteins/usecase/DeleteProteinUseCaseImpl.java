package org.gpc.proteins.usecase;


import lombok.AllArgsConstructor;
import org.gpc.proteins.port.RepositoryPort;

import java.util.UUID;

@AllArgsConstructor
public class DeleteProteinUseCaseImpl implements UseCase<UUID,UUID>{

    private final RepositoryPort repositoryPort;

    @Override
    public UUID execute (UUID id){
        repositoryPort.deleteProtein(id);
        return id;
    }
}
