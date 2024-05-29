package com.company.reverselog.domain.endereco.dto;

import jakarta.validation.constraints.NotNull;

public record AddressData(
        @NotNull(message = "O campo logradouro não pode estar vazio")
        String logradouro,
        @NotNull(message = "O campo bairro não pode estar vazio")
        String bairro,
        @NotNull(message = "O campo cpf não pode estar vazio")
        String cep,
        String numero,
        String complemento,
        @NotNull(message = "O campo cidade não pode estar vazio")
        String cidade,
        @NotNull(message = "O campo uf não pode estar vazio")
        String uf
) {
}
