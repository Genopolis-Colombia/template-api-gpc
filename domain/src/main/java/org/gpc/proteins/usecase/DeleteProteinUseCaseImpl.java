package org.gpc.proteins.usecase;


import lombok.AllArgsConstructor;
import org.gpc.proteins.port.RepositoryPort;

@AllArgsConstructor
public class DeleteProteinUseCaseImpl implements UseCase<Integer,Integer>{

    private final RepositoryPort repositoryPort;

    @Override
    public Integer execute (Integer id){
        repositoryPort.deleteProtein(id);
        return id;
    }
}
