package com.company.reverselog.domain.requestProduct.dto;

import com.company.reverselog.domain.product.entity.Produto;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.requestProduct.entity.RequestProduct;

public record ProductListDTO(
        Long id_requestProducts,
        Long solicitacao,
        String produto,
        Integer quantidade,
        String descricao
) {
    public ProductListDTO(RequestProduct requestProduct) {
        this(
                requestProduct.getId_requestProducts(),
                requestProduct.getSolicitacao().getId().longValue(),
                requestProduct.getProduto().getNome(),
                requestProduct.getQuantidade(),
                requestProduct.getDescricao()

        );
    }
}
