package com.company.reverselog.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProdutos(
        @NotBlank(message = "O campo nome não pode estar em branco")
        String nome,
        @NotBlank(message = "O modelo nome não pode estar em branco")
        String modelo,
        @NotNull(message = "O campo numero_de_serie não pode estar em branco")
        Integer numero_de_serie,
        @NotNull(message = "O campo ncm não pode estar em branco e deve conter 8 caracteres numéricos")
        Integer ncm,
        @NotNull
        String image
) {
}
