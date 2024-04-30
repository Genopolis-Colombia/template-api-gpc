package org.gpc.template.usecase;


import lombok.AllArgsConstructor;
import org.gpc.template.port.RepositoryPort;

@AllArgsConstructor
public class DeletePetUseCaseImpl implements UseCase<Integer,Integer>{

    private final RepositoryPort repositoryPort;

    @Override
    public Integer execute (Integer id){
        repositoryPort.deletePet(id);
        return id;
    }
}
