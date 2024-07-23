package com.company.reverselog.domain.requestProduct.dto;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.request.Status;

import java.time.LocalDateTime;

public record DataListRequestDto(
        Long Id_solicitacao,
        Cliente cliente,
        Status status,
        byte[] nf_compra,
        LocalDateTime data

) {
}
