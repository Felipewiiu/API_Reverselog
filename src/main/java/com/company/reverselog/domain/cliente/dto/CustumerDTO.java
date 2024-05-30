package com.company.reverselog.domain.cliente.dto;

import com.company.reverselog.domain.endereco.dto.AddressData;
import com.company.reverselog.domain.endereco.entity.Endereco;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CustumerDTO(
        Long id,
        @NotBlank(message = "O campo email não pode estar vazio")
        @Email
        String email,

        @NotBlank(message = "O campo telefone não pode estar vazio")
        String telefone,

        @NotNull(message = "O campo cpf não pode estar vazio")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotBlank(message = "O campo cnpj não pode estar vazio")
        @Pattern(regexp = "\\d{14}", message = "O campo CNPJ precisa possuir 14 dígitos")
        String cnpj,

        String logradouro,

        @NotNull(message = "O campo bairro não pode estar vazio")
        String bairro,

        @NotNull(message = "O campo CEP não pode estar vazio")
        String cep,

        String numero,

        String complemento,
        @NotNull(message = "O campo cidade não pode estar vazio")
        String cidade,
        @NotNull(message = "O campo UF não pode estar vazio")
        String uf

) {

}
