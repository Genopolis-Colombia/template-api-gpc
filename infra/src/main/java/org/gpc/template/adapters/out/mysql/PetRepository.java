package org.gpc.template.adapters.out.mysql;

import org.gpc.template.adapters.out.mysql.model.PetEntity;
import org.springframework.data.repository.CrudRepository;


public interface PetRepository extends CrudRepository<PetEntity, Integer> {}
