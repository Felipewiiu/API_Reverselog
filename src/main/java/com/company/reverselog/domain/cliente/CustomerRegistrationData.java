package com.company.reverselog.domain.cliente;

import com.company.reverselog.domain.endereco.AddressData;
import com.company.reverselog.domain.endereco.Endereco;
import com.company.reverselog.domain.solicitacao.Solicitacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record CustomerRegistrationData(
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

        @Valid
        AddressData endereco

) {
}
