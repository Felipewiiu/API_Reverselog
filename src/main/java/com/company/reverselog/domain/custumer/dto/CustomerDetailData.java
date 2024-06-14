package com.company.reverselog.domain.custumer.dto;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.address.entity.Endereco;

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
