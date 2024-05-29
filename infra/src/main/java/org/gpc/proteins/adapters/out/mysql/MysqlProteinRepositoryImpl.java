package org.gpc.proteins.adapters.out.mysql;

import org.gpc.proteins.adapters.out.mysql.model.ProteinEntity;
import org.gpc.proteins.adapters.out.mysql.transformers.ProteinTransformer;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;
import org.gpc.proteins.port.RepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


public class MysqlProteinRepositoryImpl implements RepositoryPort {

    private final ProteinRepository proteinRepository;
    private static final Logger logger = LoggerFactory.getLogger(MysqlProteinRepositoryImpl.class);

    public MysqlProteinRepositoryImpl(ProteinRepository proteinRepository) {
        this.proteinRepository = proteinRepository;
    }

    @Override
    public UUID saveProtein(Protein protein) {
        logger.debug("Starting saving protein");
        return proteinRepository.save(ProteinTransformer.proteinToEntity(protein)).getId();
    }

    @Override
    public Optional<Protein> getProtein(UUID id) {
        return proteinRepository.findById(id).map(ProteinTransformer::entityToProtein);
    }

    @Override
    public UUID deleteProtein(UUID id) {
        proteinRepository.deleteById(id);
        return id;
    }

    @Override
    public Optional<Protein> putProtein(UpdateProtein updateProtein) {

        ProteinEntity proteinEntity = ProteinTransformer.updateProteinToEntity(updateProtein);
        proteinRepository.save(proteinEntity);
        return Optional.of(ProteinTransformer.entityToProtein(proteinEntity));
    }

    @Override
    public List<Protein> getAllProteins() {
        List<ProteinEntity> proteinEntity = (List<ProteinEntity>) proteinRepository.findAll();

        return proteinEntity.stream()
                .map(ProteinTransformer::entityToProtein)
                .collect(Collectors.toList());
    }


}
