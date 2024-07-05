package com.company.reverselog.domain.custumer.dto;

import com.company.reverselog.domain.address.entity.Endereco;
import com.company.reverselog.domain.custumer.entity.Cliente;

public record CustomerUpdateRegister(
        String telefone,
        String cnpj,
        Endereco endereco,
        String name
) {
    public CustomerUpdateRegister(Cliente custumer){
        this(
                custumer.getTelefone(),
                custumer.getCnpj(),
                custumer.getEndereco(),
                custumer.getName()
        );
    }
}
