package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;

public record SolicitacaoDto(
        String nf_compra,
        Set<Produto> produto_list,
        String descricao_defeito,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        Status status,
        Cliente cliente) {
}
