package com.company.reverselog.domain.request.dto;

import com.company.reverselog.domain.custumer.entity.Cliente;

public record SolicitacaoDto(
        String nf_compra,
        String descricao_defeito,
        Cliente cliente) {
}
