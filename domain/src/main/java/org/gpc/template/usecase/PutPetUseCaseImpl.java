package org.gpc.template.usecase;

import lombok.AllArgsConstructor;
import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.UpdatePet;
import org.gpc.template.port.RepositoryPort;

import java.util.Optional;

@AllArgsConstructor
public class PutPetUseCaseImpl implements UseCase <UpdatePet, Optional<Pet>> {

    private final RepositoryPort repositoryPort;

    @Override
    public Optional <Pet> execute (UpdatePet command){
        return repositoryPort.putPet(command);
    }
}
