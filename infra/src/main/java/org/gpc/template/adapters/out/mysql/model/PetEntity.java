package org.gpc.template.adapters.out.mysql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.gpc.template.kernel.Specie;

@Entity
@Setter
@Getter
public class PetEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String name;

    private Integer age;
    private Specie specie;
    private String breed;
}
