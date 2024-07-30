package com.company.reverselog.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroProdutos(
        @NotBlank(message = "O campo nome não pode estar em branco")
        String nome,

        @NotBlank(message = "O modelo nome não pode estar em branco")
        String modelo,

        @NotNull(message = "O campo ncm não pode estar em branco ")
        @Pattern(regexp = "\\d{8}", message = "O campo ncm deve conter 8 caracteres numéricos")
        Integer ncm,

        @NotNull(message = "O campo image não pode estar nulo ou em branco")
        @NotBlank(message = "O campo image não pode estar nulo ou em branco")
        String image
) {
}
