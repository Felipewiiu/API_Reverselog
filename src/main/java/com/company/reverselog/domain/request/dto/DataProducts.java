package com.company.reverselog.domain.request.dto;

import jakarta.validation.constraints.NotBlank;

public record DataProducts(
        Long id,
        Integer amount,
        @NotBlank(message = "O campo descrição de defeito não pode ser null ou vazio")
        String description
) {
}
