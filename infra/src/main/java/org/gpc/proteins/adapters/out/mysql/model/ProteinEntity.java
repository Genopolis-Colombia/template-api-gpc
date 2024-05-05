package org.gpc.proteins.adapters.out.mysql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Setter
@Getter
public class ProteinEntity {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String fastaName;
    private String source;
    private String organism;
    private String clasification;
    private String clasificationEC;
    private String authors;
    private String fastaSequence;
    private UUID id;

}
