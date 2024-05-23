package org.gpc.proteins.port;


import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryPort {
    UUID saveProtein(Protein protein);

    Optional<Protein> getProtein(UUID id);

    UUID deleteProtein(UUID id);

    Optional<Protein> putProtein(UpdateProtein updateProtein);
    List<Protein> getAllProteins();


}
