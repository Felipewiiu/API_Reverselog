package com.company.reverselog.domain.solicitacao.dto;

import com.company.reverselog.domain.cliente.entity.Cliente;

public record SolicitacaoDto(
        String nf_compra,
        String descricao_defeito,
        Cliente cliente) {
}
