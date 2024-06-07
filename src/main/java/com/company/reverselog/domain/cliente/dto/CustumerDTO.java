package com.company.reverselog.domain.cliente.dto;

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

        @NotBlank(message = "O campo cpf não pode estar vazio")
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotBlank(message = "O campo cnpj não pode estar vazio")
        @Pattern(regexp = "\\d{14}", message = "O campo CNPJ precisa possuir 14 dígitos")
        String cnpj,

        String logradouro,

        @NotNull(message = "O campo bairro não pode estar vazio")
        String bairro,

        @NotBlank(message = "O campo CEP não pode estar vazio")
        @Pattern(regexp = "\\d{5}\\-?\\d{3}", message = "Formato do cep está incorreto")
        String cep,

        String numero,

        String complemento,
        @NotBlank(message = "O campo cidade não pode estar vazio")
        String cidade,
        @NotBlank(message = "O campo UF não pode estar vazio")
        String uf,
        @NotBlank(message = "O campo password não pode estar vazio")
        String password

) {

}
