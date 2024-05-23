package org.gpc.proteins.adapters.in.http.dto;

public record ErrorResponse(String failure, String detail) implements DTO {
}
