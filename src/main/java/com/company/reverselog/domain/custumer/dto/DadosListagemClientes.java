package com.company.reverselog.domain.custumer.dto;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.address.entity.Endereco;
import com.company.reverselog.domain.request.entity.Solicitacao;

import java.util.List;

public record DadosListagemClientes(
    Long id,
    String email,
    String telefone,
    String cpf,
    String cnpj,
    List<Solicitacao> solicitacoes,
    Endereco endereco,
    Boolean ativo
) {
    public DadosListagemClientes(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getCpf(),
                cliente.getCnpj(),
                cliente.getSolicitacoes(),
                cliente.getEndereco(),
                cliente.getAtivo()
        );
    }
}
