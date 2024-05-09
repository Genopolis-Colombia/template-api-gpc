package org.gpc.template.adapters.in.http.dto;

public record PetResponseDTO(Integer id, String name, Integer age, String specie, String breed) implements DTO {

}
