package com.company.reverselog.domain.request.dto;

import com.company.reverselog.domain.request.Status;

import java.util.List;

public record RequestRegistrationData(
        String nf_compra,
        List<QuantityProducts> produto,
        String descricao_defeito,
        Status status,
        Long cliente_id,
        Integer quantidade
) {

}
