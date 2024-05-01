package org.gpc.proteins.adapters.out.mysql;

import org.gpc.proteins.adapters.out.mysql.model.PetEntity;
import org.springframework.data.repository.CrudRepository;


public interface PetRepository extends CrudRepository<PetEntity, Integer> {

}
