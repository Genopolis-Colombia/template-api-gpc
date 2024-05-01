package org.gpc.proteins.usecase;

import org.gpc.proteins.kernel.Pet;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.port.RepositoryPort;

public class CreateProteinUseCaseImpl implements UseCase<Protein, Integer>{

    private final RepositoryPort repositoryPort;

    public CreateProteinUseCaseImpl(RepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Integer execute(Protein command) {
        return repositoryPort.saveProtein(command);
    }
}
