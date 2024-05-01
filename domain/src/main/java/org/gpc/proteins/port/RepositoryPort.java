package org.gpc.proteins.port;


import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;
import java.util.Optional;

public interface RepositoryPort {
    Integer saveProtein(Protein protein);

    Optional<Protein> getProtein(Integer id);

    Integer deleteProtein(Integer id);

    Optional<Protein> putProtein(UpdateProtein updateProtein);


}
