package org.gpc.template.port;

import org.gpc.template.kernel.Pet;

import java.util.Optional;

public interface RepositoryPort {
    Integer savePet(Pet pet);

    Optional<Pet> getPet(Integer id);
}
