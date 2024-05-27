package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.cliente.Cliente;
import com.company.reverselog.domain.produto.Produto;
import com.company.reverselog.domain.requestProduct.RequestProduct;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public record SolicitacaoDto(
        String nf_compra,
        String descricao_defeito,
        Cliente cliente) {
}
