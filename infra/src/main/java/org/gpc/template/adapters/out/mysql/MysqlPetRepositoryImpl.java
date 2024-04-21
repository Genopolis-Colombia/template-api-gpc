package org.gpc.template.adapters.out.mysql;

import org.gpc.template.adapters.out.mysql.transformers.PetTransformer;
import org.gpc.template.kernel.Pet;
import org.gpc.template.port.RepositoryPort;

import java.util.Optional;


public class MysqlPetRepositoryImpl implements RepositoryPort {

    private final PetRepository petRepository;

    public MysqlPetRepositoryImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Integer savePet(Pet pet) {
        return petRepository.save(PetTransformer.petToEntity(pet)).getId();
    }

    @Override
    public Optional<Pet> getPet(Integer id) {
        return petRepository.findById(id).map(PetTransformer::entityToPet);
    }
}
