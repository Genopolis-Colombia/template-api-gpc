package org.gpc.proteins.port;


import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryPort {
    UUID saveProtein(Protein protein);

    Optional<Protein> getProtein(Integer id);

    Integer deleteProtein(Integer id);

    Optional<Protein> putProtein(UpdateProtein updateProtein);


}
