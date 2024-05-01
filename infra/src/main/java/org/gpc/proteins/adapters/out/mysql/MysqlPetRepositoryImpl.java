package org.gpc.proteins.adapters.out.mysql;

import org.gpc.proteins.adapters.out.mysql.model.PetEntity;
import org.gpc.proteins.adapters.out.mysql.transformers.PetTransformer;
import org.gpc.proteins.kernel.Pet;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.port.RepositoryPort;

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

    @Override
    public Integer deletePet(Integer id) {
        petRepository.deleteById(id);
        return id;
    }

    @Override
    public Optional<Pet> putPet(UpdateProtein updatepet) {

        PetEntity petEntity = PetTransformer.updatePetToEntity(updatepet);
        petRepository.save(petEntity);
        return Optional.of(PetTransformer.entityToPet(petEntity));
    }


}
