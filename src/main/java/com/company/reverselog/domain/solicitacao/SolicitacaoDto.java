package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record SolicitacaoDto(
        String nf_compra,
        List<Produto> produto_list,
        String descricao_defeito,
        Status status,
        Cliente cliente) {
}
