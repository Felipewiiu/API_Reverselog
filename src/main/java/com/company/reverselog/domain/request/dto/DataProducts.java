package com.company.reverselog.domain.request.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataProducts(
        Long id,

        Integer amount,

        @NotBlank(message = "O campo descrição de defeito não pode ser null ou vazio")
        @NotNull(message = "O campo descrição de defeito não pode ser null ou vazio")
        String description
) {
}
