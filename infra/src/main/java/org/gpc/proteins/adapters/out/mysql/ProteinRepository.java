package org.gpc.proteins.adapters.out.mysql;

import org.gpc.proteins.adapters.out.mysql.model.ProteinEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface ProteinRepository extends CrudRepository<ProteinEntity, UUID> {

}
