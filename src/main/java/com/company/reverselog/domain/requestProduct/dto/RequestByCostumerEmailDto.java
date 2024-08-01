package com.company.reverselog.domain.requestProduct.dto;

import com.company.reverselog.domain.request.Status;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.requestProduct.entity.RequestProduct;

import java.time.LocalDateTime;
import java.util.List;

public record RequestByCostumerEmailDto(
        byte[] nf_RMA,
        Integer numero_nf,
        List<RequestProduct> requestProductList,
        LocalDateTime data,
        Status status

) {
}
