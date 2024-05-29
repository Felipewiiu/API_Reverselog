package com.company.reverselog.domain.cliente.dto;

import com.company.reverselog.domain.cliente.entity.Cliente;
import com.company.reverselog.domain.endereco.entity.Endereco;
import com.company.reverselog.domain.solicitacao.entity.Solicitacao;

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
