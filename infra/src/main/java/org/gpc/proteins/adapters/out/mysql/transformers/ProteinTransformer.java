package org.gpc.proteins.adapters.out.mysql.transformers;

import org.gpc.proteins.adapters.out.mysql.model.ProteinEntity;
import org.gpc.proteins.kernel.Protein;
import org.gpc.proteins.kernel.UpdateProtein;

public class ProteinTransformer {

    public static ProteinEntity proteinToEntity(Protein protein) {
        ProteinEntity proteinEntity = new ProteinEntity();
        proteinEntity.setFastaName(protein.fastaName());
        proteinEntity.setSource(protein.source());
        proteinEntity.setOrganism(protein.organism());
        proteinEntity.setClasification(protein.clasification());
        proteinEntity.setClasificationEC(protein.clasificationEC());
        proteinEntity.setAuthors(protein.authors());
        proteinEntity.setFastaSequence(protein.fastaSequence());

        return proteinEntity;
    }

    public static Protein entityToProtein(ProteinEntity proteinEntity) {

        return new Protein(
                proteinEntity.getFastaName(),
                proteinEntity.getSource(),
                proteinEntity.getOrganism(),
                proteinEntity.getClasification(),
                proteinEntity.getClasificationEC(),
                proteinEntity.getAuthors(),
                proteinEntity.getFastaSequence()
        );
    }

    public static ProteinEntity updateProteinToEntity(UpdateProtein updateProtein) {
        ProteinEntity proteinEntity = new ProteinEntity();
        proteinEntity.setId(updateProtein.protein_id());
        proteinEntity.setFastaName(updateProtein.fastaName());
        proteinEntity.setSource(updateProtein.source());
        proteinEntity.setOrganism(updateProtein.organism());
        proteinEntity.setClasification(updateProtein.clasification());
        proteinEntity.setClasificationEC(updateProtein.clasificationEC());
        proteinEntity.setAuthors(updateProtein.authors());
        proteinEntity.setFastaSequence(updateProtein.fastaSequence());
        return proteinEntity;
    }
}
