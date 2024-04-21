package org.gpc.template.usecase;

import lombok.AllArgsConstructor;
import org.gpc.template.kernel.Pet;
import org.gpc.template.port.RepositoryPort;

import java.util.Optional;

@AllArgsConstructor
public class GetPetUseCaseImpl implements UseCase<Integer, Optional<Pet>> {

    private final RepositoryPort repositoryPort;
    @Override
    public Optional<Pet> execute(Integer id) {
        return repositoryPort.getPet(id);
    }
}
