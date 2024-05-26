package com.company.reverselog.domain.solicitacao;

import com.company.reverselog.domain.produto.DadosCadastroProdutos;
import com.company.reverselog.domain.produto.Produto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DetailRequestData(Long id, String nf_compra, String descricao_Defeito,
                                LocalDateTime data, Status status, Long id_cliente) {
    public DetailRequestData(Solicitacao request){
        this(
                request.getId(),
                request.getNf_compra(),
                request.getDescricao_defeito(),
                request.getData(),
                request.getStatus(),
                request.getCliente().getId()
        );
    }
}
