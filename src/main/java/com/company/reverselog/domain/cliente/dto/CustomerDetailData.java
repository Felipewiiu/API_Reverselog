package com.company.reverselog.domain.cliente.dto;

import com.company.reverselog.domain.cliente.entity.Cliente;
import com.company.reverselog.domain.endereco.entity.Endereco;

public record CustomerDetailData(Long id, String email, String telefone, String cpf, String cnpj, Endereco endereco, Boolean ativo) {
    public CustomerDetailData(Cliente custumers){
        this(
                custumers.getId(),
                custumers.getEmail(),
                custumers.getTelefone(),
                custumers.getCpf(),
                custumers.getCnpj(),
                custumers.getEndereco(),
                custumers.getAtivo()
        );
    }
}
