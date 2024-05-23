package org.gpc.proteins.usecase;

import lombok.AllArgsConstructor;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.port.RepositoryPort;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ListProteinUseCaseImpl implements UseCase<Void, List<Protein>> {

    private final RepositoryPort repositoryPort;

    @Override
    public List<Protein> execute(Void input)  {
        return repositoryPort.getAllProteins();
    }
}
