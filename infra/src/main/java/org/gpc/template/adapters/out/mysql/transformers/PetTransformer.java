package org.gpc.template.adapters.out.mysql.transformers;

import org.gpc.template.adapters.out.mysql.model.PetEntity;
import org.gpc.template.kernel.Pet;
import org.gpc.template.kernel.UpdatePet;

public class PetTransformer {

    public static PetEntity petToEntity(Pet pet) {
        PetEntity petEntity = new PetEntity();
        petEntity.setAge(pet.age());
        petEntity.setName(pet.name());
        petEntity.setSpecie(pet.specie());
        petEntity.setBreed(pet.breed());
        return petEntity;
    }

    public static Pet entityToPet(PetEntity petEntity) {
        return new Pet(
                petEntity.getName(),
                petEntity.getAge(),
                petEntity.getSpecie(),
                petEntity.getBreed()
        );
    }

    public static PetEntity updatePetToEntity(UpdatePet updatePet) {
        PetEntity petEntity = new PetEntity();
        petEntity.setId(updatePet.id());
        petEntity.setAge(updatePet.age());
        petEntity.setName(updatePet.name());
        petEntity.setSpecie(updatePet.specie());
        petEntity.setBreed(updatePet.breed());
        return petEntity;
    }
}
