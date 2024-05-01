package org.gpc.template.port;

import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.UpdatePet;

import java.util.Optional;

public interface RepositoryPort {
    Integer savePet(Pet pet);

    Optional<Pet> getPet(Integer id);

    Integer deletePet(Integer id);

    Optional<Pet> putPet(UpdatePet updatePet);

    void deleteAll();
}
