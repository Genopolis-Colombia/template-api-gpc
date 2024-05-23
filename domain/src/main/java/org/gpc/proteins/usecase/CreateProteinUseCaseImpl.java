package org.gpc.proteins.usecase;

import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.port.RepositoryPort;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateProteinUseCaseImpl implements UseCase<Protein, UUID>{

    private final RepositoryPort repositoryPort;
    private static final Logger logger = LoggerFactory.getLogger(CreateProteinUseCaseImpl.class);


    public CreateProteinUseCaseImpl(RepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public UUID execute(Protein command) {
        logger.debug("Executing command:" + command);
        return repositoryPort.saveProtein(command);
    }
}
