package com.company.reverselog.domain.cliente;

import com.company.reverselog.domain.endereco.Endereco;
import com.company.reverselog.domain.solicitacao.Solicitacao;

import java.util.List;

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
