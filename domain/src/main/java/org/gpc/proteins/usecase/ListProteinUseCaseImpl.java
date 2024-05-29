package org.gpc.proteins.usecase;

import lombok.AllArgsConstructor;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.port.RepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ListProteinUseCaseImpl implements UseCase<Optional<Integer>, List<Protein>> {

    private final RepositoryPort repositoryPort;

    @Override
    public List<Protein> execute(Optional<Integer> limit) {
        List<Protein> proteins = repositoryPort.getAllProteins();
        return limit.map(value -> proteins.stream().limit(value).collect(Collectors.toList()))
                .orElse(proteins);
    }

}
