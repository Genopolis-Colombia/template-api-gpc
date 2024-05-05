package org.gpc.proteins.adapters.out.mysql;

import org.gpc.proteins.adapters.out.mysql.model.ProteinEntity;
import org.gpc.proteins.adapters.out.mysql.transformers.PetTransformer;
import org.gpc.proteins.adapters.out.mysql.transformers.ProteinTransformer;
import org.gpc.proteins.kernel.Pet;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.port.RepositoryPort;

import java.util.Optional;
import java.util.UUID;


public class MysqlProteinRepositoryImpl implements RepositoryPort {

    private final ProteinRepository proteinRepository;

    public MysqlProteinRepositoryImpl(ProteinRepository proteinRepository) {
        this.proteinRepository = proteinRepository;
    }

    @Override
    public UUID saveProtein(Protein protein) {
        return proteinRepository.save(ProteinTransformer.proteinToEntity(protein)).getId();
    }

    @Override
    public Optional<Protein> getProtein(Integer id) {
        return proteinRepository.findById(id).map(ProteinTransformer::entityToProtein);
    }

    @Override
    public Integer deleteProtein(Integer id) {
        proteinRepository.deleteById(id);
        return id;
    }

    @Override
    public Optional<Protein> putProtein(UpdateProtein updateProtein) {

        ProteinEntity proteinEntity = ProteinTransformer.updateProteinToEntity(updateProtein);
        proteinRepository.save(proteinEntity);
        return Optional.of(ProteinTransformer.entityToProtein(proteinEntity));
    }


}
