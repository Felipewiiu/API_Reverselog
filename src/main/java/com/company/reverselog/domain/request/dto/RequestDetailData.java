package com.company.reverselog.domain.request.dto;

import com.company.reverselog.domain.custumer.entity.Cliente;
import com.company.reverselog.domain.request.entity.Solicitacao;
import com.company.reverselog.domain.request.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RequestDetailData(

        String nf_compra,

        @NotNull
        String descricao_defeito,

        LocalDateTime data,

        Status status,

        Cliente cliente

) {
    public RequestDetailData(Solicitacao request) {
        this(
                request.getNf_compra(),
                request.getDescricao_defeito(),
                request.getData(),
                request.getStatus(),
                request.getCliente()
        );
    }
}
