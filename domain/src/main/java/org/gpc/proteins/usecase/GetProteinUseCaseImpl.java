package org.gpc.proteins.usecase;

import lombok.AllArgsConstructor;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.port.RepositoryPort;

import java.util.Optional;

@AllArgsConstructor
public class GetProteinUseCaseImpl implements UseCase<Integer, Optional<Protein>> {

    private final RepositoryPort repositoryPort;
    @Override
    public Optional<Protein> execute(Integer id) {
        return repositoryPort.getProtein(id);
    }
}
