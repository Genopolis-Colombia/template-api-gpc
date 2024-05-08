package org.gpc.template.adapters.out.mysql;

import org.gpc.template.adapters.out.mysql.model.PetEntity;
import org.gpc.template.adapters.out.mysql.transformers.PetTransformer;
import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.UpdatePet;
import org.gpc.template.port.RepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class MysqlPetRepositoryImpl implements RepositoryPort {

    private final PetRepository petRepository;
    private static final Logger logger = LoggerFactory.getLogger(MysqlPetRepositoryImpl.class);

    public MysqlPetRepositoryImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Integer savePet(Pet pet) {
        logger.debug("Starting saving pet");
        return petRepository.save(PetTransformer.petToEntity(pet)).getId();
    }

    @Override
    public Optional<Pet> getPet(Integer id) {
        return petRepository.findById(id).map(PetTransformer::entityToPet);
    }

    @Override
    public void deletePet(Integer id) {
        petRepository.deleteById(id);
    }

    @Override
    public Optional<Pet> putPet(UpdatePet updatepet) {

        PetEntity petEntity = PetTransformer.updatePetToEntity(updatepet);
        petRepository.save(petEntity);
        return Optional.of(PetTransformer.entityToPet(petEntity));
    }

    // Only for testing purposes
    @Override
    public void deleteAll(){
        petRepository.deleteAll();
    }
}
