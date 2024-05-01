package org.gpc.template.usecase;

import org.gpc.template.kernel.Pet;
import org.gpc.template.port.RepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CreatePetUseCaseImpl implements UseCase<Pet, Integer>{

    private final RepositoryPort repositoryPort;
    private static final Logger logger = LoggerFactory.getLogger(CreatePetUseCaseImpl.class);

    public CreatePetUseCaseImpl(RepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Integer execute(Pet command) {
        logger.debug("Executing command: " + command);
        return repositoryPort.savePet(command);
    }
}
