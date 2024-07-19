package com.company.reverselog.domain.product.dto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoProduto(
        @NotNull(message = "É obrigatório passar um ID para o produto")
        Long id,
        String nome,
        String modelo,
        Integer numero_de_serie,
        Integer ncm,
        Boolean ativo,
        String image
) {
}
