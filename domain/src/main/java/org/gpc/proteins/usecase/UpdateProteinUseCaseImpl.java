package org.gpc.proteins.usecase;

import lombok.AllArgsConstructor;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.port.RepositoryPort;

import java.util.Optional;

@AllArgsConstructor
public class UpdateProteinUseCaseImpl implements UseCase <UpdateProtein, Optional<Protein>> {

    private final RepositoryPort repositoryPort;

    @Override
    public Optional <Protein> execute (UpdateProtein command){
        return repositoryPort.putProtein(command);
    }
}
