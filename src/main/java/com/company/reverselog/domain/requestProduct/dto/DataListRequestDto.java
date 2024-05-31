package com.company.reverselog.domain.requestProduct.dto;

import com.company.reverselog.domain.cliente.entity.Cliente;
import com.company.reverselog.domain.solicitacao.Status;

import java.time.LocalDateTime;

public record DataListRequestDto(
        Long Id_solicitacao,
        Cliente cliente,
        Status status,
        String nf_compra,
        LocalDateTime data

) {
}
