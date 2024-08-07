package com.company.reverselog.domain.requestProduct.dto;

import com.company.reverselog.domain.request.Status;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.requestProduct.entity.RequestProduct;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record RequestByCostumerEmailDto(
        byte[] nf_RMA,
        Integer numero_nf,
        List<ProductListDTO> requestProductList,
        LocalDateTime data,
        Status status

) {
    public RequestByCostumerEmailDto(Solicitacao solicitacao) {
        this (
                solicitacao.getNf_RMA(),
                solicitacao.getNumero_nf(),
                solicitacao.getRequestProducts().stream()
                        .map(ProductListDTO::new) // Converte cada RequestProduct em ProductListDTO
                        .collect(Collectors.toList()),
                solicitacao.getData(),
                solicitacao.getStatus()
        );
    }
}
