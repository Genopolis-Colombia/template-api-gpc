package org.gpc.template.usecase;

import org.gpc.template.kernel.Pet;
import org.gpc.template.port.RepositoryPort;

public class CreatePetUseCaseImpl implements UseCase<Pet, Integer>{
    private final RepositoryPort repositoryPort;

    public CreatePetUseCaseImpl(RepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Integer execute(Pet command) {
        return repositoryPort.savePet(command);
    }
}
